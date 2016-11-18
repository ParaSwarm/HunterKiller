package Behaviors;

public class IRSample {
		
	public IRSample(float[] samples) {
		this.angleOfTarget = (int)samples[0];
		this.distanceToTarget = samples[1];
	}
	
	public int angleOfTarget;
	
	public float distanceToTarget;
	
	public boolean targetDetected() {
		return angleOfTarget <= 1 
			&& distanceToTarget != 0
			&& distanceToTarget != Float.POSITIVE_INFINITY;
	}
}
