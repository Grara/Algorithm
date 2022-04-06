#계단 오르기

n = int(input())

scoreList = list()

for i in range(n):
    score = int(input())
    scoreList.append(score)

accumList = [[scoreList[i], scoreList[i]] for i in range(n)]

for i in range(n-1):
    if (n-1) - i == 1:
        accumList[i+1][0] += max(accumList[i][1:])
        continue

    accumList[i+2][1] += accumList[i][0]
    accumList[i+1][0] += max(accumList[i][1:])
    for j in range(1, len(accumList[i])):
        accumList[i+2].append(accumList[i][j] + scoreList[i+2])

print(accumList)
print(max(accumList[n-1]))