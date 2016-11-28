package HunterKiller;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.robotics.EncoderMotor;
import lejos.robotics.SampleProvider;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import Behaviors.BehaviorBase;
import Behaviors.IRMode;
import Behaviors.MovementBehaviorBase;
import Behaviors.MovementBehaviorEngage;
import Behaviors.MovementBehaviorSeek;
import Behaviors.MovementMode;
import Behaviors.TouchBehaviorBase;
import Behaviors.TouchBehaviorWaitForTouch;
import Behaviors.TouchMode;
import Behaviors.IMode;
import Behaviors.IRBehaviorBase;
import Behaviors.IRBehaviorSeek;
import Behaviors.IRBehaviorMaintainLock;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;

import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.EV3TouchSensor;

public class HunterKillerMain {

	private int IRMotorSpeed = 80;
	private int IRMotorAcceleration = 6000;
	private EV3MediumRegulatedMotor IRMotor;

	private EV3MediumRegulatedMotor LeftWheelMotor;
	private EV3MediumRegulatedMotor RightWheelMotor;
	
	private EV3IRSensor IRSensor;
	private EV3TouchSensor TouchSensor;

	private TouchBehaviorBase TouchBehavior;
	private ArrayList<BehaviorBase> TouchBehaviors;
	
	private IRBehaviorBase IRBehavior;
	private ArrayList<BehaviorBase> IRBehaviors;
	private Thread IRBehaviorThread;
	
	private MovementBehaviorBase MovementBehavior;
	private ArrayList<BehaviorBase> MovementBehaviors;
	private Thread MovementBehaviorThread;
	
	public HunterKillerMain() {
		setupButtons();
		setupMotorsAndSensors();
		setupIRBehaviors();
		setupMovementBehaviors();
		setupTouchBehaviors();
	}

	private void setupButtons(){
		Button.ESCAPE.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(Key k) {
			}

			@Override
			public void keyReleased(Key k) {
				System.exit(1);
			}
		});
		Button.ENTER.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(Key k) {
			}

			@Override
			public void keyReleased(Key k) {
//				try {
//					IRBehaviorThread.sleep(10000);
//					MovementBehaviorThread.sleep(10000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		});
		Button.LEFT.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(Key k) {
			}

			@Override
			public void keyReleased(Key k) {
			}
		});
		Button.RIGHT.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(Key k) {
			}

			@Override
			public void keyReleased(Key k) {
			}
		});
	}
	
	private void setupMotorsAndSensors() {
		try {
			IRMotor = new EV3MediumRegulatedMotor(MotorPort.A);
			IRMotor.setAcceleration(IRMotorAcceleration);
			IRMotor.setSpeed(IRMotorSpeed);

			LeftWheelMotor = new EV3MediumRegulatedMotor(MotorPort.B);
			RightWheelMotor = new EV3MediumRegulatedMotor(MotorPort.D);

			TouchSensor = new EV3TouchSensor(SensorPort.S2);
			
			IRSensor = new EV3IRSensor(SensorPort.S1);
			IRSensor.setCurrentMode("Seek");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setupIRBehaviors() {
		IRBehaviors = new ArrayList<>();
		IRBehaviorSeek irBehaviorSeek = new IRBehaviorSeek(IRSensor, IRMotor);
		IRBehaviorMaintainLock irBehaviorMaintainLock = new IRBehaviorMaintainLock(IRSensor, IRMotor);
		IRBehaviors.add(irBehaviorSeek);
		IRBehaviors.add(irBehaviorMaintainLock);
		
		IRBehavior = irBehaviorSeek;
	}

	private void setupMovementBehaviors() {
		MovementBehaviors = new ArrayList<>();
		MovementBehaviorSeek MovementBehaviorSeek = new MovementBehaviorSeek(LeftWheelMotor, RightWheelMotor);
		MovementBehaviorEngage MovementBehaviorEngage = new MovementBehaviorEngage(LeftWheelMotor, RightWheelMotor, IRMotor);
		MovementBehaviors.add(MovementBehaviorSeek);
		MovementBehaviors.add(MovementBehaviorEngage);
		
		MovementBehavior = MovementBehaviorSeek;
	}

	private void setupTouchBehaviors() {
		TouchBehaviors = new ArrayList<>();
		TouchBehaviorWaitForTouch TouchBehaviorWaitForTouch = new TouchBehaviorWaitForTouch(TouchSensor);
		TouchBehaviors.add(TouchBehaviorWaitForTouch);
		
		TouchBehavior = TouchBehaviorWaitForTouch;
	}
	
	public void execute() {
		IRBehaviorThread = new Thread(new Runnable() {
	         public void run() { processIR(); }
		});
		
		MovementBehaviorThread = new Thread(new Runnable() {
	         public void run() { processMovement(); }
		});

		IRBehaviorThread.start();
		MovementBehaviorThread.start();
		
		processTouchBehavior();
	}

	private void processIR()	{
		while(true) {
			if(this.IRBehavior == null)
				continue;
			
			this.IRBehavior.executeBehavior();
			this.IRBehavior = (IRBehaviorBase)this.GetBehavior(this.IRBehaviors, this.IRBehavior);
		}
	}

	private void processMovement()	{
		while(true) {
			if(this.MovementBehavior == null)
				continue;
			
			this.MovementBehavior.executeBehavior();
			this.MovementBehavior.setIRMode(IRBehavior.ImplementedMode);
			this.MovementBehavior = (MovementBehaviorBase)this.GetBehavior(this.MovementBehaviors, this.MovementBehavior);
		}
	}
	
	private void processTouchBehavior() {
		while(true) {
			if(this.TouchBehavior.NextMode == TouchMode.Depressed) {
				continue;
			}
			this.TouchBehavior.executeBehavior();
		}
	}
	
	private BehaviorBase GetBehavior(ArrayList<BehaviorBase> behaviors, BehaviorBase currentBehavior) {
		if(currentBehavior.NextMode == currentBehavior.ImplementedMode){
			return currentBehavior;
		}
		
		if(this.TouchBehavior.NextMode == TouchMode.Depressed) {
			currentBehavior.ceaseBehavior();
			return null;
		}
		
		for(BehaviorBase newBehavior : behaviors) {
			if(newBehavior.ImplementedMode == currentBehavior.NextMode) {
				currentBehavior.resetMode();
				return newBehavior;
			}
		}
		
		return null;
	}
	
}