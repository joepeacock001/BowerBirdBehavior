package bowerBird;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

/**
 * Decouples all of the movement functions into one helper class
 */
public class MovementUtils {
	// make motors
	private RegulatedMotor Left = new EV3LargeRegulatedMotor(MotorPort.A);
	private RegulatedMotor Wing = new EV3MediumRegulatedMotor(MotorPort.B);
	private RegulatedMotor Scoop = new EV3MediumRegulatedMotor(MotorPort.C);
	private RegulatedMotor Right = new EV3LargeRegulatedMotor(MotorPort.D);

/**
 * Sets initial speed
 * @return nothing
 */
	public MovementUtils() {
		Left.setSpeed(145);
		Right.setSpeed(145);
	}

	public void moveForward() {
		Left.forward();
		Right.forward();
	}

	public void turnLeft() {
		Right.backward();
		Left.forward();
	}

	public void turnRight() {
		Right.forward();
		Left.backward();
	}

	public void turn(int degrees) {

		Left.rotate((-1) * degrees);
		Right.rotate(degrees);
	}

/**
 * Turns up to a half rotation in either direction, randomly.
 */
	public void turnRandomDegrees() {
		int turnDegrees = (int) (Math.random() * 420 - 210);
		turn(turnDegrees);
	}

	public void setLeftSpeed(int speed) {
		Left.setSpeed(speed);
	}

	public void setRightSpeed(int speed) {
		Right.setSpeed(speed);
	}

	public void setMoveSpeed(int speed) {
		Left.setSpeed(speed);
		Right.setSpeed(speed);
	}

	public void setWingSpeed(int speed) {
		Wing.setSpeed(speed);
	}

	public void moveWing(int degrees) {
		Wing.rotate(degrees);
	}

/**
 * Lifts the 'claw' for releasing objects
 */
	public void up() {
		Scoop.rotate(-111);
	}

/**
 * Lowers the 'claw' for collecting objects
 */
	public void down() {
		Scoop.rotate(111);
	}

	public void turnAround() {
		turn(180);
	}

}
