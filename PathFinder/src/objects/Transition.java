package objects;

public class Transition {
	public int toRoomNumber;
	public String transitionType;
	public double length;

    public Transition(int toRoomId, String transitionType) {
        this.toRoomNumber = toRoomId;
        this.transitionType = transitionType;
    }

    public Transition(int toRoomId, String transitionType, double length) {
        this(toRoomId, transitionType);
        this.length = length;
    }

    public int getToRoomNumber() {
        return toRoomNumber;
    }

    public void setToRoomNumber(int toRoomNumber) {
        this.toRoomNumber = toRoomNumber;
    }

    public String getTransitionType() {
        return transitionType;
    }

    public void setTransitionType(String transitionType) {
        this.transitionType = transitionType;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
