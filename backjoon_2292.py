n = int(input())

count = 0

def divide(k):
        global count

        if k == 0:
                print(count)
        elif k < 0:
                count = -1
                print(count)
        elif k % 5 == 0 and k // 5 > 0:
                count += 1
                divide(k-5)
        elif k % 3 == 0 and k // 3 > 0:
                count += 1
                divide(k-3)

divide(n)