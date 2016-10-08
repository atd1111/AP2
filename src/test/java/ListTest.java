import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created by Sebastian on 08/08/15.
 */
public class ListTest {

    @Before
    public void setUp() {

        // Add any maintenance which is necessary to set up your tests.
    }

    @Test
    public void testIsEmpty() {
        // Test an empty node.
        List<Letter> list = new List<>();
        assertTrue("New node should be empty", list.isEmpty());

        list.insert(new Letter('a'));
        assertFalse("Adding one element should return false.", list.isEmpty());

        list.remove();
        assertTrue("Removing should make node empty again.", list.isEmpty());

        // TODO: You can add more of your own tests.
    }

    @Test
    public void testInit() {
        List<Letter> list = new List<>();

        // Create an empty node with init.
        list.init();
        assertTrue("Init on empty node should return an empty node", list.isEmpty());

        // Add item, init should still be empty.
        list.insert(new Letter('a'));
        list.init();
        assertTrue("Init on non-empty node should return an empty node", list.isEmpty());

        // TODO: You can add more of your own tests.
    }

    @Test
    public void testSize() {
        List<Letter> list = new List<>();

        assertEquals("Empty node should be size 0", 0, list.size());

        // Insert one item
        list.insert(new Letter('a'));
        assertEquals("List of one element should have size 1", 1, list.size());

        // Add 200 items to the node.
        for (int i = 0; i < 200; i++) {
            list.insert(new Letter('a'));
        }
        assertEquals("Adding many elements should result in a long node", 201, list.size());

        // Remove 1 item -> 200 items left
        list.remove();
        assertEquals("Removing one item should decrement the size", 200, list.size());

        // Init should empty the node.
        list.init();
        assertEquals("Init should set size to zero", 0, list.size());

        // TODO: You can add more of your own tests.
    }

    @Test
    public void testInsert() {
        List<Letter> list = new List<>();

        /* Insert first item
         * Inserting into empty node is an edge case.
         */
        Letter c = new Letter('c');
        list.insert(c);
        assertEquals("Inserted element should be in node", c, list.retrieve());

        // Append second item
        Letter f = new Letter('f');
        list.insert(f);

        list.goToLast();
        assertEquals("Insert should order larger elements later in node", f, list.retrieve());

        list.goToFirst();
        assertEquals("Previous elements should still be in node", c, list.retrieve());


        // Insert in front of node
        // Test that the node is sorted correctly when inserting a smaller item.
        Letter a = new Letter('a');
        list.insert(a);
        list.goToFirst();
        assertEquals("Insert should order smaller elements earlier in node", a, list.retrieve());


        // Insert at the end.
        // Test that the node is sorted correctly when inserting a larger item.
        Letter k = new Letter('k');
        list.insert(k);
        list.goToLast();
        assertEquals(k, list.retrieve());


        // Insert between two items.
        // The order has to be preserved when inserting an item between existing items.
        Letter b = new Letter('b');
        list.insert(b);
        list.goToFirst();
        assertEquals(a, list.retrieve());
        list.goToNext();
        assertEquals(b, list.retrieve());
        list.goToNext();
        assertEquals(c, list.retrieve());

        // TODO: You can add more of your own tests. e.g.:
        // Insert duplicate item.
    }

    @Test
    public void testRetrieve() {

        List<Letter> list = new List<>();

        Letter z = new Letter('z');
        list.insert(z);

        Letter letter = list.retrieve();
        assertEquals("Retrieve should return an equal object", z, letter);

        // TODO: You can add more of your own tests.
    }

    @Test
    public void testRemove() {
        List<Letter> list = new List<>();
        Letter a = new Letter('a');
        Letter b = new Letter('b');
        Letter c = new Letter('c');
        Letter d = new Letter('d');
        list.insert(a);
        list.insert(b);
        list.insert(c);
        list.insert(d);
        // Remove an element in the middle
        list.goToFirst();
        list.goToNext();
        list.remove();

        assertEquals(c, list.retrieve());

        // Remove last element in node
        list.goToLast();
        list.remove();
        assertEquals(c, list.retrieve());


        // Remove on node with size 1
        list.remove();
        list.remove();
        try {
            assertNull(list.retrieve()); // Inconsistent specification. Undefined behaviour for retrieve on empty node.
        } catch (NullPointerException e) {
        }

        // TODO: You can add more of your own tests.
    }

    @Test
    public void testFind() {

        // TODO: You can add more of your own tests.
    }

    @Test
    public void testgoToFirst() {

        List<Letter> list = new List<>();

        // Test on empty node
        assertFalse(list.goToFirst());

        Letter a = new Letter('a');
        Letter b = new Letter('b');
        Letter c = new Letter('c');
        Letter d = new Letter('d');
        list.insert(a);
        list.insert(b);
        list.insert(c);
        list.insert(d);

        // Test on node with some elements.
        assertTrue(list.goToFirst());

        assertEquals(a, list.retrieve());

        // TODO: You can add more of your own tests.
    }

    @Test
    public void testgoToLast() {
        List<Letter> list = new List<>();

        // Test on empty node
        assertFalse(list.goToLast());

        Letter a = new Letter('a');
        Letter b = new Letter('b');
        Letter c = new Letter('c');
        Letter d = new Letter('d');
        list.insert(a);
        list.insert(b);
        list.insert(c);
        list.insert(d);

        // Test on node with some elements.
        assertTrue(list.goToLast());
        assertEquals(d, list.retrieve());

        // TODO: You can add more of your own tests.
    }

    @Test
    public void testgoToNext() {
        List<Letter> list = new List<>();

        // Test on empty node
        assertFalse(list.goToNext());

        Letter a = new Letter('a');
        Letter b = new Letter('b');
        Letter c = new Letter('c');
        Letter d = new Letter('d');
        list.insert(a);
        list.insert(b);
        list.insert(c);
        list.insert(d);

        // Test on last element
        assertFalse(list.goToNext());
        assertEquals(d, list.retrieve());


        // Test on first
        list.goToFirst();
        assertTrue(list.goToNext());
        assertEquals(b, list.retrieve());

        // TODO: You can add more of your own tests.
    }

    @Test
    public void testgoToPrevious() {

        List<Letter> list = new List<>();

        // Test on empty node
        assertFalse(list.goToNext());

        Letter a = new Letter('a');
        Letter b = new Letter('b');
        Letter c = new Letter('c');
        Letter d = new Letter('d');
        list.insert(a);
        list.insert(b);
        list.insert(c);
        list.insert(d);

        // Test on last element
        list.goToLast();
        assertTrue(list.goToPrevious());
        assertEquals(c, list.retrieve());

        // Test on first
        list.goToFirst();
        assertFalse(list.goToPrevious());
        assertEquals(a, list.retrieve());

        // TODO: You can add more of your own tests.
    }


    /**
     * Represents a comparable and clonable Letter.
     *
     * This internal class is only used for testing.
     * It is independent of any of your Implementations.
     *
     * If you write your own tests you may also use your own
     * Implementations (i.e., of Identifier).
     */
    private class Letter implements Comparable<Letter>, Cloneable {

        private char letter;

        public Letter(char c){
            this.letter = c;
        }

        public int compareTo(Letter l) {
            return this.letter - l.letter;
        }

        public Letter clone() {
            return new Letter(this.letter);
        }

        /*
         * Tests whether o is the same as this Letter.
         *
         * For assertEquals() to work an equals() method is necessary.
         *
         * Adapt this method for any of your classes that you use in assertEquals(). 
         */
        public boolean equals(Object o){

            // First clause: Test whether o is null before treating it as an object.
            // Second clause: Test whether o is of correct type.
            //                Change this type when copying to another class.
            if(o != null && o.getClass() == getClass()) {

                // Now we know that o is not null and has the same type as this.

                // Do any calculation to determine whether o and this are the same Letter.
                // In this case, Letter implements Comparable, so we can use compareTo().
                return this.compareTo((Letter)o) == 0;
            }

            // Since o was null or not of type Letter, we can safely conclude
            // that o does not equal this.
            return false;
        }
    }
}