#분해합
#예시 : 256 = 245+2+4+5
#예시에서 245가 256의 생성자가 됨
#N이 주어졌을 때 가장 작은 생성자를 구해라

N = int(input())
hasResult = False #생성자가 있는지 여부

for i in range(N): #0부터 N까지
    result = i #결과값에 일단 N
    temp = i #반복문내에서 임시로 쓸 변수
    
    for j in range(len(str(temp)), 0, -1): #숫자의 자리수만큼 반복, 3자리 숫자면 3부터 1로
        num = temp // ( 10 ** (j-1) ) #큰자리수부터 숫자 추출
        result += num #추출한 숫자를 결과값에 더함
        temp -= num * ( 10 ** (j-1) ) #추출한 숫자의 자리수를 뺀다
    
    if result == N: #결과값이 N과 같다면 출력
        print(i)
        hasResult = True
        break
    
    else:
        continue

if hasResult == False: #생성자가 없으면 0출력
    print(0)