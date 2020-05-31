package interfaces;

/**
 * Collection interface Java 1.4.2.
 * @author Giacomo Camposampiero
 */
public interface HCollection {

    /**
     * Appends the specified element to the end of this list (optional operation).
     * @param o  element to be appended to this list. 
     * @return true (as per the general contract of the Collection.add method). 
     */
    boolean add(Object o);

    /**
     * Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator (optional operation).
     * @param c  collection whose elements are to be added to this list. 
     * @return  true if this list changed as a result of the call. 
     */
    boolean addAll(HCollection c);

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
     * Returns the hash code value for this list.
     * @return  the hash code value for this list.
     */
    @Override
    int hashCode();

    /**
     * Returns true if this list contains no elements.
     * @return  true if this list contains no elements.
     */
    boolean isEmpty(); 

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * @return  an iterator over the elements in this list in proper sequence.
     */
    HIterator iterator();

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
     * Returns the number of elements in this list.
     * @return the number of elements in this list.
     */
    int size();

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