package Behaviors;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.sensor.EV3IRSensor;

public abstract class MovementBehaviorBase extends BehaviorBase {

	public MovementBehaviorBase(EV3MediumRegulatedMotor leftWheelMotor, EV3MediumRegulatedMotor rightWheelMotor) {
		this.LeftWheelMotor = leftWheelMotor;
		this.RightWheelMotor = rightWheelMotor;
	}

	protected EV3MediumRegulatedMotor LeftWheelMotor;
	protected EV3MediumRegulatedMotor RightWheelMotor;
}
