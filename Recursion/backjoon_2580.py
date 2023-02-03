#스도쿠 문제
#백트래킹

sudokuList = list() #스도쿠 전체 판
isFinish = False #다 채웠는지 체크

for i in range(9):
    numbers = list(map(int, input().split())) #행
    sudokuList.append(numbers)

def SudoKu(arr):

    def FindBlank(V):
        global isFinish

        for i in range(V, 9): #V행부터 검색
            if 0 in arr[i]: #i행에 0이 있다면
                idx = arr[i].index(0) #idx=0의 열
                EnterNumber(i, idx) #0의 행과 열을 매개변수로 넘기고 숫자입력으로
                break
            
            elif i == 8: #0이 없이 다 채웠다면 끗!
                isFinish = True

        return

    def EnterNumber(V, H):
        global isFinish

        vList = list() #해당 0이 위치한 열의 숫자들
        for i in range(9):
            vList.append(arr[i][H])
            
        List_33 = list() #해당 열이 위치한 3x3 구역의 숫자들
        area_V = 0
        area_H = 0
        #행과 열을 0~2, 3~5, 6~8 3구역으로 나눈뒤 현재 0이 해당하는 구역의 숫자 리스트 생성
        for i in range(0,9,3):
            if i <= V <= i+2:
                area_V = i
            if i <= H <= i+2:
                area_H = i
        
        for i in range(area_V, area_V + 3):
            for j in range(area_H, area_H + 3):
                List_33.append(arr[i][j])

        for i in range(1, 10): #1~9까지 숫자들 탐색
            
            #행, 열, 3x3구역에 해당 숫자가 있으면 패스
            if i in arr[V] or i in vList or i in List_33: 
                continue
            
            else: #없으면 숫자를 집어넣고 다음 0 찾기
                arr[V][H] = i
                FindBlank(V)

        if arr[V][H] == 0: #숫자를 넣을 수 없으면 반환
            return

        #해당 칸에 숫자를 넣었을 때 모든 경우의 수가 불가능하다면 다시 0으로 만들고 반환
        if isFinish == False: 
            arr[V][H] = 0
            return

    FindBlank(0) #0행부터 0 찾기 시작
    return arr #배열 반환
                        
sudokuList = SudoKu(sudokuList)

for i in range(9):
    for j in range(9):
        print(sudokuList[i][j], end = ' ')
    print('')
    