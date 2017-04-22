package bowerBird;


public class BowerBirdBehavior {

	private MovementUtils mover;
	private SensorUtils senses;
	private boolean seeFemale = false;
	private boolean hasObject = false;
	private boolean wingUp = false;

	public static void main(String[] args)
	{
		mover = new MovementUtils();
		senses = new SensorUtils();

		while (true)
		{
			if (seeFemale)
			{
				Wing.setSpeed(250);
				Left.setSpeed(111);
				Right.setSpeed(111);
				if (wingUp)
				{
					Wing.rotate(-45);
					Left.forward();
					Right.backward();
				}
				else
				{
					Wing.rotate(45);
					Right.forward();
					Left.backward();
				}
				wingUp = !wingUp;
			}

			senses.getColorReading();
			if (senses.getColor() != -1)
			{
				hasObject = true;
			}
			if (hasObject)
			{
				hadObject();
			}
			senses.takeLightReading();
			if (senses.getLeft() < 0.02 && senses.getRight() < 0.02)
			{
				mover.goTowardsHome(0.11);
			}
			wander()
		}

	}

	public static void hasObject()
	{
		if (senses.getColor() == 2)
		{
			mover.goTowardsHome(0.2);
			mover.move(-360);
			mover.turnLeft();
		}
		else
		{
			mover.up();
			mover.moveForward();
			mover.down();
		}
	}

	public static void wander()
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
	}

}
