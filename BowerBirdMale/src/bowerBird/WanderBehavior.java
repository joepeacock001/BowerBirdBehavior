package bowerBird;

import lejos.robotics.subsumption.Behavior;

public class WanderBehavior implements Behavior{
	
	private boolean suppressed = false;
	
	public WanderBehavior()
	{
		
	}
	
	@Override
	public boolean takeControl()
	{
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
		suppressed = true;
		
	}

}
