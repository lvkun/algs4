mergeTime = 0

def merge(ary, aux, lo, mid, hi):
    lo_index = lo
    hi_index = mid

    index = lo
    # copy to aux
    while index != hi:
        aux[index] = ary[index]
        index += 1

    index = lo
    while (lo_index != mid or hi_index != hi):
        if lo_index == mid:
            ary[index] = aux[hi_index]
            hi_index += 1
        elif hi_index == hi:
            ary[index] = aux[lo_index]
            lo_index += 1
        elif aux[lo_index] <= aux[hi_index]:
            ary[index] = aux[lo_index]
            lo_index += 1
        elif aux[lo_index] > aux[hi_index]:
            ary[index] = aux[hi_index]
            hi_index += 1

        index += 1

    global mergeTime
    mergeTime += 1
    print mergeTime
    print " ".join(ary)

def top_down_merge_sort(ary, aux, lo, mid, hi):

    if mid - lo > 1:
        top_down_merge_sort(ary, aux, lo, (mid+lo)/2, mid)
    if hi - mid > 1:
        top_down_merge_sort(ary, aux, mid, (mid+hi)/2, hi)
    merge(ary, aux, lo, mid, hi)


def bottom_up_merge_sort(ary, aux):
    step = 2
    while step < len(ary)*2:
        index = 0
        while index < len(ary):

            if len(ary) - index < step:
                merge(ary, aux, index, (index+len(ary))/2, len(ary))
            else:
                merge(ary, aux, index, index+step/2, index+step)
            index += step

        step *= 2

import re
class Point:
    def __init__(self, x, y, name=""):
        self.x = x
        self.y = y
        self.name = name

    @staticmethod
    def createFrom(info):
        pattern = "(\S) \(([\d.]+), ([\d.]+)\)"
        m = re.match(pattern, info)
        return Point(float(m.group(2)), float(m.group(3)), m.group(1))

def ccw(p1, p2, p3):
    return (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x)

import math
def angle(p1, p2):
    return (p2.x - p1.x) / math.sqrt( (p2.x - p1.x)*(p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y) )

def Graham_scan(points_info):
    points = []
    for line in points_info.split("\n"):
        line = line.strip()
        if len(line) != 0:
            points.append(Point.createFrom(line))

    miny_index = 0
    index = 0
    while index < len(points):
        if points[miny_index].y > points[index].y:
            miny_index = index
        index += 1

    points[0], points[miny_index] = points[miny_index], points[0]
    points[1:] = sorted(points[1:], key = lambda po: angle(po, points[0]))
    for p in points:
        print p.name
    vex = points[:2]

    index = 2
    droped = []
    while index < len(points):

        while True:

            if len(vex) < 2:
                vex.append(points[index])
                break

            p1 = vex[-2]
            p2 = vex[-1]
            p3 = points[index]

            c = ccw(p1, p2, p3)
            #print c
            if c < 0:
                drop = vex.pop()
                droped.append(drop.name)
                continue
            elif c > 0:
                vex.append(p3)
                break;

        index += 1

    print " ".join(droped)
    for v in vex:
        print v.name, v.x, v.y

if __name__ == "__main__":
#    ary = "I K B Q Z P A T S F E D".split()
#    aux = "I K B Q Z P A T S F E D".split()
#    top_down_merge_sort(ary, aux, 0, len(ary)/2, len(ary))
#    ary = "Z X V P H C Y T U D".split()
#    aux = "Z X V P H C Y T U D".split()
#    bottom_up_merge_sort(ary, aux)

    points_info = """
    A (8.0, 4.0)
    B (1.0, 7.0)
    C (7.0, 0.0)
    D (6.0, 6.0)
    E (9.0, 9.0)
    F (3.0, 8.0)
    G (2.0, 5.0)
    H (5.0, 1.0)
    I (4.0, 2.0)
    J (0.0, 3.0)
    """
    Graham_scan(points_info)