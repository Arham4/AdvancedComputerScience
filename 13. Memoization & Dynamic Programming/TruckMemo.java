import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class TruckMemo {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("truck.dat"))) {
            int cases = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < cases; i++) {
                int capacity = scanner.nextInt();
                int itemsCount = scanner.nextInt();
                int[][] items = new int[itemsCount][2];
                for (int i1 = 0; i1 < itemsCount; i1++) {
                    items[i1][0] = scanner.nextInt();
                    items[i1][1] = scanner.nextInt();
                }
                int[][] dp = new int[itemsCount + 1][capacity + 1];
                for (int[] ints : dp) {
                    Arrays.fill(ints, -1);
                }
				f(dp.length - 1, dp[0].length - 1, dp, items);
                System.out.println(dp[dp.length - 1][dp[0].length - 1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int f(int i, int w, int[][] dp, int[][] items) {
        if (i == 0 || w == 0) {
            return 0;
        }
        if (getDp(i, w, dp) >= 0) {
            return getDp(i, w, dp);
        }
        int value = items[i - 1][1];
        int weight = items[i - 1][0];
        if (w < weight) {
            dp[i][w] = f(i - 1, w, dp, items);
        } else {
            dp[i][w] = Math.max(value + f(i, w - weight, dp, items), f(i - 1, w, dp, items));
        }
        return dp[i][w];
    }

    private static int getDp(int row, int col, int[][] dp) {
        try {
            return dp[row][col];
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }
}
