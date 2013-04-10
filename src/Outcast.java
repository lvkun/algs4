
public class Outcast {
    private WordNet wn;
    /* 
     * constructor takes a WordNet object
     */
    public Outcast(WordNet wordnet) {
        wn = wordnet;
    }

    /* 
     * given an array of WordNet nouns, return an outcast
     */
    public String outcast(String[] nouns) {        
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < nouns.length; i++) {
            int dist = 0;
            for (int j = 0; j < nouns.length; j++) {
                dist += wn.distance(nouns[i], nouns[j]);
            }
            
            if (max < dist) {
                max = dist;
                maxIndex = i;
            }
        }
        
        return nouns[maxIndex];
    }

    /* 
     * for unit testing of this class (such as the one below)
     */
    public static void main(String[] args) {
        
    }
}