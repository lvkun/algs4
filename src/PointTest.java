import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PointTest extends TestCase {
    
    public static Test suite() {
        TestSuite suite = new TestSuite(PointTest.class);
        return suite;
    }
    
    public void testCreation() {
        Point p = new Point(0, 0);
        assertEquals(p.toString(), "(0, 0)");
    }
    
    public void testCompareTo() {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        
        assertTrue(p1.compareTo(p2) < 0);
        
        Point p3 = new Point(4, 4);
        assertTrue(p2.compareTo(p3) < 0);
    }
    
    public void testSlopeTo() {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        
        assertEquals(p1.slopeTo(p2), 1.0);
        
        Point p3 = new Point(3, 4);
        assertEquals(p2.slopeTo(p3), Double.NEGATIVE_INFINITY);
        
        Point p4 = new Point(3, 5);
        assertEquals(p2.slopeTo(p4), Double.POSITIVE_INFINITY);
        
        Point p5 = new Point(6, 4);
        assertEquals(p2.slopeTo(p5), 0.0);
    }
    
    public void testBySlope() {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(3, 6);
        
        assertTrue(p1.SLOPE_ORDER.compare(p2, p3) < 0);
        
        Point p4 = new Point(4, 2);
        Point p5 = new Point(1, 3);
        
        assertTrue(p1.SLOPE_ORDER.compare(p5, p4) > 0);
    }
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
