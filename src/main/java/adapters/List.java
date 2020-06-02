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
        HIterator it = c.iterator();
        while (it.hasNext()) add(index++, it.next());
        return true;
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
        return new ListIterator(0, size());
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
        return new ListIterator(index, size());
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
        if(fromIndex < 0 || fromIndex > toIndex || toIndex >= size()) throw new IndexOutOfBoundsException();
        return new SubList(fromIndex, toIndex);
    }

    @Override
    public Object[] toArray() {
        Object[] res = new Object[size()];
        vec.copyInto(res);
        return res;
    }

    @Override
    public Object[] toArray(Object[] a) {
        if(a.length >= size()) {
                vec.copyInto(a);
                return a;
            }
        Object[] arr = new Object[size()];
        vec.copyInto(arr);
        return arr;
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
        private int upperBond;
        
        public ListIterator(int index, int upperBond) {
            this.cursor = index;
            this.upperBond = upperBond;
            current = -1;
        }

        @Override
        public void add(Object o) {
            vec.add(cursor++, o);
            upperBond++;
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
            return cursor < upperBond;
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
            if(current != cursor) cursor--;
            upperBond--;
            current = -1;
        }

    }
    
    class SubList implements HList {

        private final int fromIndex;
        private int toIndex;

        public SubList(int fromIndex, int toIndex) {
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }
 
        @Override
        public boolean add(Object o) {
            add(size(), o);
            return true;
        }
        
        @Override
        public void add(int index, Object element) {
            if(index < 0 || index > toIndex) throw new IndexOutOfBoundsException();
            List.this.add(fromIndex + index, element);
            toIndex++;
        }

        @Override
        public boolean addAll(HCollection c) {
            return addAll(size(), c);
        }
        
        @Override
        public boolean addAll(int index, HCollection c) {
            if(index < 0 || index > toIndex) throw new IndexOutOfBoundsException();
            boolean res =  List.this.addAll(fromIndex + index, c);
            toIndex += c.size();
            return res;
        }

        @Override
        public void clear() {
            while(fromIndex < toIndex)
                List.this.remove(toIndex--);
            List.this.remove(fromIndex);
        }

        @Override
        public boolean contains(Object o) {
            return indexOf(o) != -1;        
        }

        @Override
        public boolean containsAll(HCollection c) {
            boolean res = true;
            HIterator it = c.iterator();
            while(it.hasNext())
                res = contains(it.next()) && res;
            return res;                
        }
        
        @Override
        public Object get(int index) {
            if(index < 0 || index > toIndex) throw new IndexOutOfBoundsException();
            return List.this.get(fromIndex + index);
        }
        
        @Override
        public int indexOf(Object o) {
            HIterator it = iterator();
            int i = 0;
            while(it.hasNext()) {
                if(o.equals(it.next())) return i;
                i++;
            }
            return -1;
        }
        
        @Override
        public boolean isEmpty() {
            return fromIndex == toIndex;
        }

        @Override
        public HIterator iterator() {
            return new ListIterator(fromIndex, toIndex + 1);
        }
        
        @Override
        public int lastIndexOf(Object o) {
            int res = -1;
            HIterator it = iterator();
            for(int i=-1; it.hasNext(); i++) {
                if(o.equals(it.next())) res = i;
            }
            return res;
        }

        @Override
        public HListIterator listIterator() {
            return new ListIterator(fromIndex, toIndex + 1);
        }

        @Override
        public HListIterator listIterator(int index) {
            if(index < 0 || index > toIndex) throw new IndexOutOfBoundsException();
            return new ListIterator(fromIndex + index, toIndex + 1);
        }

        @Override
        public boolean remove(Object o) {
            int index = indexOf(o);
            if (index >= 0) {
                remove(index);
                return true;
            }
            return false;
        }
        
        @Override
        public Object remove(int index) {
            if(index < 0 || index > toIndex) throw new IndexOutOfBoundsException();
            List.this.remove(index);
            toIndex--;
            return true;
        }

        @Override
        public boolean removeAll(HCollection c) {
            boolean res = false;
            HIterator it = c.iterator();
            while(it.hasNext())
                res = remove(it.next()) || res;
            return res;
        }

        @Override
        public boolean retainAll(HCollection c) {
            boolean changed = false;
            HIterator it = iterator();
            int i = 0;
            while(it.hasNext()) {
                Object elem = it.next();
                if(!c.contains(elem)) {
                    List.this.remove(i+fromIndex);
                    changed = true;
                }
            }
            return changed;
        }
         
        @Override
        public Object set(int index, Object element) {
            if(index<0 || index>toIndex) throw new IndexOutOfBoundsException();
            return List.this.set(index, element);
        }
        
        @Override
        public int size() {
            return toIndex - fromIndex + 1;
        } 
        
        @Override
        public HList subList(int fromIndex, int toIndex) {
            if(fromIndex < 0 || fromIndex > toIndex || toIndex >= size()) throw new IndexOutOfBoundsException();
            return new SubList(this.fromIndex + fromIndex, this.fromIndex + toIndex);
        }

        @Override
        public Object[] toArray() {
            HIterator it = iterator();
            Object[] arr = new Object[size()];
            for(int i=0; it.hasNext(); i++) arr[i] = it.next();
            return arr;
        }

        @Override
        public Object[] toArray(Object[] a) {
            HIterator it = iterator();
            if(a.length >= size()) {
                for(int i=0; it.hasNext(); i++) a[i] = it.next();
                return a;
            }
            Object[] arr = new Object[size()];
            for(int i=0; it.hasNext(); i++) a[i] = it.next();
            return arr;
        }

    }
    
    /*
    class SubList implements HList {
        
        private final int fromIndex;
        private final Vector subvec;    

        public SubList(int fromIndex, int toIndex) {
            this.fromIndex = fromIndex;
            subvec = new Vector(toIndex - fromIndex);
            HIterator it = List.this.listIterator(fromIndex);
            while(toIndex!=fromIndex && it.hasNext()) {
                subvec.add(it.next());
                toIndex--;
            }
        }

        @Override
        public boolean add(Object o) {
            add(size(), o);
            return true;
        }
        
        @Override
        public void add(int index, Object element) {
            if(index < 0 || index >= subvec.size()) throw new IndexOutOfBoundsException();
            if(element == null) throw new NullPointerException();
            vec.insertElementAt(element, index);
            List.this.add(fromIndex + index, element);
        }

        @Override
        public boolean addAll(HCollection c) {
            return addAll(size(), c);
        }
        
        @Override
        public boolean addAll(int index, HCollection c) {
            if (index < 0 || index > size()) throw new IndexOutOfBoundsException();
            HIterator it = c.iterator();
            while (it.hasNext()) add(index++, it.next());
            List.this.addAll(fromIndex + index, c);
            return true;
        }

        @Override
        public void clear() {
            List.this.removeAll(this);
            subvec.removeAllElements();
        }

        @Override
        public boolean contains(Object o) {
            return subvec.contains(o);
        }

        @Override
        public boolean containsAll(HCollection c) {
            boolean res = true;
            HIterator it = c.iterator();
            while (it.hasNext()) res = res && contains(it.next());
            return res;
        }
        
        @Override
        public Object get(int index) {
            if(index < 0 || index >= subvec.size()) throw new IndexOutOfBoundsException();
            return subvec.get(index);
        }   
        
        @Override
        public int indexOf(Object o) {
            if(o == null) throw new NullPointerException();
            return subvec.indexOf(o);
        }   

        @Override
        public boolean isEmpty() {
            return subvec.isEmpty();
        }

        @Override
        public HIterator iterator() {
            return new ListIterator(fromIndex, fromIndex + size());
        }
        
        @Override
        public int lastIndexOf(Object o) {
            if(o == null) throw new NullPointerException();
            return subvec.lastIndexOf(o);
        }

        @Override
        public HListIterator listIterator() {
            return new ListIterator(fromIndex, fromIndex + size());
        }

        @Override
        public HListIterator listIterator(int index) {
            return new ListIterator(fromIndex, fromIndex + size());
        }

        @Override
        public boolean remove(Object o) {
            if(o == null) throw new NullPointerException();
            List.this.remove(o);
            return subvec.remove(o);
        }
        
        @Override
        public Object remove(int index) {
            if(index < 0 || index >= subvec.size()) throw new IndexOutOfBoundsException();
            List.this.remove(fromIndex + index);
            Object obj = subvec.get(index);
            subvec.remove(index);
            return obj;
        }

        @Override
        public boolean removeAll(HCollection c) {
            boolean changed = false;
            HIterator it = c.iterator();
            while(it.hasNext())
                changed = remove(it.next()) || changed;
            List.this.removeAll(c);
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
            List.this.retainAll(c);
            return changed;
        }
        
        @Override
        public Object set(int index, Object element) {
            if(index < 0 || index >= subvec.size()) throw new IndexOutOfBoundsException();
            if(element == null) throw new NullPointerException();
            Object res = subvec.get(index);
            subvec.set(index, element);
            return res;
        }

        @Override
        public int size() {
            return subvec.size();
        }
        
        @Override
        public HList subList(int fromIndex, int toIndex) {
            if(fromIndex < 0 || fromIndex > toIndex || toIndex >= size()) throw new IndexOutOfBoundsException();
            return new SubList(this.fromIndex + fromIndex, this.fromIndex + toIndex);
        }

        @Override
        public Object[] toArray() {
            Object[] res = new Object[size()];
            subvec.copyInto(res);
            return res;
        }

        @Override
        public Object[] toArray(Object[] a) {
            if(a.length >= size()) {
                subvec.copyInto(a);
                return a;
            }
            Object[] arr = new Object[size()];
            subvec.copyInto(arr);
            return arr;
        }
       
    }*/

}