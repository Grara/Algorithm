
def solution(s):
    ans = float('inf')
    n = len(s)
    for i in range(1, n // 2 + 1):
        
        for j in range(0, i):
            r = ''
            if j != 0:
                r = s[:j]
            cnt = 1
            tmp = s[j:j+i]
            tail = ''
            for k in range(j + i, n, i):
                
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
            print(r)
            if ans > len(r):
                ans = len(r)

    return ans

m = input()

print(solution(m))
