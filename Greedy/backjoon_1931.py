#N개의 회의가 안겹치도록 하는 최대 회의 수

from sys import stdin


read = stdin.readline

n = int(input())

meetings = list()

for i in range(n):
    meetings.append(list(map(int, read().split())))

meetings.sort(key = lambda x : x[0]) #시작 시간 순서대로 정렬
meetings.sort(key = lambda x : x[1]) #끝나는 시간 순서대로 정렬

cnt = 1

end = meetings[0][1]

for i in range(1,n):
#현재 회의의 시작시간이 이전에 진행된 마지막 회의의 종료시간 이후라면 
    
    if meetings[i][0] >= end:
        cnt += 1
        end = meetings[i][1]

print(cnt)