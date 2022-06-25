import java.io.*;
import java.util.*;

public class Main{
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        
        int n = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[n][n];
        
        for(int i = 0; i < n; i++){
            String[] input = br.readLine().split("");
            for(int j = 0; j < n; j++){
                if(input[j].equals("1")) map[i][j] = true;
            }
        }
        
        Compress(map);
        
        bw.close();
    }
        public static void Compress(boolean[][] arr)throws Exception{
            boolean is_0 = false; //현재 영역에 0이 있는지 체크
            boolean is_1 = false; //현재 영역에 1이 있는지 체크
            boolean needComp = false; // 압축 필요 여부
            int len = arr.length;
            
            //현재 영역 순회하면서 압축 필요 여부 검사
            for(int i = 0; i < len; i++){
                if(needComp)break;
                for(int j = 0; j < len; j++){
                    if(arr[i][j]) is_1 = true;
                    else is_0 = true;

                    //0과 1이 섞여있으면 압축 필요
                    if(is_0 && is_1) needComp = true;
                    if(needComp) break;
                }
            }
            
            //더이상 압축이 필요없으면 현재 영역의 색깔 반환
            if(!needComp){
                if(is_0) bw.append("0");
                else bw.append("1");
                return;
            }

            //압축 시작 시 ( 추가
            bw.append("(");
            
            //4사분면으로 나눠서 압축
            boolean[][] quad_1 = new boolean[len / 2][len / 2];
            boolean[][] quad_2 = new boolean[len / 2][len / 2];
            boolean[][] quad_3 = new boolean[len / 2][len / 2];
            boolean[][] quad_4 = new boolean[len / 2][len / 2];
            
            for(int i = 0; i < (len / 2); i++){
                quad_1[i] = Arrays.copyOfRange(arr[i], 0, len/2);
                quad_2[i] = Arrays.copyOfRange(arr[i], len/2, len);
                quad_3[i] = Arrays.copyOfRange(arr[i + (len/2)], 0, len/2);
                quad_4[i] = Arrays.copyOfRange(arr[i + (len/2)], len/2, len);
            }
            
            Compress(quad_1);
            Compress(quad_2);
            Compress(quad_3);
            Compress(quad_4);
            
            //압축 끝나면 )으로 닫기
            bw.append(")");
            

            return;
        }
}