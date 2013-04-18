
class Node:

    def __init__(self, key, value, level):
        self.key = key
        self.value = value
        self.level = level
        self.left = None
        self.right = None

class BST:

    def __init__(self):
        self.root = None

    def put(self, key, value):
        self.root = self.insert(self.root, key, value, 0)

    def insert(self, node, key, value, level):

        if node == None:
            return Node(key, value, level)

        if node.key > key:
            node.left = self.insert(node.left, key, value, level+1)
        elif node.key < key:
            node.right = self.insert(node.right, key, value, level+1)
        else:
            node.value = value

        return node

    def get(self, key):
        return self.search(self.root, key)

    def search(self, node, key):
        if node == None:
            return None

        print node.key, key

        if node.key > key:
            return self.search(node.left, key)
        elif node.key < key:
            return self.search(node.right, key)
        else:
            return node.value

    def printOut(self, node):

        if node == None:
            return

        print " " * node.level + str(node.key) + "\r\n",

        self.printOut(node.left)
        self.printOut(node.right)

    def leveltravse(self):
        out = []
        out.append(self.root)
        keys = []
        while len(out) != 0:
            node = out.pop(0)

            if node == None:
                continue
            keys.append(node.key)

            out.append(node.left)
            out.append(node.right)

        print " ".join(keys)

    def deleteMax(self):
        root = self.delMax(root)

    def delMax(self, node):
        if node.right == None:
            return node.left

        node.right = delMax(node.right)
        return node

    def deleteMin(self):
        root = self.delMin(root)

    def delMin(self, node):
        if node.left == None:
            return node.right
        node.left = self.delMin(node.left)
        return node

    def deleteKey(self, key):
        self.root = self.delete(self.root, key)

    def delete(self, node, key):
        if node == None:
            return None

        if node.key > key:
            node.left = self.delete(node.left, key)
        elif node.key < key:
            node.right = self.delete(node.right, key)
        else:
            if node.left == None:
                return node.right
            if node.right == None:
                return node.left

            temp = node
            node = self.min(temp.right)
            node.right = self.delMin(temp.right)
            node.left = temp.left

        return node

    def min(self, node):
        if node.left == None:
            return node

        return self.min(node.left)

if __name__ == "__main__":
    bst1 = BST()

    for i, k in enumerate("C G D V U Z N K T R".split()):
        bst1.put(k.strip(), i)

#    bst1.printOut(bst1.root)
    bst1.get("R")

    bst2 = BST()
    for i, k in enumerate("N T A P D H E V L Q".split()):
        bst2.put(k.strip(), i)

#    bst2.printOut(bst2.root)
    bst2.leveltravse()

    bst3 = BST()
    for i, k in enumerate("A F N K R I P Z H U Y V".split()):
        bst3.put(k.strip(), i)

#    bst3.printOut(bst3.root)
    bst3.deleteKey("V")
    bst3.deleteKey("I")
    bst3.deleteKey("N")
#    bst3.printOut(bst3.root)
    bst3.leveltravse()