import java.io.*;
import java.util.*;

//행렬의 곱셈
//행렬을 곱하려면 앞 행렬의 열의 갯수와 뒤 행렬의 행의 갯수가 똑같아야 됨
public class Main{
    
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] input1 = br.readLine().split(" ");
        int n = Integer.parseInt(input1[0]);
        int m = Integer.parseInt(input1[1]);
        int[][] A = new int[n][m];
        
        for(int i = 0; i < n; i++){
            String[] inputs = br.readLine().split(" ");
            for(int j = 0; j < m; j++){
                A[i][j] =  Integer.parseInt(inputs[j]);
            }
        }
        
        String[] input2 = br.readLine().split(" ");
        int k = Integer.parseInt(input2[1]);
        int[][] B = new int[m][k];
    
        for(int i = 0; i < m; i++){
            String[] inputs = br.readLine().split(" ");
            for(int j = 0; j < k; j++){
                B[i][j] =  Integer.parseInt(inputs[j]);
            }
        }
        
        //여기까지 행렬 입력받고 생성

        int[][] ans = new int[n][k];
        
        //행렬의 곱셈
        for(int i = 0; i < n; i++){
            for(int j = 0; j < k; j++){
                
                for(int h = 0; h < m; h++){
                    ans[i][j] += A[i][h] * B[h][j];
                }
                bw.append(Integer.toString(ans[i][j]) + " ");
                
            }
            bw.append("\n");
        }
        
        bw.close();
    }
}