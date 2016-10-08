import javax.lang.model.element.Element;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by ATD on 03/10/2016.
 */
public class Parser {

    private static final String DIGITS_REGEX = "[1-9]",
                                LETTER_REGEX = "[a-zA-Z]";
    private static final char[] OPERATION_CHARACTERS = {'!','*','+','-','|','=','\0'};
    private static final char COMMA = ',',
                              PRINT_CHAR = '?',
                              COMMENT_CHAR = '/';
    private static final int ASSIGNMENT = 1,
                             PRINT = 2,
                             COMMENT = 3;

    ArrayList<Identifier> identifierArray;
    ArrayList<BigInteger> set;
    ArrayList miscChars;
    Scanner in;
    private int currentStatementType;

    Parser(String input) {
        in = new Scanner(input);
        in.useDelimiter("");
        identifierArray = new ArrayList<>();
        set = new ArrayList();
        try {
            parseInput();
        } catch (APException e) {
            System.out.println("Valid statements start with ? (printing), / (comment) or with an assignment such as X = ....");
            System.exit(1);
        }

        for (int i = 0; i < identifierArray.size(); i++) {
            System.out.println(identifierArray.get(i).getIdentifier());
        }
        /*
        System.out.println("set: ");
        for (int i = 0; i < set.size(); i++) {
            System.out.print(set.get(i)+" ");
        }

        System.out.println();
        System.out.println("misc chars: ");
        for (int i = 0; i < miscChars.size(); i++) {
            System.out.print(miscChars.get(i));
        }
        */
    }

    /*void printInput() {
        while (in.hasNext()) {
            if (nextCharIsPunctuation(in)) {
                System.out.println("found one");
            }
            System.out.println(nextChar(in));
        }
    }*/

    private char nextChar(Scanner in) {
        return in.next().charAt(0);
    }

    private boolean isNextChar(Scanner in) {
        return in.hasNext();
    }

    private boolean nextCharIs(Scanner in, char c) {
        return in.hasNext(Pattern.quote(c+""));
    }

    private boolean nextCharIsDigit(Scanner in){
        Pattern p = Pattern.compile(DIGITS_REGEX);
        return in.hasNext(p);
    }

    private boolean nextCharIsLetter(Scanner in){
        return in.hasNext(LETTER_REGEX);
    }

    boolean nextCharIsPunctuation(Scanner in) {
        return in.hasNext("^\\w");
    }


    // TODO check if regex can be used here
    private int statementType() {
        char firstChar = nextChar(in);
        if (firstChar >= 'A' && firstChar <= 'Z' || firstChar >= 'a' && firstChar <= 'z') {
            currentStatementType = ASSIGNMENT;
            return ASSIGNMENT;
        } else if (firstChar == PRINT_CHAR) {
            currentStatementType = PRINT;
            return PRINT;
        } else if (firstChar == COMMENT_CHAR) {
            currentStatementType = COMMENT;
            return COMMENT;
        } else {
            return -1;
        }
    }

    private void parseInput() throws APException {
        ArrayList chars = new ArrayList();
        Statement statement = new Statement();
        if (statementStartValid()) {
            statement.setStatementType(currentStatementType);
            while (in.hasNext()) {
                if (nextCharIsLetter(in)) {
                    identifierParser(in,statement);
                } else if (nextCharIsDigit(in)) {
                    digitParser(in,statement);
                    in.useDelimiter("");
                } else {
                    while (in.hasNext() && !nextCharIsLetter(in) && !nextCharIsDigit(in)) {
                        chars.add(nextChar(in));
                    }
                }
            }
            in.close();
            miscChars = chars;
        } else {
            throw new APException("Valid statements start with ? (printing), / (comment) or with an assignment such as X = ....");
        }
    }

    private boolean statementStartValid() {
        return (statementType() == 1 || statementType() == 2 || statementType() == 3);
    }

    private boolean notEndOfIdentifier(Scanner in) {
        for (int i = 0; i < OPERATION_CHARACTERS.length; i++) {
            if (in.hasNext(Pattern.quote(OPERATION_CHARACTERS[i]+""))) {
                return false;
            }
        }
        return true;
    }

    private void identifierParser(Scanner in, Statement statement) {
        Identifier identifier = new Identifier();
        while (notEndOfIdentifier(in) && isNextChar(in)) {
            identifier.identifierBuilder(nextChar(in));
        }
        statement.add((Element) identifier);
        identifierArray.add(identifier);
    }

    private void digitParser(Scanner in, Statement statement) {
        Set set = new Set();
        while (notEndOfSet(in)) {
            StringBuffer numberAsString = new StringBuffer();
            while (areElementsOfSet(in)) {
                numberAsString.append(nextChar(in));
            }
            BigInteger number = new BigInteger(numberToStringWhileChecking(numberAsString));
            statement.add((Element)number);
            set.add(number);
        }
    }

    private boolean notEndOfSet(Scanner in) {
        return (!nextCharIs(in,'}') && isNextChar(in) && nextCharIsDigit(in));
    }

    private boolean areElementsOfSet(Scanner in) {
        return (!nextCharIs(in,COMMA) && isNextChar(in) && !nextCharIs(in,'}'));
    }

    private String numberToStringWhileChecking(StringBuffer number) {
        for (int i = 0; i < number.length(); i++) {
            if (!charIsDigit(number.charAt(i))) {
                numberCorrector(number,i);
            }
        }
        return number.toString();
    }

    private void numberCorrector(StringBuffer number, int indexOfIllegalChar) {
        number.deleteCharAt(indexOfIllegalChar);
    }

    boolean charIsDigit(char c) {
        return Character.toString(c).matches(DIGITS_REGEX);
    }

}
