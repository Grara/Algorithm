#사람들의 능력치가 1~N까지의 행, 열로 이루어진 행렬로 주어짐
#여기서 능력치 S는 i, j가 같은 팀일 때의 능력치임
#S[ij] 와 S[ji]는 다를 수 있음
#전체 인원을 반으로 나눠서 팀을 꾸리고, 각 팀의 능력치 합계의 최소차이를 구해라

N = int(input())
totalList = list()
startTeam = [0] #start팀의 첫번째는 무조건 1번이 들어가도록 한다.
linkTeam = list() #link팀

isOver = False #탐색 중 차이가 0일 때 즉시 종료를 위한 변수

minDif = float('Inf') #최소차이

for i in range(N):
    stat = list(map(int, input().split()))
    totalList.append(stat)

def Cal_Stat(arr): #주어진 팀의 능력치 합을 구함
    score = 0

    for i in range((N // 2) - 1):
        for j in range(i+1, N//2):
            score += totalList[arr[i]][arr[j]]
            score += totalList[arr[j]][arr[i]]
    
    return score

def StartTeamSelect(num):
    global isOver
    global minDif
    Dif = 0 #차이
    limit = (num + N//2) + 1 #for문내 최대범위
    
    if len(startTeam) == N // 2: #스타트팀의 인원을 모두 뽑았으면 비교 시작
        LinkTeamSelect() #링크팀 인원 선택
        Dif = abs(Cal_Stat(startTeam) - Cal_Stat(linkTeam)) #각 팀의 능력치 차이 비교
        if minDif > Dif:
            minDif = Dif
        if Dif == 0: #차이가 0이면 isOver를 트루로
            isOver = True
        linkTeam.clear() #링크팀 인원 비우기
        return

    if limit >= N: #limit가 N보다 크면 N으로 고정
        limit = N

    for i in range(num, limit): #i번의 인원을 스타트팀에 넣음
        startTeam.append(i)
        StartTeamSelect(i+1) #i번+1로 재귀함수 실행
        if isOver: #파생된 재귀에서 차이가 0이 나왔다면 탐색 종료
            break
        startTeam.pop() #아니라면 넣었던 i번 인원을 빼고 다음 i로 탐색 시작
    return

def LinkTeamSelect(): #스타트팀 인원 제외하고 링크팀에 투입
    for i in range(N):
        if i not in startTeam:
            linkTeam.append(i)

StartTeamSelect(1)

print(minDif)