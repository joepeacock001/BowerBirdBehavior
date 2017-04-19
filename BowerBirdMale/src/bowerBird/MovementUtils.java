package bowerBird;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class MovementUtils {
//make motors
  private RegulatedMotor  Left = new EV3LargeRegulatedMotor(MotorPort.A);
  private RegulatedMotor  Wing = new EV3MediumRegulatedMotor(MotorPort.B);
  private RegulatedMotor  Scoop = new EV3MediumRegulatedMotor(MotorPort.C);
  private RegulatedMotor  Right = new EV3LargeRegulatedMotor(MotorPort.D);
  private int speed = 300;
  private int degrees = 360;
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
  
  public void turn(int degrees, boolean right){
	  if (right) {
		  Left.rotate(degrees);
		  Right.rotate(-degrees);
	  }
	  
	  else{
		  Left.rotate(-degrees);
		  Right.rotate(degrees);
	  }
	  	
  }

  public void turnRandom(){
	  double random = Math.random();
	  if (random>0.5)
		  turn(degrees,true);
	  else
		  turn(degrees,false);
  }

  public void goHome(){
	  
  }
  
  public void halfwayHome(){
	  
  }
  
  public void scoop(){
	  Left.rotate(360);
	  Right.rotate(360);
	  Scoop.rotate(ScoopDegrees);
  }
  
  public void drop(){
	  Scoop.rotate(-ScoopDegrees);
	  Left.rotate(-360);
	  Right.rotate(-360);
  }
  
}
