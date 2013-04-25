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
    
    def __str__(self):
        return ("%s %s %f" % (self.v, self.w, self.weight))

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
        self.pq.insert(letter_to_index(start), 0.0)

        while not self.pq.isEmpty():
            v = index_to_letter(self.pq.delMin())

            for edge in graph.adj[v]:
                self.relax(edge)
            print(v)
            result = []
            for k in "A   B   C   D   E   F   G   H".split():
                if self.distTo[k] == maxfloat():
                    result.append("-")
                else:
                    result.append(str(int(self.distTo[k])))
            print(" ".join(result))

    def relax(self, edge):

        v = edge.From()
        w = edge.To()
        #print(edge)
        #print(self.distTo[w], self.distTo[v], edge.weight)
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
            result = []
            for k in "A   B   C   D   E   F   G   H".split():
                if self.distTo[k] == maxfloat():
                    result.append("-")
                else:
                    result.append(str(int(self.distTo[k])))
            print(" ".join(result))

    def relax(self, edge):

        v = edge.From()
        w = edge.To()
        if self.distTo[w] > self.distTo[v] + edge.weight:
            self.distTo[w] = self.distTo[v] + edge.weight
            self.edgeTo[w] = edge

class BellmanFordSP:

    def __init__(self, graph, start):

        self.distTo = defaultdict(maxfloat)
        self.distTo[start] = 0.0
        self.edgeTo = defaultdict(DirectedEdge)

        j = 0
        for i in range(0, graph.V()):
            
            print(j)
            for v in "A   B   C   D   E   F   G   H".split():
                for edge in graph.adj[v]:
                    self.relax(edge)

            result = []
            for k in "A   B   C   D   E   F   G   H".split():
                if self.distTo[k] == maxfloat():
                    result.append("-")
                else:
                    result.append(str(int(self.distTo[k])))
            print(" ".join(result))
            
            j += 1

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
    A->E     9
    A->F     1
    B->A    10
    B->C    39
    B->F    17
    C->D     1
    C->F     5
    C->G     9
    C->H    30
    D->H    20
    F->E     1
    F->G    39
    H->G     5
    """)
    DijkstraSP(graph1, "B")

    print("********")

    graph2 = EdgeWeightedDigraph("""
    A->B    37
    A->F    26
    B->C    27
    C->D     8
    C->G    28
    D->H    22
    E->A     5
    E->F    41
    F->B     9
    F->C    32
    F->G    62
    G->D     3
    G->H     3
    """)
    AcyclicSP(graph2, "E", "E A F B C G D H")

    print("********")

    graph3 = EdgeWeightedDigraph("""
    B->A    36
    B->C     3
    B->E    19
    C->F     0
    D->G    58
    D->H    21
    D->C    66
    E->A    24
    F->E    38
    F->B    17
    G->C     6
    G->F     1
    H->G    31
    """)
    BellmanFordSP(graph3, "D")