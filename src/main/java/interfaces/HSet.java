package interfaces;

/**
 * Set interface Java 1.4.2.
 * A collection that contains no duplicate elements. More formally, sets contain no pair of elements e1 and e2 such that e1.equals(e2). As implied by its name, this interface models the mathematical set abstraction.
 * Null elements are not considered as valid elements of the set. An attempt of insertion of a null element will result in an unchecked exception, NullPointerException. The same behaviour is expected as result of any operation on Set elements with a null reference.
 * @author Giacomo Camposampiero
 */
public interface HSet extends HCollection {

    /**
     * Adds the specified element to this set if it is not already present.
     * More formally, adds the specified element, o, to this set if this set contains no element e such that (o==null ? e==null : o.equals(e)). If this set already contains the specified element, the call leaves this set unchanged and returns false. In combination with the restriction on constructors, this ensures that sets never contain duplicate elements.
     * null is not a valid value in then set, so the attempt of adding a null to the set will result in the throwing of an un-checked exception
     * @param o  element to be appended to this set. 
     * @return true if this set did not already contain the specified element.  
     * @throws NullPointerException if the specified element is null.
     */
    @Override
    boolean add(Object o);

    /**
     * Adds all of the elements in the specified collection to this set if they're not already present.
     * If the specified collection is also a set, the addAll operation effectively modifies this set so that its value is the union of the two sets. The behavior of this operation is unspecified if the specified collection is modified while the operation is in progress. 
     * @param c  collection whose elements are to be added to this set. 
     * @return true if this set changed as a result of the call.
     * @throws NullPointerException if the specified element is null.
     * @throws NullPointerException if the specified collection contains an element which is null.
     */
    @Override
    boolean addAll(HCollection c);

    /**
     * Removes all of the elements from this set.
     * This set will be empty after this call returns. 
     */
    @Override
    void clear();

    /**
     * Returns true if this set contains the specified element.
     * More formally, returns true if and only if this set contains an element e such that (o==null ? e==null : o.equals(e)). 
     * @param o  element whose presence in this set is to be tested. 
     * @return  true if this set contains the specified element. 
     * @throws NullPointerException if the specified element is null.
     */
    @Override
    boolean contains(Object o);

    /**
     * Returns true if this set contains all of the elements of the specified collection. 
     * If the specified collection is also a set, this method returns true if it is a subset of this set.
     * @param c  collection to be checked for containment in this set. 
     * @return  true if this set contains all of the elements of the specified collection.
     * @throws NullPointerException if the specified element is null.
     * @throws NullPointerException if the specified collection contains an element which is null.
     */
    @Override
    boolean containsAll(HCollection c);

    /**
     * Compares the specified object with this set for equality.
     * Returns true if the specified object is also a set, the two sets have the same size, and every member of the specified set is contained in this set (or equivalently, every member of this set is contained in the specified set). This definition ensures that the equals method works properly across different implementations of the set interface. 
     * @param o  the object to be compared for equality with this set. 
     * @return  true if the specified object is equal to this set.
     */
    @Override
    boolean equals(Object o);

    /**
     * Returns the hash code value for this set.
     * The hash code of a set is defined to be the sum of the hash codes of the elements in the set, where the hashcode of a null element is defined to be zero. This ensures that s1.equals(s2) implies that s1.hashCode()==s2.hashCode() for any two sets s1 and s2, as required by the general contract of the Object.hashCode method. 
     * @return  the hash code value for this set.
     */
    @Override
    int hashCode();

    /**
     * Returns true if this set contains no elements. 
     * @return  true if this set contains no elements.
     */
    @Override
    boolean isEmpty(); 

    /**
     * Returns an iterator over the elements in this set.
     * The elements are returned in no particular order.
     * @return an iterator over the elements in this set.
     */
    @Override
    HIterator iterator();

    /**
     * Removes the specified element from this set if it is present.
     * More formally, removes an element e such that (o==null ? e==null : o.equals(e)), if the set contains such an element. Returns true if the set contained the specified element (or equivalently, if the set changed as a result of the call). (The set will not contain the specified element once the call returns.) 
     * @param o  element to be removed from this set, if present. 
     * @return  true if this set contained the specified element. 
     * @throws NullPointerException if the specified element is null.
     */
    @Override
    boolean remove(Object o);

    /**
     * Removes from this set all of its elements that are contained in the specified collection.
     * If the specified collection is also a set, this operation effectively modifies this set so that its value is the asymmetric set difference of the two sets. 
     * @param c  collection that defines which elements will be removed from this set.  
     * @return  true if this set changed as a result of the call. 
     * @throws NullPointerException if the specified element is null.
     * @throws NullPointerException if the specified collection contains an element which is null.
     * 
     */
    @Override
    boolean removeAll(HCollection c);

    /**
     * Retains only the elements in this set that are contained in the specified collection.
     * In other words, removes from this set all of its elements that are not contained in the specified collection. If the specified collection is also a set, this operation effectively modifies this set so that its value is the intersection of the two sets.
     * @param c  collection that defines which elements this set will retain. 
     * @return  true if this collection changed as a result of the call. 
     * @throws NullPointerException if the specified element is null.
     * @throws NullPointerException if the specified collection contains an element which is null.
     */
    @Override
    boolean retainAll(HCollection c);

    /**
     * Returns the number of elements in this set (its cardinality). 
     * @return the number of elements in this set.
     */
    @Override
    int size();

    /**
     * Returns an array containing all of the elements in this set.
     * @return  an array containing all of the elements in this set.
     */
    @Override
    Object[] toArray();
    
    /**
     * Method not supported yet.
     * @return nothing
     * @throws UnsupportedOperationException always 
     */
    @Override
    Object[] toArray(Object[] a);

}