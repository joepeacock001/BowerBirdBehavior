package bowerBird;

import lejos.robotics.subsumption.Behavior;

public class TooFarFromHomeBehavior implements Behavior{

	private boolean suppressed = false;

	public TooFarFromHomeBehavior()
	{

	}

	@Override
	public boolean takeControl()
	{
		//take control when we are too far from home
		return true;
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


	}

}
