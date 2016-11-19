package Behaviors;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.hardware.sensor.SensorModes;

public class IRBehaviorMaintainLock extends IRBehaviorBase {

	public IRBehaviorMaintainLock(EV3IRSensor irSensor, EV3MediumRegulatedMotor irMotor) {
		super(irSensor, irMotor);
		this.ImplementedMode = IRMode.LockedOn;
		this.resetMode();
	}

	private int buffer;
	
	@Override
	public void executeBehavior() {
		
		IRSample sample = this.getIRSample();

		if(sample.targetDetected()) {
			if(sample.angleOfTarget > 1) {
				IRMotor.forward();
			}
			else if (sample.angleOfTarget < -1) {
				IRMotor.backward();
			}
			else {
				IRMotor.stop();
				
				if(sample.angleOfTarget == 1) {
					buffer++;
					if(buffer == 3) {
						buffer = 0;
						IRMotor.rotate(sample.angleToMaintainLock());
					}
				}
				else if(sample.angleOfTarget == -1) {
					buffer--;
					if(buffer == -3) {
						buffer = 0;
						IRMotor.rotate(-sample.angleToMaintainLock());
					}
				} else {
					buffer = 0;
				}
			}
		} else {
			this.NextMode = IRMode.Seeking;
		}
	}

	@Override
	public void resetMode() {
		this.NextMode = IRMode.LockedOn;
	}
	
}
