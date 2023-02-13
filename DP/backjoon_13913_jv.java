import java.io.*;
import java.util.*;
import java.util.stream.Stream;

//문제이름 : 숨바꼭질4
//DP문제
//BFS 활용
//N부터 K까지 가는 최소 시간을 구하라
//1초마다 +1, -1, *2 세가지 이동이 가능함

//위치와 오는데 걸린 시간 저장
class MoveInfo implements Comparable<MoveInfo>{
    int time;
    int point;

    MoveInfo(int point, int time){
        this.point = point;
        this.time = time;
    }

    //시간순으로 오름차순
    @Override
    public int compareTo(MoveInfo m){
        return this.time - m.time;
    }
}

public class Main{

    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    public static void main(String[] args)throws Exception{
    	
    	int LIMIT = 3_000_000;
        int N, K;
        int [] time = new int[LIMIT]; //시간DP
        int [] prev = new int[LIMIT]; //이전위치 저장 DP
        boolean [] visited = new boolean[LIMIT]; //방문 체크

        PriorityQueue<MoveInfo> pq = new PriorityQueue<>(); //우선순위 큐

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        time[N] = 0;
        pq.add(new MoveInfo(N, 0));

        //BFS실행
        while(!pq.isEmpty()){
            
            MoveInfo cur = pq.poll();
            
            //K도착하면 종료
            if(cur.point == K){                
                break;
            }
            
            //위치 범위를 벗어나면 무시
            if(cur.point < 0 || cur.point >1400000) continue;
            
            //현재위치 + 1
            if(!visited[cur.point + 1]){
                time[cur.point + 1] = cur.time + 1;
                prev[cur.point + 1] = cur.point;
                pq.add(new MoveInfo(cur.point + 1, cur.time + 1));
                visited[cur.point + 1] = true;
            }

            //현재위치 - 1
            if(cur.point > 0 && !visited[cur.point - 1]){
                time[cur.point - 1] = cur.time + 1;
                prev[cur.point - 1] = cur.point;
                pq.add(new MoveInfo(cur.point - 1, cur.time + 1));
                visited[cur.point - 1] = true;
            }

            //현재위치 * 2
            if(!visited[cur.point * 2]){
                time[cur.point * 2] = cur.time + 1;
                prev[cur.point * 2] = cur.point;
                pq.add(new MoveInfo(cur.point * 2, cur.time + 1));
                visited[cur.point * 2] = true;
            }
        }

        bw.write(time[K]+"\n");
        
        Stack<Integer> stack = new Stack<>(); 

        //K부터 N까지 역추적
        int idx = K;
        while(idx != N){
        	stack.add(prev[idx]);            
            idx = prev[idx];
        }
        
        while(!stack.isEmpty()){
            bw.write(stack.pop() + " ");
        }
        
        bw.write(Integer.toString(K));
        bw.close();
    } //End of main

} //End of Main
