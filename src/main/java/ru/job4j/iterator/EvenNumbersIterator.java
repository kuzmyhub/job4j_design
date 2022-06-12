package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] data;
    private int indexHasNext = 0;
    private int indexNext = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean condition = false;
        for (int i = indexHasNext; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                condition = true;
                indexHasNext = i + 1;
                break;
            }
        }
        if (!condition) {
            indexHasNext++;
        }

        for (int i = indexNext; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                indexNext = i;
                break;
            }
        }
        return condition;
    }

    @Override
    public Integer next() {
        if (indexHasNext != 0) {
            indexHasNext--;
        }
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[indexNext++];
    }
}
