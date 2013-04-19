# Shortest Paths
from collections import defaultdict

class DirectedEdge:
    
    def __init__(self, info):
        
        items = info.split(" ")
        vertexs = items[0].split("->")
        self.v = vertexs[0]
        self.w = vertexs[1]
        self.weight = float(items[-1])
        
    def From(self):
        return self.v
    
    def To(self):
        return self.w
    
    def __cmp__(self, other):
        return self.weight - other.weight
    
class EdgeWeightedDigraph:
    
    def __init__(self, lines):
        self.adj = defaultdict(list)
        
        for line in lines.split("\n"):
            line = line.strip()
            
            if len(line) == 0:
                continue
            
            edge = DirectedEdge(line)
            self.addEdge(edge)
    
    def addEdge(self, edge):
        self.adj[edge.v].append(edge)
        
    def V(self):
        return len(self.adj)

from PriorityQueue import PQ
# Consider vertices in increasing order of distance from s
# Add vertex to tree and relax all edges pointing from that vertex
class DijkstraSP:
    
    def __init__(self, graph, start):
        self.distTo = defaultdict(float)
        self.edgeTo = defaultdict(DirectedEdge)
        
        self.pq = PQ()
        
    def relax(self, edge):
        
        v = edge.From()
        w = edge.To()
        if self.distTo[v] > self.distTo[w]:
            self.pq.insert("")
    
if __name__ == "__main__":
    graph = EdgeWeightedDigraph("""
    A->B    29
    A->E     2
    A->F    54
    B->C     8
    C->D    10
    C->F     7
    C->G    48
    D->G    34
    D->H    62
    E->F    48
    F->B     3
    F->G    41
    G->H    20
    """)