package interfaces;

/**
 * Collection interface Java 1.4.2.
 * @author Giacomo Camposampiero
 */
public interface HCollection {

    /**
     * Ensures that this collection contains the specified element (optional operation). Returns true if this collection changed as a result of the call. (Returns false if this collection does not permit duplicates and already contains the specified element.) 
     * @param o  element whose presence in this collection is to be ensured. 
     * @return  true if this collection changed as a result of the call  
     * @throws NullPointerException whether the parameter is a null reference, which is not considered a valid type of entry for the collection
     */
    boolean add(Object o);

    /**
     * Adds all of the elements in the specified collection to this collection. 
     * The behavior of this operation is undefined if the specified collection is modified while the operation is in progress.     * The behavior of this operation is undefined if the specified collection is modified while the operation is in progress.
     * @param c  elements to be inserted into this collection.
     * @return  true if this collection changed as a result of the call. 
     */
    boolean addAll(HCollection c);

    /**
     * Removes all of the elements from this collection.
     * This collection will be empty after this method returns unless it throws an exception. 
     */
    void clear();

    /**
     * Returns true if this collection contains the specified element.
     * More formally, returns true if and only if this collection contains at least one element e such that (o==null ? e==null : o.equals(e)). 
     * @param o  element whose presence in this collection is to be tested. 
     * @return  true if this collection contains the specified element. 
     * @throws NullPointerException if the parameters is null.
     */
    boolean contains(Object o);

    /**
     * Returns true if this collection contains all of the elements of the specified collection.
     * @param c  collection to be checked for containment in this collection. 
     * @return  true if this collection contains all of the elements of the specified collection. 
     * @throws NullPointerException if the parameters is null.
     * @throws NullPointerException if the specified collection contains one or more null elements.
     */
    boolean containsAll(HCollection c);

    /**
     * Compares the specified object with this collection for equality.
     * The general contract for the Object.equals method states that equals must be symmetric (in other words, a.equals(b) if and only if b.equals(a)). The contracts for List.equals and Set.equals state that collections are only equal to other collections, and collections to other collections. Thus, a custom equals method for a collection class that implements neither the List nor Set interface must return false when this collection is compared to any collection or collection. (By the same logic, it is not possible to write a class that correctly implements both the Set and List interfaces.) 
     * @param o  the object to be compared for equality with this collection. 
     * @return  true if the specified object is equal to this collection.
     */
    @Override
    boolean equals(Object o);

    /**
     * Returns the hash code value for this collection.
     * In particular, c1.equals(c2) implies that c1.hashCode()==c2.hashCode(). 
     * @return the hash code value for this collection.
     */
    @Override
    int hashCode();

    /**
     * Returns true if this collection contains no elements.
     * @return  true if this collection contains no elements.
     */
    boolean isEmpty(); 

    /**
     * Returns an iterator over the elements in this collection. 
     * There are no guarantees concerning the order in which the elements are returned. 
     * @return  an iterator over the elements in this collection.
     */
    HIterator iterator();

    /**
     * Removes the first occurrence in this collection of the specified element, if it is present.
     * More formally, removes an element e such that (o==null ? e==null : o.equals(e)), if this collection contains one or more such elements. Returns true if this collection contained the specified element (or equivalently, if this collection changed as a result of the call). 
     * @param o  element to be removed from this collection, if present. 
     * @return  true if this collection contained the specified element. 
     * @throws NullPointerException if the parameters is null.
     */
    boolean remove(Object o);

    /**
     * Removes from this collection all the elements that are contained in the specified collection.
     * After this call returns, this collection will contain no elements in common with the specified collection. 
     * @param c  collection that defines which elements will be removed from this collection.  
     * @return  true if this collection changed as a result of the call. 
     * @throws NullPointerException if the parameters is null.
     * @throws NullPointerException if the specified collection contains one or more null elements.
     */
    boolean removeAll(HCollection c);

    /**
     * Retains only the elements in this collection that are contained in the specified collection.
     * In other words, removes from this collection all of its elements that are not contained in the specified collection.  
     * @param c  collection that defines which elements this collection will retain. 
     * @return  true if this collection changed as a result of the call. 
     * @throws NullPointerException if the parameters is null.
     * @throws NullPointerException if the specified collection contains one or more null elements.
     */
    boolean retainAll(HCollection c);

    /**
     * Returns the number of elements in this collection.
     * @return the number of elements in this collection.
     */
    int size();

    /**
     * Returns an array containing all of the elements in this collection.
     * The returned array will be "safe" in that no references to it are maintained by this collection. (In other words, this method must allocate a new array even if this collection is backed by an array). The caller is thus free to modify the returned array.
     * @return  an array containing all of the elements in this collection.
     */
    Object[] toArray();
    
    /**
     * Method not supported yet.
     * @return nothing
     * @throws UnsupportedOperationException always 
     */
    Object[] toArray(Object[] a);
    
}