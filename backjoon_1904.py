#01타일
#00타일과 1타일을 이용해 N길이의 수열의 경우의 수
#N=4 : 0011, 0000, 1001, 1111, 1100

from sys import stdin
import sys
sys.setrecursionlimit(10**6)

n= int(input())

fiboDic = {0:0, 1:1}


for i in range(n+1):
    if i > 1:
        fiboDic[i] = (fiboDic[i-1] + fiboDic[i-2]) % 15746

print(fiboDic[n])