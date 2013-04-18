# Hash


class ChainHash:

    def __init__(self, size, hash):

        self.li = []
        for i in range(size):
            self.li.append([])

        self.hash = hash

    def put(self, key):

        i = self.hash[key]
        self.li[i].insert(0, key)

    def search(self, key):

        i = self.hash[key]

        for k in self.li[i]:
            print k
            if k == key:
                break


class ProbingHash:

    def __init__(self, size, hash):

        self.li = []
        self.size = size
        for i in range(size):
            self.li.append("")
        self.hash = hash

    def put(self, key):

        i = self.hash[key]

        while len(self.li[i]) > 0:
            i = (i + 1) % self.size

        self.li[i] = key

    def search(self, key):

        i = self.hash[key]
        while self.li[i] != key:
            print key
            if len(self.li[i]) == 0:
                return None
            i = (i + 1) % self.size

        return self.li[i]

import re

def getDict(dictInfo):
    pattern = "(\S+)\W+(\d+)"

    dict = {}
    li = []
    for line in dictInfo.split("\n"):
        line = line.strip()
        if len(line) == 0:
            continue

        m = re.match(pattern, line)
        if m != None:
            dict[m.group(1)] = int(m.group(2))
            li.append(m.group(1))

    return dict, li


def check(hash, strli):

    keyList = []
    for key in strli.split():

        keyList.append(str(hash[key]))

    print " ".join(keyList)
    print "0 1 2 3 4 5 6"
    print ""

if __name__ == "__main__":
    separateChainHash = """
     Y    2
     D    2
     H    0
     V    2
     P    2
     J    2
     N    0
     O    1
     L    1
     U    1
     S    2
     M    2
    """
    dict, li = getDict(separateChainHash)

    chain = ChainHash(3, dict)
    for i in li:
        chain.put(i)

    chain.search("P")

    probHash = """
     T    4
     O    9
     V    6
     D    8
     U    5
     K    5
     W    7
     Y    9
     B    6
     Q    1
    """
    dict, li = getDict(probHash)
    prob = ProbingHash(10, dict)

    for i in li:
        prob.put(i)

    print " ".join(prob.li)


    checkHash = """
     B    3
     J    4
     K    5
     O    2
     Q    4
     S    6
     Y    5
    """

    dict, li = getDict(checkHash)
    check(dict, "K Y Q O J B S")
    check(dict, "J Q Y O B K S")
    check(dict, "K S O B Q J Y")
    check(dict, "J K O B Q Y S")
    check(dict, "O S Q J Y B K")

