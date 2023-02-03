#오큰수 구하기
#오큰수 : 해당 숫자보다 큰 숫자인 동시에 오른쪽에 위치한 첫번째 수

n = int(input())

#수열 리스트
seqList = list(map(int, input().split()))

#오큰수를 찾지못한 숫자들의 인덱스를 저장할 스택
stack = [0]

#정답 리스트
ans = [-1] * n

for i in range(1, n):
    #스택이 비어있지않고 i인덱스의 숫자가 스택 맨위 인덱스의 숫자보다 크면
    while stack and seqList[stack[-1]] < seqList[i]: #stack[-1] = stack.peek()
        #해당 인덱스 숫자의 오큰수는 i인덱스의 숫자가 된다.
        ans[stack.pop()] = seqList[i]
    stack.append(i) #현재 i인덱스의 숫자도 이후 오큰수를 구해야 하므로 스택에 넣는다.

print(*ans)