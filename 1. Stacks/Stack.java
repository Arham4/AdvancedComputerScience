package org.friscoisd.k12.arham.stackcalc;

/**
 * Created by 142817 on 8/22/2018.
 */
public class Stack<T> implements IStack {
    private int top;
    private T[] stack;
    private int size;

    public Stack() {
        top = 0;
        size = 50;
        stack = (T[]) new Object[size];
    }

    public Stack(int size) {
        top = 0;
        this.size = size;
        stack = (T[]) new Object[size];
    }

    @Override
    public void push(Object value) {
        if (isFull()) {
            return;
        }
        stack[top++] = (T) value;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        return stack[--top];
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return stack[top];
    }

    @Override
    public int size() {
        return top;
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    @Override
    public boolean isFull() {
        return top == size - 1;
    }

    @Override
    public void clear() {
        top = 0;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = top - 1; i >= 0; i--) {
            output.append("\n").append(stack[i]);
        }
        return output.toString();
    }
}
