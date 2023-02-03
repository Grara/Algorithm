#최장 공통 부분 수열

A = input()
B = input()

n = len(A)
m = len(B)

cntCache = [0] * (m + 1) #맨앞 0 + 글자길이 만큼

for i in range(n):
    accum = 0
    tempCache = [0] #누적되는 수열길이 값
    for j in range(m):
        if A[i] == B[j]: #A의 i번째와 B의 j번째 문자가 같으면
            accum = cntCache[j] + 1 #누적값 = j번째 수열길이 + 1
        if cntCache[j+1] < accum: #j번째 수열길이가 누적값보다 작으면
            tempCache.append(accum) #누적값으로 덮어씀
        else:
            tempCache.append(cntCache[j+1]) #이외엔 이전에 누적된 수열의 j+1번째 수열길이로 채움 
    cntCache = tempCache #현재 for문에서 완성된 리스트를 다음 for문을 위해 사용
        


print(max(cntCache))
