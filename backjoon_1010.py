#다리 놓기
#본질적으로 n개에서 r개를 고르는 조합 문제

t = int(input())

def Fac(n):
    if n == 0:
        return 1
    
    r = 1

    for i in range(1, n + 1):
        r *= i
    
    return r

for i in range(t):
    a, b = map(int, input().split())

    print(Fac(b) // (Fac(a) * Fac(b-a)))