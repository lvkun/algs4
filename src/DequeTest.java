import java.util.Iterator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DequeTest extends TestCase {

    public static Test suite() {
        TestSuite suite = new TestSuite(DequeTest.class);
        return suite;
    }
    
    public void testCreation() {
        Deque<Integer> deque = new Deque<Integer>();
        assertEquals(deque.size(), 0);
        assertEquals(deque.isEmpty(), true);
    }
    
    public void testAddFirst() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        assertEquals(deque.size(), 1);
        assertEquals(deque.isEmpty(), false);
    }
    
    public void testIterator() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        Iterator<Integer> iter = deque.iterator();
        assertEquals(iter.hasNext(), false);
    }
    
    public static void main(String args[]) {
        junit.textui.TestRunner.run(suite());
    }
}
