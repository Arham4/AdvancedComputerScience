import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KoolAidBottomUp {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("koolaid.dat"))) {
            int cases = scanner.nextInt();
            for (int _ = 0; _ < cases; _++) {
                int bottlesAmount = scanner.nextInt();
                int[] bottles = new int[bottlesAmount];
                for (int i = 0; i < bottlesAmount; i++) {
                    bottles[i] = scanner.nextInt();
                }
                int[] dp = new int[bottles.length];
                System.out.println(mcv(bottles, bottles.length - 1, dp));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int mcv(int[] volumes, int n, int[] dp) {
        dp[0] = volumes[0];
        dp[1] = volumes[0] < volumes[1] ? volumes[0] + volumes[1] : Math.max(volumes[0], volumes[1]);
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 2] + volumes[i], getOptimalVolume(dp[i - 1], volumes, i));
        }
        return dp[n];
    }

    private static int getOptimalVolume(int value, int[] volumes, int n) {
        try {
            return volumes[n - 1] < volumes[n] ? value + volumes[n] : value;
        } catch(ArrayIndexOutOfBoundsException exception) {
            return volumes[n];
        }
    }
}