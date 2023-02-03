#스택 구현
from sys import stdin, stdout


read = stdin.readline

prout = stdout.write

n = int(input())

s = list()

for i in range(n):
    a = int(read().strip())

    if a > 0:
        s.append(a)
    
    else:
        s.pop()

print(sum(s))
    