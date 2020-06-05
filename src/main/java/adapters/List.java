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
 * This implementation is the actual concrete implementation for Collection interface too. If a concrete implementation of a Collection is needed, a List object should be instantiated.
 * @author Giacomo Camposampiero (Set interface is defined by java.util.Set and this is just an adapted redefinition of it, all credit goes to Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA)
 */
public class List implements HList {

    private final Vector vec;

    public List() {
        vec = new Vector();
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices). 
     * Null parameters are not accepted in this list, an exception will be thrown as result of an attempt to add a null object to the list
     * @param index index at which the specified element is to be inserted.
     * @param element element to be inserted. 
     * @throws IndexOutOfBoundsException if the index parameter isn't in the interval [0, size()]
     * @throws NullPointerException if the element parameter is null
     */    
    @Override
    public void add(int index, Object element) {
        if (element == null) throw new NullPointerException();
        if (index < 0 || index > size()) throw new IndexOutOfBoundsException();
        vec.insertElementAt(element, index);
    }

    /**
     * Appends the specified element to the end of this list.
     * Null parameters are not accepted in this list, an exception will be thrown as result of an attempt to add a null object to the list
     * @param o  element that has to be inserted. 
     * @return  true if this list changed as a result of the call  
     * @throws NullPointerException whether the parameter is a null reference, which is not considered a valid type of entry for the collection
     */
    @Override
    public boolean add(Object o) {
        add(size(), o);
        return true;
    }

    /**
     * Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator.
     * The behavior of this operation is undefined if the specified collection is modified while the operation is in progress.
     * @param c  elements to be inserted into this list.
     * @return  true if this collection changed as a result of the call. 
     * @throws IndexOutOfBoundsException if the index parameter isn't in the interval [0, size()]
     * @throws NullPointerException if the element parameter is null
     * @throws NullPointerException if the collection contains null elementes
     */
    @Override
    public boolean addAll(HCollection c) {
         return addAll(size(), c);
    }

    /**
     * Inserts all of the elements in the specified collection into this list at the specified position.
     * Shifts the element currently at that position (if any) and any subsequent elements to the right (increases their indices).
     * The new elements will appear in this list in the order that they are returned by the specified collection's iterator.
     * The behavior of this operation is unspecified if the specified collection is modified while the operation is in progress.
     * @param index  index at which to insert first element from the specified collection.
     * @param c  elements to be inserted into this list.    
     * @return  true if this list changed as a result of the call. 
     * @throws IndexOutOfBoundsException if the index parameter isn't in the interval [0, size()]
     * @throws NullPointerException if the element parameter is null
     * @throws NullPointerException if the collection contains null elementes
     */
    @Override
    public boolean addAll(int index, HCollection c) {
        if (index < 0 || index > size()) throw new IndexOutOfBoundsException();
        boolean res = false;
        HIterator it = c.iterator();
        while (it.hasNext()) {
            add(index++, it.next());
            res = true;
        }
        return res;
    }
    
    /**
     * Removes all of the elements from this list.
     * This list will be empty after this method returns unless it throws an exception. 
     */
    @Override
    public void clear() {
        vec.removeAllElements();
    }

    /**
     * Returns true if this list contains the specified element.
     * More formally, returns true if and only if this list contains at least one element e such that (o==null ? e==null : o.equals(e)). 
     * @param o  element whose presence in this list is to be tested. 
     * @return  true if this list contains the specified element. 
     * @throws NullPointerException if the parameters is null.
     */
    @Override
    public boolean contains(Object o) {
        if (o == null) throw new NullPointerException();
        return vec.contains(o);
    }

    /**
     * Returns true if this list contains all of the elements of the specified collection.
     * @param c  collection to be checked for containment in this list. 
     * @return  true if this list contains all of the elements of the specified collection. 
     * @throws NullPointerException if the parameters is null.
     * @throws NullPointerException if the specified collection contains one or more null elements.
     */
    @Override
    public boolean containsAll(HCollection c) {
        boolean res = true;
        HIterator it = c.iterator();
        while (it.hasNext()) {
            res = res && contains(it.next());
        }
        return res;
    }

    /**
     * Compares the specified object with this list for equality.
     * The general contract for the Object.equals method states that equals must be symmetric (in other words, a.equals(b) if and only if b.equals(a)). 
     * @param o  the object to be compared for equality with this list. 
     * @return  true if the specified object is equal to this list.
     */
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

    /**
     * Returns the hash code value for this list.
     * In particular, c1.equals(c2) implies that c1.hashCode()==c2.hashCode(). 
     * @return the hash code value for this list.
     */
    @Override
    public int hashCode() {
        int hashCode = 1;
        HIterator it = iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            hashCode = 31 * hashCode + obj.hashCode();
        }
        return hashCode;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index index of element to return. 
     * @return  the element at the specified position in this list. 
     * @throws IndexOutOfBoundsException if the index parameter isn't in the interval [0, size()-1]
     */
    @Override
    public Object get(int index) {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
        return vec.elementAt(index);
    }

    /**
     * Returns the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
     * More formally, returns the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))), or -1 if there is no such index. 
     * @param o  element to search for.     
     * @return  the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element. 
     * @throws NullPointerException if the element parameter is null
     */
    @Override
    public int indexOf(Object o) {
        if (o == null) throw new NullPointerException();
        return vec.indexOf(o);
    }

    /**
     * Returns true if this list contains no elements.
     * @return  true if this list contains no elements.
     */
    @Override
    public boolean isEmpty() {
        return vec.isEmpty();
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence. 
     * @return  an iterator over the elements in this collection.
     */
    @Override
    public HIterator iterator() {
        return new ListIterator(0, size());
    }

    /**
     * Returns the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element.
     * More formally, returns the highest index i such that (o==null ? get(i)==null : o.equals(get(i))), or -1 if there is no such index. 
     * @param o  element to search for. 
     * @return the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element. 
     * @throws NullPointerException if the element parameter is null
     */
    @Override
    public int lastIndexOf(Object o) {
        if (o == null) throw new NullPointerException();
        return vec.lastIndexOf(o);
    }

    /**
     * Returns a list iterator of the elements in this list (in proper sequence).
     * @return a list iterator of the elements in this list (in proper sequence).
     */
    @Override
    public HListIterator listIterator() {
        return listIterator(0);
    }

    /**
     * Returns a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list.
     * The specified index indicates the first element that would be returned by an initial call to the next method. An initial call to the previous method would return the element with the specified index minus one. 
     * @param index  index of first element to be returned from the list iterator (by a call to the next method). 
     * @return  a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list. 
     * @throws IndexOutOfBoundsException if the index parameter isn't in the interval [0, size()-1]
     */
    @Override
    public HListIterator listIterator(int index) {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
        return new ListIterator(index, size());
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from the list. 
     * @param index the index of the element to removed.   
     * @return the element previously at the specified position.
     * @throws IndexOutOfBoundsException if the index parameter isn't in the interval [0, size()-1]
     */
    @Override
    public Object remove(int index) {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
        Object old = get(index);
        vec.removeElementAt(index);
        return old;
    }

    /**
     * Removes the first occurrence in this list of the specified element, if it is present.
     * More formally, removes an element e such that (o==null ? e==null : o.equals(e)), if this list contains one or more such elements. Returns true if this list contained the specified element (or equivalently, if this list changed as a result of the call). 
     * @param o  element to be removed from this list, if present. 
     * @return  true if this list contained the specified element. 
     * @throws NullPointerException if the parameters is null.
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) throw new NullPointerException();
        return vec.remove(o);
    }

    /**
     * Removes from this list all the elements that are contained in the specified collection.
     * After this call returns, this list will contain no elements in common with the specified collection. 
     * @param c  collection that defines which elements will be removed from this list.  
     * @return  true if this list changed as a result of the call. 
     * @throws NullPointerException if the parameters is null.
     * @throws NullPointerException if the specified collection contains one or more null elements.
     */
    @Override
    public boolean removeAll(HCollection c) {
        boolean changed = false;
        HIterator it = c.iterator();
        while(it.hasNext())
            changed = remove(it.next()) || changed;
        return changed;
    }

    /**
     * Retains only the elements in this list that are contained in the specified collection.
     * In other words, removes from this list all of its elements that are not contained in the specified collection.  
     * @param c  collection that defines which elements this list will retain. 
     * @return  true if this list changed as a result of the call. 
     * @throws NullPointerException if the parameters is null.
     * @throws NullPointerException if the specified collection contains one or more null elements.
     */
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

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * @param index  index of element to replace.
     * @param element element to be stored at the specified position. 
     * @return the element previously at the specified position. 
     * @throws IndexOutOfBoundsException if the index parameter isn't in the interval [0, size()-1]
     * @throws NullPointerException if the element parameter is null
     */
    @Override
    public Object set(int index, Object element) {
        if (element == null) throw new NullPointerException();
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
        Object old = get(index);
        vec.setElementAt(element, index);
        return old;
    }

    /**
     * Returns the number of elements in this collection.
     * @return the number of elements in this collection.
     */
    @Override
    public int size() {
        return vec.size();
    }

    /**
     * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
     * (If fromIndex and toIndex are equal, the returned list is empty.) The returned list is backed by this list, so non-structural changes in the returned list are reflected in this list, and vice-versa. The returned list supports all of the optional list operations supported by this list.
     * The semantics of the list returned by this method become undefined if the backing list (i.e., this list) is structurally modified in any way other than via the returned list. (Structural modifications are those that change the size of this list, or otherwise perturb it in such a fashion that iterations in progress may yield incorrect results.) 
     * @param fromIndex  low endpoint (inclusive) of the subList.
     * @param toIndex  high endpoint (exclusive) of the subList. 
     * @return  a view of the specified range within this list. 
     * @throws IndexOutOfBoundsException for an illegal endpoint index value (fromIndex < 0 || toIndex > size || fromIndex > toIndex).
     */
    @Override
    public HList subList(int fromIndex, int toIndex) {
        if(fromIndex < 0 || fromIndex > toIndex || toIndex >= size()) throw new IndexOutOfBoundsException();
        return new SubList(fromIndex, toIndex);
    }

    /**
     * Returns an array containing all of the elements in this listin proper sequence.
     * The returned array will be "safe" in that no references to it are maintained by this list. (In other words, this method must allocate a new array even if this list is backed by an array). The caller is thus free to modify the returned array.
     * @return  an array containing all of the elements in this list.
     */
    @Override
    public Object[] toArray() {
        Object[] res = new Object[size()];
        vec.copyInto(res);
        return res;
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence.
     * The runtime type of the returned array is that of the specified array if a parameter is big enough to contain all the elements of this list (all the elements of the list are copied inside the a parameter).
     * If a is not big enough, a new Object[] array will be instantiated, and the return array won't be of the specified array type.
     * The returned array will be "safe" in that no references to it are maintained by this list. (In other words, this method must allocate a new array even if this list is backed by an array). The caller is thus free to modify the returned array.
     * If the parameter a is big enough to contain all the element of this list, the elements will be stored in the parametric array. Only the first size() cell of the array will be written, the others will mantain their actual value.
     * @param a the array into which the elements of this list are to be stored, if it is big enough 
     * @return an array containing the elements of this list. 
     * @throws NullPointerException if the parameter is null
     */
    @Override
    public Object[] toArray(Object[] a) {
        Object[] res; 
        if(a.length >= size()) res = a;
        else res = new Object[size()];
        vec.copyInto(res);
        return res;
    }
    
    /**
     * Returns a string representation of the object.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        String res = "";
        HIterator it = iterator();
        while(it.hasNext()) res += it.next().toString() + " ";
        return res;
    }

    /**
     * Inner class ListIterator, implements HListIterator interface. 
     */
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
            if(o == null) throw new IllegalArgumentException();
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
            if(o == null) throw new IllegalArgumentException();
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
    
    /**
     * SubList inner class.
     */
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
            if(index < 0 || index > size()) throw new IndexOutOfBoundsException();
            List.this.add(fromIndex + index, element);
            toIndex++;
        }

        @Override
        public boolean addAll(HCollection c) {
            return addAll(size(), c);
        }
        
        @Override
        public boolean addAll(int index, HCollection c) {
            if(index < 0 || index > size()) throw new IndexOutOfBoundsException();
            boolean res =  List.this.addAll(fromIndex + index, c);
            toIndex += c.size();
            return res;
        }

        @Override
        public void clear() {
            while(fromIndex < toIndex)
                List.this.remove(--toIndex);
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
            if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();
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
            return new ListIterator(fromIndex, toIndex);
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
            return new ListIterator(fromIndex, toIndex);
        }

        @Override
        public HListIterator listIterator(int index) {
            if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();
            return new ListIterator(fromIndex + index, toIndex);
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
            if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();
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
                    List.this.remove(fromIndex + i);
                    changed = true;
                }
                i++;
            }
            return changed;
        }
         
        @Override
        public Object set(int index, Object element) {
            if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();
            return List.this.set(fromIndex + index, element);
        }
        
        @Override
        public int size() {
            return toIndex - fromIndex;
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
    
}