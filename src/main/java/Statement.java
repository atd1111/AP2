import javax.lang.model.element.Element;

/**
 * Created by ATD on 08/10/2016.
 */
public class Statement {
    private static final String STATEMENT_COMMENT = "comment",
                                STATEMENT_ASSIGNMENT = "assignment",
                                STATEMENT_PRINT = "print";
    private static final int ASSIGNMENT = 1,
                             PRINT = 2,
                             COMMENT = 3;

    private Element[] statementArray;
    private boolean statementIsComment;
    private boolean statementIsAssignment;
    private boolean statementIsPrint;

    Statement() {
        statementArray = new Element[0];
        statementIsAssignment = false;
        statementIsComment = false;
        statementIsPrint = false;
    }

    public void add(Element element) {
        Element[] tempArray = copyArray(new Element[statementArray.length + 1],0,statementArray.length);
        tempArray[tempArray.length - 1] = element;
        statementArray =  tempArray;
    }

    private Element[] copyArray(Element[] result, int startOfRange, int endOfRange) {
        int index = 0;
        for (int i = startOfRange; i < endOfRange; i++) {
            result[index] = statementArray[i];
            index += 1;
        }

        return result;
    }

    public Element[] getStatementArray() {
        return statementArray;
    }

    public String getStatementType() {
        if (statementIsComment) {
            return STATEMENT_COMMENT;
        } else if (statementIsAssignment){
            return STATEMENT_ASSIGNMENT;
        } else {
            return STATEMENT_PRINT;
        }
    }

    public void setStatementType(int type) {
        if (type == ASSIGNMENT) {
            statementIsAssignment = true;
        } else if (type == COMMENT) {
            statementIsComment = true;
        } else if (type == PRINT) {
            statementIsPrint = true;
        }
    }

}
