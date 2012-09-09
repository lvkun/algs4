
class PQ:
    
    def __init__(self):
        self.li = [" "]
        
    def insert(self, el):
        self.li.append(el)
        
        k = len(self.li) - 1
        self.swim(k)
    
    def delMax(self):
        k = 1
        last = len(self.li) - 1
        self.li[k], self.li[last] = self.li[last], self.li[k]
        el = self.li.pop()
        
        self.sink(k)
    
    def setLi(self, li):
        self.li.extend(li)
        
    def swim(self, k):
        while k > 1 and self.li[k/2] < self.li[k]:
            self.li[k/2], self.li[k] = self.li[k], self.li[k/2]
            k = k/2
    
    def sink(self, k):
        while 2*k <= len(self.li) - 1:
            j = 2*k
            if j < len(self.li) - 1 and self.li[j] < self.li[j + 1]:
                j += 1
                
            if self.li[j] < self.li[k]:
                break
            
            self.li[k], self.li[j] = self.li[j], self.li[k]
            
            k = j
    
    def __str__(self):
        return " ".join(self.li)
    
def HeapSort(li):
    i = len(li)/2
    
    while i>=1:
        sink(li, i)
        i -= 1

    return " ".join(li)

def sink(li, k):
    while 2*k <= len(li):
        j = 2*k

        if j < len(li) and li[j-1] < li[j]:
            j += 1
            
        if li[j-1] < li[k-1]:
            break
        
        li[j-1], li[k-1] = li[k-1], li[j-1]
        k = j

if __name__ == "__main__":
    pq1 = PQ()
    pq1.setLi("Z W R V M E F B D G".split())
    print pq1
    pq1.insert("N")
    print pq1
    pq1.insert("L")
    print pq1
    pq1.insert("T")
    print pq1
    
    pq2 = PQ()
    pq2.setLi("Z Y N V E A K R S C".split())
    print pq2
    pq2.delMax()
    print pq2
    pq2.delMax()
    print pq2
    pq2.delMax()
    print pq2

    print HeapSort("I Y D P Z J X C G A".split())
    