import java.io.*;
import java.util.*;

//숫자가 입력될 때마다 현재 입력된 숫자 중 중간값을 출력
public class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        //최대힙과 최소힙 준비

        int n = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < n; i++){
            int x = Integer.parseInt(br.readLine());

            //최대힙과 최소힙의 원소 갯수가 같으면 최대힙에 추가
            if(maxPQ.size() == minPQ.size()) maxPQ.add(x);
            
            //이외엔 최소힙에 추가
            else minPQ.add(x);
            
            //최대힙의 맨 위 숫자가 최소힙의 맨위 숫자보다 크면 서로 반대 힙으로 집어넣음
            if(0 < minPQ.size() && minPQ.peek() < maxPQ.peek()){
                minPQ.add(maxPQ.poll());
                maxPQ.add(minPQ.poll());
            }
            
            //최대힙의 맨 위 숫자 출력
            bw.append(Integer.toString(maxPQ.peek()) + "\n");
        }
        
        bw.close();
    }
}