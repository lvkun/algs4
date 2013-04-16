import java.util.ArrayList;


public class WordNet {
    
    private RedBlackBST<String, ArrayList<Integer>> dict;
    private ArrayList<String> wordlist;
    private Digraph hypernymsGraph;
    private SAP sap;
    private int maxIndex;
    
    /* 
     * constructor takes the name of the two input files
     */
    public WordNet(String synsets, String hypernyms) {
        readSynsets(synsets);
        int root = readHypernyms(hypernyms);
        
        DirectedCycle dc = new DirectedCycle(hypernymsGraph);
        
        if (root > 1 || dc.hasCycle()) {
            throw new java.lang.IllegalArgumentException();
        }
        
        sap = new SAP(hypernymsGraph);
    }
    
    private void addToDict(String word, int idx) {
        if (dict.contains(word)) {
            ArrayList<Integer> idxs = dict.get(word);
            idxs.add(idx);
        } else {
            ArrayList<Integer> idxs = new ArrayList<Integer>();
            idxs.add(idx);
            dict.put(word, idxs);
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
        
        dict = new RedBlackBST<String, ArrayList<Integer>>();
        wordlist = new ArrayList<String>();
        
        String[] items = {"0"};
        while (line != null) {
            items = line.split(",");
            
            if (items.length < 3) {
                continue;
            }
            
            String[] words = items[1].split(" ");
            
            for (String word : words) {
                addToDict(word, Integer.parseInt(items[0]));
            }
            
            
            wordlist.add(items[1]);
            
            line = in.readLine();
        }
        
        maxIndex = Integer.parseInt(items[0]) + 1;
    }
    
    private int readHypernyms(String hypernymsFilename) {
        In in = new In(hypernymsFilename);
        String line = in.readLine();
        hypernymsGraph = new Digraph(maxIndex);
        int unroots = 0;
        
        while (line != null) {
            String[] items = line.split(",");
            
            if (items.length < 2) {
                line = in.readLine();
                continue;
            }
            
            int v = Integer.parseInt(items[0]);
            unroots += 1;
            for (int i = 1; i < items.length; i++) {
               int w = Integer.parseInt(items[i]);
               hypernymsGraph.addEdge(v, w);
            }
            line = in.readLine();
        }
        
        return maxIndex - unroots;
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
        Iterable<Integer> idxA = dict.get(nounA);
        Iterable<Integer> idxB = dict.get(nounB);
        
        if (idxA == null || idxB == null) {
            throw new java.lang.IllegalArgumentException();
        }
        
        return sap.length(idxA, idxB);
    }

    /*
     * a synset (second field of synsets.txt) that is 
     * the common ancestor of nounA and nounB
     * in a shortest ancestral path (defined below)
     */
    public String sap(String nounA, String nounB) {
        Iterable<Integer> idxA = dict.get(nounA);
        Iterable<Integer> idxB = dict.get(nounB);
        
        if (idxA == null || idxB == null) {
            throw new java.lang.IllegalArgumentException();
        }
        
        int idxAncestor = sap.ancestor(idxA, idxB);
        return wordlist.get(idxAncestor);
    }

    /*
     * for unit testing of this class
     */
    public static void main(String[] args) {
        
    }
}