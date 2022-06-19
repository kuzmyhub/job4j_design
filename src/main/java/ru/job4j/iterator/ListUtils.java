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
        Objects.requireNonNull(filter);
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            int index = iterator.previousIndex();
            if (filter.test(element)) {
                list.set(index, value);
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> listIterator = list.listIterator();
        ListIterator<T> elementsIterator = list.listIterator();
        while (listIterator.hasNext()) {
            T value = listIterator.next();
            while (elementsIterator.hasNext()) {
                if (value == elementsIterator.next()) {
                    listIterator.remove();
                }
            }
        }
    }
}
