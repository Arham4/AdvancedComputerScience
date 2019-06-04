import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Scooby {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("scooby.dat"))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            int cases = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < cases; i++) {
                Map<Character, Room> roomForIdentity = new HashMap<>();
                String[] roomPairsRaw = scanner.nextLine().split(" ");
                for (String roomPair : roomPairsRaw) {
                    char[] rooms = roomPair.toCharArray();
                    Room roomOne = getRoom(rooms[0], roomForIdentity);
                    Room roomTwo = getRoom(rooms[1], roomForIdentity);
                    roomOne.addNeighbor(roomTwo);
                    roomTwo.addNeighbor(roomOne);
                }
                char[] startEndPair = scanner.nextLine().toCharArray();
                Room start = getRoom(startEndPair[0], roomForIdentity);
                Room end = getRoom(startEndPair[1], roomForIdentity);

                Set<Room> visited = new HashSet<>();
                if (!visitRoom(start, end, visited)) {
                    System.out.println("no");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static boolean visitRoom(Room room, Room end, Set<Room> visited) {
        visited.add(room);
        if (room == end) {
            System.out.println("yes");
            return true;
        }
        for (Room neighbor : room.neighbors) {
            if (!visited.contains(neighbor) && visitRoom(neighbor, end, visited)) {
                return true;
            }
        }
        return false;
    }

    private static Room getRoom(char identity, Map<Character, Room> roomForIdentity) {
        if (!roomForIdentity.containsKey(identity)) {
            Room room = new Room(identity);
            roomForIdentity.put(identity, room);
        }
        return roomForIdentity.get(identity);
    }

    static class Room {
        final char identity;
        Set<Room> neighbors;

        Room(char identity) {
            this.identity = identity;
            neighbors = new HashSet<>();
        }

        void addNeighbor(Room neighbor) {
            neighbors.add(neighbor);
        }

        @Override
        public String toString() {
            StringBuilder output = new StringBuilder("Identity: " + identity + ", Neighbors: [");
            for (Room neighbor : neighbors) {
                output.append(neighbor.identity).append(", ");
            }
            output.append("]");
            return output.toString();
        }
    }
}