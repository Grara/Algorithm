#옷 조합의 경우의 수
#어쩌다보니 DP를 이용해서 풀게됨

t = int(input())


def Fac(num):
    if num == 0:
        return 1
    
    r = 1

    for i in range(1, num + 1):
        r *= i
    
    return r

for i in range(t):
    clthList = list()
    n = int(input())
    cnt = 0
    
    if n == 0:
        print(0)
        continue
    
    dp = [0] * n


    for j in range(n):
        clothes = list(map(str, input().split()))
        clthList.append(clothes)
    
    clthList.sort(key = lambda x : x[1])

    for k in range(n-1, -1, -1):
        for l in range(k, n):
            if clthList[k][1] != clthList[l][1]:
                dp[k] += dp[l]
        dp[k] += 1
    
    print(sum(dp))