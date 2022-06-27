import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        String[] input_n = br.readLine().split(" ");
        int[] numbers = new int[n];
        for(int i = 0; i < n; i++){
            numbers[i] = Integer.parseInt(input_n[i]);
        }
        
        Arrays.sort(numbers); //오름차 정렬
        
        int m = Integer.parseInt(br.readLine());
        String[] input_m = br.readLine().split(" ");
        
        for(int i = 0; i < m; i++){
            if(BinarySearch(numbers,Integer.parseInt(input_m[i])) < 0 ){
                bw.append("0\n"); //없으면 0
            }
            else bw.append("1\n"); //있으면 1
        }
        
        bw.close();
    }
    public static int BinarySearch(int[] arr, int num){

        int low = 0;
        int high = arr.length - 1;

        while(low <= high){
            int mid = (low + high) / 2; //low와 high의 중간 인덱스 값과 비교
            
            //찾는 숫자보다 크면 high를 mid-1로 조정해서 범위를 좁힘 (더 작은 숫자 탐색)
            if(num < arr[mid]){
                high = mid - 1;
            }
            
            //찾는 숫자보다 작으면 low를 mid+1로 조정해서 범위를 좁힘 (더 큰 숫자 탐색)
            else if(num > arr[mid]){
                low = mid + 1;
            }

            else return mid; //현재 값이 찾는 숫자면 반환
        }
        
        return -1; //해당 숫자가 배열에 없으면 -1 반환
    }   
}