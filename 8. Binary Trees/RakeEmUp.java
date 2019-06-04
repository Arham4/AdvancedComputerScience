import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;

public class RakeEmUp {

    private static Scanner treeRaw;
    private static TreeMap<Long, Long> values;

    private static class Node {
        Node left;
        Node right;
        long id;
        long value;

        Node(long id, long value) {
            this.id = id;
            this.value = value;
        }
    }

    private static class Tree {
        private Node root;

        Tree(Node node) {
            root = node;
        }

        void populateTree(Node node) {
            try {
                long left = treeRaw.nextLong();
                if (left != -1) {
                    node.left = new Node(node.id - 1, left);
                    populateTree(node.left);
                }
                long right = treeRaw.nextLong();
                if (right != -1) {
                    node.right = new Node(node.id + 1, right);
                    populateTree(node.right);
                }
            } catch (NoSuchElementException e) { }
        }

        void populateMap(Node node) {
            if (node == null) {
                return;
            }
            if (!values.containsKey(node.id)) {
                values.put(node.id, node.value);
            } else {
                values.put(node.id, node.value + values.get(node.id));
            }
            populateMap(node.left);
            populateMap(node.right);
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("rake.dat"))) {
            int caseNumber = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("-1")) {
                    break;
                }
                treeRaw = new Scanner(line);
                values = new TreeMap<>();

                Tree tree = new Tree(new Node(0, treeRaw.nextLong()));
                tree.populateTree(tree.root);
                tree.populateMap(tree.root);
                
                System.out.println("Case " + caseNumber++ + ":");
                for (Map.Entry<Long, Long> entry : values.entrySet()) {
                    System.out.print(entry.getValue() + " ");
                }
                System.out.println();
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}