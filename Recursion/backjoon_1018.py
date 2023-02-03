#여러칸으로 이루어진 칸에서 8x8 체스판을 잘라낸 후 그중 최소 색칠 수를 구해야함

def DrawSquare(nowN, nowM, prevColor, nRange, mRange):
    global square
    global color
    drawCount = int()
    if square[nowN][nowM] == color[prevColor]: #현재칸의 색이 매개변수로 받은 색과 똑같다면
        drawCount += 1 #카운트 추가
    
    if mRange == 1: #행의 마지막 열이면
        
        if nRange == 1: #마지막 행이면 반환하고 끝
            return drawCount 
        
        else: #마지막 행이 아니라면
            
            #다음 행의 첫열부터 진행
            #다만 현재 칸과 반대색을 매개변수로 넘김 
            return drawCount + DrawSquare(nowN+1, nowM-7, prevColor, nRange - 1, 8) 
    
    else:#행의 마지막 열이 아니면
        
        #다음 열로 진행
        #현재 칸의 색을 매개변수로 넘김
        return drawCount + DrawSquare(nowN, nowM + 1, not(prevColor), nRange, mRange - 1)

n, m = map(int, input().split())

square = list()
color = list(["W","B"])
result = list()

for i in range(n):
    square.append(input())

for i in range(0, n - 7):
    for j in range(0, m - 7):
        
        result.append(DrawSquare(i, j, 0, 8, 8)) #현재칸부터 하얀색을 시작색으로 하여 재귀함수 시작
        result.append(DrawSquare(i, j, 1, 8, 8)) #현재칸부터 검은색을 시작색으로 하여 재귀함수 시작

print(min(result))
