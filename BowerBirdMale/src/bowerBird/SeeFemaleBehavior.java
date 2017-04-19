package bowerBird;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.Behavior;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;

public class SeeFemaleBehavior implements Behavior{

	private boolean suppressed = false;
	private boolean wingUp = false;
	private static RegulatedMotor  Left = new EV3LargeRegulatedMotor(MotorPort.A);
	  private static RegulatedMotor  Wing = new EV3MediumRegulatedMotor(MotorPort.B);
	  private static RegulatedMotor  Scoop = new EV3MediumRegulatedMotor(MotorPort.C);
	  private static RegulatedMotor  Right = new EV3LargeRegulatedMotor(MotorPort.D);


	public SeeFemaleBehavior()
	{

	}

	@Override
	public boolean takeControl()
	{
    //take control when we detect a female
	}

	@Override
	public void action() {
		
		while (!suppressed)
		{
			Wing.setSpeed(250);
			Left.setSpeed(111);
			Right.setSpeed(111);
			if (wingUp)
			{
				Wing.rotate(-45);
				Left.forward();
				Right.backward();
			}
			else
			{
				Wing.rotate(45);
				Right.forward();
				Left.backward();
			}
			wingUp = !wingUp;
			suppressed = !takeControl();
			Thread.yield();
		}

	}

	@Override
	public void suppress() {
		suppressed = true;

	}

}
