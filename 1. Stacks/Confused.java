package org.friscoisd.k12.arham.stackcalc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by 142817 on 8/24/2018.
 */
public class Confused {
    public static void main(String[] args) throws FileNotFoundException {
        Stack<String> stack = new Stack<>();
        Scanner scanner = new Scanner(new File("confused.dat"));
        while (scanner.hasNextLine()) {
            String next = scanner.next();
            String[] chars = next.split("");
            boolean success = true;
            for (String aChar : chars) {
                if (isOpener(aChar)) {
                    stack.push(aChar);
                } else {
                    String item = stack.pop();
                    if (item == null) {
                        System.out.println("No");
                        success = false;
                        break;
                    }
                }
            }
            if (!stack.isEmpty()) {
                System.out.println("No");
                success = false;
            }
            if (success) {
                System.out.println("Yes");
            }
            stack.clear();
        }
    }

    private static boolean isOpener(String operand) {
        return operand.equals("(") || operand.equals("[");
    }
}