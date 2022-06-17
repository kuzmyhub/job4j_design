package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();

    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        while (!in.isEmpty()) {
            out.push(in.pop());
        }
        T value = out.pop();
        while (!out.isEmpty()) {
            in.push(out.pop());
        }
        return value;
    }

    public void push(T value) {
        in.push(value);
    }
}
