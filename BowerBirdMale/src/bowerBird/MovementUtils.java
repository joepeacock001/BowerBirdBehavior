package bowerBird;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class MovementUtils {
//make motors
  private RegulatedMotor  Left = new EV3LargeRegulatedMotor(MotorPort.A);
  private RegulatedMotor  Wing = new EV3MediumRegulatedMotor(MotorPort.B);
  private RegulatedMotor  Scoop = new EV3MediumRegulatedMotor(MotorPort.C);
  private RegulatedMotor  Right = new EV3LargeRegulatedMotor(MotorPort.D);
  private int ScoopDegrees=90;
  private SensorUtils senses;

  public MovementUtils()
  {
		Left.setSpeed(145);
		Right.setSpeed(145);
    senses = new SensorUtils();
  }

  public void moveForward(){
	  Left.forward();
	  Right.forward();
  }

  public void moveBackward(){
	  Left.backward();
	  Right.backward();
  }

  public void stop(){
	  Left.stop();
	  Right.stop();
  }

  public void turnLeft()
  {
    Right.backward();
    Left.forward();
  }

  public void turnRight()
  {
    Right.forward();
    Left.backward();
  }

  public void turn(int degrees){

		  Left.rotate((-1)*degrees);
		  Right.rotate(degrees);


  }


  public void turnRandomDegrees(){
	  int turnDegrees = (int)(Math.random()*720 - 360);
	  turn(turnDegrees);

  }

  public void goTowardsHome(float threshold){
    senses.takeLightReading();
    while (senses.getRight() < 0.05 && senses.getLeft() < 0.05)
    {
      turnLeft();
      senses.takeLightReading();
    }
    while (senses.getRight() < threshold && senses.getLeft() < threshold)
    {
      if (senses.getRight() > senses.getLeft())
      {
        Left.setSpeed(165);
        Right.setSpeed(145);
        moveForward();
      }
      else{
        Left.setSpeed(145);
        Right.setSpeed(165);
        moveForward();
      }
    }
    Left.setSpeed(145);
    Right.setSpeed(145);

  }

	public void up()
	{
		Scoop.rotate(-111);
	}

	public void down()
	{
		Scoop.rotate(111);
	}

}
