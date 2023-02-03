#자연수 n과 정수 k가 주어졌을 때 이항계수를 구하라
#DP 적용

n, k = map(int, input().split())

facDic = {}

def Factorial(a):
    if a in facDic:
        return facDic[a]
    

    r = a
    for i in range(a-1, 0, -1):
        if i != 0:
            r *= i

    facDic[a] = r
    return r

if n==0 or k==0 or n==k:
    print(1)

else:
    d = Factorial(n) // (Factorial(n-k) * Factorial(k))
    print(d % 10007)