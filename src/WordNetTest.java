import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;



public class WordNetTest extends TestCase {
    public static Test suite() {
        TestSuite suite = new TestSuite(WordNetTest.class);
        return suite;
    }
    
    public void testIsNoun() {
        WordNet wn = new WordNet("test\\wordnet\\synsets.txt", 
        "test\\wordnet\\hypernyms.txt");
        assertTrue(wn.isNoun("'s_Gravenhage"));
    }
    
    public void testSynsets6() {
        WordNet wn = new WordNet("test\\wordnet\\synsets6.txt", 
                "test\\wordnet\\hypernyms6TwoAncestors.txt");
        assertTrue(wn.isNoun("a"));
        assertFalse(wn.isNoun("ab"));
    }
    
    public void testNouns() {
        WordNet wn = new WordNet("test\\wordnet\\synsets6.txt", 
        "test\\wordnet\\hypernyms6TwoAncestors.txt");
        String[] nouns = {"a", "b", "c", "d", "e", "f"};
        int i = 0;
        for (String n : wn.nouns()) {
            assertEquals(nouns[i], n);
            i++;
        }
    }
    
    public void testDistance() {        
        WordNet wn15 = new WordNet("test\\wordnet\\synsets15.txt", 
        "test\\wordnet\\hypernymsTree15.txt");
        assertEquals(wn15.distance("b", "c"), 2);
    }
    
    public void testExceptionConstructor() {
        try {
            WordNet wn = new WordNet("test\\wordnet\\synsets3.txt", 
            "test\\wordnet\\hypernymsInvalidCycle.txt");
            fail("No exception");
        } catch (java.lang.IllegalArgumentException expected) {
            System.out.println("catch expected exception");
        }
    }
    
    public void testExceptionTwoRoots() {
        try {
            WordNet wn = new WordNet("test\\wordnet\\synsets3.txt", 
            "test\\wordnet\\hypernymsInvalidTwoRoots.txt");
            fail("No exception");
        } catch (java.lang.IllegalArgumentException expected) {
            System.out.println("catch expected exception");
        }
    }
    
    public void testExceptionSap() {
        try {
            WordNet wn = new WordNet("test\\wordnet\\synsets15.txt", 
            "test\\wordnet\\hypernymsTree15.txt");
            wn.sap("invalid", "a");
            fail("No exception");
        } catch (java.lang.IllegalArgumentException expected) {
            System.out.println("catch expected exception");
        }
    }
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}