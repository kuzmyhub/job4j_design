package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] data;
    private int index = 0;
    private int i = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean condition = false;
        i = index;
        while (i < data.length) {
            if (data[i] % 2 == 0) {
                index = i;
                condition = true;
                break;
            }
            i++;
        }
        return condition;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}
