package Behaviors;

import lejos.hardware.motor.EV3MediumRegulatedMotor;

public class MovementBehaviorEngage extends MovementBehaviorBase {

	public MovementBehaviorEngage(EV3MediumRegulatedMotor leftWheelMotor, EV3MediumRegulatedMotor rightWheelMotor) {
		super(leftWheelMotor, rightWheelMotor);
		this.ImplementedMode = MovementMode.Engaging;
		this.ResetMode();
	}

	@Override
	public void ExecuteBehavior() {
	}

	@Override
	public void ResetMode() {
		this.NextMode = MovementMode.Engaging;
	}
}
