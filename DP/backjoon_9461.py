#나선모양으로 놓인 삼각형 수열의 변의 길이

from sys import stdin, stdout


t= int(input())


fiboDic = {0:1, 1:1, 2:1, 3:2}

for _ in range(t):
    n= int(stdin.readline())

    for i in range(n):
        if i > 3:
            fiboDic[i] = (fiboDic[i-2] + fiboDic[i-3])

    stdout.write(str(fiboDic[n - 1]) + '\n')