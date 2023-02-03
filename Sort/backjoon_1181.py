#단어 정렬
#길이가 짧은 것부터, 같으면 사전순

from sys import stdin, stdout

def MergeSort(arr):
    if len(arr) == 1:
        return arr
    
    mid = len(arr) // 2
    rList = MergeSort(arr[mid:])
    lList = MergeSort(arr[:mid])
    mergedList = list()

    r = l = 0

    while l < len(lList) and r < len(rList): 
        
        #단어의 길이 비교

        if len(lList[l]) < len(rList[r]):
            mergedList.append(lList[l])
            l += 1
        
        elif len(lList[l]) > len(rList[r]):
            mergedList.append(rList[r])
            r += 1
        
        #단어의 길이가 똑같다면
        else:
            #i번째 문자끼리 아스키코드 비교
            for i in range(len(rList[r])):
                
                if ord(lList[l][i]) < ord(rList[r][i]):
                    mergedList.append(lList[l])
                    l += 1
                    break
                
                elif ord(lList[l][i]) > ord(rList[r][i]):
                    mergedList.append(rList[r])
                    r += 1
                    break
                
                else: #같다면 i증가
                    continue

    mergedList += rList[r:]
    mergedList += lList[l:]

    return mergedList


n = int(input())

wordList = list()


for i in range(n):
    word = stdin.readline()
    if word not in wordList:
        wordList.append(word) #리스트에 중복없이 단어추가

wordList = MergeSort(wordList)

for i in wordList:
    stdout.write(i)