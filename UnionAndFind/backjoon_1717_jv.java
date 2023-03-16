import java.io.*;
import java.util.*;
import java.util.stream.Stream;

//문제명 : 여행 가자
//유니온&파인드 문제

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static Scanner sc =new Scanner(System.in);
    
    static int[] parent;

    public static void main(String[] args)throws Exception{
    	
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        

        parent = new int[n+1]; //parent[x] : x의 최상위 부모

        for(int i = 0; i <= n; i++){ //자기 자신을 부모로 설정
            parent[i] = i;
        }
        
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                int isConnect = Integer.parseInt(st.nextToken());
                if(isConnect == 1) //1이면 합쳐줘야 함
                    union(i, j);
            }
        }
        
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int commonParent = parent[start];
        
        String ans = "YES";
        
        for(int i = 1; i < m; i++){
            int cur = Integer.parseInt(st.nextToken());
            if(!isSameParent(commonParent, cur)){
                ans = "NO";
                break;
            }
        }

        System.out.println(ans);
        

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
