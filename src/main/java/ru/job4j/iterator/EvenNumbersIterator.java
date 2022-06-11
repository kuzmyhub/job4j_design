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
        for (int i = indexHasNext; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                break;
            }
        }
        indexHasNext++;
        if (indexHasNext - 1 > data.length - 1) {
            return false;
        }
        return data[indexHasNext - 1] % 2 == 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        for (int i = indexNext; i < data.length; i++) {
            indexNext++;
            if (data[i] % 2 == 0) {
                break;
            }
        }
        return data[indexNext - 1];
    }
}
