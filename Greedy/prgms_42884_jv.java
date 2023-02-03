package Greedy;
import java.util.*;

class Solution {
    public int solution(int[][] r) {
        int cnt = 1;
        Arrays.sort(r, (c1, c2) -> c1[1] - c2[1]); //각 차량의 나가는 위치로 오름차순
        int[] enterCam = new int[r.length]; //각 차량의 만난 카메라 위치 저장
        Arrays.fill(enterCam, Integer.MIN_VALUE); //초기화
        enterCam[0] = r[0][1]; //첫번째 차량의 나가는 위치에 카메라 설치
        
        for(int i = 1; i < r.length; i++){
            //이전 차량이 만난 카메라 위치가 현재 차량의 출발점보다 뒤에 있으면
            //현재 차량이 만난 카메라 위치에 그대로 저장 (만나게 되니까)
            if(r[i][0] <= enterCam[i-1])
            enterCam[i] = enterCam[i-1];
            
            //아닐 경우 현재 차량이 나가는 지점에 카메라 설치
            else {
                cnt++;
                enterCam[i] = r[i][1];
            }
        }
        
        return cnt;
    }
}