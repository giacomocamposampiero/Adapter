package interfaces;

/**
 * Set interface Java 1.4.2.
 * A collection that contains no duplicate elements. More formally, sets contain no pair of elements e1 and e2 such that e1.equals(e2). As implied by its name, this interface models the mathematical set abstraction.
 * Null elements are not considered as valid elements of the list. An attempt of insertion of a null element will result in an unchecked exception, NullPointerException. The same behaviour is expected as result of any operation on Set elements with a null reference.
 * @author Giacomo Camposampiero
 */
public interface HSet extends HCollection {}