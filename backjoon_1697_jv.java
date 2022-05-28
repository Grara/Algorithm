import java.io.*;
import java.util.*;

//위치와 해당 위치에 도달한 시간을 담을 클래스
class Info{
    int pos;
    int time;
    
    Info(int pos, int time){
        this.pos = pos;
        this.time = time;
    }
}

public class Main{
    
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] visited = new boolean[100001];
        
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);
        
        LinkedList<Info> Qu = new LinkedList<Info>();
        
        int ans = 0;
        
        Qu.add(new Info(n,0));
        visited[n] = true;
        
        //BFS탐색
        while(!Qu.isEmpty()){
            //큐에서 위치와 위치에 도달한 시간 정보를 꺼냄
            Info info = Qu.poll();
            int p = info.pos;
            int t = info.time;
            
            //목표 위치에 도달하면 종료
            if(p == k){
                ans = t;
                break;
            }
            
            //현재 큐에서 꺼낸 위치의 +1, -1, *2 위치에 방문한적이 없으면
            //방문 후 큐에 넣음 (시간+1)
            if(0 <= p+1 && p+1 <= 100000 && !visited[p+1]){
                Qu.add(new Info(p+1, t+1));
                visited[p+1] = true;
            }
            if(0 <= p-1 && p-1 <= 100000 && !visited[p-1]){
                Qu.add(new Info(p-1, t+1));
                visited[p-1] = true;
            }
            if(0 <= p*2 && p*2 <= 100000 && !visited[p*2]){
                Qu.add(new Info(p*2, t+1));
                visited[p*2] = true;
            }
        }
        System.out.println(ans);
    }
}