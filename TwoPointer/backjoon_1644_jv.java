import java.io.*;
import java.util.*;

public class Main{
    
    static int n;
    static ArrayList<Integer> primes = new ArrayList<>();


    public static void main(String[] args)throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int n = Integer.parseInt(st.nextToken());
        getPrime(n);

        int ans = 0;
        int sum = 2;
        int right = 0, left = 0;

        //투포인터
        while(left < primes.size() && right < primes.size()){
            if(sum == n){
                ans++;
                sum -= primes.get(left);
                left++;
            }

            else if(sum > n){
                sum -= primes.get(left);
                left++;
            }else {
                right++;
                if(right >= primes.size()) break;
                sum += primes.get(right);
            }
        }

        System.out.println(ans);

    } //End of main

    //에라토스테네스의 체로 소수 구하기
    static void getPrime(int n){
        int temp[] = new int[n+1];
        int rootN = (int)Math.sqrt(n);
        for(int i=2; i <= n; i++){
            temp[i] = i;
        }

        for(int i=2; i <= rootN; i++){
            if(temp[i] != 0){
                for(int j=i+i; j <= n; j+=i){
                    temp[j] = 0;
                }
            }
        }

        for(int i=2; i <= n; i++){
            if(temp[i] != 0){
                primes.add(temp[i]);
            }
        }
    } //End of getPrime


} //End of Main
