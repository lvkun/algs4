import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;



public class SAPTest extends TestCase {
    public static Test suite() {
        TestSuite suite = new TestSuite(SAPTest.class);
        return suite;
    }
    
    public void testDigraph1() {
        In in = new In("test\\wordnet\\digraph1.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        
        assertEquals(sap.length(3, 11), 4);
        assertEquals(sap.ancestor(3, 11), 1);
        assertEquals(sap.length(9, 12), 3);
        assertEquals(sap.ancestor(9, 12), 5);
        assertEquals(sap.length(7, 2), 4);
        assertEquals(sap.ancestor(7, 2), 0);
        assertEquals(sap.length(1, 6), -1);
        assertEquals(sap.ancestor(1, 6), -1);
    }
    
    public void testDigraph2() {
        In in = new In("test\\wordnet\\digraph2.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        
        assertEquals(sap.length(1, 5), 2);
        assertEquals(sap.ancestor(1, 5), 0);
    }
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}