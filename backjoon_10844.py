#계단수 : 각 자릿수가 양옆 자릿수와 1씩 차이나는 수
#n이 주어졌을 때 n자릿수의 계단수를 만들 수 있는 경우의 수
#ex) n = 5 : 12343

n = int(input())

dp = [[1] * 10] #첫 리스트는 0~9까지 1씩

total = 0

for i in range(n-1):
    dp.append([0] * 10) #이후 리스트는 다 0으로 채움


for i in range(n-1):
    for j in range(10):
        if i+j == 0: #0으로 시작하는 계단수는 안되므로 패스
            continue
        
        if j == 0: #0에서 -1은 못가므로
            dp[i+1][1] += dp[i][0]
        elif j == 9: #9에서 10은 못가므로
            dp[i+1][8] += dp[i][9]
        else: #이외에는 다음 리스트의 j+1, j-1에 현재 리스트 j에 누적된 경우의 수를 더함
            dp[i+1][j-1] += dp[i][j]
            dp[i+1][j+1] += dp[i][j]

total = sum(dp[n-1]) % 1000000000

if n == 1:
    total = 9


print(total)