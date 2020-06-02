package adapters;

import interfaces.HCollection;
import interfaces.HIterator;
import interfaces.HSet;
import exceptions.IllegalStateException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;

/**
 * Implementation of Set interface Java 1.4.2.
 * Object Adapter of an Hashtable
 * @author Giacomo Camposampiero
 */
public class Set implements HSet{

    Hashtable table;

    public Set() {
        this.table = new Hashtable();
    }
    
    public Set(int initialCapacity) {
        this.table = new Hashtable(initialCapacity);
    }
    
    
    @Override
    public boolean add(Object o) {
        if(table.containsKey(o.hashCode())) return false;
        table.put(o.hashCode(), o);
        return true;
    }

    @Override
    public boolean addAll(HCollection c) {
        HIterator it = c.iterator();
        boolean res = false;
        while(it.hasNext()){
            res = add(it.next()) || res;
        }
        return res;
    }

    @Override
    public void clear() {
        table.clear();
    }

    @Override
    public boolean contains(Object o) {
        int hash = o.hashCode();
        if(!table.containsKey(hash)) return false;
        return o.equals(table.get(hash));
    }

    @Override
    public boolean containsAll(HCollection c) {
        HIterator it = c.iterator();
        boolean res = true;
        while(it.hasNext()){
            res = contains(it.next()) && res;
        }
        return res;
    }

    @Override
    public boolean isEmpty() {
        return table.isEmpty();
    }

    @Override
    public HIterator iterator() {
        return new SetIterator();
    }

    @Override
    public boolean remove(Object o) {
        if(!contains(o)) return false;
        table.remove(o.hashCode());
        return true;
    }

    @Override
    public boolean removeAll(HCollection c) {
        HIterator it = c.iterator();
        boolean res = false;
        while(it.hasNext()){
            res = remove(it.next()) || res;
        }
        return res;
    }

    @Override
    public boolean retainAll(HCollection c) {
        boolean res = false;
        HIterator it = iterator();
        while(it.hasNext()) {
            Object elem = it.next();
            if(!c.contains(elem)) res = remove(elem) || res;
        }
        return res;
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
        while(it.hasNext()) res[i++] = it.next();
        return res;
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override 
    public boolean equals(Object o) {
         if (o == this) return true; 
        if (!(o.getClass().equals(this.getClass()))) { 
            return false; 
        }    
        Set other = (Set) o;
        if (other.size() != size()) return false;
        for (Enumeration elems = table.elements() ; elems.hasMoreElements() ;) {
            if(!other.contains(elems.nextElement())) return false;
        }
        return true;
    }
    
    @Override 
    public int hashCode() {
        int hash = 0;
        for (Enumeration elems = table.elements() ; elems.hasMoreElements() ;) {
            hash += elems.nextElement().hashCode();
        }
        return hash;
    }
    
    class SetIterator implements HIterator {
        
        Object currentKey;
        Enumeration keys;
        
        public SetIterator() {
            currentKey = null;
            keys = table.keys();
        }
        
        @Override
        public boolean hasNext() {
            return keys.hasMoreElements();
        }

        @Override
        public Object next() {
            if(!hasNext()) throw new NoSuchElementException();   
            currentKey = keys.nextElement();
            return table.get(currentKey);
        }

        @Override
        public void remove() {
            if(currentKey == null) throw new IllegalStateException();
            table.remove(currentKey);
            currentKey = null;
        }
        
    }
    
    @Override
    public String toString() {
        String res = "";
        HIterator it = iterator();
        while(it.hasNext()) res += it.next().toString() + " ";
        return res;
    }

}