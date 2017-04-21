package bowerBird;

import lejos.robotics.subsumption.Behavior;

public class FoundObjectBehavior implements Behavior{

	private boolean suppressed = false;
	private MovementUtils mover;
	private SensorUtils senses;
	private float COLOR_THRESHOLD = (float).3;

	public FoundObjectBehavior()
	{
		mover = new MovementUtils();
		senses = new SensorUtils();
	}

	@Override
	public boolean takeControl()
	{
    //take control when we see a color with the color sensor
  	senses.getColorReading();
		if (senses.getColor() != -1)
		{
			return true;
		}
		return false;
	}

	@Override
	public void action() {

		while (!suppressed)
		{
			if (senses.getColor() == 2)
			{
				mover.goHome();
				mover.up();
				mover.moveForward();
			}	
			else
			{
				mover.up();
				mover.moveForward();
				mover.down();
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
