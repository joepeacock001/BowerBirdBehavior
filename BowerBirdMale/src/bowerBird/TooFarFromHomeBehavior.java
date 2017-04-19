package bowerBird;

import lejos.robotics.subsumption.Behavior;

public class TooFarFromHomeBehavior implements Behavior{

	private boolean suppressed = false;
	private MovementUtils mover;
	private SensorUtils senses;
	private float DISTANCE_THRESHOLD = 1.1;

	public TooFarFromHomeBehavior()
	{
		mover = new MovementUtils();
		senses = new SensorUtils();
	}

	@Override
	public boolean takeControl()
	{
		senses.getIRReading();
		if (senses.getDistance() > DISTANCE_THRESHOLD)
		{
			return true;
		}
		return false;
	}

	@Override
	public void action() {
		suppressed = false;
		while (!suppressed)
		{
			mover.halfwayHome();

			suppressed = !takeControl();
			Thread.yield();
		}

	}

	@Override
	public void suppress() {
		suppressed = true;

	}

}
