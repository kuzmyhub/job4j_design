package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            iterator.next();
            if (iterator.previousIndex() == index) {
                iterator.add(value);
                break;
            }
        }

    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        Objects.requireNonNull(filter);
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            T value = iterator.next();
            if (filter.test(value)) {
                iterator.remove();
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {

    }

    public static <T> void removeAll(List<T> list, List<T> elements) {

    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        ListIterator<Integer> it = list.listIterator();
        while (it.hasNext()) {
            Integer el = it.next();
            System.out.println(el);
        }
        while (it.hasPrevious()) {
            System.out.println(it.previous());
        }
    }
}
