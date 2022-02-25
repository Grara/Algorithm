#n에 대한 소인수 분해를 한줄씩 출력

n = int(input())

i = 2
pList = list()

while n != 1: #n이 1이 아니면 계속 반복

    if n % i == 0: #n이 i로 나눠지면
        n = n // i
        pList.append(i)
    else: #안나눠지면
        i += 1

for i in pList:
    print(i)