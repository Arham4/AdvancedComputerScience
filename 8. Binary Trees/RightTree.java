import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RightTree {
    private static int count = 0;
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("righttree.dat"))) {
            int cases = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < cases; i++) {
                String treeInput = scanner.nextLine();
                String[] tree = new String[treeInput.length() + 1];
                for (int i1 = 0; i1 < treeInput.length(); i1++) {
                    tree[i1 + 1] = String.valueOf(treeInput.charAt(i1));
                }
                System.out.println(output(isRightTree(tree, 1)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String output(boolean rightTree) {
        return rightTree ? "Tree " + ++count + " is a right-tree." : "Tree " + ++count + " is not a right-tree.";
    }

    private static boolean isRightTree(String[] tree, int index) {
        int left = index * 2;
        int right = index * 2 + 1;
        if (right > tree.length - 1 && left == tree.length - 1) {
            return false;
        }
        if (left > tree.length - 1) {
            return true;
        }
        int leftCount = getCount(tree, index * 2, 0);
        int rightCount = getCount(tree, index * 2 + 1, 0);
        return rightCount >= leftCount && isRightTree(tree, index * 2) && isRightTree(tree, index * 2 + 1);
    }

    private static int getCount(String[] tree, int index, int count) {
        if (index > tree.length - 1) {
            return count;
        }
        if (tree[index].equals("1")) {
            return 1 + getCount(tree, index * 2, count) + getCount(tree, index * 2 + 1, count);
        }
        return count;
    }
}
