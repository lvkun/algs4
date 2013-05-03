
# for integer
def LSD_sort(a, W):
    
    R = 10
    N = len(a)
    
    aux = [""] * N
    
    for d in range(W - 1, -1, -1):
        
        count = [0] * (R + 1)
        
        for i in range(0, N):
            count[int(a[i][d]) + 1] += 1
        
        print(count)
    

if __name__ == '__main__':
    
    LSD_sort("2424 2411 1121 3211 4211 1123 4231 2314 4221 1223".split(), 4)