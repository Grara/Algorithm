n = int(input()) #테스트 케이스 수

words = list() #단어 리스트

groupTotal = int() #그룹단어의 수

for i in range(n):
    words.append(input())

for word in words:

    isNotGroup = False #해당 단어가 그룹단어인지 판별하기 위한 변수

    for i in range(len(word)): #해당 단어의 문자마다 반복

        a = word[i]
        startIndx = str(word).find(a) #해당 문자의 처음 위치
        endIndx = str(word).rfind(a) #해당 문자의 마지막 위치 

        for j in range(startIndx, endIndx + 1): #문자의 처음 위치부터 마지막 위치까지 반복

            if a != word[j]: #중간에 다른 문자가 있다면 그룹문자가 아닌걸로 판정 후 반복 종료
                isNotGroup = True
                break
        
        if isNotGroup: #그룹문자가 아니면 반복종료
            break
    
    if isNotGroup == False: #그룹문자면 groupTotal 증가
        groupTotal += 1

print(groupTotal)