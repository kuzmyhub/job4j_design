package ru.job4j.iterator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class ListUtilsTest {

    @Test
    public void wheAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIfValueIsEven() {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 3, 4));
        ListUtils.removeIf(input, n -> n % 2 == 0);
        assertThat(input, is(Arrays.asList(3)));
    }
}