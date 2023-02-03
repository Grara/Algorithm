x1, y1 = map(int, input().split())

x2, y2 = map(int, input().split())

x3, y3 = map(int, input().split())

xList = [x1, x2, x3] #각 점의 X좌표 리스트
yList = [y1, y2, y3] #각 점의 Y좌표 리스트

def FindCoordinate(List): #리스트를 받아옴
    Coordinate = int() #네번째점의 좌표
    for i in List: #리스트에서 해당 좌표가 1개밖에 없다면 네번째점의 좌표
        if List.count(i) == 1:
           Coordinate = i
    
    return Coordinate

print(FindCoordinate(xList), FindCoordinate(yList))