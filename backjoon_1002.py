t = int(input())

def OverlapCheck(a ,b): #두 반지름의 길이의 합이 두 점의 거리보다 길 때 원이 겹치는지 체크
    global dis
    
    if a == b: #두 반지름의 길이가 같으면 2점이 겹침
        return 2

    #두 반지름중 긴쪽과 짧은쪽 구분
    longR = max([a,b])
    shortR = min([a,b])
    
    if longR - dis > shortR: #긴 반지름에서 거리를 뺀 길이가 짧은 반지름보다 길다면
        return 0 #원이 겹치지 않음

    elif longR - dis == shortR: #긴 반지름에서 거리를 뺀 길이가 짧은 반지름과 같다면
        return 1 #1점이 겹침

    else:
        return 2 #이외엔 2점이 겹침

for i in range(t):

    x1, y1, r1, x2, y2, r2 = map(int, input().split())
    disX = abs(x1-x2)
    disY = abs(y1-y2)
    dis = (disX**2 + disY**2) ** 0.5
    
    if dis == 0 and r1 == r2: #거리가 0이고 반지름이 똑같다면
        print(-1) #원이 정확히 겹치므로 무한대

    elif r1 + r2 == dis: #반지름의 합이 거리와 같다면
        print(1) #한 점이 겹침
    
    elif r1 + r2 < dis: #반지름의 합이 거리보다 짧다면
        print(0) #안 겹침

    elif r1 + r2 > dis: #반지름의 합이 거리보다 길다면
        print(OverlapCheck(r1, r2))