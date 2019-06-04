import java.io.*;
import java.util.*;

public class House {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("house.dat"))) {
            int cases = scanner.nextInt();
            for (int i = 0; i < cases; i++) {
                int lines = scanner.nextInt();
                scanner.nextLine();
                int[][] grid = new int[lines][lines + lines - 1];
                for (int i1 = 0; i1 < lines; i1++) {
                    int[] numbers = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                    int startingIndex = lines - i1 - 1;
                    for (int i2 = 0, i3 = 0; i2 < grid[i1].length && i3 < numbers.length; i2 += 2, i3++) {
                        if (startingIndex + i2 < grid[i1].length) {
                            grid[i1][startingIndex + i2] = numbers[i3];
                        }
                    }
                }
                int[][] dp = new int[grid.length][grid[0].length];
                for(int r = 0; r < dp.length; r++) {
                    dp[r] = grid[r].clone();
                }
                for (int r = dp.length - 1; r >= 0; r--) {
                    for (int c = 0; c < dp[r].length; c++) {
                        int topLeft = getGrid(grid, r - 1, c - 1) + dp[r][c];
                        int topRight = getGrid(grid, r - 1, c + 1) + dp[r][c];
                        
                        try {
                            dp[r - 1][c - 1] = Math.max(dp[r - 1][c - 1], topLeft);
                        } catch (ArrayIndexOutOfBoundsException e) { }
                        try {
                            dp[r - 1][c + 1] = Math.max(dp[r - 1][c + 1], topRight);
                        } catch (ArrayIndexOutOfBoundsException e) { }
                        
                    }
                }
                System.out.println(dp[0][lines - 1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int getGrid(int[][] grid, int y, int x) {
        try {
            return grid[y][x];
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }
}
