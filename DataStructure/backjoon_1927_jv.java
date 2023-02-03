package DataStructure;
import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < n; i++){
        int x = Integer.parseInt(br.readLine());
            if(0 < x) {
                pq.add(x);
            }
            else if(0 < pq.size()){
                bw.append(Integer.toString(pq.poll()) + "\n");
            }
            else bw.append("0\n");
        }
        bw.close();
    }
}