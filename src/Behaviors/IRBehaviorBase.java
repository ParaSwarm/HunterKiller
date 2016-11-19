package Behaviors;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.sensor.EV3IRSensor;

public abstract class IRBehaviorBase extends BehaviorBase {

	public IRBehaviorBase(EV3IRSensor IRSensor, EV3MediumRegulatedMotor IRMotor) {
		this.IRSensor = IRSensor;
		this.IRMotor = IRMotor;
		IRSampleArray = new float[IRSensor.sampleSize()];
	}

	protected EV3IRSensor IRSensor;
	
	protected EV3MediumRegulatedMotor IRMotor;
	
	protected float[] IRSampleArray;
	
	protected IRSample getIRSample() {
		IRSensor.fetchSample(IRSampleArray, 0);
		return new IRSample(IRSampleArray);
	}
}
