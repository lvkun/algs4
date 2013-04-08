import java.util.ArrayList;


public class WordNet {
    
    private BinarySearchST<String, WordInfo> dict;
    private ArrayList<String> wordlist;
    private Digraph hypernyms;
    
    /* 
     * constructor takes the name of the two input files
     */
    public WordNet(String synsets, String hypernyms) {
        readSynsets(synsets);
        readHypernyms(dict.size(), hypernyms);
    }
    
    private class WordInfo {
        private int idx;
        private String gloss;
        
        public WordInfo(int i, String g) {
            idx = i;
            gloss = g;
        }

        public int getIdx() {
            return idx;
        }

        public String getGloss() {
            return gloss;
        }
    }
    
    /**
     * Create wordlist (synsets) from file
     * 
     * @param synsets_filename
     */
    private void readSynsets(String synsetsFilename) {
        In in = new In(synsetsFilename);
        String line = in.readLine();
        
        dict = new BinarySearchST<String, WordInfo>();
        wordlist = new ArrayList<String>();

        while (line != null) {
            String[] items = line.split(",");
            
            if (items.length < 3) {
                continue;
            }
            
            WordInfo wi = new WordInfo(Integer.parseInt(items[0]), items[2]);
            
            dict.put(items[1], wi);
            wordlist.add(items[1]);
            
            line = in.readLine();
        }
    }
    
    private void readHypernyms(int V, String hypernymsFilename) {
        In in = new In(hypernymsFilename);
        String line = in.readLine();

        hypernyms = new Digraph(V);
        while (line != null) {
            String[] items = line.split(",");
            
            if (items.length < 2) {
                continue;
            }
            
            int w = Integer.parseInt(items[0]);
            for (int i = 1; i < items.length; i++) {
               int v = Integer.parseInt(items[i]);
               hypernyms.addEdge(v, w);
            }
            
            line = in.readLine();
        }
    }
    


    /*
     * returns all WordNet nouns
     */
    public Iterable<String> nouns() {
        return dict.keys();
    }

    /*
     * is the word a WordNet noun?
     */
    public boolean isNoun(String word) {
        return dict.contains(word);
    }

    /*
     * distance between nounA and nounB (defined below)
     */
    public int distance(String nounA, String nounB) {
        SAP sap = new SAP(hypernyms);
        int idxA = dict.get(nounA).getIdx();
        int idxB = dict.get(nounB).getIdx();
        return sap.length(idxA, idxB);
    }

    /*
     * a synset (second field of synsets.txt) that is 
     * the common ancestor of nounA and nounB
     * in a shortest ancestral path (defined below)
     */
    public String sap(String nounA, String nounB) {
        SAP sap = new SAP(hypernyms);
        int idxA = dict.get(nounA).getIdx();
        int idxB = dict.get(nounB).getIdx();
        int idxAncestor = sap.ancestor(idxA, idxB);
        return wordlist.get(idxAncestor);
    }

    /*
     * for unit testing of this class
     */
    public static void main(String[] args) {
        
    }
}