#자연수 1~N이 M개로 이루어진 중복없는 수열
#수열의 수는 오름차순이어야 한다.

n, m = map(int, input().split())

numList = []

def Draw():
    global numList
    if len(numList) == m:
        print(" ".join(map(str, numList)))
        return
    
    for i in range(1, n+1):
        if i in numList:
            continue

        if len(numList) > 0 and i < numList[len(numList) - 1]:
            continue

        numList.append(i)
        Draw()
        numList.pop()

Draw()
