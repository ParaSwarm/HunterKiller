package Behaviors;

public class IRSample {
		
	public IRSample(float[] samples) {
		this.angleOfTarget = (int)samples[0];
		this.distanceToTarget = samples[1];
	}
	
	public int angleOfTarget;
	
	public float distanceToTarget;
}
