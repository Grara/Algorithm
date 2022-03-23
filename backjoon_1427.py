#주어진 수의 숫자들을 내림차순으로 정렬해서 한줄로 출력 

n = input()

sList = list(n)

sList.sort(reverse=True)

for i in sList:
    print(i, end='')