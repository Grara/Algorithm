#3X3 별그리기

def append_star(LEN): 
    if LEN == 1: return ['*'] 
    Stars = append_star(LEN//3) #LEN을 3으로 나눈 재귀함수의 리스트를 받음
    L = [] 
    for S in Stars:
        L.append(S*3) 
    for S in Stars: 
        L.append(S+' '*(LEN//3)+S) 
    for S in Stars: 
        L.append(S*3) 
    return L
n = int(input())
print('\n'.join(append_star(n)))