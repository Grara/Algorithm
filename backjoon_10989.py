freqList = list()

def FindMaxFrequency(arr):
    result = int()
    maxidx = arr.index(max(arr))
    print("최대값", max(arr))
    print("인덱스", maxidx)
    print(arr)

    if maxidx < 4001:
        result = -maxidx
    else:
        result = maxidx - 4001

    if max(arr) == max(arr[maxidx + 1:]):
        freqList.append(result)
        FindMaxFrequency(arr[maxidx + 1:])

    else:
        freqList.append(result)

a = [5,5,3,3,4,4,2,2]

FindMaxFrequency(a)

print(freqList)