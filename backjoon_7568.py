#몸무게와 키 입력을 받은 후 순위 출력
#몸무게와 키가 상대적으로 모두 커야 더 큰걸로 인정 

n = int(input())

bulkList = list() #몸무게와 키 리스트 저장

for i in range(n):
    bulkList.append(list(map(int, input().split())))

for i in range(n): #각 몸무게와 키를 다른 체급과 비교 
    rank = 1
    for j in range(n):
        if bulkList[i][0] < bulkList[j][0] and bulkList[i][1] < bulkList[j][1]:
            #i의 몸무게와 키보다 j의 몸무게의 키가 모두 크다면
            #랭크 1하락
            rank += 1 
        else:
            continue
    
    print(rank, end = " ")