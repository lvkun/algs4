#
class FlowEdge:

    def __init__(self, v, w, capacity):
        self.v = v
        self.w = w
        self.capacity = capacity
        self.flow = 0

    def From(self):
        return self.v

    def To(self):
        return self.w

    def other(self, vertex):
        if self.v == vertex:
            return self.w
        elif self.w == vertex:
            return self.v

    def residualCapacityTo(self, vertex):
        if vertex == self.v:
            return self.flow
        elif vertex == self.w:
            return self.capacity - self.flow

    def addResidualFlowTo(self, vertex, delta):
        if vertex == self.v:
            self.flow -= delta
        elif vertex == self.w:
            self.flow += delta

from collections import defaultdict
class FlowNetwork:

    def __init__(self):
        self.adj = defaultdict(list)

    def addEdge(self, edge):

        v = edge.From()
        w = edge.To()

        self.adj[v].append(edge)
        self.adj[w].append(edge)

from collections import deque
import sys
class FordFulkerson:

    def __init__(self, G, s, t):

        self.value = 0.0

        while self.hasAugmentingPath(G, s, t):

            bottle = sys.float_info.max

            v = t
            while v != s:
                bottle = min(bottle, self.edgeTo[v].residualCapacityTo(v))
                v = self.edgeTo[v].other(v)

            v = t
            while v != s:
                self.edgeTo[v].addResidualFlowTo(v, bottle)
                v = self.edgeTo[v].other(v)

            self.value += bottle
            
            # print out augmenting path
            path = deque()
            v = t
            
            while v != s:
                
                if not v in self.edgeTo:
                    break
                
                path.appendleft(v)
                v = self.edgeTo[v].other(v)
            
            path.appendleft(s)
            print(" ".join(path))

    def hasAugmentingPath(self, G, s, t):
        self.edgeTo = defaultdict(FlowEdge)
        self.marked = defaultdict(bool)
        queue = deque()
        queue.append(s)
        self.marked[s] = True

        while len(queue) > 0:

            v = queue.popleft()
            for edge in G.adj[v]:

                w = edge.other(v)

                if edge.residualCapacityTo(w) > 0 and not self.marked[w]:

                    self.edgeTo[w] = edge
                    self.marked[w] = True
                    queue.append(w)
        
        return self.marked[t]

    def inCut(self, v):
        return self.marked[v]

def createFlowEdge(line):
    items = line.split()
    vertexs =  items[0].split("->")
    v = vertexs[0]
    w = vertexs[1]
    flow = int(items[1])
    capacity = int(items[3])
    edge = FlowEdge(v, w, capacity)
    edge.flow = flow
    return edge

def createFlowNetWork(info):
    
    g = FlowNetwork()
    
    for line in info.split("\n"):
        line = line.strip()
        if len(line) > 0:
            edge = createFlowEdge(line)
            g.addEdge(edge)
            
    return g
            

if __name__ == "__main__":
    fn1 = createFlowNetWork("""
    A->B     10  /  10
    A->G     28  /  28
    A->F      1  /   6
    B->G      3  /   5
    B->H      7  /   7
    B->C      0  /   4
    H->C      8  /  11
    C->D      8  /   8
    I->D      5  /  14
    D->H      0  /   6
    D->J      8  /   8
    D->E      5  /   5
    E->J      5  /   5
    F->G      1  /   7
    G->H     32  /  32
    H->I     31  /  38
    I->J     26  /  38
    """)
    FordFulkerson(fn1, "A", "J")

    fn2 = createFlowNetWork("""
    A->F      6  /   6
    A->B     15  /  15
    A->G      9  /  15
    G->B      2  /  14
    B->H     12  /  12
    B->C      5  /   5
    C->D      5  /   9
    C->H      0  /  14
    D->J      5  /   7
    D->E      0  /  12
    D->H      0  /  11
    D->I      0  /   5
    E->J      0  /   6
    F->G      6  /  12
    G->H     13  /  15
    H->I     25  /  26
    I->J     25  /  25
    """)
    mf2 = FordFulkerson(fn2, "A", "J")
    path = []
    for v in "A B C D E F G H I J".split():
        if mf2.marked[v]:
            path.append(v)
            
    print(" ".join(path))
