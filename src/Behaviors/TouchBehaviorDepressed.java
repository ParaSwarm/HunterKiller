package Behaviors;

import java.io.File;

import lejos.hardware.Sound;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.sensor.EV3TouchSensor;

public class TouchBehaviorDepressed extends TouchBehaviorBase {

	public TouchBehaviorDepressed(EV3TouchSensor touchSensor) {
		super(touchSensor);
		this.ImplementedMode = TouchMode.Depressed;
		this.resetMode();
		this.victorySound = new File("victory.wav");
	}

	private File victorySound;
	
	@Override
	public void executeBehavior() {
		Sound.playSample(victorySound, 100);
	}

	@Override
	public void resetMode() {
		this.NextMode = TouchMode.Depressed;
	}
}
