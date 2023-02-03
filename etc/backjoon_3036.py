#첫번째 링이 한바퀴돌 때 나머지 연결된 링은 몇바퀴 도는가


n = int(input())

ringList = list(map(int, input().split()))

for i in range(1, n):
    smallRing = min(ringList[0], ringList[i])
    a = ringList[0]
    b = ringList[i]

    for j in range(smallRing, 0, -1):
        if ringList[0] % j == 0 and ringList[i] % j == 0:
            a = ringList[0] // j
            b = ringList[i] // j
            break
    
    print('{0}/{1}'.format(a,b))