#전깃줄이 교차하지않게 빼는 전기줄의 최소 수

n = int(input())

elecList = list()
cache = [0] * n


for i in range(n):
    Elec = list(map(int,input().split()))
    elecList.append(Elec)

#B전봇대에 연결된 위치를 A전봇대에 연결된 위치순서를 기준으로 정렬해서 수열로 만듬
elecList.sort()

#해당 수열에서 가장 긴 증가하는 부분 수열을 구함
#증가하는 부분수열 길이 = 연결 가능한 전깃줄 수
for i in range(n-1, -1, -1):
    
    tempCache = [0]
    for j in range(i, n):
        if elecList[i][1] < elecList[j][1]:
            tempCache.append(cache[j])
    
    cache[i] = max(tempCache) + 1

#전체 전깃줄 수에서 가장 긴 증가하는 부분 수열길이를 뺌
#전체 전깃줄 수 - 최대한 연결 가능한(연결위치를 바꾸지않고) 전깃줄 수 = 뺴야하는 전기줄의 최소 수
print(n - max(cache))
