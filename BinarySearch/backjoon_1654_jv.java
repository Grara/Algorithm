import java.io.*;
import java.util.*;


public class Main{
    public static void main(String[] args)throws Exception{
        //랜선 k개를 잘라서 n개로 만들어야한다. 이때 가능한 최대 길이를 출력
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int k = Integer.parseInt(input[0]);
        int n = Integer.parseInt(input[1]);
        long max = 0;
        long min = 0;
        long mid = 0;
        long[] lines = new long[k];
        
        for(int i = 0; i < k; i++){
            long len = Long.parseLong(br.readLine());
            lines[i] = len;
            if(max < len) max = len;
        }

        max++; //UpperBound를 이용하기때문에 +1
        
        while(min < max){
            
            mid = min + ((max - min) / 2); //전선을 자를 길이 
            
            int cnt = 0; //현재 자를 길이로 만들 수 있는 전선 갯수
            
            for(int i = 0; i < k; i++){
                cnt += lines[i] / mid;
            }
            
            //현재 만든 전선의 갯수가 목표 갯수보다 적거나 같으면 max를 좁힘
            //=길이를 더 짧게 한다.
            if(cnt < n){
                max = mid;
            }
            
            //현재 만든 전선의 갯수가 목표 갯수보다 많으면 min을 좁힘
            //=길이를 더 길게 한다.
            else min = mid + 1;
        }
        //정답 : 목표 갯수를 '1개 초과'해서 만들 수 있는 전선의 '최소길이'에서 1을 뺀 길이
        System.out.println(min - 1);
    }
}