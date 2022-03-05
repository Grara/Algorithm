n = int(input())

#m이 1보다 크면 m-1로 재귀함수 호출
def Factorial(m):
    if m > 1:
        return m * Factorial(m-1)
    else:
        return 1

print(Factorial(n))