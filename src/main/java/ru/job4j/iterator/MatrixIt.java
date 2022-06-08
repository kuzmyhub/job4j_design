package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (data.length == 0) {
            return false;
        }

        while (data[row].length == 0) {
            if (data.length == row + 1 && )
            row++;
        }

        if (data[row].length > column) {
            return true;
        } else if (data[row].length == column) {
            row++;
            column = 0;
            while (data[row].length == 0) {
                row++;
            }
            return true;
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}
