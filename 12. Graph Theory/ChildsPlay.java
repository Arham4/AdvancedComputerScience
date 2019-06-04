import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Play {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("play.dat"))) {
            int cases = scanner.nextInt();
            for (int i = 0; i < cases; i++) {
                Map<Integer, Domino> dominos = new HashMap<>();

                int n = scanner.nextInt();
                int m = scanner.nextInt();
                int l = scanner.nextInt();
                for (int i1 = 0; i1 < m; i1++) {
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    Domino xDomino = getDomino(dominos, x);
                    xDomino.fallingNeighbors.add(getDomino(dominos, y));
                }
                Set<Domino> dominosFallen = new HashSet<>();
                for (int i1 = 0; i1 < l; i1++) {
                    int z = scanner.nextInt();
                    // start falling
                    Domino zDomino = getDomino(dominos, z);
                    if (!dominosFallen.contains(zDomino)) {
                        dominosFallen.addAll(zDomino.fall(dominosFallen));
                    }
                }
                System.out.println(dominosFallen.size());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Domino getDomino(Map<Integer, Domino> dominos, int id) {
        if (!dominos.containsKey(id)) {
            dominos.put(id, new Domino(id));
        }
        return dominos.get(id);
    }

    private static class Domino {
        private int number;
        private Set<Domino> fallingNeighbors;

        private Domino(int number) {
            this.number = number;
            fallingNeighbors = new HashSet<>();
        }

        List<Domino> fall(Set<Domino> fell) {
            return fall(this, fell);
        }

        private List<Domino> fall(Domino domino, Set<Domino> visited) {
            List<Domino> fell = new ArrayList<>();

            fell.add(domino);
            visited.add(domino);

            for (Domino fallingNeighbor : domino.fallingNeighbors) {
                if (!visited.contains(fallingNeighbor)) {
                    visited.add(fallingNeighbor);
                    fell.addAll(fall(fallingNeighbor, visited));
                }
            }
            return fell;
        }
    }
}
