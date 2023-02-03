import java.io.*;
import java.util.*;


//종이에있는 -1, 0, 1 갯수 세기

public class Main{
    
    static int cnt1 = 0; // -1 갯수
    static int cnt2 = 0; // 0 갯수
    static int cnt3 = 0; // 1 갯수
    
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        int[][] confetti = new int[n][n];
        
        for(int i = 0; i < n; i++){
            String[] input = br.readLine().split(" ");
            for(int j = 0; j < n; j++){
                confetti[i][j] = Integer.parseInt(input[j]);
            }
        }
        
        Devide(confetti);
        
        System.out.println(cnt1);
        System.out.println(cnt2);
        System.out.println(cnt3);
    }
    
    public static void Devide(int[][] arr){
        boolean is1 = false; // -1이 있는지 체크
        boolean is2 = false; // 0이 있는지 체크
        boolean is3 = false; // 1이 있는지 체크
        int diff_Cnt = 0; // 서로 다른 종류의 숫자가 섞여있는지 체크
        boolean needDevide = false; //분할 필요 여부
        int len = arr.length;
        

        //영역 순회하면서 각각의 숫자의 존재 여부 체크
        for(int i = 0; i < len; i++){
            if(needDevide) break;
            for(int j = 0; j < len; j++){
                if(arr[i][j] == -1 && !is1){ 
                    is1 = true;
                    diff_Cnt += 1;
                }
                else if(arr[i][j] == 0 && !is2){ 
                    is2 = true;
                    diff_Cnt += 1;
                }
                else if(arr[i][j] == 1 && !is3){ 
                    is3 = true;
                    diff_Cnt += 1;
                }

                //다른 종류의 숫자가 섞여있으면 분할 필요
                if(2 <= diff_Cnt) needDevide = true;
                if(needDevide) break;
            }
        }
        
        //분할이 필요없으면 현재 영역의 숫자 카운트 증가
        if(!needDevide){
            if(is1) cnt1 += 1;
            else if(is2) cnt2 += 1;
            else if(is3) cnt3 += 1;
            return;
        }
        
        //영역을 9분할해서 재귀 실행
        for(int i = 0; i < len; i += len/3){
            for(int j = 0; j < len; j += len/3){
                int[][] quad = new int[len/3][len/3];
                for(int k = 0; k < len / 3; k++){
                    quad[k] = Arrays.copyOfRange(arr[k + i], j, j+len/3);
                }
                Devide(quad);
            }
        }
        return;
    }
}