/** @domain : Identifier for a set. Rows of alphanumeric characters starting with a letter.
 *  @elemetns : (??)
 *  @structure : Linear
 *	@constructor - Identifier();
 *
 **/
public interface IdentifierInterface {

    /**
     * @pre -
     * @post - TRUE: if identifier's name is equal to the argument
     *         FALSE: if identifier's name is not equal to the argument
     */
    boolean equals(String name);

    /**
     * @pre -
     * @post - String of the identifier's name is returned
     */
    String getValue();
}
