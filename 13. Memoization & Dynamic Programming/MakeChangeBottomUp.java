import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ChangeBottomUp {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("change.dat"))) {
            int cases = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < cases; i++) {
                int denominationsAmount = scanner.nextInt();
                int[] denominations = new int[denominationsAmount];
                for (int i2 = 0; i2 < denominationsAmount; i2++) {
                    denominations[i2] = scanner.nextInt();
                }
                int amount = scanner.nextInt();
                int[][] dp = new int[denominationsAmount + 1][amount + 1];

                for (int row = 1; row < dp.length; row++) {
                    for (int col = 1; col < dp[row].length; col++) {
                        int value = denominations[row - 1];
                        if (row == 1) {
                            dp[row][col] = 1 + dp[row][col - value];
                        } else if (col < value) {
                            dp[row][col] = dp[row - 1][col];
                        } else {
                            dp[row][col] = Math.min(1 + dp[row][col - value], dp[row - 1][col]);
                        }
                    }
                }

                System.out.print(dp[dp.length - 1][dp[0].length - 1] + " ");
                Map<Integer, Integer> answer = new LinkedHashMap<>();
                for (int denomination : denominations) {
                    answer.put(denomination, 0);
                }
                interpretDPAsAnswer(dp.length - 1, dp[0].length - 1, dp, denominations, answer);
                answer.forEach((coin, frequency) -> System.out.print(frequency + " "));
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Map<Integer, Integer> interpretDPAsAnswer(int row, int col, int[][] dp, int[] denominations, Map<Integer, Integer> answer) {
        if (row <= 0 || col <= 0) {
            return answer;
        }
        int value = denominations[row - 1];
        if (row - 1 >= 1 && dp[row - 1][col] == dp[row][col]) {
            return interpretDPAsAnswer(row - 1, col, dp, denominations, answer);
        } else {
            answer.put(value, answer.getOrDefault(value, 0) + 1);
            interpretDPAsAnswer(row, col - value, dp, denominations, answer);
            return answer;
        }
    }
}
