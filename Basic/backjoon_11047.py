#동전N개와 K원이 주어짐
#k원을 만드는데 필요한 동전개수
#동전은 N번째 동전은 N-1번째 동전의 배수
#첫번째 동전은 무조건 1원

n, k = map(int, input().split())
cnt = 0

coins = list()
for i in range(n):
    coins.append(int(input()))

coins.reverse()

for coin in coins:
    if k != 0 and coin <= k:
        a = k // coin
        k = k - coin * a
        cnt += a

print(cnt)