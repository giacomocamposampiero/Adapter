package interfaces;

/**
 * List interface Java 1.4.2.
 * An ordered collection (also known as a sequence). The user of this interface has precise control over where in the list each element is inserted. The user can access elements by their integer index (position in the list), and search for elements in the list.
 * Unlike Set, List allows duplicate elements. More formally, List allows pairs of elements e1 and e2 such that e1.equals(e2).
 * Null elements are not considered as valid elements of the list. An attempt of insertion of a null element will result in an unchecked exception, NullPointerException. The same behaviour is expected as result of any operation on Set elements with a null reference.
 * @author Giacomo Camposampiero
 */
public interface HList extends HCollection {

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
    
    //METODI DI LIST e basta
    
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
     */
    boolean addAll(int index, HCollection c);
    
    /**
     * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
     * @param fromIndex  low endpoint (inclusive) of the subList.
     * @param toIndex  high endpoint (exclusive) of the subList. 
     * @return  a view of the specified range within this list. 
     */
    HList subList(int fromIndex, int toIndex);
    
    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from the list. 
     * @param index the index of the element to removed.   
     * @return the element previously at the specified position.
     * @throws IndexOutOfBoundsException if the index parameter isn't in the interval [0, size()]
     * @throws NullPointerException if the element parameter is null
     */
    Object remove(int index);
    
    /**
     * Replaces the element at the specified position in this list with the specified element.
     * @param index  index of element to replace.
     * @param element element to be stored at the specified position. 
     * @return the element previously at the specified position. 
     * @throws IndexOutOfBoundsException if the index parameter isn't in the interval [0, size()-1]
     * @throws NullPointerException if the element parameter is null
     */
    Object set(int index, Object element);
    
    /**
     * Returns the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element.
     * More formally, returns the highest index i such that (o==null ? get(i)==null : o.equals(get(i))), or -1 if there is no such index. 
     * @param o  element to search for. 
     * @return the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element. 
     * @throws NullPointerException if the element parameter is null
     */
    int lastIndexOf(Object o);

    /**
     * Returns a list iterator of the elements in this list (in proper sequence).
     * @return a list iterator of the elements in this list (in proper sequence).
     */
    HListIterator listIterator();

    /**
     * Returns a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list.
     * @param index  index of first element to be returned from the list iterator (by a call to the next method). 
     * @return  a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list. 
     */
    HListIterator listIterator(int index);
    
    /**
     * Returns the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
     * More formally, returns the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))), or -1 if there is no such index. 
     * @param o  element to search for.     
     * @return  the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element. 
     * @throws NullPointerException if the element parameter is null
     */
    int indexOf(Object o);
    
    /**
     * Returns the element at the specified position in this list.
     * @param index index of element to return. 
     * @return  the element at the specified position in this list. 
     * @throws IndexOutOfBoundsException if the index parameter isn't in the interval [0, size()-1]
     */
    Object get(int index);
    
    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices). 
     * @param index index at which the specified element is to be inserted.
     * @param element element to be inserted. 
     * @throws IndexOutOfBoundsException if the index parameter isn't in the interval [0, size()]
     * @throws NullPointerException if the element parameter is null
     */
    void add(int index, Object element);
    
}