
while True:
    a ,b, c = map(int, input().split())
    
    List = [a,b,c]
    List.sort() #리스트를 오름차순으로 정렬
    
    if a+b+c == 0:
        break
    else:
        if List[0]**2 + List[1]**2 == List[2]**2:
            print("right") #직각삼각형임
        else:
            print("wrong") #아님