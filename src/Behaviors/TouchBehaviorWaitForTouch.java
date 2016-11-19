package Behaviors;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.sensor.EV3TouchSensor;

public class TouchBehaviorWaitForTouch extends TouchBehaviorBase {

	public TouchBehaviorWaitForTouch(EV3TouchSensor touchSensor) {
		super(touchSensor);
		this.ImplementedMode = TouchMode.Waiting;
		this.resetMode();
	}

	@Override
	public void executeBehavior() {
		if(this.getTouchSample().buttonDepressed) {
			
		}
	}

	@Override
	public void resetMode() {
		this.ImplementedMode = TouchMode.Waiting;
	}
}
