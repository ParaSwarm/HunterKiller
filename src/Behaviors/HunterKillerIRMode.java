package Behaviors;

public enum HunterKillerIRMode implements IMode {
	Seeking(0), 
	LockedOn(1);
	
    private final int value;
    private HunterKillerIRMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
