package bowerBird;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;

public class SensorUtils {
	private EV3ColorSensor sensorColor;
	private float[] colorSample, leftSample, rightSample;
	private SampleProvider color, left, right;
	private EV3ColorSensor lightLeft;
	private EV3ColorSensor lightRight;
	private TouchAdapter ts;


	public SensorUtils(){
		sensorColor = new EV3ColorSensor(SensorPort.S1);
		color = sensorColor.getAmbientMode();
		colorSample = new float[color.sampleSize()];

		lightLeft = new EV3ColorSensor(SensorPort.S3);
		lightRight = new EV3ColorSensor(SensorPort.S4);
		left = lightLeft.getAmbientMode();
		right = lightRight.getAmbientMode();
		leftSample = new float[left.sampleSize()];
		rightSample = new float[right.sampleSize()];

		ts = new TouchAdapter(new EV3TouchSensor(SensorPort.S2));
	}

	public void getIRReading(){
		sensorIR.fetchSample(IRSample, 0);
	}

	public void getColorReading(){
		sensorColor.fetchSample(colorSample, 0);
	}

	public float getDistance(){
		return IRSample[1];
	}

	public float getDirection(){
		return IRSample[0];
	}

	public float getColor(){
		return colorSample[0];
	}

	public void takeLightReading()
	{
		left.fetchSample(leftSample, 0);
		right.fetchSample(rightSample, 0);
	}

	public float getLeft()
	{
		return leftSample[0];
	}

	public void getRight()
	{
		return rightSample[0];
	}


}
