package objects;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Building {
	private HashMap<Integer, Room> myBuilding = new HashMap<>();
    
    Comparator<Room> byWeight = (Room r1, Room r2)
            -> Double.compare(r1.weight, r2.weight);
            
    Comparator<Room> byRoomNumber = (Room r1, Room r2)
            -> Integer.compare(r1.roomNumber, r2.roomNumber);
            
    Comparator<Room> byDistance = (Room r1, Room r2)
            -> Double.compare(r1.distanceToGoal, r2.distanceToGoal);


    public void addRoom(Room room) {

        if (room == null || myBuilding.containsKey(room.roomNumber)) {
            System.err.println("Room with number: " + room.roomNumber + " already exists");
            return;
        }
        myBuilding.put(room.roomNumber, room);
    }

    public void addTransition(Integer startRoomNumber, Integer endRoomNumber, String type, boolean isBiDirectional, double length) {
        if (myBuilding.containsKey(startRoomNumber) && myBuilding.containsKey(endRoomNumber)) {
        	
            Room startRoom = myBuilding.get(startRoomNumber);
            Room endRoom = myBuilding.get(endRoomNumber);
            
            startRoom.transitions.add(new Transition(endRoomNumber, type, length));

            if (isBiDirectional) {
                endRoom.transitions.add(new Transition(startRoomNumber, type, length));
            }
        } else {
            System.err.println("Wrong or missing rooms");
        }
    }


    public Room getRoom(int roomNumber) {
        return myBuilding.get(roomNumber);
    }

    public boolean containsRoom(int roomNumber) {
        return myBuilding.containsKey(roomNumber);
    }


    public ArrayList<Room> getLinkedRooms(int roomNumber) {
    	ArrayList<Room> linkedRooms = new ArrayList<>();
        Room room = myBuilding.get(roomNumber);
        for (Transition transition : room.transitions) {
            linkedRooms.add(myBuilding.get(transition.toRoomNumber));
        }
        return linkedRooms;
    }

    public ArrayList<Room> getLinkedRoomsAvoid(int roomNumber, String type) {
    	ArrayList<Room> linkedRooms = new ArrayList<>();
        Room room = myBuilding.get(roomNumber);
        for (Transition transition : room.transitions) {
            if (!transition.transitionType.equals(type)) {
                linkedRooms.add(myBuilding.get(transition.toRoomNumber));
            }
        }
        return linkedRooms;
    }

    public void resetAllRooms() {
        myBuilding.forEach((k, v) -> v.reset());
    }

    public void sortByRoomNumber(ArrayList<Room> list) {
        list.sort(byRoomNumber);
    }

    public void sortByDistance(ArrayList<Room> list) {

        list.sort(byDistance.thenComparing(byRoomNumber));
    }

    public void sortByWeight(ArrayList<Room> list) {
        list.sort(byWeight.thenComparing(byRoomNumber));
    }

    public void setDepths(int roomNumber) {
        Room room = getRoom(roomNumber);
        for (Room r : getLinkedRooms(roomNumber)) {
            if (r.depth == -1) {
                r.depth = room.depth + 1;
            }
        }
    }

    public void printPath(Room currentRoom) {
        StringBuilder path = new StringBuilder();

        while (currentRoom.depth != 0) {
            path.append(currentRoom.roomNumber);

            for (Room room : getLinkedRooms(currentRoom.roomNumber)) {
                if (room.depth == currentRoom.depth - 1) {
                    currentRoom = room;
                    break;
                }
            }
        }

        path.append(currentRoom.roomNumber);

        System.out.print(path);
    }

    public double findLength(int roomOneNumber, int roomTwoNumber) {

        Room roomOne = getRoom(roomOneNumber);

        for (Transition transition : roomOne.transitions) {
            if (transition.getToRoomNumber() == roomTwoNumber) {
//                System.out.println("Transition distance between room " + roomOneNumber + " and room " + roomTwoNumber + " is " + transition.getLength());
                return transition.getLength();
            }
        }

//        System.out.println("No transition between room " + roomOneNumber + " and room " + roomTwoNumber + " has been found");
        return 0.00;
    }

    public double findDistance(int roomOneNumber, int roomTwoNumber) {

        Room roomOne = getRoom(roomOneNumber);
        Room roomTwo = getRoom(roomTwoNumber);

        double distance = Math.sqrt(Math.pow(roomOne.x - roomTwo.x, 2)
                + Math.pow(roomOne.y - roomTwo.y, 2));

        System.out.println("Distance between room " + roomOneNumber + " and room " + roomTwoNumber + " is " + distance);
        return distance;
    }

}
