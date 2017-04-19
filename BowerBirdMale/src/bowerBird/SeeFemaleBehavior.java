package bowerBird;

import lejos.robotics.subsumption.Behavior;
import lejos.hardware.motor.Motor;

public class SeeFemaleBehavior implements Behavior{

	private boolean suppressed = false;
	private boolean wingUp = false;

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
			if (wingUp)
			{
				Motor.B.rotate(30);
			}
			else
			{
				Motor.B.rotate(-30);
			}

			suppressed = !takeControl();
			Thread.yield();
		}

	}

	@Override
	public void suppress() {
		suppressed = true;

	}

}
