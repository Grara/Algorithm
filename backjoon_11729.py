#하노이의 탑

N = int(input())


def hanoi(num, start, end):
    if num == 1:
        print(start, end)
        return
    
    hanoi(num-1, start, 6-start-end)
    print(start, end)
    hanoi(num-1, 6-start-end, end)
    
    return
print(2**N - 1)
hanoi(N, 1, 3)