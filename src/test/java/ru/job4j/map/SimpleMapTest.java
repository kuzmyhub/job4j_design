package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    SimpleMap<Integer, String> map = new SimpleMap<Integer, String>();

    @Test
    public void whenPutAndGetByKey() {
        map.put(1, "First pair");
        map.put(2, "Second pair");
        map.put(3, "Third pair");
        assertThat(map.get(1), is("First pair"));
        assertThat(map.get(2), is("Second pair"));
        assertThat(map.get(3), is("Third pair"));
    }

    @Test
    public void whenPutMoreThanDefaultCapacity() {
        map.put(1, "First pair");
        map.put(2, "Second pair");
        map.put(3, "Third pair");
        map.put(4, "Fourth pair");
        map.put(5, "Fifth pair");
        map.put(6, "Sixth pair");
        map.put(7, "Seventh pair");
        map.put(8, "Eight pair");
        map.put(9, "Ninth pair");
        assertThat(map.get(9), is("Ninth pair"));
    }

    @Test
    public void whenPutPairWithSameKeyAndDifferentValue() {
        map.put(1, "First pair");
        map.put(1, "Second pair");
        assertThat(map.get(1), is("First pair"));
    }

    @Test
    public void whenPutAndGetByIncorrectKey() {
        map.put(1, "First pair");
        assertNull(map.get(2));
    }

    @Test
    public void whenRemoveThanEmpty() {
        map.put(1, "First pair");
        map.remove(1);
        assertNull(map.get(1));
    }

    @Test
    public void whenRemoveThanMustNotBeEmpty() {
        map.put(1, "First pair");
        map.put(2, "Second pair");
        map.remove(1);
        assertThat(map.get(2), is("Second pair"));
    }

    @Test
    public void whenRemoveByIncorrectIndex() {
        map.put(1, "First pair");
        assertFalse(map.remove(2));
    }

    @Test
    public void whenIteratorHasNext() {
        map.put(1, "First pair");
        map.put(3, "Third pair");
        Iterator<Integer> it = map.iterator();
        assertTrue(it.hasNext());
    }

    @Test
    public void whenIteratorHasNextAndNext() {
        map.put(1, "First pair");
        map.put(3, "Third pair");
        Iterator<Integer> it = map.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is(1));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(3));
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorFromEndOfMap() {
        map.put(1, "First pair");
        Iterator<Integer> it = map.iterator();
        it.next();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenMapChangeIteratorException() {
        map.put(1, "First pair");
        Iterator<Integer> it = map.iterator();
        map.put(2, "Second pair");
        it.hasNext();
    }

}