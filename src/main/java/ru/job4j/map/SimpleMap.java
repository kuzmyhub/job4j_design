package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        float loadFactor = (float) count / capacity;
        if (loadFactor >= LOAD_FACTOR) {
            expend();
        }
        boolean rsl = false;
        int i = indexFor(hash(key.hashCode()));
        MapEntry<K, V> p = table[i];
        if (p == null) {
            table[i] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    public int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expend() {
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity * 2];
        capacity = table.length;
        for (int i = 0; i < oldTable.length; i++) {
            MapEntry<K, V> e = oldTable[i];
            if (e != null) {
                table[indexFor(hash(e.key.hashCode()))] = e;
            }
        }
    }

    @Override
    public V get(K key) {
        V value = null;
        MapEntry<K, V> pair = table[indexFor(hash(key.hashCode()))];
        if (pair != null) {
            if (pair.key.hashCode() == key.hashCode()
                    && pair.key.equals(key)) {
                value = pair.value;
            }
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        MapEntry<K, V> pair = table[indexFor(hash(key.hashCode()))];
        if (pair != null) {
            if (pair.key.hashCode() == key.hashCode()
                    && pair.key.equals(key)) {
                table[indexFor(hash(key.hashCode()))] = null;
                rsl = true;
            }
        }
        modCount--;
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {

            int index = 0;

            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
