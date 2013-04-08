
public class SAP {
    private Digraph graph;
    /*
     * constructor takes a digraph (not necessarily a DAG)
     */
    public SAP(Digraph G) {
        graph = G;
    }

    /* 
     * length of shortest ancestral path between v and w; -1 if no such path
     */
    public int length(int v, int w) {
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(graph, w);
        
        int max = -1;
        for (int i = 0; i < graph.V(); i++) {
            int v2i = bfsV.distTo(i);
            int w2i = bfsW.distTo(i);
            int dist = v2i + w2i;
            
            if (v2i != Integer.MAX_VALUE && w2i != Integer.MAX_VALUE 
                    && (max > dist || max == -1)) {
                max = dist;
            }
        }
        
        return max;
    }

    /* 
     * a common ancestor of v and w that participates in a shortest 
     * ancestral path; -1 if no such path
     */
    public int ancestor(int v, int w) {
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(graph, w);
        
        int max = Integer.MAX_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < graph.V(); i++) {
            int v2i = bfsV.distTo(i);
            int w2i = bfsW.distTo(i);
            int dist = v2i + w2i;
            
            if (v2i != Integer.MAX_VALUE && w2i != Integer.MAX_VALUE 
                    && max > dist) {
                max = dist;
                maxIndex = i;
            }
        }
        
        return maxIndex;
    }

    /* 
     * length of shortest ancestral path between any vertex in v and 
     * any vertex in w; -1 if no such path
     */
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(graph, w);
        
        int max = -1;
        for (int i = 0; i < graph.V(); i++) {
            int v2i = bfsV.distTo(i);
            int w2i = bfsW.distTo(i);
            int dist = v2i + w2i;
            
            if (v2i != Integer.MAX_VALUE && w2i != Integer.MAX_VALUE 
                    && (max > dist || max == -1)) {
                max = dist;
            }
        }
        
        return max;
    }

    /* 
     * a common ancestor that participates in shortest ancestral path;
     * -1 if no such path
     */
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(graph, w);
        
        int max = Integer.MAX_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < graph.V(); i++) {
            int v2i = bfsV.distTo(i);
            int w2i = bfsW.distTo(i);
            int dist = v2i + w2i;
            
            if (v2i != Integer.MAX_VALUE && w2i != Integer.MAX_VALUE 
                    && max > dist) {
                max = dist;
                maxIndex = i;
            }
        }
        
        return maxIndex;
    }

    /* 
     * for unit testing of this class (such as the one below)
     */
    public static void main(String[] args) {
        
    }
}