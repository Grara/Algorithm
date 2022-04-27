#스택 구현
from sys import stdin, stdout
read = stdin.readline
prout = stdout.write

n = int(input())

seqList = list() #원본 수열

stackList = list() #스택

r = list() #결과 리스트

for i in range(n):
    seqList.append(int(read().strip()))

#1부터 시작
a = 1

for i in range(n):
#a가 수열의 i번째 숫자보다 작거나 같으면 1씩 증가시키면서 스택에 넣는다.
    while a <= seqList[i]:
        stackList.append(a)
        r.append('+')
        a += 1

#스택의 맨위가 수열의 i번째 숫자랑 같으면 꺼낸다.(=출력)
    if stackList[-1] == seqList[i]:
        stackList.pop()
        r.append('-')

#위의 과정을 진행하던 중 불가능한 경우가 나오면 스택에 숫자가 남게된다.
if len(stackList) > 0:
    print('NO')

else:
    print(*r)
