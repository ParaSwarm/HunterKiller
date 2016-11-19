package Behaviors;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.sensor.EV3IRSensor;

public class IRBehaviorSeek extends IRBehaviorBase {

	public IRBehaviorSeek(EV3IRSensor irSensor, EV3MediumRegulatedMotor irMotor) {
		super(irSensor, irMotor);
		this.ImplementedMode = IRMode.Seeking;
		this.resetMode();
		this.IRMotor.rotateTo(0);
	}

	private final int seekMaximumAngle = 90;
	
	@Override
	public void executeBehavior() {
		System.out.println("Seeking");
		IRSample sample = this.getIRSample();
		
		if(!sample.targetDetected()) {
			if(!IRMotor.isMoving()) {
				IRMotor.forward();
			} else {
				if(IRMotor.getPosition() >= seekMaximumAngle) {
					IRMotor.backward();
				} 
				else if(IRMotor.getPosition() <= -seekMaximumAngle) {
					IRMotor.forward();
				}
			}
		}
		else {
			IRMotor.stop();
		}
		
		this.NextMode = sample.targetDetected()
				? IRMode.LockedOn
				: IRMode.Seeking;
	}

	@Override
	public void resetMode() {
		this.NextMode = IRMode.Seeking;
	}
}
