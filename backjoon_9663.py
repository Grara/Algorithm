#N-Queen 문제
#N x N 크기의 체스판에서 퀸을 놓는 방법의 수


n = int(input())

cnt = 0
row = list()

def Check(x): #배치할 수 있는지 체크
    for i in range(x): #0행부터 x행 체크 (x가 0이면 패스)
        if row[x] == row[i] or abs(row[x] - row[i]) == abs(x-i): #수직이나 대각선 경로에 겹친다면 못둠
            return False

    return True

def Collocate(x): #배치
    global cnt
    
    if x == n:
        cnt += 1
        return
    
    else:
        for i in range(n):
            if i in row: #해당 열에 놓인 퀸이 있다면
                continue
            if x > 0 and row[x-1] - 1 <= i <= row[x-1] + 1: #해당행 위 행의 아래와 대각선에 i가 위치하면
                continue
            row.append(i) #x행의 i열에 퀸 임시배치
            if Check(x): #임시배치한 퀸이 확정가능한지 체크
                Collocate(x+1) #가능하면 배치 확정 후 다음 행으로
            row.pop() #해당 열 배치에서 비롯된 재귀함수가 모두 끝났으면 비우고 다음 열로
Collocate(0)

print(cnt)