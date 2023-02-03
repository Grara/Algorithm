t = int(input()) #테스트 케이스 갯수

rList = list() #각 층의 리스트

for i in range(t): #각 테스트 케이스마다 입력 후 바로 출력

        a = int(input()) #층
        b = int(input()) #호

        for i in range(a + 1): #0층부터 a층까지 반복

                iList = list() #해당 층의 호에 대한 리스트
                prevNum = int() #j호 바로 이전호의 주민수

                for j in range(1, b + 1): #1호부터 b호까지 반복, 주민수를 구한뒤 iList에 추가 
                        
                        if i == 0: #0층이면 1 2 3 4 5...로 채워짐
                                iList.append(j)
                                prevNum = j

                        elif j == 1: #모든층의 1호는 항상 주민수 1
                                iList.append(1)
                                prevNum = 1

                        elif j == 2: #모든층의 2호는 항상 주민수 + 2
                                iList.append(i + 2)
                                prevNum = i+2

                        else: #j호의 주민수는 같은 층의 j-1호 주민수와 아래층의 j호의 주민수를 합친것과 같다.
                                iList.append(rList[i-1][j - 1] + prevNum)
                                prevNum = rList[i-1][j - 1] + prevNum

                rList.append(iList) #해당 층의 리스트가 전부 채워지면 iList를 rList에 추가

        print(rList[a][b-1]) #a층 b호의 주민수 출력

        rList.clear() #다음 테스트 케이스를 위해 rList를 비움