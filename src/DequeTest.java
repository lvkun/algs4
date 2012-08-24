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

        deque.addFirst(2);
        assertEquals(deque.size(), 2);
    }

    public void testAddLast() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);

        assertEquals(deque.size(), 1);
        assertEquals(deque.isEmpty(), false);

        deque.addLast(2);
        assertEquals(deque.size(), 2);
    }

    public void testIteratorAddFirst() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);

        Iterator<Integer> iter = deque.iterator();
        assertEquals(iter.hasNext(), true);
        assertEquals((int) iter.next(), 3);
        assertEquals((int) iter.next(), 2);
        assertEquals((int) iter.next(), 1);
    }

    public void testIteratorAddLast() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        Iterator<Integer> iter = deque.iterator();
        assertEquals(iter.hasNext(), true);
        assertEquals((int) iter.next(), 1);
        assertEquals((int) iter.next(), 2);
        assertEquals((int) iter.next(), 3);
    }

    public void testRemoveFirst() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        
        assertEquals((int) deque.removeFirst(), 3);
        assertEquals(deque.size(), 2);
        assertEquals((int) deque.removeFirst(), 2);
        assertEquals(deque.size(), 1);
        assertEquals((int) deque.removeFirst(), 1);
        assertEquals(deque.size(), 0);
    }
    
    public void testRemoveLast() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        
        assertEquals((int) deque.removeLast(), 3);
        assertEquals(deque.size(), 2);
        assertEquals((int) deque.removeLast(), 2);
        assertEquals(deque.size(), 1);
        assertEquals((int) deque.removeLast(), 1);
        assertEquals(deque.size(), 0);
    }
    
    public void testAddFirstExceptional() {
        Deque<Integer> deque = new Deque<Integer>();
        try {
            deque.addFirst(null);
            fail("No exception");
        } catch (java.lang.NullPointerException expected) {
            System.out.println("catch expected exception");
        }
        
    }
    
    public void testAddLastExceptional() {
        Deque<Integer> deque = new Deque<Integer>();
        try {
            deque.addLast(null);
            fail("No exception");
        } catch (java.lang.NullPointerException expected) {
            System.out.println("catch expected exception");
        }
    }
    
    public void testRemoveFirstExceptional() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        
        deque.removeFirst();
        try {
            deque.removeFirst();
            fail("No Exception");
        } catch (java.util.NoSuchElementException expected) {
            System.out.println("catch expected exception");
        }
    }
    
    public void testRemoveLastExceptional() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        
        deque.removeLast();
        try {
            deque.removeLast();
            fail("No Exception");
        } catch (java.util.NoSuchElementException expected) {
            System.out.println("catch expected exception");
        }
    }
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
