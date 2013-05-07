
# for integer
def LSD_sort(a, W):
    
    R = 10
    N = len(a)
    
    aux = [""] * N
    
    for d in range(W - 1, -1, -1):
        
        count = [0] * (R + 1)
        
        for i in range(0, N):
            count[int(a[i][d]) + 1] += 1
            
        for i in range(0, R):
            count[i + 1] += count[i]
            
        for i in range(0, N):
            aux[count[int(a[i][d])]] = a[i];
            count[int(a[i][d])] += 1
        
        for i in range(0, N):
            a[i] = aux[i]
        
        print(" ".join(a))
        
def MSD_sort(a):
    
    aux = [" "] * len(a)
    msd_sort(a, aux, 0, len(a) - 1, 0)

def get_char(string, i):
    
    if i < len(string):
        return int(string[i])

    return -1

def msd_sort(a, aux, lo, hi, d):
    if hi <= lo:
        return
    
    R = 10
    count = [0] * (R + 2)
    
    for i in range(lo, hi + 1):
        count[get_char(a[i], d) + 2] += 1
    
    for r in range(0, R + 1):
        count[r+1] += count[r]
    
    for i in range(lo, hi + 1):
        aux[count[get_char(a[i], d) + 1]] = a[i]
        count[get_char(a[i], d) + 1] += 1
    
    for i in range(lo, hi + 1):
        a[i] = aux[i - lo]
        
    print(" ".join(a))
        
    for r in range(0, R):
        msd_sort(a, aux, lo + count[r], lo + count[r + 1] - 1, d + 1)

class Quick3Way:
    
    def sort(self, a):
        self.in_sort(a, 0, len(a) - 1, 0)
        
    def in_sort(self, a, lo, hi, d):
        if hi <= lo:
            return
        
        lt = lo
        gt = hi
        
        v = get_char(a[lo], d)
        
        i = lo + 1
        
        while i <= gt:
            
            t = get_char(a[i], d)
            
            if t < v:
                a[i], a[lt] = a[lt], a[i]
                i += 1
                lt += 1
            elif t > v:
                a[i], a[gt] = a[gt], a[i]
                gt -= 1
            else:
                i += 1

        print(" ".join(a))
        
        self.in_sort(a, lo, lt - 1, d)
        if v >= 0:
            self.in_sort(a, lt, gt, d + 1)
        self.in_sort(a, gt + 1, hi, d)
        
            

if __name__ == '__main__':
    
    print("**** LSD sort")
    LSD_sort("2424 2411 1121 3211 4211 1123 4231 2314 4221 1223".split(), 4)
    print()
    print("**** MSD sort")
    print()
    MSD_sort("1424 3143 4434 1342 3413 2131 3433 1232 2232 1122 1233 1213 1243 3443 4421".split())
    print()
    print("**** Quick3Way sort")
    print()
    Quick3Way().sort("6143 2144 8185 5563 5423 4761 7462 4584 6713 7663 2837 6227".split())