package Behaviors;

public enum IRMode implements IMode {
	Seeking(0), 
	LockedOn(1);
	
    private final int value;
    private IRMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
