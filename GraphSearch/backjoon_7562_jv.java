package GraphSearch;
import java.io.*;
import java.util.*;

//칸의 위치와 해당 칸에 도달한 턴수를 담을 클래스
class Square{
    int x;
    int y;
    int turn;
    Square(int x, int y, int turn){
        this.x = x;
        this.y = y;
        this.turn = turn;
    }
}

public class Main{
    
    public static void main(String[] args)throws Exception{
        //나이트의 이동 가능 범위 탐색을 위한 배열
        int[] dx = {1,2,2,1,-1,-2,-2,-1};
        int[] dy = {2,1,-1,-2,-2,-1,1,2};
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < t; i++){
            LinkedList<Square> qu = new LinkedList<Square>();
            int n = Integer.parseInt(br.readLine());
            
            String[] startInput = br.readLine().split(" ");
            int startX = Integer.parseInt(startInput[0]);
            int startY = Integer.parseInt(startInput[1]);
            
            String[] targetInput = br.readLine().split(" ");
            int targetX = Integer.parseInt(targetInput[0]);
            int targetY = Integer.parseInt(targetInput[1]);
            
            boolean[][] visited = new boolean[n][n];
            
            qu.add(new Square(startX, startY, 0));
            visited[startY][startX] = true;
            
            //BFS탐색
            while(true){
                //큐에서 칸정보 꺼내기
                Square sq = qu.poll();
                int x = sq.x;
                int y = sq.y;
                int turn = sq.turn;
                
                //목표칸에 도달하면 목표칸에 도달한 턴수 출력 후 종료
                if(x == targetX && y == targetY){
                    bw.append(Integer.toString(turn)+"\n");
                    break;
                }
                for(int j = 0; j < 8; j++){
                    int rx = x + dx[j];
                    int ry = y + dy[j];
                    //해당칸에 방문한적없으면 방문 후 큐에 넣기(턴수+1)
                    if(0 <= rx && rx < n && 0 <= ry && ry < n && !visited[ry][rx]){
                        qu.add(new Square(rx, ry, turn+1));
                        visited[ry][rx] = true;
                    }
                }
            }
        }
        
        bw.close();
        
    }
}