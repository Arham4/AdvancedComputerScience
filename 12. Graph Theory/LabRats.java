import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Rats {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("rats.dat"))) {
            int cases = scanner.nextInt();
            for (int i = 0; i < cases; i++) {
                Map<Integer, Node> nodes = new HashMap<>();
                int cells = scanner.nextInt();
                int exit = scanner.nextInt();
                int cap = scanner.nextInt();
                int connections = scanner.nextInt();
                for (int i1 = 0; i1 < connections; i1++) {
                    Node from = getNode(nodes, scanner.nextInt());
                    Node to = getNode(nodes, scanner.nextInt());
                    int cost = scanner.nextInt();
                    from.neighbors.put(to, cost);
                }
                int passed = 0;
                for (int cell = 1; cell <= cells; cell++) {
                    makeShortestPath(getNode(nodes, cell), exit);
                    if (canMakeIt(getNode(nodes, exit), cap)) {
                        passed++;
                    }
                    clearPath(nodes.values());
                }
                System.out.println(passed);
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void clearPath(Collection<Node> values) {
        for (Node node : values) {
            node.minimumNeighbor = null;
            node.minimumCost = Integer.MAX_VALUE;
        }
    }

    private static boolean canMakeIt(Node exit, int cap) {
        return exit.minimumCost <= cap;
    }

    private static void makeShortestPath(Node origin, int goal) {
        PriorityQueue<Node> nodesToVisit = new PriorityQueue<>();
        Set<Node> visited = new HashSet<>();

        origin.minimumCost = 0;
        nodesToVisit.add(origin);
        while (!nodesToVisit.isEmpty()) {
            Node node = nodesToVisit.remove();
            int ownCost = node.minimumCost;
            if (node.id != goal) {
                visited.add(node);
            }

            for (Map.Entry<Node, Integer> neighborEntry : node.neighbors.entrySet()) {
                Node neighbor = neighborEntry.getKey();
                int cost = neighborEntry.getValue();
                int totalCostToGo = ownCost + cost;

                if (totalCostToGo < neighbor.minimumCost) {
                    neighbor.minimumCost = totalCostToGo;
                    neighbor.minimumNeighbor = node;
                }

                if (!visited.contains(neighbor)) {
                    nodesToVisit.add(neighbor);
                }
            }
        }
    }

    private static Node getNode(Map<Integer, Node> nodes, int id) {
        if (!nodes.containsKey(id)) {
            nodes.put(id, new Node(id));
        }
        return nodes.get(id);
    }

    private static class Node implements Comparable<Node> {
        private final Map<Node, Integer> neighbors;
        private final int id;

        private Node minimumNeighbor;
        private int minimumCost = Integer.MAX_VALUE;

        Node(int id) {
            this.id = id;
            neighbors = new HashMap<>();
        }
        
        @Override
        public int compareTo(Node o) {
            return minimumCost - o.minimumCost;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return id == node.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "node " + id;
        }
    }
}
