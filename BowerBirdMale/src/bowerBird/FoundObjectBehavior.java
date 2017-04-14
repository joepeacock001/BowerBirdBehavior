package bowerBird;

import lejos.robotics.subsumption.Behavior;

public class FoundObjectBehavior implements Behavior{

	private boolean suppressed = false;

	public FoundObjectBehavior()
	{

	}

	@Override
	public boolean takeControl()
	{
    //take control when we see a color with the color sensor
	}

	@Override
	public void action() {

		while (!suppressed)
		{
			//do things here

		}

	}

	@Override
	public void suppress() {
		suppressed = true;

	}

}
