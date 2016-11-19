package Behaviors;

import lejos.hardware.motor.EV3MediumRegulatedMotor;

public class MovementBehaviorSeek extends MovementBehaviorBase {

	public MovementBehaviorSeek(EV3MediumRegulatedMotor leftWheelMotor, EV3MediumRegulatedMotor rightWheelMotor) {
		super(leftWheelMotor, rightWheelMotor);
		this.ImplementedMode = MovementMode.Seeking;
		this.resetMode();
		this.resetSpeeds();
	}

	private int distanceToTravel = 3000;
	
	private boolean movingForward = true;
	
	@Override
	public void executeBehavior() {
		
		if(!this.RightWheelMotor.isMoving()) {
			this.goForward();
		} else {
			if(this.getTachoCount() >= distanceToTravel || this.getTachoCount() <= -distanceToTravel) {
				this.resetTachometer();
				
				if(this.movingForward)
					this.goBackwards();
				else
					this.goForward();

				movingForward = !movingForward;
			}
		}
	}

	@Override
	public void resetMode() {
		this.NextMode = MovementMode.Seeking;
		this.resetSpeeds();
	}
}
