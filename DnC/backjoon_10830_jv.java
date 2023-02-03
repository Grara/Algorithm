import java.io.*;
import java.util.*;
public class Main{
    static int MOD = 1000;
    
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] input1 = br.readLine().split(" ");
        int n = Integer.parseInt(input1[0]);
        long b = Long.parseLong(input1[1]);
        int[][] A = new int[n][n];
        
        
        for(int i = 0; i < n; i++){
            String[] inputs = br.readLine().split(" ");
            for(int j = 0; j < n; j++){
                A[i][j] =  Integer.parseInt(inputs[j]) % MOD;
            }
        }
        
        A = Pow(A, b);
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                bw.append(Integer.toString(A[i][j])+" ");
            }
            bw.append("\n");
        }
        
        bw.close();
    }
    
    //분할 정복
    public static int[][] Pow(int[][] arr, long expo){
        if(expo == 1){
            return arr;
        }
        
        int[][] temp = Pow(arr, expo / 2);
        
        temp = multiply(temp, temp);
        
        if(expo % 2 == 1) return multiply(temp, arr);
       
        return temp;
    }
    
    //행렬의 곱셈
    public static int[][] multiply(int[][] arr1, int[][] arr2){
        int len = arr1.length;
        int[][] result = new int[len][len];
        
        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                
                for(int h = 0; h < len; h++){
                    result[i][j] += arr1[i][h] * arr2[h][j];
                    result[i][j] %= MOD; //행렬 곱셈 시 원소를 1000으로 나누어줌
                }  
            }
        }
        
        return result;
    }
}