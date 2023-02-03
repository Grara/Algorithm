import java.io.*;
import java.util.*;


public class Main{
	
	static int[] house;
	
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int c = Integer.parseInt(input[1]);
        
        house = new int[n];
        
        for(int i = 0; i < n; i++) {
        	int coor = Integer.parseInt(br.readLine());
        	house[i] = coor; 
        }
    	
        Arrays.sort(house);
        
        int low = 0;
        int high = house[n-1] - house[0] + 1;
        
        //UpperBound
        while(low < high) {
        	
        	int mid = (high + low) / 2; //설치 최소 간격
        	
            //현재 최소 간격으로 설치한 갯수가 목표갯수보다 적으면 high를 좁힘 = 설치 최소 간격 줄임
        	if(installCount(mid) < c) {
        		high = mid;
        	}
        	
            //현재 최소 간격으로 설치한 갯수가 목표갯수보다 많거나 같으면 low를 좁힘 = 설치 최소 간격 늘림
        	else low = mid + 1;
        }
        
        System.out.println(low - 1);
        
    }
    
    public static int installCount(int dis) {
    	
    	int count = 1;
    	int lastLocate = house[0];
    	
        //마지막에 설치한 집을 기준으로 비교해서 설치 가능여부 확인
    	for(int i = 1; i < house.length; i++) {
    		int currentLocate = house[i];
    		
    		if(dis <= currentLocate - lastLocate) {
    			count++;
    			lastLocate = currentLocate;
    		}
    	}
    	
        //설치한 집 갯수 반환
    	return count;
    }
    
}