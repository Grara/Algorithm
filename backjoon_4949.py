#스택 구현
from sys import stdin, stdout
read = stdin.readline
prout = stdout.write


s = list()
needReset = False
isNot = False

while True:
    if needReset:
        s.clear()
        isNot = False
        needReset = False

    a = input()

    if a == '.':
        break

    for i in a:
        if 65 <= ord(i) <= 90 or 97 <= ord(i) <= 122:
            continue

        if i == '(' or i == '[':
            s.append(i)

        elif i == ')':
            if len(s) == 0 or s[-1] != '(':
                isNot = True
            else:
                s.pop()

        elif i == ']':
            if len(s) == 0 or s[-1] != '[':
                isNot = True
            else:
                s.pop()

        elif i == '.':   
            if isNot or len(s) > 0:
                print('no')
            
            else:
                print('yes')
            
            needReset = True