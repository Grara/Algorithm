n = int(input())

count = 0 #봉지 수

def divide(k):
        global count

        if k == 0: #설탕이 딱 맞게 떨어졌으면 봉지 수 출력
                print(count)

        elif k < 0: #설탕이 딱 맞게 떨어지지않았으면 -1 출력
                count = -1
                print(count)

        elif k % 5 == 0: #5로 나눠지면 5kg봉투 1추가 이후 봉지에 설탕 담기 진행
                count += 1
                divide(k-5)

        elif k % 3 == 0: #3으로 나눠지면 3kg봉투 1추가 이후 봉지에 설탕 담기 진행
                count += 1
                divide(k-3)

        else:
                count+=1 #3과 5 둘 모두로 안나눠지면 5kg봉투 1추가 이후 봉지에 설탕 담기 진행
                divide(k-5)

divide(n)