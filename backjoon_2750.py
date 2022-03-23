#N개의 수가 주어졌을 때 오름차순으로 정렬

from sys import stdin

N = int(input())

numList = list()

for i in range(N):
    numList.append(int(input()))

numList.sort()

for num in numList:
    print(num)