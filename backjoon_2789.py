#N장의 카드로 M을 최대값으로 하는 블랙잭
#브루트포스

N, M = map(int, input().split())

card = list(map(int, input().split())) #카드 리스트

maxValue = 0

for i in range(N-2):
    for j in range(i+1, N-1):
        for k in range(j+1, N):
            result = card[i]+card[j]+card[k]
            if result > M or result < maxValue: #더한 값이 M보다 크거나 현재 최대값보다 작다면
                continue #패스
            else: #M보다 작고 현재 최대값보다 크면 최대값으로 설정
                maxValue = result

print(maxValue)
