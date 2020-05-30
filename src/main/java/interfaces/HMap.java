package interfaces;

/**
 * Map interface Java 1.4.2.
 * @author Giacomo Camposampiero
 */
public interface HMap {

    /**
     * Removes all mappings from this map (optional operation).
     */
    void clear();

    /**
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key key of the element to check
     * @return true if the key is contained, false otherwise
     */
    boolean containsKey(Object key);

    /**
     * Returns true if this map maps one or more keys to the specified value.
     *
     * @param value value whose presence in this map is to be tested
     * @return true if this map maps one or more keys to the specified value
     */
    boolean containsValue(Object value);

    /**
     * Returns a set view of the mappings contained in this map.
     *
     * @return a set view of the mappings contained in this map.
     */
    HSet entrySet();

    /**
     * Compares the specified object with this map for equality
     *
     * @param o object to be compared for equality with this map.
     * @return true if the specified object is equal to this map.
     */
    @Override
    boolean equals(Object o);

    /**
     * Returns the value to which this map maps the specified key.
     *
     * @param key key whose associated value is to be returned.
     * @return the value to which this map maps the specified key, or null if
     * the map contains no mapping for this key.
     */
    Object get(Object key);

    /**
     * Returns the hash code value for this map.
     *
     * @return the hash code value for this map
     */
    @Override
    int hashCode();

    /**
     * Returns true if this map contains no key-value mappings.
     *
     * @return true if this map contains no key-value mappings.
     */
    boolean isEmpty();

    /**
     * Returns a set view of the keys contained in this map.
     *
     * @return a set view of the keys contained in this map.
     */
    HSet keySet();

    /**
     * Associates the specified value with the specified key in this map.
     *
     * @param key key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     * @return previous value associated with specified key, or null if there
     * was no mapping for key. A null return can also indicate that the map
     * previously associated null with the specified key, if the implementation
     * supports null values.
     */
    Object put(Object key, Object value);

    /**
     * Copies all of the mappings from the specified map to this map (optional
     * operation).
     *
     * @param t Mappings to be stored in this map.
     */
    void putAll(HMap t);

    /**
     * Removes the mapping for this key from this map if it is present (optional
     * operation).
     *
     * @param key key whose mapping is to be removed from the map.
     * @return previous value associated with specified key, or null if there
     * was no mapping for key.
     */
    Object remove(Object key);

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map.
     */
    int size();

    /**
     * Returns a collection view of the values contained in this map.
     *
     * @return a collection view of the values contained in this map.
     */
    HCollection values();

    
    /**
     * Map entry (key-value pair).
     */
    public interface Entry {

        /**
         * Compares the specified object with this entry for equality.
         * @param o  object to be compared for equality with this map entry. 
         * @return  true if the specified object is equal to this map entry.
         */
        @Override
        boolean equals(Object o);

        /**
         * Returns the key corresponding to this entry.
         * @return  the key corresponding to this entry.
         */
        Object getKey();

        /**
         * Returns the value corresponding to this entry.
         * @return  the value corresponding to this entry.
         */
        Object getValue();

        /**
         * Returns the hash code value for this map entry.
         * @return the hash code value for this map entry.
         */
        @Override
        int hashCode();

        /**
         * Replaces the value corresponding to this entry with the specified value (optional operation).
         * @param value  new value to be stored in this entry. 
         * @return  old value corresponding to the entry. 
         */
        Object setValue(Object value);

    }

}