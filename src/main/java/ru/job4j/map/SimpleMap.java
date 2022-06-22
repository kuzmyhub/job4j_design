package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int fullness = 0;
        for (MapEntry<K, V> m : table) {
            if (m != null) {
                fullness++;
            }
        }
        float loadFactor = (float) fullness / capacity;
        if (loadFactor >= LOAD_FACTOR) {
            expend();
        }
        boolean rsl = false;
        int i = indexFor(hash(key.hashCode()));
        MapEntry<K, V> p = table[i];
        if (p == null) {
            table[i] = new MapEntry<>(key, value);
            rsl = true;
        } else if (hash(p.key.hashCode()) == hash(key.hashCode())
        && (p.key == key || key.equals(p.key))) {
            p.value = value;
            rsl = true;
        }
        modCount++;
        return rsl;
    }

    public int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 8);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expend() {
        MapEntry<K, V>[] oldTable = table;
        int oldCap = oldTable.length;
        int newCap = oldCap * 2;
        MapEntry<K, V>[] newTable = new MapEntry[newCap];
        for (int i = 0; i < oldCap; i++) {
            MapEntry<K, V> e = oldTable[i];
            if (e != null) {
                newTable[hash(e.key.hashCode()) & (newCap - 1)] = e;
            }
        }
        capacity = newTable.length;
        table = newTable;
    }

    @Override
    public V get(K key) {
        V value = null;
        MapEntry<K, V> pair = table[hash(key.hashCode())];
        if (table != null && table.length > 0 && pair != null) {
            if (hash(pair.key.hashCode()) == hash(key.hashCode())
                    && (pair.key == key || pair.key.equals(key))) {
                value = pair.value;
            }
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        MapEntry<K, V> pair = table[hash(key.hashCode())];
        if (table != null && table.length > 0 && pair != null) {
            if (hash(pair.key.hashCode()) == hash(key.hashCode())
                    && (pair.key == key || pair.key.equals(key))) {
                table[hash(key.hashCode())] = null;
                rsl = true;
            }
        }
        modCount++;
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
                return table[index].key;
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
