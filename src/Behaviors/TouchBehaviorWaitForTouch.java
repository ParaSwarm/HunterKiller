package Behaviors;

import java.io.File;

import lejos.hardware.Sound;
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
			this.NextMode = TouchMode.Depressed;
		}
	}

	@Override
	public void resetMode() {
		this.NextMode = TouchMode.Waiting;
	}
}
