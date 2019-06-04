import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class House {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("house.dat"))) {
            int cases = scanner.nextInt();
            for (int i = 0; i < cases; i++) {
                int lines = scanner.nextInt();
                scanner.nextLine();
                Node[][] grid = new Node[lines][lines + lines - 1];
                for (int i1 = 0; i1 < lines; i1++) {
                    int[] numbers = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                    int startingIndex = lines - i1 - 1;
                    for (int i2 = 0, i3 = 0; i2 < grid[i1].length && i3 < numbers.length; i2 += 2, i3++) {
                        if (startingIndex + i2 < grid[i1].length) {
                            grid[i1][startingIndex + i2] = new Node(numbers[i3], i1, startingIndex + i2);
                        }
                    }
                }
//                printPretty(grid);
//                Node root = populateTree(grid, new Node(grid[0][lines - 1], 0, lines - 1));
                System.out.println(traverseGraph(grid, 0, lines - 1, 0));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int traverseGraph(Node[][] grid, int row, int col, int sum) {
        Node node = getNumber(grid, row, col);
        if (node.max != Integer.MAX_VALUE) {
            return node.max;
        }

        int total = node.id + sum;
        int totalLeft = 0;
        Node left = getNumber(grid, row + 1, col - 1);
        if (left != null) {
            totalLeft = traverseGraph(grid, row + 1, col - 1, totalLeft);
        }

        int totalRight = 0;
        Node right = getNumber(grid, row + 1, col + 1);
        if (right != null) {
            totalRight = traverseGraph(grid, row + 1, col + 1, totalRight);
        }

        node.max = Math.max(total + totalLeft, total + totalRight);
        return node.max;
    }

    private static void printPretty(Node[][] grid) {
        for (Node[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
    }

    /*private static Node populateTree(int[][] grid, Node node) {
        Integer bottomLeft = getNumber(grid, node.row + 1, node.col - 1);
        if (bottomLeft != null) {
            node.left = populateTree(grid, new Node(bottomLeft, node.row + 1, node.col - 1));
        }
        Integer bottomRight = getNumber(grid, node.row + 1, node.col + 1);
        if (bottomRight != null) {
            node.right = populateTree(grid, new Node(bottomRight, node.row + 1, node.col + 1));
        }
        return node;
    }*/

    private static Node getNumber(Node[][] grid, int row, int col) {
        try {
            return grid[row][col];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    private static class Node {
        private int id;
        private int row;
        private int col;
        private int max = Integer.MAX_VALUE;

        Node(int id, int row, int col) {
            this.id = id;
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Node: " + id; //, Children: [" + left.id + ", " + right.id + "]";
        }
    }
}
