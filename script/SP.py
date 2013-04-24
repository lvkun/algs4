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

class IndexMinPQ:
    
    def __init__(self):
        self.MAXSIZE = 200 + 1
        
        self.N = 0
        self.keys = [None] * self.MAXSIZE 
        self.pq = [-1] * self.MAXSIZE
        self.qp = [-1] * self.MAXSIZE
    
    def contains(self, k):
        return self.qp[k] != -1
    
    def isEmpty(self):
        return self.N == 0
    
    def size(self):
        return self.N
        
    def insert(self, k, key):
        if self.contains(k):
            return
        
        self.N += 1
        self.qp[k] = self.N
        self.pq[self.N] = k
        self.keys[k] = key
        
        self.swim(self.N)
        
    def change(self, k, key):
        if not self.contains(k):
            return
        
        self.keys[k] = key
        self.swim(self.qp[k])
        self.sink(self.qp[k])

    def delMin(self):
        if self.N == 0:
            return None
        
        min = self.pq[1]
        self.exch(1, self.N)
        self.N -= 1
        self.sink(1)
        
        self.qp[min] = -1
        self.keys[self.pq[self.N+1]] = None
        self.pq[self.N+1] = -1
        
        return min

    def greater(self, i, j):
        return self.keys[self.pq[i]] > self.keys[self.pq[j]]
    
    def exch(self, i, j):
        self.pq[i], self.pq[j] = self.pq[j], self.pq[i]
        self.qp[self.pq[i]] = i
        self.qp[self.pq[j]] = j

    def swim(self, k):
        while k > 1 and self.greater(int(k/2), k):
            self.exch(k, int(k/2))
            k = int(k/2)

    def sink(self, k):
        while 2*k <= self.N:
            j = 2*k
            if j < self.N and self.greater(j, j+1):
               j += 1
            if not self.greater(k, j):
                break
            self.exch(k, j)
            k = j

import sys
def maxfloat():
    return sys.float_info.max
# Consider vertices in increasing order of distance from s
# Add vertex to tree and relax all edges pointing from that vertex
class DijkstraSP:

    def __init__(self, graph, start):
        self.distTo = defaultdict(maxfloat)
        self.distTo[start] = 0.0
        self.edgeTo = defaultdict(DirectedEdge)

        self.pq = IndexMinPQ()
        self.pq.insert(0, 0.0)

        while not self.pq.isEmpty():
            v = index_to_letter(self.pq.delMin())
            
            for edge in graph.adj[v]:
                self.relax(edge)
            print(v)
            for i in "A   B   C   D   E   F   G   H".split():
                print(self.distTo[i])

    def relax(self, edge):
        
        v = edge.From()
        w = edge.To()
        if self.distTo[w] > self.distTo[v] + edge.weight:
            self.distTo[w] = self.distTo[v] + edge.weight
            self.edgeTo[w] = edge
            
            if self.pq.contains(letter_to_index(w)):
                self.pq.change(letter_to_index(w), self.distTo[w])
            else:
                self.pq.insert(letter_to_index(w), self.distTo[w])

class AcyclicSP:
    
    def __init__(self, graph, start, topological):
        
        self.distTo = defaultdict(maxfloat)
        self.distTo[start] = 0.0
        self.edgeTo = defaultdict(DirectedEdge)
        
        for v in topological.split():
            for edge in graph.adj[v]:
                self.relax(edge)
            
            print(v)
            for i in "A   B   C   D   E   F   G   H".split():
                print(self.distTo[i])
                 
    def relax(self, edge):
        
        v = edge.From()
        w = edge.To()
        if self.distTo[w] > self.distTo[v] + edge.weight:
            self.distTo[w] = self.distTo[v] + edge.weight
            self.edgeTo[w] = edge
 

def letter_to_index(ch):
    return ord(ch) - ord("A")

def index_to_letter(i):
    return chr(i + ord("A"))

if __name__ == "__main__":
    graph1 = EdgeWeightedDigraph("""
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
    DijkstraSP(graph1, "A")
    
    print("********")
    
    graph2 = EdgeWeightedDigraph("""
    A->E    15
    A->F     1
    B->A    21
    B->F     4
    C->B    34
    C->F    26
    D->C     0
    D->H    33
    F->E    39
    G->C     8
    G->D     3
    G->F    30
    G->H    46
    """)
    AcyclicSP(graph2, "G", "G D H C B A F E")