package bowerBird;

import lejos.robotics.subsumption.Behavior;
import java.lang.Math;

public class WanderBehavior implements Behavior{

	private boolean suppressed = false;
private MovementUtils mover;

	public WanderBehavior()
	{
		mover = new MovementUtils();
	}

	@Override
	public boolean takeControl()
	{
		//lowest level behavior so always take control if given the option
		return true;
	}

	@Override
	public void action() {

		while (!suppressed)
		{
			int shouldTurn = (int)(Math.random()*10000);
			if (shouldTurn == 11)
			{
				mover.turnRandomDegrees();
			}
			else
			{
				mover.moveForward();
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
