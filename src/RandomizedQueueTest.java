
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

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
