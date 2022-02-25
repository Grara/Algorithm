dices = list(map(int,input().split()))

overlap = int()

dice = int()

result = int()

for i in dices:
    if dices.count(i) > overlap:
        overlap = dices.count(i)
        dice = i

if overlap == 3:
    result = 10000 + dice * 1000
elif overlap == 2:
    result = 1000 + dice * 100
else:
    result = max(dices) * 100

print(result)