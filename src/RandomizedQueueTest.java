import java.util.Iterator;
import java.util.NoSuchElementException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class RandomizedQueueTest extends TestCase {

    public static Test suite() {
        TestSuite suite = new TestSuite(RandomizedQueueTest.class);
        return suite;
    }
    
    public static void testCreation() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        assertEquals(queue.isEmpty(), true);
        assertEquals(queue.size(), 0);
    }
    
    public static void testEnqueue() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        
        queue.enqueue(0);
        assertEquals(queue.isEmpty(), false);
        assertEquals(queue.size(), 1);
        
        queue.enqueue(1);
        assertEquals(queue.isEmpty(), false);
        assertEquals(queue.size(), 2);
    }
    
    public static void testEnqueueException() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        try {
            queue.enqueue(null);
            fail("no exception caught");
        } catch (NullPointerException exception) {
            System.out.println("catch NullPointerException");
        }
    }
    
    public static void testResize() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        try {
            queue.enqueue(0);
            queue.enqueue(1);
            queue.enqueue(2);
            queue.enqueue(3);
            queue.enqueue(4);
        } catch (ArrayIndexOutOfBoundsException unexpected) {
            fail("throw exception");
        }
        
        assertEquals(queue.size(), 5);
    }
    
    public static void testDequeue() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        
        queue.enqueue(0);
        int item = queue.dequeue();
        
        assertEquals(item, 0);
        assertEquals(queue.size(), 0);
    }
    
    public static void testDequeueException() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        
        try {
            queue.dequeue();
            fail("no exception caught");
        } catch (NoSuchElementException exception) {
            System.out.println("catch NoSuchElementException");
        }
    }
    
    public static void testSample() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        
        queue.enqueue(0);
        int item = queue.sample();
        
        assertEquals(item, 0);
        assertEquals(queue.size(), 1);
    }
    
    public static void testSampleException() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        
        try {
            queue.sample();
            fail("no exception caught");
        } catch (NoSuchElementException exception) {
            System.out.println("catch NoSuchElementException");
        }
    }
    
    public static void testIterator() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        
        Iterator<Integer> iter = queue.iterator();
        int size = 0;
        while (iter.hasNext()) {
            System.out.println(iter.next());
            size++;
        }
        
        assertEquals(size, 5);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
