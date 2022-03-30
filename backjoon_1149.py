#t만큼의 집갯수가 주어짐
#각 집마다 R,G,B 세가지 중 하나의 색으로 색칠가능
#집마다 각각의 색을 칠하는 색칠비용이 주어짐
#i번째의 집색깔은 i-1, i+1 번째 집색깔과 같으면 안됨
#모든 집을 칠했을 때 가능한 최소비용 구하기

from sys import stdin

t= int(input())

costList = list()

for i in range(t):
    cost = list(map(int,stdin.readline().split()))
    costList.append(cost)

#각 색깔마다 i-1번째에서 칠할 수 있는 최소비용을 더함
for i in range(1,t):
    costList[i][0]+=min(costList[i-1][1],costList[i-1][2])
    costList[i][1]+=min(costList[i-1][0],costList[i-1][2])
    costList[i][2]+=min(costList[i-1][0],costList[i-1][1])

print(min(costList[t-1]))