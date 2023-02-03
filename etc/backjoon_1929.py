from sys import stdout


M, N= map(int,input().split()) #입력

multiple = list([2]) #리스트의 배수들은 소수에서 제외

for i in range(1,N + 1): #1~N까지

    isPrime = False #소수인지 판정하기 위한 변수

    for num in multiple: #i가 multiple리스트 숫자들의 배수인지 확인
    
        if (i % num == 0 or i == 1) and i != num: #i가 배수 혹은 1이면
            isPrime = False #isPrime을 False로 하고 배수확인 종료
            break
            
        else: 
            isPrime = True #isPrime을 True로 하고 다음 multiple숫자의 배수인지 확인

    if isPrime == True: #i가 소수면
        if M <= i <= N: #i가 M보다 크고 N보다 작으면
            stdout.write(str(i)+"\n")
        
        if i <= N ** (1/2): #i가 N의 제곱근보다 작다면 multiple에 추가
            multiple.append(i)