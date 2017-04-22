package bowerBird;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class BowerBirdBehavior {

	private static MovementUtils mover;
	private static SensorUtils senses;
	private static boolean seeFemale = false;
	private static boolean hasObject = false;
	private static boolean wingUp = false;

	public static void main(String[] args) {
		mover = new MovementUtils();
		senses = new SensorUtils();

		while (true) {
			if (seeFemale) {
				mover.setWingSpeed(250);
				mover.setMoveSpeed(111);
				if (wingUp) {
					mover.moveWing(-45);
					mover.turnRight();
				} else {
					mover.moveWing(45);
					mover.turnLeft();
				}
				wingUp = !wingUp;
			}

			senses.getColorReading();
			if (senses.getColor() != -1) {
				hasObject = true;
			}
			if (hasObject) {
				hasObject();
			}
			senses.takeLightReading();
			if (senses.getLeft() < 0.02 && senses.getRight() < 0.02) {
				goTowardsHome((float) 0.11);
			}
			wander();
		}

	}

	public static void hasObject() {
		if (senses.getColor() == 2) {
			goTowardsHome((float) 0.3);
			mover.up();
			mover.turnAround();
			mover.down();
		} else {
			mover.up();
			mover.turn(111);
			mover.down();
		}
		hasObject = false;
	}

	public static void wander() {
		int shouldTurn = (int) (Math.random() * 16000);
		if (shouldTurn == 11) {
			mover.turnRandomDegrees();
		} else {
			mover.moveForward();
		}
	}
	
	public static void goTowardsHome(float threshold) {
		senses.takeLightReading();
		while (senses.getRight() < 0.05 && senses.getLeft() < 0.05) {
			mover.turnLeft();
			senses.takeLightReading();
		}
		while (senses.getRight() < threshold && senses.getLeft() < threshold) {
			senses.takeLightReading();
			if (senses.getRight() > senses.getLeft()) {
				mover.setLeftSpeed(165);
				mover.setRightSpeed(145);
				mover.moveForward();
			} else {
				mover.setLeftSpeed(145);
				mover.setRightSpeed(165);
				mover.moveForward();
			}
		}
		mover.setMoveSpeed(145);

	}

}
