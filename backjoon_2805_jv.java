import java.io.*;
import java.util.*;


public class Main{
    public static void main(String[] args)throws Exception{
        //각각의 나무를 정해진 높이에서 자른 후 M미터를 가져가야함
        //이때 가능한 최대 절단 높이
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        long max = 0;
        long min = 0;
        long mid = 0;
        long[] Trees = new long[n];
        
        String[] input_Tree = br.readLine().split(" ");
        
        for(int i = 0; i < n; i++){
            long len = Long.parseLong(input_Tree[i]);
            Trees[i] = len;
            if(max < len) max = len;
        }
        
        max++; //UpperBound를 이용하기때문에 +1
        
        while(min < max){
            
            mid = min + ((max - min) / 2); 
            
            long len = 0; //자르고 난 후 나무길이
            
            for(int i = 0; i < n; i++){
                //절단 높이가 나무보다 낮으면 그만큼 베어내고 len에 더함
                if(mid < Trees[i]) {
                	len += Trees[i] - mid;
                }
            }
            
            //수확한 나무길이가 목표 길이보다 짧으면 max를 좁힘 = 절단 높이를 낮춤 = 더 많이 자름
            if(len < m){
                max = mid;
            }
            
            //수확한 나무길이가 목표 길이보다 길거나 같으면 min을 좁힘 = 절단 높이를 높힘 = 더 적게 자름
            else min = mid + 1;
        }
        
        System.out.println(min - 1);
    }
}