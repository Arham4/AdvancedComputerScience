import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Pavers {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("paver.dat"))) {
            int cases = scanner.nextInt();
            for (int i = 0; i < cases; i++) {
                Map<Integer, Node> nodes = new HashMap<>();
                int pricePer = scanner.nextInt();
                int buildings = scanner.nextInt();
                int streets = scanner.nextInt();
                for (int i1 = 0; i1 < streets; i1++) {
                    Node from = getNode(nodes, scanner.nextInt());
                    Node to = getNode(nodes, scanner.nextInt());
                    int cost = scanner.nextInt() * pricePer;
                    from.neighbors.put(to, cost);
                }
                buildMinimumSpanningTree(nodes.get(1));
                int cost = calculateCost(nodes.values());
                System.out.println(cost);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int calculateCost(Collection<Node> nodes) {
        int totalCost = 0;
        for (Node node : nodes) {
            totalCost += node.minimumCost;
        }
        return totalCost;
    }

    private static void buildMinimumSpanningTree(Node root) {
        Queue<Node> nodesToVisit = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        visited.add(root);
        nodesToVisit.add(root);

        root.minimumCost = 0;
        while (!nodesToVisit.isEmpty()) {
            Node node = nodesToVisit.remove();
            node.neighbors.forEach((neighbor, cost) -> {
                if (cost < neighbor.minimumCost) {
                    neighbor.minimumCost = cost;
                }
                if (cost < node.minimumCost) {
                    node.minimumCost = cost;
                }
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    nodesToVisit.add(neighbor);
                }
            });
        }
    }

    private static Node getNode(Map<Integer, Node> nodes, int id) {
        if (!nodes.containsKey(id)) {
            nodes.put(id, new Node(id));
        }
        return nodes.get(id);
    }

    private static class Node {
        private final Map<Node, Integer> neighbors;
        private final int id;

        private int minimumCost = Integer.MAX_VALUE;

        Node(int id) {
            this.id = id;
            neighbors = new HashMap<>();
        }
        
        @Override
        public String toString() {
            return "node " + id;
        }
    }
}
