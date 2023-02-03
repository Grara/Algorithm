import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args)throws Exception{
        //n * n 크기의 2차원 배열 A의 각 원소 A[i][j]는 i * j 다. (인덱스는 1부터 시작)
        //원소들을 n * n 크기의 1차원 배열 B로 옮긴 후 오름차순 정렬을 했을 때,
        //B[k]를 구해라 
        
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        
        //B[k] = B[k]보다 작거나 같은 수가 k개 만큼 있다.
        //1~k 까지의 범위 탐색
        long high = k;
        long low = 1;
        
        //LowerBound 이용
        while(low < high){
            long mid = (low + high) / 2;
            
            //각각의 A[i][0] ~ A[i][j]는 i단을 나타냄
            //임의의 수 x를 i로 나눴을 때 몫 = x보다 작거나 같은 i단의 갯수
            //하지만 n개를 초과할 순 없으므로 최대 n개

            long cnt = 0; //x로 위의 과정을 진행했을 때 작거나 같은 수의총합
            
            for(int i = 1; i <= n; i++){
                cnt += Math.min(mid / i, n);
            }
            
            //k가 cnt 보다 작거나 같다 = x를 낮춰야한다. = high를 좁힌다.
            if(k <= cnt ) high = mid;
            else low = mid + 1;
        }
        
        System.out.println(low);
    }
}