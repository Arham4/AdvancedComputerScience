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
                for (int row = 1; row < dp.length; row++) {
                    for (int col = 1; col < dp[row].length; col++) {
						int value = items[row - 1][1];
						int weight = items[row - 1][0];
						if (col < weight) {
							dp[row][col] = Math.max(0, dp[row - 1][col]);
						} else {
							int left = 0;
							int top = 0;
							if (col - weight >= 1) {
								left = dp[row][col - weight];
							}
							if (row - 1 >= 1) {
								top = dp[row - 1][col];
							}
							dp[row][col] = Math.max(value + left, top);
						}
                    }
                }
                System.out.println(dp[dp.length - 1][dp[0].length - 1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
