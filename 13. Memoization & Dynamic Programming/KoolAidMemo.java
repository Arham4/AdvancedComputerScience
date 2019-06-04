import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class KoolAidMemo {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("koolaid.dat"))) {
            int cases = scanner.nextInt();
            for (int _ = 0; _ < cases; _++) {
                int bottlesAmount = scanner.nextInt();
                int[] bottles = new int[bottlesAmount];
                for (int i = 0; i < bottlesAmount; i++) {
                    bottles[i] = scanner.nextInt();
                }
                Map<Integer, Integer> dp = new HashMap<>();
                System.out.println(mcv(bottles, bottles.length - 1, dp));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int mcv(int[] volumes, int n, Map<Integer, Integer> dp) {
        int max;
        if (dp.containsKey(n)) {
            return dp.get(n);
        }
        if (n <= 0) {
            max = volumes[0];
        } else if (n == 1) {
            max = volumes[n - 1] < volumes[n] ? volumes[n - 1] + volumes[n] : Math.max(volumes[n - 1], volumes[n]);
        } else {
            max = Math.max(mcv(volumes, n - 2, dp) + volumes[n], getOptimalVolume(mcv(volumes, n - 1, dp), volumes, n));
        }
        dp.put(n, max);
        return max;
    }

    private static int getOptimalVolume(int value, int[] volumes, int n) {
        try {
            return volumes[n - 1] < volumes[n] ? value + volumes[n] : value;
        } catch(ArrayIndexOutOfBoundsException exception) {
            return volumes[n];
        }
    }
}
