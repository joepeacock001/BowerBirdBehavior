package bowerBird;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

/**
 *This class controls the behavior of the bowerbird robot. It's goal is to wander in an area reasonably close to its bower
 *picking up ideal objects (blue and green marbles) and bringing them back while ignoring bad objects (red and yellow marbles)
 *
 * When it is made aware of the presence of a female, it returns to it's bower and begins a mating dance.
 *
 * We initially planned to use the Lejos subsumption architecture, but abandoned that because initial testing showed some hard to
 * track bugs and other groups reported having significant issues. So, in the interest of saving time we moved back to manual behaviour
 * control through if checks.
 *
 * @author Joe Peacock, Nata Price, Lydia Sancetta
 *
 *April 23, 2017
 *
 *There are some minor bugs in this program, mostly introduced by hardware limitations.
 *	-Sometimes blue/green marbles are not chosen or yellow red marbles are because of inaccurate color readings
 *	-Sometimes the bird gets it's "claw" (marble collecting mechanism) stuck on it's bower. We can't detect this because all sensor ports are in use
 *	-If the bird collects 'bad' objects while returning a previously detected 'good' object to it's nest, it returns the 'bad' object as well.
 *
 */
public class BowerBirdBehavior {

	private static MovementUtils mover;
	private static SensorUtils senses;
	private static boolean seeFemale = false;
	private static boolean hasObject = false;

	private static float HOME_THRESHOLD = (float) 0.77;
	private static float SCARED_THRESHOLD = (float) 0.12;
	private static float NOT_SCARED_THRESHOLD = (float) 0.4;
	private static float TURN_LIGHT_THRESHOLD = (float) 0.28;
	private static int MOVE_SPEED = 145;
	private static int TURN_SPEED_INCREASE = 40;

/**
 * Controls the flow of what behavior happens when in responses to certain stimuli
 *
 * @param String[] args Not used
 */
	public static void main(String[] args) {
		mover = new MovementUtils();
		senses = new SensorUtils();

		while (true) {
			//The female is "detected" through manually pushing a button on the tail due to unreliability in other methods of signalling
			seeFemale = senses.isTouched();

			//We dance upon seing the female. The dance should contain the 'tossing' of objects across the female's field of vision.
			//This proved too difficult with our hardware and time constraints so it consists of wing motion and a slow rotation
			if (seeFemale) {
				//Print statements intentionally kept in so we can more accurately narrate our demo
				System.out.println("Aware of Female");
				goTowardsHome((float) HOME_THRESHOLD);
				boolean wingUp = false;
				while (true) {
					mover.setWingSpeed(250);
					mover.setMoveSpeed(MOVE_SPEED - TURN_SPEED_INCREASE);
					if (wingUp) {
						mover.moveWing(-45);
						mover.turnRight();
					} else {
						mover.moveWing(45);
						mover.turnLeft();
					}
					wingUp = !wingUp;
				}
			}

			//Here we detect if we have found a marble, and if so, trigger the associated behavior
			senses.getColorReading();
			if (senses.getColor() != -1) {
				hasObject = true;
				System.out.println("Sees object");
			}
			if (hasObject) {
				hasObject();
			}

			//We use light to determine how far from the bower we are. If the light level is too low, we get "scared" and return
			//partway to the bower
			senses.takeLightReading();
			if (senses.getLeft() < SCARED_THRESHOLD && senses.getRight() < SCARED_THRESHOLD) {
				System.out.println("Too far");
				goTowardsHome((float) NOT_SCARED_THRESHOLD);
				System.out.println("Close enough");
			}

			//In the absence of other stimuli, we wander randomly
			wander();
		}

	}

/**
 * If our object is blue or green, we return to the bower and drop it off. Otherwise, we raise our 'claw' and turn away,
 * thus effectively ignoring the bad object
 */
	public static void hasObject() {
		if (senses.getColor() == 2 || senses.getColor() == 1) {
			System.out.println("Good Object");
			goTowardsHome(HOME_THRESHOLD);
			System.out.println("Dropped at home");
			mover.up();
			mover.turnAround();
			mover.down();
		} else {
			System.out.println("Bad Object");
			mover.up();
			mover.turn(111);
			mover.down();
		}
		hasObject = false;
	}

/**
 * Move forward, with a small chance of turning in a random direction
 */
	public static void wander() {
		int shouldTurn = (int) (Math.random() * 20000);
		if (shouldTurn == 11) {
			mover.turnRandomDegrees();
		} else {
			mover.moveForward();
		}
	}

/**
 * Turn until we are confident that we are facing in the general direction of the bower (which will have the brightest light source in the room)
 * Then follow that light source, adjusting our course based on readings from two ambient light sensors, until the light level is greater than
 * the provided threshold.
 *
 * @param float threshold The light level at which we should stop moving towards home.
 */
	public static void goTowardsHome(float threshold) {
		senses.takeLightReading();
		//turn until we are facing the light
		while (senses.getRight() < TURN_LIGHT_THRESHOLD && senses.getLeft() < TURN_LIGHT_THRESHOLD) {
			mover.turnLeft();
			senses.takeLightReading();
		}
		//track towards the light
		while (senses.getRight() < threshold && senses.getLeft() < threshold) {
			senses.takeLightReading();
			senses.getColorReading();
			//If we pick up an object while we are moving towards home, we deal with it immediately.
			if (senses.getColor() != -1 && hasObject == false) {
				hasObject = true;
				break;
			}
			if (senses.getRight() > senses.getLeft()) {
				mover.setLeftSpeed(MOVE_SPEED + TURN_SPEED_INCREASE);
				mover.setRightSpeed(MOVE_SPEED);
				mover.moveForward();
			} else {
				mover.setLeftSpeed(MOVE_SPEED);
				mover.setRightSpeed(MOVE_SPEED + TURN_SPEED_INCREASE);
				mover.moveForward();
			}
		}
		mover.setMoveSpeed(MOVE_SPEED);

	}

}
