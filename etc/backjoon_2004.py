#서로다른 n개에서 m개를 뽑는 조합의 경우의 수에서 끝자리 0의 개수 출력

def PF(num, prime): #num!에서 prime의 등장횟수
    a = prime
    r = 0
    exponent = 1
    while a <= num:
        r += num // a
        exponent += 1
        a = prime**exponent

    return r

n, m = map(int, input().split())

l = n - m

if m == 0: #m이 0이면 무조건 1
    print(0)

elif n == m: #n과 m이 같으면 무조건 1
    print(0)

#조합공식의 분모와 분자에서 각각 2와 5의 등장횟수를 구한다.

else:
    n2 = PF(n, 2) #n!에서 2의 등장횟수
    n5 = PF(n, 5) #n!에서 5의 등장횟수

    m2 = PF(m, 2) + PF(l, 2) #m!과 (n-m)!에서 2의 등장횟수
    m5 = PF(m, 5) + PF(l, 5) #m!과 (n-m)!에서 2의 등장횟수

    r2 = n2 - m2
    r5 = n5 - m5

    print(min(r2, r5)) #r2 와 r5 중 더 적은쪽이 끝자리 0의 개수