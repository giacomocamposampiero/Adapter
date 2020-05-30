package interfaces;

/**
 * Collection interface Java 1.4.2.
 * @author Giacomo Camposampiero
 */
public interface HCollection {
    
    /**
     * Inserts the specified element at the specified position in this list (optional operation).
     * @param index  index at which the specified element is to be inserted.
     * @param element  element to be inserted. 
     */
    void add(int index, Object element);

    /**
     * Appends the specified element to the end of this list (optional operation).
     * @param o  element to be appended to this list. 
     * @return      true (as per the general contract of the Collection.add method). 
     */
    boolean add(Object o);

    /**
     * Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator (optional operation).
     * @param c  collection whose elements are to be added to this list. 
     * @return  true if this list changed as a result of the call. 
     */
    boolean addAll(HCollection c);

    /**
     * Inserts all of the elements in the specified collection into this list at the specified position (optional operation).
     * @param index  index at which to insert first element from the specified collection.
     * @param c  elements to be inserted into this list. 
     * @return  true if this list changed as a result of the call. 
     */
    boolean addAll(int index, HCollection c);

    /**
     * Removes all of the elements from this list (optional operation).
     */
    void clear();

    /**
     * Returns true if this list contains the specified element.
     * @param o  element whose presence in this list is to be tested. 
     * @return  true if this list contains the specified element. 
     */
    boolean contains(Object o);

    /**
     * Returns true if this list contains all of the elements of the specified collection.
     * @param c  collection to be checked for containment in this list. 
     * @return  true if this list contains all of the elements of the specified collection. 
     */
    boolean containsAll(HCollection c);

    /**
     * Compares the specified object with this list for equality.
     * @param o  the object to be compared for equality with this list. 
     * @return  true if the specified object is equal to this list.
     */
    @Override
    boolean equals(Object o);

    /**
     * Returns the element at the specified position in this list.
     * @param index  index of element to return. 
     * @return  the element at the specified position in this list. 
     */
    Object get(int index);

    /**
     * Returns the hash code value for this list.
     * @return  the hash code value for this list.
     */
    @Override
    int hashCode();

    /**
     * Returns the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
     * @param o  element to search for.     
     * @return  the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element. 
     */
    int indexOf(Object o);

    /**
     * Returns true if this list contains no elements.
     * @return      true if this list contains no elements.
     */
    boolean isEmpty(); 

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * @return      an iterator over the elements in this list in proper sequence.
     */
    HIterator iterator();

    /**
     * Returns the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element.
     * @param o  element to search for. 
     * @return      the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element. 
     */
    int lastIndexOf(Object o);

    /**
     * Returns a list iterator of the elements in this list (in proper sequence).
     * @return      a list iterator of the elements in this list (in proper sequence).
     */
    HListIterator listIterator();

    /**
     * Returns a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list.
     * @param index  index of first element to be returned from the list iterator (by a call to the next method). 
     * @return  a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list. 
     */
    HListIterator listIterator(int index);

    /**
     * Removes the element at the specified position in this list (optional operation).
     * @param index      the index of the element to removed.   
     * @return  the element previously at the specified position. 
     */
    Object remove(int index);

    /**
     * Removes the first occurrence in this list of the specified element (optional operation).
     * @param o  element to be removed from this list, if present. 
     * @return  true if this list contained the specified element. 
     */
    boolean remove(Object o);

    /**
     * Removes from this list all the elements that are contained in the specified collection (optional operation).
     * @param c  collection that defines which elements will be removed from this list.  
     * @return  true if this list changed as a result of the call. 
     */
    boolean removeAll(HCollection c);

    /**
     * Retains only the elements in this list that are contained in the specified collection (optional operation).
     * @param c  collection that defines which elements this set will retain. 
     * @return  true if this list changed as a result of the call. 
     */
    boolean retainAll(HCollection c);

    /**
     * Replaces the element at the specified position in this list with the specified element (optional operation).
     * @param index  index of element to replace.
     * @param element     element to be stored at the specified position. 
     * @return  the element previously at the specified position. 
     */
    Object set(int index, Object element);

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list.
     */
    int size();

    /**
     * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
     * @param fromIndex  low endpoint (inclusive) of the subList.
     * @param toIndex  high endpoint (exclusive) of the subList. 
     * @return  a view of the specified range within this list. 
     */
    HList subList(int fromIndex, int toIndex);

    /**
     * Returns an array containing all of the elements in this list in proper sequence.
     * @return  an array containing all of the elements in this list in proper sequence.
     */
    Object[] toArray();
    
    /**
     * Returns an array containing all of the elements in this collection; the runtime type of the returned array is that of the specified array.
     * @param a  the array into which the elements of this collection are to be stored, if it is big enough; otherwise, a new array of the same runtime type is allocated for this purpose. 
     * @return  an array containing the elements of this collection 
     */
    Object[] toArray(Object[] a);
    
}