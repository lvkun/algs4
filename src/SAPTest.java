import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;



public class SAPTest extends TestCase {
    public static Test suite() {
        TestSuite suite = new TestSuite(SAPTest.class);
        return suite;
    }
    
    public void testSAP() {
        
    }
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}