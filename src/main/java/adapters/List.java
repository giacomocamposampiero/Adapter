package adapters;

import interfaces.HCollection;
import interfaces.HIterator;
import interfaces.HList;
import interfaces.HListIterator;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Implementation of List interface Java 1.4.2.
 * @author Giacomo Camposampiero
 */
public class List implements HList  {

    private Vector vec;
    
    public List() {
        vec = new Vector();
    }
    
    @Override
    public void add(int index, Object element) {
        if(element == null) throw new NullPointerException();
        if(index < 0 || index > size()) throw new IndexOutOfBoundsException();
        vec.insertElementAt(element, index);
    }

    @Override
    public boolean add(Object o) {
        if(o == null) throw new NullPointerException();
        vec.add(o);
        return true;
    }

    @Override
    public boolean addAll(HCollection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(int index, HCollection c) {
        if(index < 0 || index > size()) throw new IndexOutOfBoundsException();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        vec.removeAllElements();
    }

    @Override
    public boolean contains(Object o) {
        if(o == null) throw new NullPointerException();
        return vec.contains(o);
    }

    @Override
    public boolean containsAll(HCollection c) {
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
        for (Enumeration elems = vec.elements() ; elems.hasMoreElements() ;) {
            if(!other.contains(elems.nextElement())) return false;
        }
        return true;
    }
    
    @Override 
    public int hashCode() {
        int hash = 0;
        for (Enumeration elems = vec.elements() ; elems.hasMoreElements() ;) {
            hash += elems.nextElement().hashCode();
        }
        return hash;
    }

    @Override
    public Object get(int index) {
        if(index < 0 || index > size()) throw new IndexOutOfBoundsException();
        return vec.elementAt(index);
    }

    @Override
    public int indexOf(Object o) {
        if(o == null) throw new NullPointerException();
        return vec.indexOf(o);
    }

    @Override
    public boolean isEmpty() {
        return vec.isEmpty();
    }

    @Override
    public HIterator iterator() {
        return new ListIterator(0);
    }

    @Override
    public int lastIndexOf(Object o) {
        if(o == null) throw new NullPointerException();
        return vec.lastIndexOf(o);
    }

    @Override
    public HListIterator listIterator() {
        return new ListIterator(0);
    }

    @Override
    public HListIterator listIterator(int index) {
        if(index < 0 || index > size()) throw new IndexOutOfBoundsException();
        return new ListIterator(index);
    }

    @Override
    public Object remove(int index) {
        if(index < 0 || index > size()) throw new IndexOutOfBoundsException();
        Object old = get(index);
        vec.removeElementAt(index);
        return old;
    }

    @Override
    public boolean remove(Object o) {
        if(o == null) throw new NullPointerException();
        return vec.remove(o);
    }

    @Override
    public boolean removeAll(HCollection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(HCollection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object set(int index, Object element) {
        if(element == null) throw new NullPointerException();
        if(index < 0 || index > size()) throw new IndexOutOfBoundsException();
        Object old = get(index);
        vec.setElementAt(element, index);
        return old;
    }

    @Override
    public int size() {
        return vec.size();
    }

    @Override
    public HList subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    class ListIterator implements HListIterator {
        
        public ListIterator(int index) {
            
        }
        
        @Override
        public void add(Object o) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object previous() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void set(Object o) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object next() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
}