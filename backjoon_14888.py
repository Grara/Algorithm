#연산자 끼워넣기
#주어진 숫자와 연산자를 이용한 계산의 최대, 최소 구하기

N = int(input())

numList = list(map(int, input().split())) #수열 리스트

opList = list(map(int, input().split())) #연산자 리스트 [더하기, 빼기, 곱셈, 나눗셈]

maxValue = -100000000
minValue = 100000000

def Cal(arr):
    
    def Operate(arr, idx, prev): #idx: numList내 인덱스값, prev: 이전 재귀에서 넘겨받은 계산값
        
        global maxValue
        global minValue
        result = prev
        backup = prev

        if idx >= N: #idx가 수열길이와 똑같으면 더이상 계산할 숫자가 없으므로 반환
            if result > maxValue:
                maxValue = result
            if result < minValue:
                minValue = result
            return
        
        for i in range(4): #모든 연산자를 이용한 계산을 한번씩 탐색
            
            if opList[i] > 0:
                opList[i] -= 1 #해당 연산자 갯수 감소

                if i == 0: #더하기
                    result = result + arr[idx]
                elif i == 1: #빼기
                    result = result - arr[idx]     
                elif i == 2: #곱하기
                    result = result * arr[idx]
                elif i == 3: #나누기
                    if result < 0:
                        result = -(-result // arr[idx])
                    else:
                        result = result // arr[idx]
                
                Operate(arr, idx+1, result) #다음 재귀로
                #해당 재귀에서 파생된 재귀의 탐색을 모두 마쳤으면 result를 계산전으로 돌리고 다음 연산자 계산 탐색
                result = backup
                opList[i] += 1 #사용이 끝났으면 연산자 갯수 다시 증가
        return

    Operate(arr, 1, arr[0])

    return
    
Cal(numList)

print(maxValue)
print(minValue)