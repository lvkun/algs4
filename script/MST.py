# Minimum Spanning Trees
from collections import defaultdict

class Edge:

    def __init__(self, info):

        items = info.split(" ")
        vertexs = items[0].split("-")

        self.v = vertexs[0]
        self.w = vertexs[1]
        self.weight = int(items[-1])

    def either(self):
        return self.v

    def other(self, vertex):
        if vertex == self.v:
            return self.w

        return self.v


class EdgeWeightedGraph:

    def __init__(self, lines):

        self.adj = defaultdict(list)

        for line in lines.split("\n"):
            line = line.strip()

            if len(line) == 0:
                continue

            edge = Edge(line)
            self.addEdge(edge)

    def addEdge(self, edge):

        v = edge.either()
        w = edge.other(v)

        self.adj[v].append(edge)
        self.adj[w].append(edge)

    def edges(self):

        ret = []
        for i in self.adj.keys():

            for edge in self.adj[i]:

                if edge.other(i) > i:
                    ret.append(edge)

                # not consider self loop now

        return ret

    def V(self):
        return len(self.adj)

from Queue import PriorityQueue
from UnionFind import QuickUnionWeighted
# Add next edge to tree T unless doing so would create a cycle
def Kruskal(graph):
    mst = []

    queue = PriorityQueue()

    for edge in graph.edges():
        queue.put((edge.weight, edge))

    uf = QuickUnionWeighted(graph.V())
    while not queue.empty():
        edge = queue.get()[1]

        v = edge.either()
        w = edge.other(v)

        if not uf.connected(letter_to_index(v), letter_to_index(w)):
            uf.union(letter_to_index(v), letter_to_index(w))
            print (edge.weight)
            mst.append(edge)

    return mst

# Start with vertex 0 and greedily grow tree T.
# Add to T the min weight edge with exactly one endpoint in T.
# Repeat until V - 1 edges.

class LazyPrim:

    def __init__(self, graph, start):
        self.marked = defaultdict(bool)
        self.pq = PriorityQueue()
        self.mst = []
        self.graph = graph

        self.visit(start)

        while not self.pq.empty():
            edge = self.pq.get()[1]
            v = edge.either()
            w = edge.other(v)
            if self.marked[v] and self.marked[w]:
                continue

            self.mst.append(edge)
            print edge.weight

            if not self.marked[v]:
                self.visit(v)
            if not self.marked[w]:
                self.visit(w)

    def visit(self, v):
        self.marked[v] = True

        for edge in self.graph.adj[v]:
            if not self.marked[edge.other(v)]:
                self.pq.put((edge.weight, edge))


def letter_to_index(ch):
    return ord(ch) - ord("A")

def index_to_letter(i):
    return chr(i + ord("A"))

def main():

    graph = EdgeWeightedGraph("""
    A-F    16
    A-B     4
    A-G     1
    B-C     5
    B-G     3
    C-I     9
    C-H     8
    C-D     6
    G-C     2
    D-I    17
    E-D    12
    J-D     7
    J-E    13
    G-F    15
    H-G    10
    I-H    11
    J-I    14""")

    Kruskal(graph)

    print ""

    graph2 = EdgeWeightedGraph("""
    A-B      17
    A-F       9
    B-G      14
    B-C      12
    B-F       5
    H-B       4
    C-D      10
    C-I       6
    C-H       1
    E-D      11
    D-I       3
    D-J       2
    E-J      16
    F-G       8
    H-G       7
    I-H      15
    J-I      13""")

    LazyPrim(graph2, "G").mst

if __name__ == '__main__':
    main()