#n에 대해서 n의 진짜 약수가 주여졌을 때 n을 구해라

m = int(input())

numList = list(map(int, input().split()))

print(max(numList) * min(numList))