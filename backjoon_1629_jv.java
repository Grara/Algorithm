import java.io.*;
import java.util.*;

public class Main{
    //숫자의 범위가 커서 long 사용
    static long a;
    static long b;
    static long c;
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        a = Long.parseLong(input[0]);
        b = Long.parseLong(input[1]);
        c = Long.parseLong(input[2]);
        
        System.out.println(Pow(a,b));
    }
    
    
    public static long Pow(long num, long exponent){
        if(exponent == 1){
            return num % c;
        }
        
        //지수를 분할
        long temp = Pow(num, exponent / 2);
        
        //모듈러 연산 활용
        if(exponent % 2 == 1){
            return (temp * temp % c) * num % c;
        }
        
        return temp * temp % c;
    }
}