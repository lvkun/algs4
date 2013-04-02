from collections import defaultdict

# use defaultdict to simulate array.
class Graph:
    def __init__(self, info):
        self.dict = defaultdict(list)
        for line in info.split("\n"):
            items = line.split(":")
            
            if len(items) < 2:
                continue
            
            key = items[0].strip()
            values = items[1].split()
            self.dict[key] = values

    def adj(self, index):
        return self.dict[index]

    def length(self):
        return len(self.dict)

    def V(self):
        return self.dict.keys()

class DepthFirstSearch:
    def __init__(self, graph, index):
        self.marked = defaultdict(bool)
        self.visited = []
        
        self.dfs(graph, index)

        print " ".join(self.visited)

    def dfs(self, graph, index):
        self.marked[index] = True
        self.visited.append(index)

        for i in graph.adj(index):
            if not self.marked[i]:
                self.dfs(graph, i)

from collections import deque
class BreathFirstSearch:
    def __init__(self, graph, index):
        self.marked = defaultdict(bool)
        self.visited = []

        self.queue = deque()
        
        self.bfs(graph, index)

        print " ".join(self.visited)

    def bfs(self, graph, index):
        self.marked[index] = True
        self.visited.append(index)
        self.queue.append(index)

        while len(self.queue) > 0:
            key = self.queue.popleft()

            for i in graph.adj(key):
                if not self.marked[i]:
                    self.queue.append(i)
                    self.marked[i] = True
                    self.visited.append(i)



def main():

    # Question 1
    g1 = Graph("""
    A:  E F B 
    B:  C A 
    C:  B F 
    D:  G H 
    E:  A 
    F:  A C G 
    G:  H D F 
    H:  G D 
        """)

    dfs = DepthFirstSearch(g1, "A")

    # Question 2
    g2 = Graph("""
    A:  B 
    B:  A F C E 
    C:  G B D 
    D:  G H C 
    E:  B F 
    F:  B E 
    G:  D C H 
    H:  D G 
        """)

    bfs = BreathFirstSearch(g2, "A")

if __name__ == '__main__':
    main()

