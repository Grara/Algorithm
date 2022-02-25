from sys import stdin, stdout


def PrimeNumber(num):
    for i in range(2, int(num ** 0.5) + 1):
        
        if num % i == 0:
            
            return False
        
    return True

while True:
    n = int(stdin.readline())
    if n == 0:
        break

    else:
        count = int()
        m = 2 * n
        
        for i in range(n + 1, m + 1):
            
            if PrimeNumber(i):
                count += 1
        
        stdout.write(str(count)+"\n")