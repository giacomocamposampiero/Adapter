package adapters;

import interfaces.HCollection;
import interfaces.HIterator;
import interfaces.HMap;
import interfaces.HSet;
import java.util.Hashtable;

/**
 * Implementation of Map interface Java 1.4.2.
 * @author Giacomo Camposampiero
 */
public class Map implements HMap {
    
    Hashtable table;

    public Map() {
        table = new Hashtable();
    }

    /**
     * Removes all of the mappings from this map.
     * The map will be empty after this call returns.
     */
    @Override
    public void clear() {
        table.clear();
    }

    /**
     * Returns true if this map contains a mapping for the specified key.  
     * More formally, returns true if and only if this map contains a mapping for a key k such that key.equals(k) is true (there can be at most one such mapping).
     * @param key key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     * @throws NullPointerException if the specified key is null
     */
    @Override
    public boolean containsKey(Object key) {
        if(key == null) throw new NullPointerException();
        return table.containsKey(key);
    }

    /**
     * Returns true if this map maps one or more keys to the specified value.  
     * More formally, returns true if and only if this map contains at least one mapping to a value v such that value.equals(v) is true.
     * @param value value whose presence in this map is to be tested
     * @return true if this map maps one or more keys to the specified value
     * @throws NullPointerException if the specified value is null 
     */
    @Override
    public boolean containsValue(Object value) {
        if(value == null) throw new NullPointerException();
        return table.containsValue(value);
    }

    /**
     * Returns a Set view of the mappings contained in this map.
     * The set is backed by the map, so changes to the map are reflected in the set, and vice-versa.  If the map is modified while an iteration over the set is in progress (except through the iterator's own remove operation, or through the setValue operation on a map entry returned by the iterator) the results of the iteration are undefined.  
     * The set supports element removal, which removes the corresponding mapping from the map, via the Iterator.remove, Set.remove, removeAll, retainAll and clear operations.  
     * It does not support the add or addAll operations.
     * @return a set view of the mappings contained in this map
     */
    @Override
    public HSet entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Compares the specified object with this map for equality.  
     * Returns  true if the given object is also a map and the two maps represent the same mappings.  
     * More formally, two maps m1 and  m2 represent the same mappings if m1.entrySet().equals(m2.entrySet()).  
     * This ensures that the equals method works properly across different implementations of the Map interface.
     * @param obj object to be compared for equality with this map
     * @return true if the specified object is equal to this map
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof HMap)) {
            return false;
        }
        HMap other = (HMap) obj;
        return other.entrySet().equals(entrySet());
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     * More formally, if this map contains a mapping from a key k to a value v such that key.equals(k), then this method returns v; otherwise it returns null (there can be at most one such mapping.)
     * The map doesn't allow null values, so a null return value explicitly represent the absence of the specified key in the map.
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
     * @throws NullPointerException if the specified key is null
     */
    @Override
    public Object get(Object key) {
        if(key == null) throw new NullPointerException();
        return table.get(key);
    }
    
    /**
     * Returns the hash code value for this map.  
     * The hash code of a map is defined to be the sum of the hash codes of each entry in the map's entrySet() view.  
     * This ensures that m1.equals(m2) implies that m1.hashCode()==m2.hashCode() for any two maps  m1 and m2, as required by the general contract of Object.hashCode
     * @return the hash code value for this map
     */
    @Override
    public int hashCode() {
        return entrySet().hashCode();
    }

    /**
     * Returns true if this map contains no key-value mappings.
     * @return true if this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return table.isEmpty();
    }

    /**
     * Returns a Set view of the keys contained in this map.
     * The set is backed by the map, so changes to the map are reflected in the set, and vice-versa. If the map is modified while an iteration over the set is in progress (except through the iterator's own remove operation), the results of the iteration are undefined.  
     * The set supports element removal, which removes the corresponding mapping from the map, via the Iterator.remove, Set.remove, removeAll, retainAll, and clear operations.  It does not support the add or addAll operations.
     * @return a set view of the keys contained in this map
     */
    @Override
    public HSet keySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced by the specified value (a map m is said to contain a mapping for a key k if and only if m.containsKey(k) would return true.)
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with key, or null if there was no mapping for key (null elements are not allowed, so a null return value means only that the specified key wasn't contained in the map).
     * @throws NullPointerException if the specified key or value is null
     */
    @Override
    public Object put(Object key, Object value) {
        if(key == null || value == null) throw new NullPointerException();
        return table.put(key, value);
    }

    /**
     * Copies all of the mappings from the specified map to this map.
     * The effect of this call is equivalent to that of calling put(Object,Object) put(k, v) on this map once for each mapping from key k to value v in the specified map.  
     * The behavior of this operation is undefined if the specified map is modified while the operation is in progress.
     * @param t mappings to be stored in this map
     * @throws NullPointerException if the specified map is null
     * @throws NullPointerException if the specified map contains null keys or values
     */
    @Override
    public void putAll(HMap t) {
        if(t == null ) throw new NullPointerException();
        HIterator it = t.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * More formally, if this map contains a mapping from key k to value v such that key.equals(k) is true, that mapping is removed (the map can contain at most one such mapping.)
     * Returns the value to which this map previously associated the key, or null if the map contained no mapping for the key.
     * The map doesn't allow null values, so a null return value explicitly represent the absence of the specified key in the map.
     * The map will not contain a mapping for the specified key once the call returns.
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null if there was no mapping for key.
     * @throws NullPointerException if the specified key is null 
     */
    @Override
    public Object remove(Object key) {
        if(key == null ) throw new NullPointerException();
        return table.remove(key);
    }

    /**
     * Returns the number of key-value mappings in this map. 
     * @return the number of key-value mappings in this map
     */
    @Override
    public int size() {
        return table.size();
    }

    /**
     * Returns a Collection view of the values contained in this map.
     * The collection is backed by the map, so changes to the map are reflected in the collection, and vice-versa.  
     * If the map is modified while an iteration over the collection is in progress (except through the iterator's own remove operation), the results of the iteration are undefined.  
     * The collection supports element removal, which removes the corresponding mapping from the map, via the Iterator.remove, Collection.remove, removeAll, retainAll and clear operations. It does not  support the add or addAll operations.
     * @return a collection view of the values contained in this map
     */
    @Override
    public HCollection values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public class MapEntry implements Map.Entry {
        
        private final Object key;
        private Object value;

        public MapEntry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Compares the specified object with this entry for equality.
         * Returns true if the given object is also a map entry and the two entries represent the same mapping.  
         * More formally, two entries e1 and e2 represent the same mapping if
         *     <pre>e1.getKey().equals(e2.getKey()) && e1.getValue().equals(e2.getValue()).</pre>
         * This ensures that the equals method works properly across different implementations of the Map.Entry interface.
         * @param o object to be compared for equality with this map entry
         * @return true if the specified object is equal to this map entry
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null) {
                return false;
            }
            if (!(o instanceof HMap.Entry)) {
                return false;
            }
            HMap.Entry other = (HMap.Entry) o;
            return other.getKey().equals(key) && other.getValue().equals(value);
        }
        
        /**
         * Returns the key corresponding to this entry.
         * @return the key corresponding to this entry
         * @throws IllegalStateException implementations throw this exception if the entry has been removed from the backing map.
         */
        @Override
        public Object getKey() {
            return key;
        }

        /**
         * Returns the value corresponding to this entry.  
         * If the mapping has been removed from the backing map (by the iterator's remove operation), the results of this call is the .
         * @return the value corresponding to this entry
         * @throws IllegalStateException implementations throw this exception if the entry has been removed from the backing map.
         */
        @Override
        public Object getValue() {
            return value;
        }

        /**
         * Replaces the value corresponding to this entry with the specified value.  
         * The behavior of this call is undefined if the mapping has already been removed from the map (by the iterator's <tt>remove</tt> operation).
         * @param value new value to be stored in this entry
         * @return old value corresponding to the entry
         * @throws NullPointerException if  the specified value is null
         * @throws IllegalStateException implementations throw this exception if the entry has been removed from the backing map.
         */
        @Override
        public Object setValue(Object value) {
            if(value == null) throw new NullPointerException();
            Object old = value;
            this.value = value;
            return old;
        }

        /**
         * Returns the hash code value for this map entry.  
         * The hash code of a map entry e is defined to be: <pre>
         *     e.getKey().hashCode()) ^ e.getValue().hashCode())</pre>
         * This ensures that e1.equals(e2) implies that e1.hashCode()==e2.hashCode() for any two Entries e1 and e2, as required by the general contract of Object.hashCode.
         * @return the hash code value for this map entry
         */
        @Override
        public int hashCode() {
            return key.hashCode() ^ value.hashCode();
        }
        
    }
    
}