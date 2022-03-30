#피보나치(n)의 피보나치(0)과 피보나치(1)의 호출횟수

from sys import stdin


t= int(input())

fiboDic = {0:0, 1:1}


def Fibonacci(num):
    if num == 0:
        return 0
    if num == 1:
        return 1
    
    if num in fiboDic:
        return fiboDic[num]
    
    else:
        fiboDic[num] = Fibonacci(num-1) + Fibonacci(num-2)
        return fiboDic[num]

for i in range(t):
    n = int(stdin.readline())
    if n == 0:
        print(1, 0)
    elif n == 1:
        print(0, 1)
    else:
        Fibonacci(n)
        print(fiboDic[n-1], end=' ')
        print(fiboDic[n])