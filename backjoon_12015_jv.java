import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args)throws Exception{
        //가장 긴 부분 증가 수열을 구해라
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int[] seq = new int[n];
        for(int i = 0; i < n; i++){
            seq[i] = Integer.parseInt(input[i]);
        }
        
        
        int[] LIS = new int[n]; //부분 증가 수열을 담을 배열
        int len = 1; //해당 배열의 길이
        LIS[0] = seq[0]; //첫 숫자는 기존 수열의 첫번째 숫자를 가져옴 
        
        for(int i = 1; i < n; i++){
            
            //LIS배열의 현재 맨끝 숫자보다 기존 수열의 현재 숫자가 크면 맨끝에 기존 수열의 현재 숫자 추가
            if(LIS[len-1] < seq[i]){
                len++;
                LIS[len-1] = seq[i];
            }
            
            //아닐 경우 LIS중 현재 숫자 이상의 숫자 중에서 가장 작은 숫자를 대체
            else{
                int low = 0;
                int high = len;
                int mid = 0;
                while(low < high){
                    mid = (low + high) / 2;
                    
                    if(seq[i] <= LIS[mid]) high = mid;
                    else low = mid+1;
                }
                
                LIS[low] = seq[i];
            }
        }
        
        System.out.println(len);
        
    }
    
    
}