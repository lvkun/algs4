# Geometric Searching Applications of BSTs
import re

class Point:
    START = True
    END = False

    def __init__(self, x, y, type, label):
        self.x = x
        self.y = y
        self.type = type
        self.label = label

    def __str__(self):
        return str(self.x) + " " + str(self.y) + " " + self.label

class Line:

    def __init__(self, start, end, label, type):
        self.start = start
        self.end = end
        self.label = label
        self.type = type

    def __str__(self):
        return str(self.start) + " " + str(self.end) + \
            " " + self.label + " " + self.type

def getLineInfo(info):
    pattern = "(\S)\W+(\d+)\W+(\d+)\W+(\d+)\W+(\d+)\W+(\S+)"

    lines = []
    for line in info.split("\n"):
        line = line.strip()
        if len(line) == 0:
            continue

        m = re.match(pattern, line)
        if m != None:
            start = Point(int(m.group(2)), int(m.group(3)), Point.START, m.group(1))
            end = Point(int(m.group(4)), int(m.group(5)), Point.END, m.group(1))
            l = Line(start, end, m.group(1), m.group(6))
            lines.append(l)

    return lines

def getPointInfo(info):
    pattern = "(\S)\W+([\d.]+)\W+([\d.]+)"
    points = []
    for line in info.split("\n"):
        line = line.strip()
        if len(line) == 0:
            continue

        m = re.match(pattern, line)
        if m != None:
            points.append(Point(float(m.group(2)), float(m.group(3)), "", m.group(1)))

    return points

class kdNode:

    Horizontal = True
    Vertical = False

    def __init__(self, key, val, type):
        self.left = None
        self.right = None

        self.key = key
        self.value = val

        self.type = type

    def cmp(self, key):

        if self.type == kdNode.Horizontal:
            return self.key.x - key.x

        return self.key.y - key.y

    def __str__(self):
        return str(self.key)

class Tree:
    def leveltravse(self):
        out = []
        out.append(self.root)
        keys = []
        while len(out) != 0:
            node = out.pop(0)

            if node == None:
                continue
            keys.append(str(node))

            out.append(node.left)
            out.append(node.right)

        print " ".join(keys)

class kdTree(Tree):

    def __init__(self):
        self.root = None

    def put(self, key, value):
        self.root = self.insert(self.root, key, value, kdNode.Horizontal)

    def insert(self, node, key, value, type):

        if node == None:
            return kdNode(key, value, type)

        if node.cmp(key) > 0:
            node.left = self.insert(node.left, key, value, not type)
        elif node.cmp(key) < 0:
            node.right = self.insert(node.right, key, value, not type)
        else:
            node.value = value

        return node




class IntNode:

    def __init__(self, key, val, parent):
        self.left = None
        self.right = None

        self.key = key
        self.val = val
        self.max = key.y
        self.parent = parent

        self.updateMax()

    def cmp(self, key):
        return self.key.x - key.x


    def updateMax(self):
        if self.parent:

            if self.parent.max > self.max:
                return

            self.parent.max = self.max
            self.parent.updateMax()

    def intersect(self, key):
        return max(self.key.x, key.x) < min(self.key.y, key.y)

    def __str__(self):
        return str(self.key) + str(self.max) + "\n"

class IntervalTree(Tree):

    def __init__(self):

        self.root = None

    def put(self, key, value):
        self.root = self.insert(self.root, key, value, None)

    def insert(self, node, key, value, parent):
        if node == None:
            return IntNode(key, value, parent)

        if node.cmp(key) > 0:
            node.left = self.insert(node.left, key, value, node)
        elif node.cmp(key) < 0:
            node.right = self.insert(node.right, key, value, node)
        else:
            node.value = value

        return node

    def search(self, key):
        self.searchNode(self.root, key)

    def searchNode(self, node, key):

        print "search --" + str(node)

        if node == None:
            return None

        if node.intersect(key):
            return node

        if node.left == None:
            return self.searchNode(node.right, key)

        if node.left.max < key.x:
            return self.searchNode(node.right, key)

        return self.searchNode(node.left, key)

if __name__ == "__main__":
    lineInfo = """
    A (14,  1)  ->  (14,  9)  [ vertical   ]
    B (11, 13)  ->  (11, 19)  [ vertical   ]
    C ( 9,  2)  ->  (19,  2)  [ horizontal ]
    D ( 4,  7)  ->  (16,  7)  [ horizontal ]
    E ( 6, 14)  ->  ( 6, 18)  [ vertical   ]
    F ( 5,  8)  ->  (17,  8)  [ horizontal ]
    G (10,  5)  ->  (15,  5)  [ horizontal ]
    H ( 2,  0)  ->  (12,  0)  [ horizontal ]
    """

#    points = getPointInfo(lineInfo)
#    points = sorted(points, key=lambda p: p.x)

    lines = getLineInfo(lineInfo)

    hlines = [line for line in lines if line.type == "horizontal"]

    result = [l for l in hlines if l.start.x < 11 and l.end.x > 11]

    for l in sorted(result, key = lambda line: line.start.y):
        print l


    pointInfo = """
    A (0.25, 0.94)
    B (0.27, 0.77)
    C (0.50, 0.06)
    D (0.53, 0.62)
    G (0.61, 0.23)
    E (0.79, 0.36)
    H (0.56, 0.30)
    F (0.94, 0.11)
    """
    points = getPointInfo(pointInfo)
    kd = kdTree()
    for p in points:
        kd.put(p, p.label)

    kd.leveltravse()

    interInfo = """
    A [ 1, 29]
    B [38, 39]
    C [ 2, 10]
    D [11, 21]
    E [17, 30]
    F [36, 40]
    G [ 9, 23]
    H [13, 27]
    """
    intervals = getPointInfo(interInfo)
    intTree = IntervalTree()
    for i in intervals:
        intTree.put(i, i.label)

    intTree.leveltravse()

    intTree.search(Point(32, 35, "", ""))