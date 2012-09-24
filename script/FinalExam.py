
import UnionFind
import sort
import MergeSort
import QuickSort
import PriorityQueue
import BST
import RedBlackBST
import GeoSearch
import Hash

def binarySearch(ary, key, lo, hi):    
    mid = (lo + hi)/2
    item = ary[mid]
    print item
    if lo == hi - 1:
        print "not found"
        return
    
    if item > key:
        binarySearch(ary, key, lo, mid)
    elif item < key:
        binarySearch(ary, key, mid, hi)
    else:
        print "found" + str(key)
    
    return


def run(N):
    
    sum = 0;
    i = 1
    
    while i*i*i <= N:
        
        j = i
        
        while j*j*j < N:
            
            k = j
            
            while k*k*k < N:
                k += 1
            
                sum += 1
            j += 1
        
        i += 1
    
    return sum

if __name__ == "__main__":
    
    # 1
    
    uf = UnionFind.QuickUnionWeighted(10)
    uf.parse("6-1 1-9 9-0 8-5 7-4 8-4 6-8 7-3 6-2")
    print str(uf.id).replace(",", "")
    
#    UnionFind.parseUnionResult("3 5 5 6 3 3 6 3 3 3")
#    UnionFind.parseUnionResult("4 7 4 1 4 7 4 4 4 4")
#    UnionFind.parseUnionResult("9 0 0 0 0 4 1 4 0 5")
#    UnionFind.parseUnionResult("0 7 8 3 7 6 8 8 3 5")
#    UnionFind.parseUnionResult("0 7 2 5 4 5 6 7 5 9")
    
    # 2
    ary = [int(i) for i in "22 24 28 30 32 49 50 57 64 67 72 77 85 96 98".split()]
    binarySearch(ary, 39, 0, 15)
    
    # 4
    i = 100
    for i in range(100, 100000, 10000):
        print i, run(i)
        
    # 7
    ary = "15 31 47 50 55 75 18 49 10 80".split()
    sort.insertion_sort(ary, 6)
    
    # 8
    ary = "80 86 11 90 84 45 14 75 85 92".split()
    aux = "80 86 11 90 84 45 14 75 85 92".split()
    MergeSort.bottom_up_merge_sort(ary, aux)
    
    # 9
    ary = "38 40 93 37 77 28 36 56 89 64 46 88".split()
    QuickSort.parti(ary, 0, len(ary)-1)
    print " ".join(ary)
    
    # 10
    pq = PriorityQueue.PQ()
    pq.setLi("Z W R V M E F B D G".split())
    
    
    print pq
    pq.delMax()
    print pq
    pq.delMax()
    print pq
    pq.delMax()
    print pq
    
    # 14
    bst = BST.BST()
    for i, k in enumerate("49 58 16 75 71 43 82 10 40 90".split()):
        bst.put(k.strip(), i)
        
    bst.leveltravse()
    
    # 15
    tree1 = RedBlackBST.RedBlackBST()
    for i, k in enumerate("56 50 77 16 51 58 89 11 43 78 28 75 64".split()):
        tree1.put(k.strip(), i)
        
    tree1.leveltravse()
    
    # 16
    pointInfo = """
    A (0.08, 0.09)
    B (0.26, 0.04)
    C (0.23, 0.37)
    D (0.07, 0.70)
    E (0.56, 0.58)
    F (0.39, 0.95)
    G (0.49, 0.07)
    H (0.47, 0.96)
    """
    points = GeoSearch.getPointInfo(pointInfo)
    kd = GeoSearch.kdTree()
    for p in points:
        kd.put(p, p.label)
        
    kd.leveltravse()
    
    # 18 
    
    separateChainHash = """
     O    1
     J    2
     U    1
     G    2
     F    1
     D    2
     I    1
     K    0
     P    2
     V    2
     A    2
     L    1
    """
    dict, li = Hash.getDict(separateChainHash)
    
    chain = Hash.ChainHash(3, dict)
    for i in li:
        chain.put(i)
        
    chain.search("P")