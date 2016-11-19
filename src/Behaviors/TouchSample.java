package Behaviors;

public class TouchSample {
		
	public TouchSample(float[] samples) {
		this.buttonDepressed = samples[0] == 1;
	}
	
	public boolean buttonDepressed;
}
