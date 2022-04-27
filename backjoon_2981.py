#N개의 수가 주어졌을 때 모든 수에 대하여 나머지가 똑같은 M을 구하라


n = int(input())

numList = list()
for i in range(n):
    numList.append(int(input()))

a = 2

result = list()

while a <= min(numList):
    
    isNot = False
    
    b = min(numList) % a

    for i in numList:
        if i % a != b:
            isNot = True
            break
    
    if isNot == False:
        result.append(a)
    
    a += 1

for i in result:
    print(i, end=' ')