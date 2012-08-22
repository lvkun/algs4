# parse stack result to see the result is valid or not. 
# if it is valid, then show the push pop process.

def parse(args):
    pre_char = None
    li = []
    result = []
    pop_symbol = "-"
    for char in args.split():
        if len(result) == 0:
            result.append(char)
            result.append(pop_symbol)
            continue
        
        pos = 0
        handled = False
        while pos < len(result):
            el = result[pos]
            if el == pop_symbol:
                pos += 1
                continue
            if char > el:
                pos += 1
                continue
            if char == 'D' or char == 'F':
                print (result)
                print (pos)
            result.insert(pos, char)
            result.append(pop_symbol)
            handled = True
            pos += 1
            break
            
        if not handled:
            result.append(char)
            result.append(pop_symbol)

    print(" ".join(result))
    
def verify(args):
    li = args.split()
    pos = 0
    print(args)
    while pos < len(li):
        
        char = li[pos]
        comp_pos = pos
        prev_char = None
        while comp_pos < len(li):
            cur_char = li[comp_pos]
            if cur_char < char:
                if not prev_char:
                    prev_char = cur_char
                elif prev_char < cur_char:
                    print("Error")
                    break
                else:
                    prev_char = cur_char
            
            comp_pos += 1
        
        pos += 1

if __name__ == "__main__":
    verify("B A C D E F H G I J")
    verify("A B C E D F H G J I")
    verify("B C E A F H G I J D")
    verify("D F E H C G B I J A")
    verify("C B A D E F G H I J")
    