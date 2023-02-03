#최대 연속합

n = int(input())

numList = list(map(int, input().split()))

dp = [0] * n
dp[0] = numList[0]

for i in range(1,n):
    dp[i] = max(numList[i], dp[i-1] + numList[i])

print(max(dp))
print(dp)