package com.isvaso.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class OptionalMap<K, V> {

    private final Map<K, V> map = new HashMap<>();

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public boolean containsValue(V value) {
        return map.containsValue(value);
    }

    public Optional<V> get(K key) {
        return Optional.ofNullable(map.get(key));
    }

    public V put(K key, V value) {
        return map.put(key, value);
    }

    public V remove(K key) {
        return map.remove(key);
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }

    public void clear() {
        map.clear();
    }

    public Set<K> keySet() {
        return map.keySet();
    }
}
