package bowerBird;

import lejos.robotics.subsumption.Behavior;

public class FoundObjectBehavior implements Behavior{

	private boolean suppressed = false;
	private MovementUtils wander;
	private SensorUtils senses;
	private int COLOR_THRESHOLD = .3;

	public FoundObjectBehavior()
	{
		wander = new MovementUtils();
		senses = new SensorUtils();
	}

	@Override
	public boolean takeControl()
	{
    //take control when we see a color with the color sensor
  	senses.getColorReading();
		if (senses.getRed() > COLOR_THRESHOLD) { return true; }
		if (senses.getBlue() > COLOR_THRESHOLD) { return true; }
		if (senses.getGreen() > COLOR_THRESHOLD) { return true; }
		return false;
	}

	@Override
	public void action() {

		while (!suppressed)
		{
			mover.scoop();
			mover.goHome();
			mover.drop();
			suppressed = !takeControl();
			Thread.yield();
		}

	}

	@Override
	public void suppress() {
		suppressed = true;

	}

}
