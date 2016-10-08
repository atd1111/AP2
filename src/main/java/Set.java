/**
 * Created by ATD on 03/10/2016.
 */
public class Set implements SetInterface {

    List list;


    @Override
    public void add(Comparable element) {
        list.insert(element);
    }

    @Override
    public void remove() {

    }

    @Override
    public Comparable getElement() {
        return null;
    }

    @Override
    public void cloneSet() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public List union(List operand) {
        return null;
    }

    @Override
    public List intersection(List operand) {
        return null;
    }

    @Override
    public List complement(List operand) {
        return null;
    }

    @Override
    public List symmetricDifference() {
        return null;
    }

}
