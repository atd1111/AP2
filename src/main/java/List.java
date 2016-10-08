public class List<E extends Comparable> implements ListInterface<E> {
    Node node;

    private class Node {

        E data;
        Node prior, next;

        public Node(E d) {
            this(d, null, null);
        }

        public Node(E data, Node prior, Node next) {
            this.data = data == null ? null : data;
            this.prior = prior;
            this.next = next;
        }

    }

    @Override
    public boolean isEmpty() {
        /*if (node == null) {
            return true;
        }
        return false;*/

        return (node.data == null);
    }

    @Override
    public ListInterface<E> init() {
        node = null;
        return this;
    }

    @Override
    public int size() {
        Node k = node;
        int size = 1;
        if (k == null) {
            return 0;
        }
        while (k.next != null) {
            size++;
            k = k.next;
        }
        return size;
    }

    @Override
    public ListInterface<E> insert(E d) {
        Node temp = new Node(d);
        if (node == null) {
            node = temp;
        } else {
            node.prior = temp;
            temp.next = node;
            node = temp;

        }
        return this;
    }

    @Override
    public E retrieve() {
        return node.data;
    }

    @Override
    public ListInterface<E> remove() {
        Node k = node;
        if (node.next == null && node.prior == null) {
            node = null;
        } else {
            if (node.prior == null) {
                node = node.next;
                node.prior = null;
            } else {
                k = node.next;
                k.prior = node.prior;
                node = node.prior;
                node.next = k;
            }

        }
        return this;
    }

    // need to check
    @Override
    public boolean find(E d) {
        if(node ==null){
            return false;
        }
        while (node.next != null) {
            if (node.data == d) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean goToFirst() {
        if (node == null) {
            return false;
        }
        while (node.next != null) {
            node = node.next;
        }
        return true;
    }

    @Override
    public boolean goToLast() {
        if (node == null) {
            return false;
        }
        while (node.prior != null) {
            node = node.prior;
        }

        return true;
    }

    @Override
    public boolean goToNext() {
        if (node == null || node.prior == null) {
            return false;
        }
        node = node.prior;
        return true;
    }

    @Override
    public boolean goToPrevious() {
        if (node == null || node.next == null) {
            return false;
        }
        node = node.next;
        return true;
    }

    @Override
    public ListInterface<E> copy() {
        return null;
    }

    @Override
    public ListInterface<E> clone() {
        return null;
    }
}