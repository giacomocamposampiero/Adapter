package interfaces;

/**
 * ListIterator interface Java 1.4.2.
 * @author Giacomo Camposampiero
 */
public interface HListIterator extends HIterator {

    /**
     * Inserts the specified element into the list (optional operation).
     * @param o  the element to insert.
     */
    void add(Object o);

    /**
     * Returns true if this list iterator has more elements when traversing the list in the reverse direction.
     * @return     true if the list iterator has more elements when traversing the list in the reverse direction.
     */
    boolean hasPrevious();

    /**
     * Returns the index of the element that would be returned by a subsequent call to next.
     * @return  the index of the element that would be returned by a subsequent call to next.
     */
    int nextIndex();

    /**
     * Returns the previous element in the list.
     * @return the previous element in the list.
     */
    Object previous();

    /** 
     * Returns the index of the element that would be returned by a subsequent call to previous.
     * @return  the index of the element that would be returned by a subsequent call to previous
     */
    int previousIndex();

    /**
     * Replaces the last element returned by next or previous with the specified element (optional operation).
     * @param o  the element with which to replace the last element returned by next or previous. 
     */
    void set(Object o);
    
}