#피보나치 수열 구하기

n = int(input())

dic = {0:0, 1:1, 2:1}

def Fibonacci(n):
    if n in dic:
        return dic[n]
    else:
        output = Fibonacci(n - 1) + Fibonacci(n - 2)
        dic[n] = output
        return output

print(Fibonacci(n))