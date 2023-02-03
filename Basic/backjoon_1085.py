x, y, w, h = map(int, input().split())

def FindShort(a, b): #경계선과 더 가까운 거리를 구함
    if b - a < a:
        return b-a
    else:
        return a

#X축에 평행한 경계선이 Y축에 평행한 경계선보다 가깝다면
if FindShort(x, w) < FindShort(y, h): 
    print(FindShort(x,w))
else:
    print(FindShort(y,h))