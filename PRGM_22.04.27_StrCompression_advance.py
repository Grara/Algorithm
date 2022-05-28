#어쩌다보니 만들게된 문자열 압축 심화버전..


def solution(s):
    ans = float('inf')
    n = len(s)
    for i in range(1, n // 2 + 1):
        r = ''
        cnt = 1
        tmp = s[0:i]
        tail = ''
        
        for k in range(i, n, i):
            if k + i > n:
                tail += s[k:]
                break
            if s[k:k+i] == tmp:
                cnt += 1
            else:
                if cnt == 1:
                    r += tmp
                else:
                    r += str(cnt) + tmp
                
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
