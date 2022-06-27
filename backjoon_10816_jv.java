import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args)throws Exception{
        //중복이 허용되는 숫자 배열이 주어지며, m개의 숫자에 대해서 각각 배열안에 몇개 있는지 출력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        String[] input_n = br.readLine().split(" ");
        int[] numbers = new int[n];
        for(int i = 0; i < n; i++){
            numbers[i] = Integer.parseInt(input_n[i]);
        }
        
        Arrays.sort(numbers);
        
        int m = Integer.parseInt(br.readLine());
        String[] input_m = br.readLine().split(" ");
        
        for(int i = 0; i < m; i++){
        	int key = Integer.parseInt(input_m[i]);
            //정답(해당 숫자의 갯수) = 상한 인덱스 - 하한 인덱스
            int ans = upperBound(numbers, key) - lowerBound(numbers, key);
            bw.append(Integer.toString(ans));
            if(i != m-1) {
            	bw.append(" ");
            }
        }
        
        bw.close();
    }
    
    //찾으려는 값의 하한 인덱스
    public static int lowerBound(int[] arr, int key) {
    	
    	int low = 0;
    	int high = arr.length; //최대범위 + 1
    	
    	while(low < high) {
    		int mid = low + ((high - low) / 2);
    		
            //찾으려는 값보다 '크거나 같으면' high를 좁힘
    		if(key <= arr[mid]) {
    			high = mid;
    		}
    		
            //찾으려는 값보다 작으면 low를 좁힘
    		else low = mid + 1;
    	}
    	
    	return low;
    }
    
    //찾으려는 값의 상한 인덱스
	public static int upperBound(int[] arr, int key) {
	    	
	    	int low = 0;
	    	int high = arr.length;
	    	
	    	while(low < high) {
	    		int mid = low + ((high - low) / 2);
	    		
                //찾으려는 값이 현재 값보다 작으면 high를 좁힘 = 더 작은 수 탐색
	    		if(key < arr[mid]) {
	    			high = mid;
	    		}
	    		
                //찾으려는 값보다 '작거나 같으면' low를 좁힘
	    		else low = mid + 1;
	    	}
	    	
	    	return low;
	   }
}
