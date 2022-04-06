#가장 긴 증가하는 부분 수열 구하기



n = int(input())

nList = list(map(int,input().split())) + [0]
cache = [0] * (n + 1)


for i in range(n-1, -1, -1):
    tempCache = [0]
    for j in range(i, n+1):
        if nList[j] > nList[i]:
            tempCache.append(cache[j])
        
    cache[i] = max(tempCache) + 1


print(max(cache))