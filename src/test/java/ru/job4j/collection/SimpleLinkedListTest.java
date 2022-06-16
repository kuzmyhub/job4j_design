package ru.job4j.collection;

import java.util.Iterator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;

import static org.junit.Assert.*;

public class SimpleLinkedListTest {

    @Test
    public void whenAddAndGet() {
        LinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetFromOutOfFoundThenExceptionThrown() {
        LinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.get(2);
    }

    @Test
    public void whenAddIterHasNextTrue() {
        LinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
    }

    public void whenAddIterNextOne() {
        LinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenEmptyIterHashNextFalse() {
        LinkedList<Integer> list = new SimpleLinkedList<>();
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenAddIterMultiHasNextTrue() {
        LinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenAddIterNextOneNextTwo() {
        LinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> it = list.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenGetIteratorTwiceThenEveryFromBegin() {
        LinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> first = list.iterator();
        assertThat(first.hasNext(), is(true));
        assertThat(first.next(), is(1));
        assertThat(first.hasNext(), is(true));
        assertThat(first.next(), is(2));
        assertThat(first.hasNext(), is(false));
        Iterator<Integer> second = list.iterator();
        assertThat(second.hasNext(), is(true));
        assertThat(second.next(), is(1));
        assertThat(second.hasNext(), is(true));
        assertThat(second.next(), is(2));
        assertThat(second.hasNext(), is(false));
    }
}