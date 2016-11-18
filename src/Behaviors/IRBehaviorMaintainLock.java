package Behaviors;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.sensor.EV3IRSensor;

public class IRBehaviorMaintainLock extends IRBehaviorBase {

	public IRBehaviorMaintainLock(EV3IRSensor irSensor, EV3MediumRegulatedMotor irMotor) {
		super(irSensor, irMotor);
		this.ImplementedMode = HunterKillerIRMode.LockedOn;
	}

	@Override
	public void ExecuteBehavior() {
		
		IRSample sample = this.GetIRSample();
		
		IRMotor.rotate(sample.angleOfTarget * 2);
		
		//System.out.println("Thing: " + sample.angleOfTarget);
		//System.out.println("Distance: " + sample.distanceToTarget);
	}
	
}
