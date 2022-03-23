#병합정렬

from sys import stdin, stdout

def MergeSort(arr):
    if len(arr) == 1: #더이상 쪼갤수없으면 return
        return arr
    
    mid = len(arr) // 2 #중간값은 배열길이의 반(정수)
    leftArr = MergeSort(arr[:mid]) #중간값 이전의 요소들로 구성된 배열
    rightArr = MergeSort(arr[mid:]) #중간값 이후의 요소들로 구성된 배열 (중간값은 포함안됨)
    mergedArr = list() #정렬 후 리턴할 배열
    l = h = 0

    while l < len(leftArr) and h < len(rightArr):
        if leftArr[l] < rightArr[h]: 
            #레프트의 l번째 요소가 라이트의 h번째 요소보다 작으면
            #l번째 요소를 리턴할 배열에 추가, 이후 l을 1더함
            mergedArr .append(leftArr[l])
            l += 1
        else:
            mergedArr .append(rightArr[h])
            h += 1
    #한쪽 배열 요소를 모두 비교했다면 남은 요소들 모두 추가
    mergedArr  += leftArr[l:]
    mergedArr  += rightArr[h:]

    return mergedArr

N = int(input())

numList = list()

for i in range(N):
    numList.append(int(stdin.readline()))

numList = MergeSort(numList)

for i in numList:
    stdout.write(str(i) + "\n")

