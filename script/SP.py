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

    def vertexs(self):
        return self.adj.keys()

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
            for i in "A   B   C   D   E   F   G   H".split():
                result.append(str(int(self.distTo[i])))
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
            for i in "A   B   C   D   E   F   G   H".split():
                result.append(str(int(self.distTo[i])))
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

        for i in range(0, graph.V()):
            for v in graph.vertexs():
                for edge in graph.adj[v]:
                    self.relax(edge)

            print(i)
            result = []
            for k in "A   B   C   D   E   F   G   H".split():
                result.append(str(int(self.distTo[k])))
            print(" ".join(result))

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
    A->B    34
    A->F     2
    B->C     4
    B->G    11
    C->D    22
    C->G     2
    C->H     5
    E->A    30
    E->F     1
    F->B    71
    F->G    75
    H->D    10
    H->G     5
    """)
    DijkstraSP(graph1, "E")

    print("********")

    graph2 = EdgeWeightedDigraph("""
    A->E    29
    A->F     4
    B->A    12
    B->F     7
    C->B    10
    C->D    26
    C->F    24
    C->G    82
    D->G    53
    D->H    32
    F->E    24
    G->F     3
    H->G    18
    """)
    AcyclicSP(graph2, "C", "C D H G B A F E")

    print("********")

    graph3 = EdgeWeightedDigraph("""
    A->E    14
    B->A    19
    B->F    33
    B->E    41
    C->B    10
    D->C    28
    D->H     4
    E->F     2
    G->F    26
    G->C     5
    G->B     0
    H->G    27
    H->C    23
    """)
    BellmanFordSP(graph3, "D")