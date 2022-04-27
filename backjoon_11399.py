#


from sys import stdin


read = stdin.readline

n = int(input())

nList = list(map(int,input().split()))

nList.sort()

total = 0

result = list()

for i in nList:
    total = total + i
    result.append(total)

print(sum(result))