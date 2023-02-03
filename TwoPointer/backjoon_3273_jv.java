import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main{
    
    public static void main(String[] args)throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	//StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int ans = 0;
    	int LIMIT = 2000_001;
    	int n = Integer.parseInt(br.readLine());
    	
    	boolean[] coordinate = new boolean[LIMIT];
    	
    	int[] seq = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	
    	for(int num : seq) {
    		coordinate[num] = true;
    	}
    	
    	int x = Integer.parseInt(br.readLine());
    	
    	for(int num : seq) {
    		if(num < x && coordinate[x - num]) {
    			ans++;
    		}
    	}
    	
    	System.out.println(ans / 2);
    	
    } //close main

} //close Main
