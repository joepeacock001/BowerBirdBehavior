package bowerBird;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

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

	public static void main(String[] args) {
		mover = new MovementUtils();
		senses = new SensorUtils();

		while (true) {

			seeFemale = senses.isTouched();

			if (seeFemale) {
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

			senses.getColorReading();
			if (senses.getColor() != -1) {
				hasObject = true;
				System.out.println("Sees object");
			}
			if (hasObject) {
				hasObject();
			}
			senses.takeLightReading();
			if (senses.getLeft() < SCARED_THRESHOLD && senses.getRight() < SCARED_THRESHOLD) {
				System.out.println("Too far");
				goTowardsHome((float) NOT_SCARED_THRESHOLD);
				System.out.println("Close enough");
			}
			wander();
		}

	}

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

	public static void wander() {
		int shouldTurn = (int) (Math.random() * 20000);
		if (shouldTurn == 11) {
			mover.turnRandomDegrees();
		} else {
			mover.moveForward();
		}
	}

	public static void goTowardsHome(float threshold) {
		senses.takeLightReading();
		while (senses.getRight() < TURN_LIGHT_THRESHOLD && senses.getLeft() < TURN_LIGHT_THRESHOLD) {
			mover.turnLeft();
			senses.takeLightReading();
		}
		while (senses.getRight() < threshold && senses.getLeft() < threshold) {
			senses.takeLightReading();
			senses.getColorReading();
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
