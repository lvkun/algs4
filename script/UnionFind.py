class Union:
    def parse(self, union_commands):
        commands = union_commands.split()
        for command in commands:
            id_p,id_q = command.split("-")

            self.union(int(id_p), int(id_q))

class QuickFind(Union):
    def __init__(self, size):
        self.count = size
        self.id = range(size)

    def find(self, p):
        return self.id[p]

    def connected(self, p, q):
        return self.id[p] == self.id[q]

    def union(self, p, q):
        if self.connected(p, q):
            return
        pid = self.id[p]
        for i, el in enumerate(self.id):
            if self.id[i] == pid:
                self.id[i] = self.id[q]

        self.count -= 1

class QuickUnionWeighted(Union):
    def __init__(self, size):
        self.count = size
        self.id = range(size)
        self.union_size = [1 for i in range(size)]

    def find(self, p):
        while p != self.id[p]:
            p = self.id[p]

        return p

    def connected(self, p, q):
        return self.find(p) == self.find(q)

    def union(self, p, q):
        if self.connected(p, q):
            return

        root_p = self.find(p)
        root_q = self.find(q)

        if self.union_size[root_p] >= self.union_size[root_q]:
            self.union_size[root_p] += self.union_size[root_q]
            self.id[root_q] = root_p
        else:
            self.union_size[root_q] += self.union_size[root_p]
            self.id[root_p] = root_q

        self.count -= 1


class TreeNode:
    def __init__(self, value):
        self.parent = None
        self.children = []
        self.value = value
        self.width = 1

    def add_child(self, node):
        node.parent = self
        self.width += node.width
        self.children.append(node)

    def is_root(self):
        return self.parent == None

    def show(self, indent):
        print "-"*indent + str(self.value)
        for child in self.children:
            child.show(indent+1)

def parseUnionResult(result):
    print result
    nodes = [TreeNode(i) for i in range(len(result.split()))]
    for index, parent_index in enumerate(result.split()):
        if index != int(parent_index):
            nodes[int(parent_index)].add_child(nodes[index])

    for node in nodes:
        if node.is_root():
            node.show(0)

if __name__ == "__main__":
    qf = QuickFind(10)
    qf.parse("1-8 2-6 1-2 3-2 9-6 4-0")
    print str(qf.id).replace(",", "")

    uf = QuickUnionWeighted(10)
    uf.parse("6-2 1-4 9-8 1-9 2-0 5-3 2-5 4-7 1-3")
    print str(uf.id).replace(",", "")

    parseUnionResult("0 1 2 0 2 5 0 7 8 9")
    parseUnionResult("6 8 8 8 8 6 8 5 8 8")
    parseUnionResult("3 1 8 1 3 3 3 8 3 3")
    parseUnionResult("0 6 6 5 4 4 5 0 5 2")
    parseUnionResult("8 8 8 9 8 4 8 8 3 4")
