#좌표 압축
#각 숫자의 위치에 자신보다 작은 숫자가 몇개인지 출력
#EX) 1, 2, 3, 5 = 0, 1, 2, 3 

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
        
        if lList[l] < rList[r]:
            mergedList.append(lList[l])
            l += 1

        else:
            mergedList.append(rList[r])
            r += 1

    mergedList += rList[r:]
    mergedList += lList[l:]

    return mergedList

n = int(input())

numList = list(map(int, stdin.readline().split()))

nOverLapList = list(set(numList)) #중복없는 리스트

nOverLapList = MergeSort(nOverLapList) #병합정렬

numDic = {nOverLapList[i]:i for i in range(len(nOverLapList))} 
#숫자 : 크기순서 형태로 딕셔너리에 저장 

for i in numList:
    stdout.write(str(numDic[i]) + " ")
    