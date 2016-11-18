package Behaviors;

public abstract class BehaviorBase {

	public IMode ImplementedMode;
	
	public IMode NextMode;
	
	public abstract void ResetMode();
}
