
def parti(ary, lo, hi):
    mid = lo

    while True:
        lo += 1
        while(ary[lo] < ary[mid]):
            lo += 1
            if lo == hi:
                break

        hi -= 1
        while(ary[mid] < ary[hi]):
            hi -= 1
            if hi == lo:
                break

        if lo >= hi:
            break

        ary[lo], ary[hi] = ary[hi], ary[lo]

    ary[mid] ,ary[lo-1] = ary[lo-1], ary[mid]

def tParti(ary, lo, hi):

    lt = lo
    ht = hi
    v = ary[lo]

    while lo <= ht:
        if ary[lo] < v:
            ary[lt], ary[lo] = ary[lo], ary[lt]
            lt += 1
            lo += 1
        elif ary[lo] > v:
            ary[ht], ary[lo] = ary[lo], ary[ht]
            ht -=1
        else:
            lo += 1

if __name__ == "__main__":
    ary = "D W G C Y A J O E Z F S".split()
    parti(ary, 0, len(ary)-1)
    print " ".join(ary)

    ary = "B A A A B B A B A B B A".split()
    parti(ary, 0, len(ary))
    print " ".join(ary)

    ary = "J U J Z W J D Y K B".split()
    tParti(ary, 0, len(ary)-1)
    print " ".join(ary)