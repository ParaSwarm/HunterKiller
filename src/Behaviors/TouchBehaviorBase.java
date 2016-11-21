package Behaviors;

import lejos.hardware.sensor.EV3TouchSensor;

public abstract class TouchBehaviorBase extends BehaviorBase {

	public TouchBehaviorBase(EV3TouchSensor touchSensor) {
		this.TouchSensor = touchSensor;
		TouchSampleArray = new float[TouchSensor.sampleSize()];
	}

	protected EV3TouchSensor TouchSensor;
	
	protected float[] TouchSampleArray;
	
	protected TouchSample getTouchSample() {
		TouchSensor.fetchSample(TouchSampleArray, 0);
		return new TouchSample(TouchSampleArray);
	}
}
