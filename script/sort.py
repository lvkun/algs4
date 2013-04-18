def shell_sort(ary, step):
    for i in range(step, len(ary)):
        pos = i - step
        while pos >= 0:
            if ary[pos + step] < ary[pos]:
                ary[pos + step], ary[pos] = ary[pos], ary[pos + step]
            pos -= step
    print(" ".join(ary))

def selection_sort(ary, times=None):
    for i in range(0, len(ary)):

        if times and times == i:
            print(times)
            print(" ".join(ary))

        min_pos = i
        for j in range(i, len(ary)):
            if ary[j] < ary[min_pos]:
                min_pos = j

        ary[i],ary[min_pos] = ary[min_pos], ary[i]


def insertion_sort(ary, times=None):
    exchanges = 0
    for i in range(1, len(ary)):
        if ary[i] > ary[i-1]:
            continue

        for j in range(i, 0, -1):
            if ary[j] < ary[j-1]:
                ary[j], ary[j-1] = ary[j-1], ary[j]
                exchanges += 1
                if times and times == exchanges:
                    print(exchanges)
                    print(" ".join(ary))
            else:
                break

if __name__ == "__main__":
    selection_sort("J Z H P N M A S V U".split(), 4)
    insertion_sort("G I O Q U S Y J D E".split(), 6)
    ary = "X U B Z R G W V T A".split()
    shell_sort(ary, 4)