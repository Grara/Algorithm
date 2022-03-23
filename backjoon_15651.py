#자연수 1~N이 M개로 이루어진 중복을 허용하는 수열

n, m = map(int, input().split())

numList = []

def Draw():
    global numList
    if len(numList) == m:
        print(" ".join(map(str, numList)))
        return
    
    for i in range(1, n+1):
        numList.append(i)
        Draw()
        numList.pop()

Draw()
