/**
 * Created by ATD on 03/10/2016.
 */
public class Identifier implements IdentifierInterface {

    private StringBuffer identifier;


    Identifier () {
        identifier = new StringBuffer();
    }

    public void identifierBuilder(char c) {
        //System.out.println("appending: "+c);
        identifier.append(c);
    }

    public String getIdentifier() {
        return identifier.toString();
    }

    @Override
    public boolean equals(String name) {
        return false;
    }

    @Override
    public String getValue() {
        return null;
    }
}
