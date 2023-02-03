import java.io.*;
import java.util.*;

//냅색문제

public class Main{
    
    static int n, c, ans;
    static int[] arr = new int[30];
    static TreeMap<Integer, Integer> hm = new TreeMap<>();

    public static void main(String[] args)throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        initLeft(0, 0);
        prefixSum();
        search(15, 0);

        System.out.println(ans);

    } //End of main

    static void initLeft(int idx, int sum){
        if(sum > c) return;
        if(idx == 15 || arr[idx] == 0){
            hm.put(sum, hm.getOrDefault(sum, 0)+1);
            return;
        }
        initLeft(idx+1, sum);
        initLeft(idx+1, sum + arr[idx]);
    } //End of initLeft

    static void prefixSum(){
        Integer tmp = hm.firstKey();
        int sumTmp = 0;
        while(true){
            sumTmp += hm.get(tmp);
            hm.put(tmp, sumTmp);

            tmp = hm.higherKey(tmp);
            if (tmp == null) break;
        }
    } //End of prefixSum
    
    static void search(int idx, int sum){
        if(sum > c) return;
        if(idx == 30 || arr[idx] == 0){
            ans += hm.getOrDefault(hm.floorKey(c - sum), 0);
            return;
        }
        search(idx + 1, sum);
        search(idx + 1, sum + arr[idx]);
    } //End of search


} //End of Main
