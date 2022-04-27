#양수와 +, - 가 주어짐
#괄호를 쳐서 최소값을 구해라

from sys import stdin


read = stdin.readline

#-로 구분하여 리스트로 저장(괄호를 침)
e = list(map(str, input().split('-')))

numList = list()

#괄호 안의 숫자끼리 모두 더한 후 numList에 추가
for i in e:
    j = list(map(int, i.split('+')))
    temp = 0
    for k in j:
        temp += k
    
    numList.append(temp)

result = numList[0]

#numList의 첫번째 숫자 제외하고 차례대로 빼기 진행
for i in range(1, len(numList)):
    result -= numList[i]

print(result)