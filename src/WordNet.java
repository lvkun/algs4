import java.util.ArrayList;


public class WordNet {
    
    private class Word {
        private String synset;
        private String gloss;
        
        public Word(String synset, String gloss) {
            this.setSynset(synset);
            this.setGloss(gloss);
        }

        public void setSynset(String synset) {
            this.synset = synset;
        }

        public String getSynset() {
            return synset;
        }

        public void setGloss(String gloss) {
            this.gloss = gloss;
        }

        public String getGloss() {
            return gloss;
        }
    }
    
    private ArrayList<Word> wordlist;
    
    private Digraph hypernyms;
    
    /**
     * Create wordlist (synsets) from file
     * 
     * @param synsets_filename
     */
    private void readSynsets(String synsets_filename) {
        In in = new In(synsets_filename);
        String line = in.readLine();
        
        wordlist = new ArrayList<Word>();
        while(line != null) {
            String[] items = line.split(",");
            
            if (items.length < 3) {
                continue;
            }
            
            Word word = new Word(items[1], items[2]);
            wordlist.add(word);
            
            line = in.readLine();
        }
    }
    
    private void readHypernyms(int V, String hypernyms_filename) {
        In in = new In(hypernyms_filename);
        String line = in.readLine();

        hypernyms = new Digraph(V);
        while(line != null) {
            String[] items = line.split(",");
            
            if (items.length < 2) {
                continue;
            }
            
            int v = Integer.parseInt(items[0]);
            for (int i = 1; i < items.length; i++) {
               int w = Integer.parseInt(items[i]);
               hypernyms.addEdge(v, w);
               
            }
            
            line = in.readLine();
        }
    }
    
    /* 
     * constructor takes the name of the two input files
     */
    public WordNet(String synsets, String hypernyms){
        
        readSynsets(synsets);
        readHypernyms(wordlist.size(), hypernyms);
        
    }

    /*
     * returns all WordNet nouns
     */
    public Iterable<String> nouns() {
        ArrayList<String> nouns = new ArrayList<String>();
        for (int i = 0; i < wordlist.size(); i++) {
            nouns.add(wordlist.get(i).synset);
        }
        return nouns;
    }

    /*
     * is the word a WordNet noun?
     */
    public boolean isNoun(String word) {
        return true;
    }

    /*
     * distance between nounA and nounB (defined below)
     */
    public int distance(String nounA, String nounB) {
        return 0;
    }

    /*
     * a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
     * in a shortest ancestral path (defined below)
     */
    public String sap(String nounA, String nounB) {
        return null;
    }

    /*
     * for unit testing of this class
     */
    public static void main(String[] args) {
        
    }
}