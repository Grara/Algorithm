import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        //기본적으로 오름차순으로 정렬 후 첫번째 원소부터 출력하므로 reverseOrder로 뒤바꿔줌

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