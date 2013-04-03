import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;



public class OutcastTest extends TestCase {
    public static Test suite() {
        TestSuite suite = new TestSuite(OutcastTest.class);
        return suite;
    }
    
    public void testOutcast() {
        
    }
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}