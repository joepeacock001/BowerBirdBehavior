package bowerBird;

import lejos.robotics.subsumption.Behavior;

public class SeeFemaleBehavior implements Behavior{

	private boolean suppressed = false;

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
			//do things here

		}

	}

	@Override
	public void suppress() {
		suppressed = true;

	}

}
