#최대 포도주 마시기
#1~n까지 순서대로 잔마다 포도주가 주어짐
#3개를 연속으로 마시는건 불가능
#가장 많은 포도주를 마셨을 때의 값

n = int(input())

wineList = [0,0,0]


for i in range(n):
    wine = int(input())
    wineList.append(wine)

cache = [0,0,0] + [0] * n

for i in range(3,n+3):
    cache[i] += max(wineList[i-1]+max(cache[:i-2]), cache[i-2]) + wineList[i]
    
print(max(cache))