package Behaviors;

import lejos.hardware.motor.EV3MediumRegulatedMotor;

public class MovementBehaviorSeek extends MovementBehaviorBase {

	public MovementBehaviorSeek(EV3MediumRegulatedMotor leftWheelMotor, EV3MediumRegulatedMotor rightWheelMotor) {
		super(leftWheelMotor, rightWheelMotor);
		this.ImplementedMode = MovementMode.Seeking;
		this.ResetMode();
	}

	@Override
	public void ExecuteBehavior() {
		
		if(!this.RightWheelMotor.isMoving()) {
			this.RightWheelMotor.forward();
			this.LeftWheelMotor.forward();	
		} else {
			if(RightWheelMotor.getTachoCount() > 1000) {
				this.RightWheelMotor.stop();
				this.LeftWheelMotor.stop();	
			}
		}

		System.out.println("yo " + RightWheelMotor.getTachoCount());
	}

	@Override
	public void ResetMode() {
		this.NextMode = MovementMode.Seeking;
	}
}
