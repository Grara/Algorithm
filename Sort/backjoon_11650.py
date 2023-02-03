#좌표 정렬
#X좌표 오름차순, 같으면 Y좌표 비교
#병합정렬을 이용

from sys import stdin, stdout

def MergeSort(arr):
    if len(arr) == 1:
        return arr
    
    mid = len(arr) // 2
    rList = MergeSort(arr[mid:])
    lList = MergeSort(arr[:mid])
    mergedList = list()

    r = l = 0

    while l < len(lList) and r < len(rList): 
        
        #X좌표가 더 큰 점을 앞으로
        if lList[l][0] < rList[r][0]:
            mergedList.append(lList[l])
            l += 1

        elif lList[l][0] > rList[r][0]:
            mergedList.append(rList[r])
            r += 1

        #X좌표가 같다면 Y좌표가 더 큰 점을 앞으로
        elif lList[l][1] < rList[r][1]: 
            mergedList.append(lList[l])
            l += 1
        
        else:
            mergedList.append(rList[r])
            r += 1

    mergedList += rList[r:]
    mergedList += lList[l:]

    return mergedList


n = int(input())

pointList = list()


for i in range(n):
    x, y = map(int, stdin.readline().split())
    pointList.append((x,y)) #리스트에 (x,y) 튜플 추가

pointList = MergeSort(pointList)

for i in pointList:
    for j in i: #좌표 출력
        stdout.write(str(j) + " ")
    
    stdout.write("\n")