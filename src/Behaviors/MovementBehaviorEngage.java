package Behaviors;

import lejos.hardware.motor.EV3MediumRegulatedMotor;

public class MovementBehaviorEngage extends MovementBehaviorBase {

	public MovementBehaviorEngage(EV3MediumRegulatedMotor leftWheelMotor, EV3MediumRegulatedMotor rightWheelMotor, EV3MediumRegulatedMotor IRMotor) {
		super(leftWheelMotor, rightWheelMotor);
		this.IRMotor = IRMotor;
		this.ImplementedMode = MovementMode.Engaging;
		this.resetMode();
	}

	private EV3MediumRegulatedMotor IRMotor;
	
	@Override
	public void executeBehavior() {
		float angleOfTarget = IRMotor.getPosition();
		
		if(angleOfTarget < -10) {		// Target is left of center
			if(angleOfTarget < -30) {
				this.rotateLeft();
			} else {
				this.setSpeed(angleOfTarget * 15, angleOfTarget * 8);
				this.goForward();
			}
		} else if (angleOfTarget > 10) {	// Target is right of center
			if(angleOfTarget > 30) {
				this.rotateRight();
			} else {
				this.setSpeed(angleOfTarget * 8, angleOfTarget * 15);
				this.goForward();
			}
		} else {
			this.resetSpeeds();
			this.goForward();
		}
		
		System.out.println(angleOfTarget);
	}

	@Override
	public void resetMode() {
		this.NextMode = MovementMode.Engaging;
	}
}
