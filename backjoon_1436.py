#N이 '666'이 들어간 숫자중 몇번째 숫자인지 찾기

n = int(input())

Count = 0
num = 665

while True:
    num += 1
    if "666" in str(num):
        Count += 1

    if Count == n:
        break

print(num)