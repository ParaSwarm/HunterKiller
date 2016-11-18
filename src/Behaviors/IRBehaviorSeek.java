package Behaviors;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.sensor.EV3IRSensor;

public class IRBehaviorSeek extends IRBehaviorBase {

	public IRBehaviorSeek(EV3IRSensor irSensor, EV3MediumRegulatedMotor irMotor) {
		super(irSensor, irMotor);
		this.ImplementedMode = IRMode.Seeking;
		this.ResetMode();
	}

	@Override
	public void ExecuteBehavior() {
		
		IRSample sample = this.GetIRSample();
		
		if(!sample.targetDetected()) {
			if(!IRMotor.isMoving()) {
				IRMotor.forward();
			} else {
				if(IRMotor.getPosition() >= 75) {
					IRMotor.backward();
				} else if(IRMotor.getPosition() <= -75) {
					IRMotor.forward();
				}

				System.out.println("Thing: " + IRMotor.getPosition());
			}
		}
		else {
			IRMotor.stop();
		}
		
		this.NextMode = sample.targetDetected()
				? IRMode.LockedOn
				: IRMode.Seeking;
		
		//System.out.println("Thing: " + sample.angleOfTarget);
		//System.out.println("Distance: " + sample.distanceToTarget);
	}

	@Override
	public void ResetMode() {
		this.NextMode = IRMode.Seeking;
	}
}
