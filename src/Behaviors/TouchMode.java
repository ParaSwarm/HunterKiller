package Behaviors;

public enum TouchMode implements IMode {
	Waiting(0), 
	Depressed(1);
	
    private final int value;
    private TouchMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
