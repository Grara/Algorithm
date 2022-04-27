#스택 구현
from sys import stdin, stdout


read = stdin.readline

prout = stdout.write

n = int(input())

s = list()

for i in range(n):
    a = read().strip()

    if 'push' in a:
        b, c = map(str, a.split())
        s.append(int(c))
    
    if a == 'pop':
        if len(s) > 0:
            prout(str(s[len(s) - 1]) + '\n')
            s.pop()
        else:
            prout('-1' + '\n')
    
    elif a == 'size':
        prout(str(len(s)) + '\n')

    elif a == 'empty':
        if len(s) > 0:
            prout('0' + '\n')

        else:
            prout('1' + '\n')

    elif a == 'top':
        
        if len(s) > 0:
            prout(str(s[len(s) - 1]) + '\n')
        
        else:
            prout('-1' + '\n')
    