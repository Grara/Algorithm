import java.io.*;
import java.util.*;
import java.util.stream.Stream;

//문제명 : DSLR
//DP문제
//BFS이용
//A를 B로 바꾸는 최소연산 순서

class Node implements Comparable<Node>{
    int num;
    String oper;

    Node(int num, String oper){
        this.num = num;
        this.oper = oper;
    }    

    @Override
    public int compareTo(Node n){
        return this.oper.length() - n.oper.length();
    }
}

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args)throws Exception{
    	
        int T = Integer.parseInt(br.readLine());
    	

        for(int i =0; i < T; i++){
            
        	
        	boolean[] visited = new boolean[20002];
            PriorityQueue<Node> pq = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            
            pq.add(new Node(A, ""));

            while(!pq.isEmpty()){
                Node node = pq.poll();
                int num = node.num;
                String oper = node.oper;

                if(num == B){
                    bw.write(oper+"\n");
                    break;
                }
                
                int D = (2 * num) % 10000; //n * 2를 한뒤 10000으로 나눈 나머지
                int S = num == 0 ? 9999 : num - 1; //n - 1, n이 0이면 9999
                int L = (num % 1000) * 10 + num / 1000; //자릿수 왼쪽으로 한칸씩 이동
                int R = (num % 10) * 1000 + num / 10; //자릿수 오른쪽으로 한칸씩 이동
                
                if(!visited[D]){
                    visited[D] = true;
                    pq.add(new Node(D, oper + "D"));
                }

                if(!visited[S]){
                    visited[S] = true;
                    pq.add(new Node(S, oper + "S"));
                }
                
                if(!visited[L]){
                    visited[L] = true;
                    pq.add(new Node(L, oper + "L"));
                }

                if(!visited[R]){
                    visited[R] = true;
                    pq.add(new Node(R, oper + "R"));
                }
            }

        }

        bw.close();
        
    } //End of main

} //End of Main
