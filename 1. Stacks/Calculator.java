package org.friscoisd.k12.arham.stackcalc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by 142817 on 8/22/2018.
 */
public class Calculator {
    public static void main(String[] args) throws FileNotFoundException {
        Stack<Double> stack = new Stack<>();
        Scanner scanner = new Scanner(new File("calculator.dat"));
        while (scanner.hasNextLine()) {

            String calculation = scanner.nextLine();
            String[] calculationSplit = calculation.split(" ");
            for (String item : calculationSplit) {
                if (isOperator(item)) {
                    double item1 = stack.pop();
                    double item2 = stack.pop();
                    switch (item) {
                        case "+":
                            stack.push(item1 + item2);
                            break;
                        case "-":
                            stack.push(item2 - item1);
                            break;
                        case "*":
                            stack.push(item1 * item2);
                            break;
                        case "/":
                            stack.push(item2 / item1);
                            break;
                    }
                } else {
                    stack.push(Double.parseDouble(item));
                }
            }
            System.out.println(stack.pop());
            stack.clear();
        }
    }

    private static boolean isOperator(String input) {
        return input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/");
    }
}