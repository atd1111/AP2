import java.util.Set;

/**	@elements : objects of type E
 *	@structure : linear
 *	@domain :   The elements in the set are not sorted.
 *          	All rows of elements of type E are valid values for a node.
 *       		For every non-empty set the reference current is pointing to an
 *				element in the set.
 *	@constructor - Set();
 *	 **/
public interface SetInterface<E extends Comparable> extends Cloneable{

    /**
     * @pre -
     * @post - New element added to the set, size increased by 1.
     *         New element must be type of BigInteger.
     */
    void add(E element);

    /**
     * @pre -
     * @post - Element removed form the set, size decreased by 1.
     */
    void remove();

    /**
     * @pre - Set is non-empty
     * @post - An element returned from the set, consequently size decreased by 1.
     */
    E getElement();

    /**
     * @pre -
     * @post - Set is cloned with every element.
     */
    void cloneSet();

    /**
     * @pre -
     * @post - TRUE: if set is empty
     *         FALSE: if set is not empty
     */
    boolean isEmpty();

    /**
     * @pre -
     * @post - Size of the set returned as an int.
     */
    int size();

    /**
     * @pre -
     * @post - Set is returned with the union of the set,
     *         from which the method call has occurred, and the operand set.
     *         If both sets are empty then an empty set will be returned.
     */
    List union(List operand);

    /**
     * @pre -
     * @post - Set is returned with the intersection of the set,
     *         from which the method call has occurred, and the operand set.
     *         If both sets are empty then an empty set will be returned.
     */
    List intersection(List operand);

    /**
     * @pre -
     * @post - Set is returned with the intersection of the set,
     *         from which the method call has occurred, and the operand set.
     *         If both sets are empty then an empty set will be returned.
     */
    List complement(List operand);

    /**
     * @pre -
     * @post - Set is returned with the symmetric difference of the set,
     *         from which the method call has occurred, and the operand set.
     *         If both sets are empty then an empty set will be returned.
     */
    List symmetricDifference();
}
