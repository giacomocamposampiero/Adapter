package interfaces;

/**
 * List interface Java 1.4.2.
 * @author Giacomo Camposampiero
 */
public interface HList extends HCollection {

    /**
     * Inserts all of the elements in the specified collection into this list at the specified position (optional operation).
     * @param index  index at which to insert first element from the specified collection.
     * @param c  elements to be inserted into this list. 
     * @return  true if this list changed as a result of the call. 
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
     * Removes the element at the specified position in this list (optional operation).
     * @param index      the index of the element to removed.   
     * @return  the element previously at the specified position. 
     */
    Object remove(int index);
    
    /**
     * Replaces the element at the specified position in this list with the specified element (optional operation).
     * @param index  index of element to replace.
     * @param element     element to be stored at the specified position. 
     * @return  the element previously at the specified position. 
     */
    Object set(int index, Object element);
    
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
     * Returns the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
     * @param o  element to search for.     
     * @return  the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element. 
     */
    int indexOf(Object o);
    
    /**
     * Returns the element at the specified position in this list.
     * @param index  index of element to return. 
     * @return  the element at the specified position in this list. 
     */
    Object get(int index);
    
    /**
     * Inserts the specified element at the specified position in this list (optional operation).
     * @param index  index at which the specified element is to be inserted.
     * @param element  element to be inserted. 
     */
    void add(int index, Object element);
    
}