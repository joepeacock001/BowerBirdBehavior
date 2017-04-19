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
  private int speed = 300;
  private int ScoopDegrees=90;
  
  public void moveForward(){
	  Left.setSpeed(speed);
	  Right.setSpeed(speed);
	  Left.forward();
	  Right.forward();
  }

  public void moveBackward(){
	  Left.setSpeed(speed);
	  Right.setSpeed(speed);
	  Left.backward();
	  Right.backward();
  }

  public void stop(){
	  Left.stop();
	  Right.stop();
  }
  
  public void turn(int degrees){

		  Left.rotate((-1)*degrees);
		  Right.rotate(degrees);
	  
	  	
  }

  
  public void turnRandomDegrees(){
	  int turnDegrees = (int)(Math.random()*720 - 360);
	  turn(turnDegrees);
	
  }

  public void goHome(){
	  
  }
  
  public void halfwayHome(){
	  
  }
  
  public void scoop(){
	  Left.rotate(270,true);
	  Right.rotate(270);
	  Scoop.rotate(-45);
  }
  
  public void drop(){
	  Scoop.rotate(45);
	  Left.rotate(-270,true);
	  Right.rotate(-270);
	  Scoop.rotate(45);
  }
  
}
