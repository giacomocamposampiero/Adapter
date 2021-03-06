package adapters;

import interfaces.HCollection;
import interfaces.HIterator;
import interfaces.HMap;
import interfaces.HSet;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;

/**
 * Implementation of Map interface Java 1.4.2.
 * @author Giacomo Camposampiero
 */
public class Map implements HMap {
    
    Hashtable table;

    /**
     * Empty constructor.
     * This constructor will initialize a new empty map.
     */
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
     * More formally, returns true if and only if this map contains a mapping 
     * for a key k such that key.equals(k) is true (there can be at most one 
     * such mapping).
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
     * More formally, returns true if and only if this map contains at least one 
     * mapping to a value v such that value.equals(v) is true.
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
     * The set is backed by the map, so changes to the map are reflected in the 
     * set, and vice-versa.  If the map is modified while an iteration over the 
     * set is in progress (except through the iterator's own remove operation, 
     * or through the setValue operation on a map entry returned by the iterator)
     * the results of the iteration are undefined (the iterator generated by the
     * set could be no longer consistent with the map real content). Except for
     * the iterator, other structural changes on the map which are not performed
     * throug the returned set does not effect the validity of the returned set.
     * 
     * The set supports element removal, which removes the corresponding mapping
     * from the map, via the Iterator.remove, Set.remove, removeAll, retainAll 
     * and clear operations. It does not support the add or addAll operations.
     * @return a set view of the mappings contained in this map
     */
    @Override
    public HSet entrySet() {
        return new EntrySet();
    }
    
    /**
     * Compares the specified object with this map for equality.  
     * Returns  true if the given object is also a map and the two maps 
     * represent the same mappings. More formally, two maps m1 and  m2 represent
     * the same mappings if m1.entrySet().equals(m2.entrySet()).  
     * 
     * This ensures that the equals method works properly across different 
     * implementations of the Map interface.
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
     * Returns the value to which the specified key is mapped, or null if this 
     * map contains no mapping for the key.
     * More formally, if this map contains a mapping from a key k to a value v 
     * such that key.equals(k), then this method returns v; otherwise it returns
     * null (there can be at most one such mapping.)
     * 
     * The map doesn't allow null values, so a null return value explicitly 
     * represent the absence of the specified key in the map.
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this 
     *         map contains no mapping for the key
     * @throws NullPointerException if the specified key is null
     */
    @Override
    public Object get(Object key) {
        if(key == null) throw new NullPointerException();
        return table.get(key);
    }
    
    /**
     * Returns the hash code value for this map.  
     * The hash code of a map is defined to be the sum of the hash codes of each
     * entry in the map's entrySet() view.  
     * 
     * This ensures that m1.equals(m2) implies that m1.hashCode()==m2.hashCode()
     * for any two maps  m1 and m2, as required by the general contract of 
     * Object.hashCode
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
     * The set is backed by the map, so changes to the map are reflected in the 
     * set, and vice-versa. If the map is modified while an iteration over the 
     * set is in progress (except through the iterator's own remove operation), 
     * the results of the iteration are undefined (the iterator generated by the
     * set could be no longer consistent with the map real content). Except for
     * the iterator, other structural changes on the map which are not performed
     * throug the returned set does not effect the validity of the returned set. 
     * 
     * The set supports element removal, which removes the corresponding mapping
     * from the map, via the Iterator.remove, Set.remove, removeAll, retainAll, 
     * and clear operations.  It does not support the add or addAll operations.
     * @return a set view of the keys contained in this map
     */
    @Override
    public HSet keySet() {
        return new ElementSet(true);
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is 
     * replaced by the specified value (a map m is said to contain a mapping for
     * a key k if and only if m.containsKey(k) would return true.)
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with key, or null if there was no 
     *         mapping for key (null elements are not allowed, so a null return 
     *         value means only that the specified key wasn't contained in the map).
     * @throws NullPointerException if the specified key or value is null
     */
    @Override
    public Object put(Object key, Object value) {
        if(key == null || value == null) throw new NullPointerException();
        return table.put(key, value);
    }

    /**
     * Copies all of the mappings from the specified map to this map.
     * The effect of this call is equivalent to that of calling put(obj,obj) 
     * put(k, v) on this map once for each mapping from key k to value v in the 
     * specified map. The behavior of this operation is undefined if the 
     * specified map is modified while the operation is in progress.
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
     * More formally, if this map contains a mapping from key k to value v such 
     * that key.equals(k) is true, that mapping is removed (the map can contain 
     * at most one such mapping.)
     * Returns the value to which this map previously associated the key, or null
     * if the map contained no mapping for the key.
     * 
     * The map doesn't allow null values, so a null return value explicitly 
     * represent the absence of the specified key in the map.
     * The map will not contain a mapping for the specified key once the call returns.
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null if there was no 
     *         mapping for key.
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
     * The collection is backed by the map, so changes to the map are reflected 
     * in the collection, and vice-versa. If the map is modified while an 
     * iteration over the collection is in progress (except through the iterator's
     * own remove operation), the results of the iteration are undefined (the 
     * iterator generated by the collection could be no longer consistent with 
     * the map real content). Except for the iterator, other structural changes 
     * on the map which are not performed throug the returned collection does not 
     * effect the validity of the returned set. 
     * 
     * The collection supports element removal, which removes the corresponding 
     * mapping from the map, via the Iterator.remove, Collection.remove, 
     * removeAll, retainAll and clear operations. It does not  support the add 
     * or addAll operations.
     * @return a collection view of the values contained in this map
     */
    @Override
    public HCollection values() {
        return new ElementSet(false);
    }
    
    public class MapEntry implements HMap.Entry {
        
        private final Object key;
        private Object value;

        public MapEntry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Compares the specified object with this entry for equality.
         * Returns true if the given object is also a map entry and the two 
         * entries represent the same mapping.  
         * More formally, two entries e1 and e2 represent the same mapping if
         * <pre>e1.getKey().equals(e2.getKey()) && e1.getValue().equals(e2.getValue()).</pre>
         * This ensures that the equals method works properly across different 
         * implementations of the Map.Entry interface.
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
         */
        @Override
        public Object getKey() {
            return key;
        }

        /**
         * Returns the value corresponding to this entry.
         * @return the value corresponding to this entry
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
         */
        @Override
        public Object setValue(Object value) {
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
    
    class EntrySet implements HSet {
        
        public EntrySet() {
        }
        
        @Override
        public boolean add(Object e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean addAll(HCollection c) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void clear() {
            table.clear();
        }
        
        @Override
        public boolean contains(Object o) {
            if(o == null) throw new NullPointerException();
            if(!o.getClass().equals(adapters.Map.MapEntry.class)) throw new IllegalArgumentException();
            Map.Entry entry = (Map.Entry) o;
            return table.containsKey(entry.getKey()) && table.get(entry.getKey()).equals(entry.getValue());
        }

        @Override
        public boolean containsAll(HCollection c) {
            boolean contained = true;
            HIterator it = c.iterator();
            while(it.hasNext()) {
                contained &= contains(it.next());
            }
            return contained;
        }
            
        @Override 
        public boolean equals(Object o) {
            if (o == this) return true; 
            if (!(o.getClass().equals(this.getClass()))) { 
                return false; 
            }    
            EntrySet other = (EntrySet) o;
            if (other.size() != size()) return false;
            HIterator it = iterator();
            while(it.hasNext())
                if(!other.contains(it.next())) 
                    return false;
            return true;
        }

        @Override 
        public int hashCode() {
            int hash = 0;
            HIterator it = iterator();
            while(it.hasNext()) 
                hash += it.next().hashCode();
            return hash;
        }

        @Override
        public boolean isEmpty() {
            return table.isEmpty();
        }

        @Override
        public HIterator iterator() {
            return new EntrySetIterator();
        }

        @Override
        public boolean remove(Object o) {
            if(o == null) throw new NullPointerException();
            if(!o.getClass().equals(adapters.Map.MapEntry.class)) throw new IllegalArgumentException();
            Map.Entry entry = (Map.Entry) o;
            Object ret = table.remove(entry.getKey());
            return ret == null;    
        }

        @Override
        public boolean removeAll(HCollection c) {
            boolean removed = false;
            HIterator it = c.iterator();
            while(it.hasNext())
                removed = remove(it.next()) || removed;
            return removed;
        }

        @Override
        public boolean retainAll(HCollection c) {
            if(c == null) throw new NullPointerException();
            List toRemove = new List();
            HIterator it = iterator();
            while(it.hasNext()) {
                Object elem = it.next();
                if(!c.contains(elem)) toRemove.add(elem);
            }
            return removeAll(toRemove); 
        }

        @Override
        public int size() {
            return table.size();
        }

        @Override
        public Object[] toArray() {
            Object[] res = new Object[size()];
            HIterator it = iterator();
            int i = 0;
            while(it.hasNext()) 
                res[i++] = it.next();
            return res;
        }

        @Override
        public Object[] toArray(Object[] a) {
            if(a == null) throw new NullPointerException();
            Object[] res; 
            if(a.length >= size()) {
                res = a;
            } else {
                res = new Object[size()];
            }        
            int i = 0;
            HIterator it = iterator();
            while(it.hasNext()) res[i++] = it.next();
            return res;
        }
        
        class EntrySetIterator implements HIterator {
        
            HMap.Entry current;
            Enumeration keys;

            public EntrySetIterator() {
                current = null;
                keys = table.keys();
            }

            @Override
            public boolean hasNext() {
                return keys.hasMoreElements();
            }

            @Override
            public Object next() {
                if(!hasNext()) throw new NoSuchElementException();   
                Object key = keys.nextElement();
                current = new Map.MapEntry(key, table.get(key));
                return current;
            }

            @Override
            public void remove() {
                if(current == null) throw new exceptions.IllegalStateException();
                table.remove(current.getKey());
                current = null;
            }

        }
        
    }
    
    class ElementSet implements HSet {
        
        //true --> keys, false --> values
        private final boolean type; 

        public ElementSet(boolean type) {
            this.type = type;
        }        
        
        @Override
        public boolean add(Object e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean addAll(HCollection c) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void clear() {
            table.clear();
        }

        @Override
        public boolean containsAll(HCollection c) {
            if(c == null) throw new NullPointerException();
            boolean contained = true;
            HIterator it = c.iterator();
            while(it.hasNext()) {
                contained &= contains(it.next());
            }
            return contained;
        }
        
        @Override 
        public boolean equals(Object o) {
            if (o == this) return true; 
            if (!(o.getClass().equals(this.getClass()))) { 
                return false; 
            }    
            ElementSet other = (ElementSet) o;
            if (other.size() != size()) return false;
            HIterator it = iterator();
            while(it.hasNext())
                if(!other.contains(it.next())) 
                    return false;
            return true;
        }

        @Override 
        public int hashCode() {
            int hash = 0;
            HIterator it = iterator();
            while(it.hasNext()) 
                hash += it.next().hashCode();
            return hash;
        }

        @Override
        public boolean isEmpty() {
            return table.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            if(o == null) throw new NullPointerException();
            if(type) return containsKey(o);
            return containsValue(o);
        }

        @Override
        public HIterator iterator() {
            return new MapElementIterator();
        }

        @Override
        public boolean remove(Object o) {
            if(o == null) throw new NullPointerException();
            if(type) return table.remove(o) == null;
            HIterator it = entrySet().iterator();
            while(it.hasNext()) {
                Map.MapEntry entry = (Map.MapEntry) it.next();
                if(entry.getValue().equals(o)) {
                    table.remove(entry.getKey());
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean removeAll(HCollection c) {
            boolean removed = false;
            HIterator it = c.iterator();
            while(it.hasNext())
                removed = remove(it.next()) || removed;
            return removed;
        }

        @Override
        public boolean retainAll(HCollection c) {
            if(c == null) throw new NullPointerException();
            List toRemove = new List();
            HIterator it = iterator();
            while(it.hasNext()) {
                Object elem = it.next();
                if(!c.contains(elem)) toRemove.add(elem);
            }
            return removeAll(toRemove); 
        }

        @Override
        public int size() {
            return table.size();
        }

        @Override
        public Object[] toArray() {
            Object[] res = new Object[size()];
            HIterator it = iterator();
            int i = 0;
            while(it.hasNext()) 
                res[i++] = it.next();
            return res;
        }

        @Override
        public Object[] toArray(Object[] a) {
            if(a == null) throw new NullPointerException();
            Object[] res; 
            if(a.length >= size()) {
                res = a;
            } else {
                res = new Object[size()];
            }        
            int i = 0;
            HIterator it = iterator();
            while(it.hasNext()) res[i++] = it.next();
            return res;
        }
        
        class MapElementIterator implements HIterator {

            private final HIterator it;
            
            public MapElementIterator() {
                it = entrySet().iterator();
            }
            
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Object next() {
                Map.Entry o = (Map.Entry) it.next();
                if(type) return o.getKey();
                return o.getValue();
            }

            @Override
            public void remove() {
                it.remove();
            }
            
        }
        
    }
    
}