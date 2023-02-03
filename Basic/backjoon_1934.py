#두 수의 최대공약수, 최대 공배수 구하기

a, b = map(int, input().split())

smallNum = min(a, b)


while True:
    if a % smallNum == 0 and b % smallNum == 0:
        c = a // smallNum
        print(b * c)
        break

    smallNum -= 1
