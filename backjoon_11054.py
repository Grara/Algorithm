#가장 부분 바이토닉 수열 구하기
#바이토닉 수열 : 차례대로 증가했다가 차례대로 감소하는 수열
#EX) 1234321

n = int(input())

numList = list(map(int,input().split())) + [0]
RDcache = [0] * (n-1) + [1]
LDcache = [1] + [0] * (n-1)
BitonicCache = [0] * n

#각 숫자마다 우측으로 감소하는 수열길이를 저장
for i in range(n-2, -1, -1):
    tempCache = [0]
    for j in range(i, n):
        if numList[j] < numList[i]:
            tempCache.append(RDcache[j])
    
    RDcache[i] = max(tempCache) + 1

#각 숫자마다 좌측으로 감소하는 수열길이를 저장 
for i in range(1, n):
    tempCache = [0]
    for j in range(i, -1, -1):
        if numList[j] < numList[i]:
            tempCache.append(LDcache[j])
    
    LDcache[i] = max(tempCache) + 1

#i의 좌측 감소 수열 길이와 i이후 i와 같지않은 숫자 중 가장 긴 우측감소 수열의 길이를 더함 
for i in range(n-1):
    tempCache = [0]
    for j in range(i+1, n):
        if numList[i] != numList[j]:
            tempCache.append(RDcache[j])

    BitonicCache[i] = LDcache[i] + max(tempCache)

if n == 1:
    BitonicCache = [1]

print(max(BitonicCache))

