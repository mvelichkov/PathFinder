package objects;

import java.util.ArrayList;

public class Room {
	
	public int roomNumber;
    public int x;
    public int y;
    public int floor;
    public String type;

    public boolean isTested = false;
    public boolean isExpanded = false;
    public int depth = -1;
    public double distanceToGoal = 0.0;
    public Room parent = null;

    public ArrayList<Transition> transitions = new ArrayList<>();
    public double weight;

    public Room(int roomNumber, int floor, String type) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.type = type;
    }

    public Room(int roomNumber, int x, int y, int floor, String type) {
        this(roomNumber, floor, type);
        this.x = x;
        this.y = y;
    }

    public void reset() {
        this.isExpanded = false;
        this.isTested = false;
        this.depth = -1;
        this.distanceToGoal = 0.0;
        this.parent = null;
    }
}
