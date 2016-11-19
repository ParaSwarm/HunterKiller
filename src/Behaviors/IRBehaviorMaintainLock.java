package Behaviors;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.sensor.EV3IRSensor;

public class IRBehaviorMaintainLock extends IRBehaviorBase {

	public IRBehaviorMaintainLock(EV3IRSensor irSensor, EV3MediumRegulatedMotor irMotor) {
		super(irSensor, irMotor);
		this.ImplementedMode = IRMode.LockedOn;
		this.resetMode();
	}

	@Override
	public void executeBehavior() {
		
		IRSample sample = this.getIRSample();
		
		if(sample.targetDetected()) {
			IRMotor.rotate(sample.angleOfTarget * 2);
		} else {
			this.NextMode = IRMode.Seeking;
		}
	}

	@Override
	public void resetMode() {
		this.NextMode = IRMode.LockedOn;
	}
	
}
