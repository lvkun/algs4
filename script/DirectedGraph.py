
from UndirectedGraph import Graph, BreathFirstSearch
from collections import defaultdict
from collections import deque

class TopologicalSort:
    def __init__(self, graph, index):
        self.marked = defaultdict(bool)
        self.visited = deque()

        for i in graph.V():
            if not self.marked[i]:
                self.dfs(graph, i)

        print " ".join(self.visited)

    def dfs(self, graph, index):
        self.marked[index] = True

        for i in graph.adj(index):
            if not self.marked[i]:
                self.dfs(graph, i)

        self.visited.appendleft(index)

def main():

    g1 = Graph("""
    A:  F E 
    B:  A C G F 
    C:  G 
    D:  C 
    E:  F 
    F:  G 
    G:  H 
    H:  C D 
        """)
    BreathFirstSearch(g1, "A")

    g2 = Graph("""
    A:  E F 
    B:  A C G F 
    C:  G D H 
    D:  H 
    E:  
    F:  G E 
    G:  H 
    H:  
        """)
    TopologicalSort(g2, "A")

if __name__ == '__main__':
    main()
