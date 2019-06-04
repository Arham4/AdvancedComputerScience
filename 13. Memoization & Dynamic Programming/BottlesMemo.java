import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Bottles {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("bottles.dat"))) {
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
        int min;
        if (dp.containsKey(n)) {
            return dp.get(n);
        }
        if (n <= 0) {
            min = volumes[0];
        } else if (n == 1) {
            min = Math.max(volumes[0], volumes[1]);
        } else {
            min = Math.max(mcv(volumes, n - 2, dp) + volumes[n], mcv(volumes, n - 1, dp));
        }
        dp.put(n, min);
        return min;
    }
}
