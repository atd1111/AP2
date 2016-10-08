/**	@elements : objects of type E
 *	@structure : linear
 *	@domain : 	The elements in the node are sorted monotonically increasing.
 *				All rows of elements of type E are valid values for a node.
 *       		For every non-empty node the reference current is pointing to an
 *				element in the node.
 *	@constructor - List();
 *	<dl>
 *		<dt><b>PRE-conditie</b><dd>		-
 *		<dt><b>POST-conditie</b><dd> 	The new List-object is the empty node.
 * </dl>
 **/

public interface ListInterface<E extends Comparable> {

    /**	@precondition -
     *  @postcondition - FALSE: node is not empty.
     *  				TRUE:  node is empty.
     **/
    boolean isEmpty();

    /** @precondition  -
     *	@postcondition - node-POST is empty and has been returned.
     **/
    ListInterface<E> init();

    /**	@precondition  -
     *	@postcondition - The number of elements has been returned.
     **/
    int size();

    /** @precondition  -
     *	@postcondition - Element d has been added to List-PRE.
     *    				current points to the newly added element.
     *   				node-POST has been returned.
     **/
    ListInterface<E> insert(E d);


    /** @precondition  - The node is not empty.
     *	@postcondition -The value of the current element has been returned.
     */
    E retrieve();


    /** @precondition  - The node is not empty.
     * 	@postcondition - The current element of node-PRE is not present in node-POST.
     * 	    			current-POST points to
     *    					- if node-POST is empty
     *   						null
     *   					- if node-POST is not empty
     *     						if current-PRE was the last element of node-PRE
     *     							the last element of node-POST
     *     						else
     *     							the element after current-PRE
     *  				node-POST has been returned.
     **/
    ListInterface<E> remove();


    /** @precondition  -
     *	@postcondition - TRUE:  The node contains the element d.
     *	     			current-POST points to the first element in node that
     *	     			contains the element d.
     *     				FALSE: node does not contain the element d.
     *	     			current-POST points to
     *	      				- if node-POST is empty
     *                    		null
     *	      				- if the first element in node > d:
     *                    		the first element in node
     *        				else
     *	    					the last element in node with value < d
     **/
    boolean find(E d);


    /** @precondition  -
     *	@postcondition - FALSE: node is empty
     *    				TRUE:  current points to the first element
     **/
    boolean goToFirst();


    /**	@precondition  -
     *	@postcondition - FALSE: node is empty
     *     				TRUE:  current points to the last element
     */
    boolean goToLast();


    /** @precondition  -
     *	@postcondition - FALSE: node is empty or current points to the last element
     *     				TRUE:  current-POST points to the next element of current-PRE
     */
    boolean goToNext();


    /** @precondition  -
     *	@postcondition - FALSE: node is empty of current points to the first element
     *     				TRUE:  current-POST points to the prior element of current-PRE
     */
    boolean goToPrevious();

    /**
     * @precondition -
     * @postcondition A deep copy of the node has been returned.
     */
    ListInterface<E> copy();



}