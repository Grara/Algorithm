#나이순 회원 정렬
#나이가 같으면 먼저 가입한 순

from os import name
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
        

        if lList[l][0] < rList[r][0]:
            mergedList.append(lList[l])
            l += 1
        
        elif lList[l][0] > rList[r][0]:
            mergedList.append(rList[r])
            r += 1
        
        else:
            mergedList.append(lList[l])
            l += 1
        

    mergedList += rList[r:]
    mergedList += lList[l:]

    return mergedList


n = int(input())

memberList = list()


for i in range(n):
    old, name = map(str, stdin.readline().split())
    memberList.append((int(old), name))

memberList = MergeSort(memberList)

for i in memberList:
    for j in i:
        stdout.write(str(j) + " ")
    stdout.write("\n")