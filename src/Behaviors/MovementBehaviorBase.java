package Behaviors;

import lejos.hardware.motor.EV3MediumRegulatedMotor;

public abstract class MovementBehaviorBase extends BehaviorBase {

	public MovementBehaviorBase(EV3MediumRegulatedMotor leftWheelMotor, EV3MediumRegulatedMotor rightWheelMotor) {
		this.LeftWheelMotor = leftWheelMotor;
		this.RightWheelMotor = rightWheelMotor;
	}

	private final float defaultWheelSpeed = 360;
	
	protected EV3MediumRegulatedMotor LeftWheelMotor;
	protected EV3MediumRegulatedMotor RightWheelMotor;
	
	protected void stop() {
		this.RightWheelMotor.stop();
		this.LeftWheelMotor.stop();
	}
	
	protected void goForward() {
		this.RightWheelMotor.forward();
		this.LeftWheelMotor.forward();
	}

	protected void goBackwards() {
		this.RightWheelMotor.backward();
		this.LeftWheelMotor.backward();
	}

	protected void rotateRight() {
		this.setSpeed(150);
		
		this.RightWheelMotor.forward();
		this.LeftWheelMotor.backward();
	}

	protected void rotateLeft() {
		this.setSpeed(150);
		
		this.RightWheelMotor.backward();
		this.LeftWheelMotor.forward();
	}
	
	protected void setSpeed(float bothWheelSpeed) {
		this.RightWheelMotor.setSpeed(bothWheelSpeed);
		this.LeftWheelMotor.setSpeed(bothWheelSpeed);
	}
	
	protected void setSpeed(float leftWheelSpeed, float rightWheelSpeed) {
		this.RightWheelMotor.setSpeed(rightWheelSpeed);
		this.LeftWheelMotor.setSpeed(leftWheelSpeed);
	}
	
	protected void resetTachometer() {
		RightWheelMotor.resetTachoCount();
		LeftWheelMotor.resetTachoCount();
	}
	
	protected int getTachoCount() {
		return RightWheelMotor.getTachoCount();
	}

	protected void resetSpeeds() {
		this.RightWheelMotor.setSpeed(defaultWheelSpeed);
		this.LeftWheelMotor.setSpeed(defaultWheelSpeed);
	}
	
	public void setIRMode(IMode mode) {
		if(mode == IRMode.Seeking) {
			this.NextMode = MovementMode.Seeking;
		} else if(mode == IRMode.LockedOn) {
			this.NextMode = MovementMode.Engaging;
		}
	}
	
	@Override
	public void ceaseBehavior() {
		this.RightWheelMotor.stop();
		this.LeftWheelMotor.stop();
	}
}
