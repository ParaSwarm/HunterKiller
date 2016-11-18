package Behaviors;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.sensor.EV3IRSensor;

public class IRBehaviorSeek extends IRBehaviorBase {

	public IRBehaviorSeek(EV3IRSensor irSensor, EV3MediumRegulatedMotor irMotor) {
		super(irSensor, irMotor);
		this.ImplementedMode = IRMode.Seeking;
	}

	@Override
	public void ExecuteBehavior() {
		
		IRSample sample = this.GetIRSample();
		
		this.NextMode = sample.angleOfTarget > 10
				? IRMode.LockedOn
				: IRMode.Seeking;
		
		//System.out.println("Thing: " + sample.angleOfTarget);
		//System.out.println("Distance: " + sample.distanceToTarget);
	}
}
