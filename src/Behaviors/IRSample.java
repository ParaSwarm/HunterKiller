package Behaviors;

public class IRSample {
		
	public IRSample(float[] samples) {
		this.angleOfTarget = (int)samples[0];
		this.distanceToTarget = samples[1];
	}
	
	public int angleOfTarget;
	
	public float distanceToTarget;
	
	public boolean targetDetected() {
		return distanceToTarget != Float.POSITIVE_INFINITY;
	}
	
	public boolean targetAtMediumOrGreaterDistance() {
		return distanceToTarget > 35;
	}
	
	public int angleToMaintainLock() {
		if(this.targetAtMediumOrGreaterDistance()) {
			return 3;
		}
		
		return 1;
	}
}
