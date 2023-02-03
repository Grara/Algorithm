#도시간의 거리와 각 도시의 주유소의 리터당 가격이 주어짐
#맨 왼쪽 도시에서 맨 오른쪽 도시까지 이동하는 최소비용 구하기

from sys import stdin

read = stdin.readline

n = int(input())

roads = list(map(int,input().split())) #도시간 거리

prices = list(map(int,input().split())) #각 도시의 기름가격

minCost = prices[0] #최소 기름값

totalCost = 0 #총 비용

for i in range(1, n):
    totalCost += minCost * roads[i-1] #i-1번째 도시부터 i번째 거리에 이전까지의 최소 기름값을 곱함

    #i번째 도시의 기름가격이 현재 최소 기름값보다 더 낮으면 최소 기름값으로 설정
    if prices[i] < minCost: 
        minCost = prices[i]

print(totalCost)