import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;



public class WordNetTest extends TestCase {
    public static Test suite() {
        TestSuite suite = new TestSuite(WordNetTest.class);
        return suite;
    }
    
    public void testWordNet() {
        
    }
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}