package HunterKiller;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.robotics.EncoderMotor;
import lejos.robotics.SampleProvider;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;

import java.util.ArrayList;

import Behaviors.BehaviorBase;
import Behaviors.IRMode;
import Behaviors.IMode;
import Behaviors.IRBehaviorBase;
import Behaviors.IRBehaviorSeek;
import Behaviors.IRBehaviorMaintainLock;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;

import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;

public class HunterKillerMain {

	Port port;

	private int IRMotorSpeed = 80;
	private int IRMotorAcceleration = 6000;
	private EV3MediumRegulatedMotor IRMotor;
	
	private EV3IRSensor IRSensor;
	
	private IRBehaviorBase IRBehavior;
	private ArrayList<BehaviorBase> IRBehaviors;
	
	public HunterKillerMain() {
		setupButtons();
		setupMotorsAndSensors();
		setupIRBehaviors();
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
			
			port = LocalEV3.get().getPort("S1");
			IRSensor = new EV3IRSensor(port);
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
	
	public void execute() {
		Thread IRBehaviorThread = new Thread(new Runnable() {
	         public void run()
	         {
	              processIR();
	         }
		});
		
		IRBehaviorThread.run();
		
		while(true) { }
	}
	
	public void processIR()	{
		while(true) {
			this.IRBehavior.ExecuteBehavior();
			this.IRBehavior = (IRBehaviorBase)this.GetBehavior(this.IRBehaviors, this.IRBehavior);
		}
	}
	
	private BehaviorBase GetBehavior(ArrayList<BehaviorBase> behaviors, BehaviorBase currentBehavior) {
		if(currentBehavior.NextMode == currentBehavior.ImplementedMode){
			return currentBehavior;
		}
		
		for(BehaviorBase newBehavior : behaviors) {
			if(newBehavior.ImplementedMode == currentBehavior.NextMode) {
				currentBehavior.ResetMode();
				return newBehavior;
			}
		}
		
		return null;
	}
	
}