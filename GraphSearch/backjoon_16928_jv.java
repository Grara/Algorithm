package GraphSearch;
import java.util.*;
import java.io.*;

//칸 위치와 해당 칸에 도달한 턴수 저장할 클래스
class Record{
    int square;
    int turn;
    Record(int square, int turn){
        this.square = square;
        this.turn = turn;
    }
}

public class Main{
    
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<Record> qu = new LinkedList<Record>();
        
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        
        int[] connects = new int[101]; //각 칸마다 연결된 칸을 저장하기 위한 배열
        boolean[] visited = new boolean[101];
        
        int ans;
        
        for(int i = 0; i < n+m; i++){
            String[] connectInput = br.readLine().split(" ");
            int a = Integer.parseInt(connectInput[0]);
            int b = Integer.parseInt(connectInput[1]);

            connects[a] = b; //a칸에 b연결
        }
        
        qu.add(new Record(1, 0));
        visited[1] = true;
        
        while(true){
            Record r = qu.poll();
            int sq = r.square;
            int t = r.turn;
            
            if(sq == 100){
                ans = t;
                break;
            }
            
            for(int i = 1; i < 7; i++){
                int next = sq + i; //주사위 1~6까지 나온 경우의 수 탐색
                if(next <= 100 && !visited[next]){
                    //해당칸에 연결된 칸이 있으면 해당칸과 연결된 칸 모두 방문
                    //이후 연결된 칸을 큐에 넣음 (턴 + 1)
                    if(connects[next] != 0){
                        visited[next] = true;
                        visited[connects[next]] = true;
                        qu.add(new Record(connects[next], t + 1));
                    }
                    //연결된 칸이 없으면 해당칸을 큐에 넣음
                    else{
                        visited[next] = true;
                        qu.add(new Record(next, t + 1));
                    }
                }
            }
        }
        
        System.out.println(ans);
    }
    
}