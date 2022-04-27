#자연수 n과 정수 k가 주어졌을 때 이항계수를 구하라

n, k = map(int, input().split())

def Factorial(a):
    r = a
    for i in range(a-1, 0, -1):
        if i != 0:
            r *= i

    return r

if n==0 or k==0:
    print(1)

else:
    print(Factorial(n) // (Factorial(n-k) * Factorial(k)))