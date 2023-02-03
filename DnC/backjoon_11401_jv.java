import java.io.*;
import java.util.*;

public class Main{
    static long n;
    static long k;
    static long p = 1000000007;
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Long.parseLong(input[0]);
        k = Long.parseLong(input[1]);
        

        //조합 공식
        long nume = Factorial(n); //분자
        
        long denom = Factorial(n-k) * Factorial(k) % p; //분모

        //모듈러 연산 활용
        //나눗셈일 경우 모듈러 연산 활용 불가능
        //때문에 곱셈으로 표현하기 위해서 분모의 역원을 구한 후 곱해야함
        //a 제곱 p의 역원은 a 제곱 (p-2)
        long ans = nume * Pow(denom, p-2) % p;
        
        System.out.println(ans);
    }
    
    
    
    public static long Pow(long num, long expo){
        if(expo == 1) return num % p;
        
        long temp = Pow(num, expo / 2);
        
        if(expo % 2 == 1) return (temp * temp % p) * num % p;
        
        return temp * temp % p;
    }
    
    public static long Factorial(long num){
        long t = 1L;
        long fac = 1L;
        
        while(t <= num){
            fac = fac * t % p;
            t++;
        }
        
        return fac;
        
    }
}