M = int(input())

N = int(input())

count = int()
total = int()
minNum = int()

for i in range(M,N + 1):
        for j in range(1, i+1):
                if i % j == 0 and i != j and j != 1:
                        break
                elif i == j and j != 1:
                        count += 1
                        total += i
                        if minNum == 0:
                            minNum = i
if count == 0:
    print(-1)

else:
    print(total)
    print(minNum)