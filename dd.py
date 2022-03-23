
from sys import stdin

n = int(input())

maxNum = -5000
minNum = 5000
total = int()
countList = [0 for _ in range(8001)]
sortList = list()
freqList = list()
mode = int()
modeCount = int()
modeAmount = int()

for i in range(n):
    a = int(stdin.readline())
    if maxNum <= a:
        maxNum = a
    
    if minNum >= a:
        minNum = a

    total += a
    
    countList[4000+a] += 1


modeCount = max(countList)
mode = countList.index(modeCount)
modeAmount = countList.count(modeCount)

j = int()
while modeAmount > 1 and len(freqList) < 2:
    if countList[j] == modeCount:
        freqList.append(j)
    
    if len(freqList) == 2:
        mode = freqList[1]
        break
    
    j += 1

mode = mode - 4000

for i in range(8001):
    while countList[i] > 0:
        countList[i] -= 1
        sortList.append(i - 4000)
        
print(round(total / n))
print(sortList[len(sortList) // 2])
print(mode)
print(maxNum-minNum)