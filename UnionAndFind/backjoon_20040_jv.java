import java.io.*;
import java.util.*;
import java.util.stream.Stream;

//문제명 : 사이클 게임
//유니온&파인드 문제
//정점을 연결하다가 사이클이 처음 만들어지는게 몇번째 입력인지 알아내라


public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static Scanner sc =new Scanner(System.in);
    
    static int[] parent; //parent[x] = x의 부모
    static int ans = 0; //정답

    public static void main(String[] args)throws Exception{
    	
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n];
        
        //자기 자신을 부모로 설정
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b, i+1);
            
            if(ans != 0)
                break; //입력을 다 받을 필요는 없음, 중간에 끊어도 됨
        }

        System.out.println(ans);
        
    } //End of main
    
    static void union(int a, int b, int turn){ //합치기
        a = find(a);
        b = find(b);
        
        if(a != b){
            if(a > b) //부모노드는 자식노드보다 작아야 함
                parent[a] = b;
            else 
                parent[b] = a;
        }
        else{ //두개의 '현재' 부모가 같을 경우 두개를 합치면 사이클이 됨
        	ans = turn;
        }
    }

    static int find(int x){ //부모 찾기
        
        if(parent[x] == x){ //자기 자신이 부모면 그대로 리턴
            return x;
        }
        
        return parent[x] = find(parent[x]); //찾는 과정에서 최상위 부모를 설정함
    }

} //End of Main
