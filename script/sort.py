def shell_sort(ary, step):
    print(ary)
    for i in range(step, len(ary)):
        for j in range(i, 0, -step):
            if ary[j] > ary[j-step]:
                break
            else:
                ary[j], ary[j-step] = ary[j-step], ary[j]
    print(ary)

def selection_sort(ary, times=None):
    for i in range(0, len(ary)):
        
        if times and times == i + 1:
            print(times)
            print(" ".join(ary))
        
        min_pos = i
        for j in range(i, len(ary)):
            if ary[j] < ary[min_pos]:
                min_pos = j
        
        ary[i],ary[min_pos] = ary[min_pos], ary[i]
        

        
    print(ary)

def insertion_sort(ary, times=None):
    print(ary)
    for i in range(1, len(ary)):
        if times and times == i:
            print(times)
            print(" ".join(ary))
        
        if ary[i] > ary[i-1]:
            continue
        
        for j in range(i, 0, -1):
            if ary[j] < ary[j-1]:
                ary[j], ary[j-1] = ary[j-1], ary[j]
            else:
                break;       
    print(ary)

if __name__ == "__main__":
    
    selection_sort("L O F E K U R S Q H".split(), 4)
    insertion_sort("L O F E K U R S Q H".split(), 4)
    ary = "L O F E K U R S Q H".split()
    shell_sort(ary, 4)
    shell_sort(ary, 1)
    
#    for i in range(4, 10):
#        print(i)