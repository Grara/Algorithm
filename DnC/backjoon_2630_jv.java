import java.io.*;
import java.util.Arrays;

public class Main{
    
    static int wCnt = 0; //하얀색의 갯수
    static int bCnt = 0; //파란색의 갯수
    
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        boolean[][] confetti = new boolean[n][n];
        
        for(int i = 0; i < n; i++){
            String[] input = br.readLine().split(" ");
            for(int j = 0; j < n; j++){
                if(input[j].equals("1")) confetti[i][j] = true;
            }
        }
        
        Devide(confetti);
        
        System.out.println(wCnt);
        System.out.println(bCnt);
    }
    
    
    public static void Devide(boolean[][] arr){
        boolean existWhite = false; //현재 분할된 영역에 하얀색이 있는지 여부
        boolean existBlue = false; //현재 분할된 영역에 파란색이 있는지 여부
        boolean needDevide = false; //해당 변수가 true면 분할 필요
        
        //현재 영역을 순회하면서 하얀색과 파란색이 존재하는지 체크
        for(int i = 0; i < arr.length; i++){
            if(needDevide) break;
            for(int j = 0; j < arr.length; j++){
                if(arr[i][j]) existBlue = true;
                else existWhite = true;

                //하얀색과 파란색이 둘다 있을 경우 4분할 필요
                if(existWhite && existBlue) needDevide = true;
                if(needDevide) break;
            }
        }
        
        //분할이 필요치않으면 현재 영역에 칠해진 색의 카운트 증가
        if(!needDevide){
            if(existWhite) wCnt += 1;
            else bCnt += 1;
            return;
        }
        
        //현재 영역을 4사분면으로 분할
        boolean[][] quad_1 = new boolean[arr.length / 2][arr.length / 2];
        boolean[][] quad_2 = new boolean[arr.length / 2][arr.length / 2];
        boolean[][] quad_3 = new boolean[arr.length / 2][arr.length / 2];
        boolean[][] quad_4 = new boolean[arr.length / 2][arr.length / 2];
        
        for(int i = 0; i < arr.length / 2; i++){
            quad_1[i] = Arrays.copyOfRange(arr[i], arr.length / 2, arr.length);
            quad_2[i] = Arrays.copyOfRange(arr[i], 0, arr.length / 2);
            quad_3[i] = Arrays.copyOfRange(arr[i + arr.length / 2], 0, arr.length / 2);
            quad_4[i] = Arrays.copyOfRange(arr[i + arr.length / 2], arr.length / 2, arr.length);
        }
        
        //각 사분면마다 재귀함수 실행
        Devide(quad_1);
        Devide(quad_2);
        Devide(quad_3);
        Devide(quad_4);
        
        return;
    }
}