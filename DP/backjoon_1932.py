#t층으로 이루어진 정수형 삼각형의 최대합

from sys import stdin

t= int(input())

numList = list()

for i in range(t):
    nums = list(map(int,stdin.readline().split()))
    numList.append(nums)

#상위노드의 수에 j,j+1번째 수 중 더 큰값을 더함
#아래부터 맨 위로
for i in range(t-1 , 0, -1):
    for j in range(len(numList[i])-1):
        numList[i-1][j] += max(numList[i][j], numList[i][j+1])

print(numList[0][0])
