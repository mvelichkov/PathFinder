package objects;

import java.util.ArrayList;

public class SearchPathAvoid implements Searchable {

	private Building building;
    private String avoidedType;

    public SearchPathAvoid(Building building, String avoidedType) {
        this.building = building;
        this.avoidedType = avoidedType;
    }

    @Override
    public boolean search(int startRoomNumber, int endRoomNumber) {
        if (!building.containsRoom(startRoomNumber) || !building.containsRoom(endRoomNumber)) {
            System.out.println("One or both of the rooms does not exist");
            return false;
        }

        Room startRoom = building.getRoom(startRoomNumber);
        ArrayList<Room> queue = new ArrayList<>();
        queue.add(startRoom);

        Room temp;

        while (!queue.isEmpty()) {
            temp = queue.get(0);
            System.out.println("Temp room is: " + temp.roomNumber);

            if (temp.roomNumber == endRoomNumber) {
                return true;
            }

            temp.isTested = true;
            queue.remove(0);

            for (Room room : building.getLinkedRoomsAvoid(temp.roomNumber, avoidedType)) {
                if (!room.isTested && !queue.contains(room)) {
                    queue.add(room);
                }
            }

            temp.isExpanded = true;

        }//end while

        return false;
    }

}
