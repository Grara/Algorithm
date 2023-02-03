# 1: 산술평균 / 2: 중앙값 / 3: 최빈값 / 4: 범위

from sys import stdin

n = int(input())


maxNum = -5000
minNum = 5000
total = int()
countList = [0 for _ in range(8001)] #수가 나오는 빈도수를 저장할 리스트
sortList = list() #countList를 기반으로 카운팅 정렬할 리스트
freqList = list() #최빈값이 두개 이상일 경우 최빈값을 저장할 리스트
mode = int() #최빈값
modeCount = int() #최빈값의 등장 횟수
modeAmount = int() #최빈값이 몇개 존재하는지를 나타냄

for i in range(n):
    a = int(stdin.readline())
    if maxNum <= a:
        maxNum = a
    
    if minNum >= a:
        minNum = a

    total += a
    
    countList[4000+a] += 1 #4000을 기준으로 아래는 음수, 위는 양수 (0은 4000)


modeCount = max(countList)
mode = countList.index(modeCount)
modeAmount = countList.count(modeCount)

j = int()
while modeAmount > 1 and len(freqList) < 2: #최빈값이 2개이상이고 아직 1개밖에 발견하지 못했다면
    if countList[j] == modeCount: #countList[j]가 최빈값이라면
        freqList.append(j) #freqList에 추가
    
    if len(freqList) == 2: #최빈값을 2개 찾았다면
        mode = freqList[1] #최빈값은 freqList의 두번째 요소
        break
    
    j += 1

mode = mode - 4000 #4000을 기준으로 음수, 양수가 나뉘어짐

for i in range(8001):
    while countList[i] > 0:
        countList[i] -= 1
        sortList.append(i - 4000)
        
print(round(total / n))
print(sortList[len(sortList) // 2])
print(mode)
print(maxNum-minNum)