package adapter;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 *
 * @author como
 * @param <K>
 * @param <V>
 */
public class HashMap<K,V> implements Map<K,V>{

    /**
     *
     * @return
     */
    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean containsKey(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean containsValue(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public V get(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param k
     * @param v
     * @return
     */
    @Override
    public V put(K k, V v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public V remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param map
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param key
     * @param defaultValue
     * @return
     */
    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return Map.super.getOrDefault(key, defaultValue); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param action
     */
    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        Map.super.forEach(action); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param function
     */
    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Map.super.replaceAll(function); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public V putIfAbsent(K key, V value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean remove(Object key, Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param key
     * @param oldValue
     * @param newValue
     * @return
     */
    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public V replace(K key, V value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param key
     * @param mappingFunction
     * @return
     */
    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param key
     * @param remappingFunction
     * @return
     */
    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param key
     * @param remappingFunction
     * @return
     */
    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param key
     * @param value
     * @param remappingFunction
     * @return
     */
    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     *
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V> Map<K,V> of() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    static <K,V> Map<K,V> of(K k1, V v1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param <K>
     * @param <V>
     * @param k1
     * @param v1
     * @param k2
     * @param v2
     * @return
     */
    public static <K,V> Map<K,V> of(K k1, V v1, K k2, V v2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param <K>
     * @param <V>
     * @param k1
     * @param v1
     * @param k2
     * @param v2
     * @param k3
     * @param v3
     * @return
     */
    public static <K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param <K>
     * @param <V>
     * @param k1
     * @param v1
     * @param k2
     * @param v2
     * @param k3
     * @param v3
     * @param k4
     * @param v4
     * @return
     */
    public static <K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param <K>
     * @param <V>
     * @param k1
     * @param v1
     * @param k2
     * @param v2
     * @param k3
     * @param v3
     * @param k4
     * @param v4
     * @param k5
     * @param v5
     * @return
     */
    public static <K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param <K>
     * @param <V>
     * @param k1
     * @param v1
     * @param k2
     * @param v2
     * @param k3
     * @param v3
     * @param k4
     * @param v4
     * @param k5
     * @param v5
     * @param k6
     * @param v6
     * @return
     */
    public static <K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param <K>
     * @param <V>
     * @param k1
     * @param v1
     * @param k2
     * @param v2
     * @param k3
     * @param v3
     * @param k4
     * @param v4
     * @param k5
     * @param v5
     * @param k6
     * @param v6
     * @param k7
     * @param v7
     * @return
     */
    public static <K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param <K>
     * @param <V>
     * @param k1
     * @param v1
     * @param k2
     * @param v2
     * @param k3
     * @param v3
     * @param k4
     * @param v4
     * @param k5
     * @param v5
     * @param k6
     * @param v6
     * @param k7
     * @param v7
     * @param k8
     * @param v8
     * @return
     */
    public static <K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param <K>
     * @param <V>
     * @param k1
     * @param v1
     * @param k2
     * @param v2
     * @param k3
     * @param v3
     * @param k4
     * @param v4
     * @param k5
     * @param v5
     * @param k6
     * @param v6
     * @param k7
     * @param v7
     * @param k8
     * @param v8
     * @param k9
     * @param v9
     * @return
     */
    public static <K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param <K>
     * @param <V>
     * @param k1
     * @param v1
     * @param k2
     * @param v2
     * @param k3
     * @param v3
     * @param k4
     * @param v4
     * @param k5
     * @param v5
     * @param k6
     * @param v6
     * @param k7
     * @param v7
     * @param k8
     * @param v8
     * @param k9
     * @param v9
     * @param k10
     * @param v10
     * @return
     */
    public static <K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9, K k10, V v10) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}