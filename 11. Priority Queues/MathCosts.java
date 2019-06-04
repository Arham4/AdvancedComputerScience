import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MathCosts {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("mathCosts.dat"));
        while (scanner.hasNextLine()) {
            int cases = scanner.nextInt();
            if (cases == 0) {
                break;
            }
            int[] numbers = new int[cases];
            for (int i = 0; i < cases; i++) {
                numbers[i] = scanner.nextInt();
            }
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            for (int number : numbers) {
                queue.add(number);
            }
            int cost = 0;
            while (queue.size() != 1) {
                int numberOne = queue.poll();
                int numberTwo = queue.poll();
                int newSum = numberOne + numberTwo;
                queue.add(newSum);
                cost += newSum;
            }
            System.out.println(cost);
        }
    }
}