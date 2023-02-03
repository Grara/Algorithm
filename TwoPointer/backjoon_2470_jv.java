import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main{
    
    public static void main(String[] args)throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int ans = Integer.MAX_VALUE;
        
        String[] seqInput = br.readLine().split(" ");

        //n보다 길이가 1 더 긴 배열, 맨끝은 0
    	int[] seq = new int[n+1];

        for(int i = 0; i < n; i++){
            seq[i] = Integer.parseInt(seqInput[i]);
        }

        int start = 0;
        int end = 0;
        int total = 0;

        //end와 start가 n보다 작거나 같으면
        while(start <= n && end <= n){

            //start~end까지의 누적합이 s이상이고, 현재 최소길이보다 start~end의 길이가 짧으면 최소길이 갱신
            if(total >= s && ans > end - start) ans = end - start;
            
            //start~end까지의 누적합이 s보다 작으면 누적합에 현재 end의 값을 더하고 end에 1을 더함
            if(total < s) total += seq[end++];

            //start~end까지의 누적합이 s보다 크거나 같으면 누적합에 현재 start의 값을 빼고 start에 1을 더함
            else total -= seq[start++];
        }

        if(ans==Integer.MAX_VALUE) ans = 0;

        System.out.println(ans);

    } //close main

} //close Main
