#문자열 압축


def solution(s):
    ans = float('inf')
    n = len(s)
    if n == 1:
        return n
    
    for i in range(1, n // 2 + 1): #1개 ~ n//2 개 단위까지 슬라이싱
        r = '' #압축 후 결과로 나올 텍스트
        cnt = 1 #현재 단위 문자열이 나온 횟수
        tmp = s[0:i] #현재 단위 문자열
        tail = '' #슬라이싱할 수 없는 끝부분
        
        for k in range(i, n, i): #i번째 인덱스부터 i개씩 슬라이싱
            if k + i > n: #슬라이싱이 불가능 할 경우 break
                tail = s[k:]
                break

            #슬라이싱한 문자열이 현재 단위 문자열과 같으면
            if s[k:k+i] == tmp: 
                cnt += 1

            #같지않으면
            else:
                #현재 단위 문자열이 연속되지않았으면
                if cnt == 1:
                    r += tmp
                #현재 단위 문자열이 연속됐으면
                else:
                    r += str(cnt) + tmp
                
                #현재 단위 문자열을 현재 슬라이싱한 문자열로 교체 
                cnt = 1
                tmp = s[k:k+i]
        
        if cnt == 1:
            r += tmp
        else:
            r += str(cnt) + tmp
        r += tail

        ans = min(ans, len(r))

    return ans

m = input()

print(solution(m))
