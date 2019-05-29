package main;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;

import objects.Building;
import objects.Room;
import objects.SearchPathAvoid;
import objects.Searchable;

public class Main {

	private static Building building = new Building();

	public static void main(String[] args) throws Exception {
		init();

        findPath(232, 330, new SearchPathAvoid(building, "lift")); //Task 1

	}

	private static void findPath(int startRoomNumber, int endRoomNumber, Searchable searcher) {
		building.resetAllRooms();
		if (searcher.search(startRoomNumber, endRoomNumber)) {
			System.out.println("HAVE A PATH");
		} else {
			System.out.println("THERE IS NO PATH");
		}
	}

	public static void init() throws Exception {
		String fileName = "structure.txt";

		ClassLoader classLoader = ClassLoader.getSystemClassLoader();

		File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());

		Scanner sc = new Scanner(file);

		try {

			boolean isRooms = true;

			while (sc.hasNextLine()) {
				String line = sc.nextLine();

				if (line.isEmpty()) {
					isRooms = false;
					continue;
				}

				if (isRooms) {
					createRoomFromLine(line);
				} else {
					createTransitionFromLine(line);
				}
			}

		} catch (Exception ignored) {
		}
	}

	private static void createRoomFromLine(String line) {
		String[] components = line.split(", ");

		String type = components[4].substring(0, components[4].length() - 1);

		building.addRoom(new Room(Integer.parseInt(components[0]), Integer.parseInt(components[1]),
				Integer.parseInt(components[2]), Integer.parseInt(components[3]), type)); // For removing semicolon (;)
	}

	private static void createTransitionFromLine(String line) {
		String[] components = line.split(", ");

		boolean isBidirectional = (components[4].substring(0, components[4].length() - 1).equalsIgnoreCase("yes"));

		building.addTransition(Integer.parseInt(components[0]), Integer.parseInt(components[1]), components[2],
				isBidirectional, Integer.parseInt(components[3]));
	}

	public Building getBuilding() {
		return building;
	}
}
