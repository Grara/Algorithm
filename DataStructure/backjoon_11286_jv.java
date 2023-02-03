package DataStructure;
import java.io.*;
import java.util.*;
import static java.lang.Math.*;

//절대값이 가장 작은 수부터 출력하며 절대값이 같으면 더 적은 수를 출력

public class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
            //compare 메소드 오버라이드
            @Override
            public int compare(Integer n1, Integer n2){
                if(abs(n1) > abs(n2)) return 1; //절대값으로 오름차순 정렬
                else if(abs(n1) == abs(n2)){
                    if(n1 > n2) return 1; //절대값이 같은수들은 오름차순 정렬
                    else return -1;
                }
                else return -1;
            }
        });

        for(int i = 0; i < n; i++){
        int x = Integer.parseInt(br.readLine());
            if(x != 0) {
                pq.add(x);
            }
            else if(0 < pq.size()){
                bw.append(Integer.toString(pq.poll()) + "\n");
            }
            else bw.append("0\n");
        }
        bw.close();
    }
}