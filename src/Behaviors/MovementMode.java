package Behaviors;

public enum MovementMode implements IMode {
	Seeking(0), 
	Engaging(1);
	
    private final int value;
    private MovementMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
