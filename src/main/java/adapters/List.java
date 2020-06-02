package adapters;

import interfaces.HCollection;
import interfaces.HIterator;
import interfaces.HList;
import interfaces.HListIterator;
import exceptions.IllegalStateException;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * Implementation of List interface Java 1.4.2.
 *
 * @author Giacomo Camposampiero
 */
public class List implements HList {

    private final Vector vec;

    public List() {
        vec = new Vector();
    }

    @Override
    public void add(int index, Object element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        vec.insertElementAt(element, index);
    }

    @Override
    public boolean add(Object o) {
        add(size(), o);
        return true;
    }

    @Override
    public boolean addAll(HCollection c) {
         return addAll(size(), c);
    }

    @Override
    public boolean addAll(int index, HCollection c) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        boolean changed = false;
        HIterator it = c.iterator();
        while (it.hasNext()) {
            changed = add(it.next()) || changed;
        }
        return changed;
    }

    @Override
    public void clear() {
        vec.removeAllElements();
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        return vec.contains(o);
    }

    @Override
    public boolean containsAll(HCollection c) {
        boolean res = true;
        HIterator it = c.iterator();
        while (it.hasNext()) {
            res = res && contains(it.next());
        }
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o.getClass().equals(this.getClass()))) {
            return false;
        }
        List other = (List) o;
        if (other.size() != size()) {
            return false;
        }
        for (Enumeration elems = vec.elements(); elems.hasMoreElements();) {
            if (!other.contains(elems.nextElement())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        HIterator it = iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
        }
        return hashCode;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return vec.elementAt(index);
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
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
        if (o == null) {
            throw new NullPointerException();
        }
        return vec.lastIndexOf(o);
    }

    @Override
    public HListIterator listIterator() {
        return listIterator(0);
    }

    @Override
    public HListIterator listIterator(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return new ListIterator(index);
    }

    @Override
    public Object remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        Object old = get(index);
        vec.removeElementAt(index);
        return old;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        return vec.remove(o);
    }

    @Override
    public boolean removeAll(HCollection c) {
        boolean changed = false;
        HIterator it = c.iterator();
        while(it.hasNext())
            changed = remove(it.next()) || changed;
        return changed;
    }

    @Override
    public boolean retainAll(HCollection c) {
        boolean changed = false;
        HIterator it = iterator();
        while(it.hasNext()) {
            Object elem = it.next();
            if(!c.contains(elem)) changed = remove(elem) || changed;
        }
        return changed;   
    }

    @Override
    public Object set(int index, Object element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
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
        Object[] res = new Object[size()];
        vec.copyInto(res);
        return res;
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString() {
        String res = "";
        HIterator it = iterator();
        while(it.hasNext()) res += it.next().toString() + " ";
        return res;
    }

    class ListIterator implements HListIterator {
        
        private int cursor;
        private int current;
        
        public ListIterator(int index) {
            this.cursor = index;
            current = -1;
        }

        @Override
        public void add(Object o) {
            vec.add(cursor++, o);
            current = -1;
        }

        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        @Override
        public int nextIndex() {
            return cursor; 
        }

        @Override
        public Object previous() {
            if(!hasPrevious()) throw new NoSuchElementException();
            current = --cursor;
            return vec.get(current);
        }

        @Override
        public int previousIndex() {
            return cursor-1;
        }

        @Override
        public void set(Object o) {
            if(current == -1) throw new IllegalStateException();
            vec.set(current, o);
        }

        @Override
        public boolean hasNext() {
            return cursor < size();
        }

        @Override
        public Object next() {
            if(!hasNext()) throw new NoSuchElementException();
            current = cursor++;
            return vec.get(current);
        }

        @Override
        public void remove() {
            if(current == -1) throw new IllegalStateException();
            vec.remove(current);
            if(current != cursor) current--;
            current = -1;
        }

    }
        
    //TODO
    class SubList implements HList {
        
        private int fromIndex;
        private int toIndex;

        public SubList(int fromIndex, int toIndex) {
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        public boolean add(Object o) {
            List.this.add(toIndex++, o);
            return true;
        }

        @Override
        public boolean addAll(HCollection c) {
            boolean res = List.this.addAll(toIndex, c);
            toIndex += c.size();
            return res;
        }

        @Override
        public void clear() {
            List.this.removeAll(this);
            toIndex = fromIndex;
        }

        @Override
        public boolean contains(Object o) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.            
        }

        @Override
        public boolean containsAll(HCollection c) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates            
        }

        @Override
        public boolean isEmpty() {
            return fromIndex == toIndex;
        }

        @Override
        public HIterator iterator() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean remove(Object o) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        public int size() {
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

        @Override
        public boolean addAll(int index, HCollection c) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public HList subList(int fromIndex, int toIndex) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object remove(int index) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object set(int index, Object element) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int lastIndexOf(Object o) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public HListIterator listIterator() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public HListIterator listIterator(int index) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int indexOf(Object o) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object get(int index) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void add(int index, Object element) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
       
    }

}