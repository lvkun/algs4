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
            return self.capacity - flow
        
    def addResidualFlowTo(self, vertex, delta):
        if vertex == self.v:
            self.flow -= delta
        elif vertex == self.w:
            self.flow += delta
            
from collections import defaultdict            
class FlowNetwork:
    
    def __init__(self, V):
        
        self.V = V
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
        
        value = 0.0
        
        while self.hasAugmentingPath(G, s, t):
            
            bottle = sys.float_info.max
        
    def hasAugmentingPath(self, G, s, t):
        
        self.edgeTo = defaultdict(FlowEdge)
        self.marked = defaultdict(bool)
        
        queue = deque()
        queue.append(s)
        self.marked[s] = True
        
        while len(queue) > 0:
            
            v = queue.popleft()
            for edge in G.adj(v):
                
                w = edge.other(v)
                
                if edge.residualCapacityTo(w) > 0 and not self.marked[w]:
                    
                    self.edgeTo[w] = edge
                    self.marked[w] = True
                    
                    queue.append(w)
                    
        return self.marked[t]