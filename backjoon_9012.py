#스택 구현
from sys import stdin, stdout


read = stdin.readline

prout = stdout.write

n = int(input())

s = list()

for i in range(n):
    s.clear()
    a = read().strip()
    isNot = False
    for j in a:
        if j == '(':
            s.append(1)
        else:
            if len(s) == 0:
                print('NO')
                isNot = True
                break
            s.pop()

    if isNot == False:
        if sum(s) > 0:
            print('NO')
        else:
            print('YES')