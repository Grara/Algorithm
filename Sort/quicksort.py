#퀵정렬


from sys import stdin

def QuickSort(arr, low, high):
    if low >= high:
        return
    
    pivot = low #피벗
    left = low + 1
    right = high
    while left <= right: #left가 right보다 크기전까지 반복

        #left인덱스의 숫자가 피벗보다 작으면 끝에 도달하기까지 점점 인덱스 증가
        while left <= high and arr[left] <= arr[pivot]: #조건문 순서 중요!! 뒤바뀌면 버그
            left += 1
            
        #right인덱스의 숫자가 피벗보다 크면 시작점에 도달하기까지 점점 인덱스 감소
        while right > low and arr[right] >= arr[pivot]: #조건문 순서 중요!! 뒤바뀌면 버그
            right -= 1
        
        if left > right: #left와 right가 교차했을 때
            arr[right], arr[pivot] = arr[pivot], arr[right]

        else: #그외에
            arr[left], arr[right] = arr[right], arr[left] 

    QuickSort(arr, low, right-1)
    QuickSort(arr, right+1, high)


n = int(input())
numList = list()

numList = [1, 10, 5, 7, 9, 3, 6, 2, 4, 8]

QuickSort(numList,0, len(numList)-1)

print(numList)