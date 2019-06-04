import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Goat {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("goat.dat"));
        int cases = scanner.nextInt();
        for (int _ = 0; _ < cases; _++) {
            int goatsAmount = scanner.nextInt();
            List<AGoat> goats = new ArrayList<>();
            for (int i = 0; i < goatsAmount; i++) {
                goats.add(null);
            }
            for (int i = 0; i < goats.size(); i++) {
                int[] cycle = new int[scanner.nextInt()];
                for (int i1 = 0; i1 < cycle.length; i1++) {
                    cycle[i1] = scanner.nextInt();
                }
                goats.set(i, new AGoat(i + 1, cycle));
            }
            int day = 0;
            int lastDayEaten = 0;
            for (int i = 0; i < 1000; i++) {
                PriorityQueue<AGoat> priorityQueue = new PriorityQueue<>(new GoatsComparator(day));
                priorityQueue.addAll(goats);
                AGoat goat1 = priorityQueue.poll();
                AGoat goat2 = priorityQueue.poll();
                if (goat1.cycle[day % goat1.cycle.length] != goat2.cycle[day % goat2.cycle.length]) {
                    goats.remove(goat1);
                    lastDayEaten = day + 1;
                }
                day++;
            }
            System.out.println(goats.size() + " " + lastDayEaten);
        }
    }

    static class AGoat {
        int id;
        int[] cycle;

        AGoat(int id, int[] cycle) {
            this.id = id;
            this.cycle = cycle;
        }

        @Override
        public String toString() {
            return "(ID: " + id + " | Cycle: " + Arrays.toString(cycle) + ")";
        }
    }

    static class GoatsComparator implements Comparator<AGoat> {
        private int day;

        GoatsComparator(int day) {
            this.day = day;
        }

        @Override
        public int compare(AGoat o1, AGoat o2) {
            return o1.cycle[day % o1.cycle.length] - o2.cycle[day % o2.cycle.length];
        }
    }
}
