#자연수 1~N이 M개로 이루어진 중복을 허용하는 수열
#고른 수열은 비내림차순(a1 <= a2 <= a3)

n, m = map(int, input().split())

numList = []

def Draw():
    global numList
    if len(numList) == m:
        print(" ".join(map(str, numList)))
        return
    
    for i in range(1, n+1):
        if len(numList) > 0 and i < numList[len(numList) - 1]:
            continue
        numList.append(i)
        Draw()
        numList.pop()

Draw()
