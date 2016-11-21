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
		this.defeatSound = new File("defeat.wav");
	}

	private File defeatSound;
	
	@Override
	public void executeBehavior() {
		if(this.getTouchSample().buttonDepressed) {
			Sound.playSample(defeatSound, 100);
		}
	}

	@Override
	public void resetMode() {
		this.NextMode = TouchMode.Waiting;
	}
}
