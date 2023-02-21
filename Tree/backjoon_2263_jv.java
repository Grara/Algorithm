import java.io.*;
import java.util.*;
import java.util.stream.Stream;

//문제명 : 트리의 순회
//트리문제
//중위 순회, 후위 순회가 주어졌을 때 전위 순회를 구하라

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static Scanner sc =new Scanner(System.in);
    
    static int n;
    static int[] in, post, pre;
    static int idx;

    public static void main(String[] args)throws Exception{
    	
        int n = Integer.parseInt(br.readLine());
        
        in = new int[n]; //중위 순회
        post = new int[n]; //후위 순회
        pre = new int[n]; //전위 순회

        st= new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            in[i] = Integer.parseInt(st.nextToken()); //중위 순회 입력받기
        }

        st= new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            post[i] = Integer.parseInt(st.nextToken()); //후위 순회 입력받기
        }

        getPreOrder(0, n-1, 0, n-1);
        
        for(int num : pre){
            bw.write(Integer.toString(num) + " ");
        }

        bw.close();

    } //End of main
    
    //재귀를 이용해서 전위 순회 채우기
    static void getPreOrder(int inStart, int inEnd, int postStart, int postEnd) {

        if(inStart <= inEnd && postStart <= postEnd){
            pre[idx++] = post[postEnd];

            int pos = inStart;

            for(int i = inStart; i <= inEnd; i++){
                if(in[i] == post[postEnd]){
                    pos = i;
                    break;
                }
            }

            getPreOrder(inStart, pos - 1, postStart , postStart + pos - inStart - 1);
            getPreOrder(pos + 1, inEnd, postStart + pos - inStart, postEnd - 1);
        }

    }


} //End of Main
