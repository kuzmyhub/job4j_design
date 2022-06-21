package ru.job4j.map;

import java.util.Iterator;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        int i = indexFor(hash(key.hashCode()));
        MapEntry<K, V> p = table[i];
        MapEntry<K, V> e = null;
        if (p == null) {
            table[i] = new MapEntry<>(key, value);
            rsl = true;
        } else if (hash(p.key.hashCode()) == hash(key.hashCode())
        && (p.key == key || (key != null && key.equals(p.key)))) {
            p.value = value;
            rsl = true;
        }
        return rsl;
    }

    public int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 8);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expend() {

    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public boolean remove(K key) {
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
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
