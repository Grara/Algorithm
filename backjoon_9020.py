from sys import stdin
input = stdin.readline

def isPrime(num): #소수 인지 판정해서 bool값 리턴
    for i in range(2, int(num ** 0.5) + 1): # 2 ~ 최대범위의 제곱근까지
        
        if num % i == 0:
            
            return False
        
    return True
        

t = int(input())

for i in range(t):
    n = int(input())
    
    a = n / 2
    b = n / 2
    
    while not(isPrime(a) and isPrime(b)): #a와 b 둘중 하나라도 소수가 아니라면
        a -= 1
        b += 1

    print(int(a),int(b))

