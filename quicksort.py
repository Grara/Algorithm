#퀵정렬


from sys import stdin

def QuickSort(arr, low, high):
    if low >= high:
        return
    
    pivot = low
    left = low + 1
    right = high
    while left <= right:
        while arr[left] <= arr[pivot] and left <= high:
            left += 1
        while arr[right] >= arr[pivot] and right > low:
            right -= 1
        if left > right:
            arr[right], arr[pivot] = arr[pivot], arr[right]
        else:
            arr[left], arr[right] = arr[right], arr[left] 
    
    QuickSort(arr, low, right-1)
    QuickSort(arr, right+1, high)

    print(arr)

    



n = int(input())
numList = list()

for i in range(n):
    num = int(stdin.readline())
    numList.append(num)

QuickSort(numList,0, len(numList)-1)

print(numList)