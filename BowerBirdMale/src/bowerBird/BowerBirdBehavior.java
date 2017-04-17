package bowerBird;

import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class BowerBirdBehavior {

	public static void main(String[] args)
	{
		Behavior wander = new WanderBehavior();
		Behavior tooFar = new TooFarFromHomeBehavior();
		Behavior foundObject = new FoundObjectBehavior();
		Behavior seeFemale = new SeeFemaleBehavior();

		Behavior[] bArray = { wander, tooFar, foundObject, seeFemale };

		Arbitrator arby = new Arbitrator(bArray);
		// Begin the Arbitrator which starts any enabled behaviors.
		arby.go();

	}

}
