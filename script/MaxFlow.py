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
    