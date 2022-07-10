package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size = 0;

    private Node<E> first;

    private Node<E> last;

    private int modCount = 0;

    private static class Node<E> {
        Node<E> prev;
        E item;
        Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.item = element;
            this.next = next;
        }
    }

    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<E>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x.item;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {

            private final int expectedModCount = modCount;

            private Node<E> tmp = new Node<>(null, null, first);

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return tmp.next != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                tmp = tmp.next;
                return tmp.item;
            }
        };
    }
}
