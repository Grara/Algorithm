import java.io.*;
import java.util.*;
import java.util.stream.Stream;

//문제명 : 집합의 표현
//유니온&파인드 기초 문제

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static Scanner sc =new Scanner(System.in);
    
    static int[] parent;

    public static void main(String[] args)throws Exception{
    	
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1]; //parent[x] = y : x의 부모는 y다

        for(int i = 0; i <= n; i++){ //자기 자신을 부모로 설정
            parent[i] = i;
        }
        
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int oper = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(oper == 0){
                union(a, b);
            }else{
                if(isSameParent(a, b))
                    bw.append("YES\n");
                else
                	bw.append("NO\n");
            }
        }

        bw.close();

    } //End of main
    
    static void union(int a, int b){ //합치기
        a = find(a);
        b = find(b);
        
        if(a != b){
            if(a > b) //부모노드는 자식노드보다 작아야 함
                parent[a] = b;
            else 
                parent[b] = a;
        }
    }

    static int find(int x){ //부모 찾기
        
        if(parent[x] == x){ //자기 자신이 부모면 그대로 리턴
            return x;
        }
        
        parent[x] = find(parent[x]); //찾는 과정에서 최상위 부모를 설정함
        return parent[x];
    }

    static boolean isSameParent(int a, int b){ //최상위 부모가 같은지 체크
        int x = find(a);
        int y = find(b);
        
        return (x == y);
    }


} //End of Main
