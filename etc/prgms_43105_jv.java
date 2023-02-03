package etc;
import java.util.Arrays;

class Solution {
    public int solution(int[][] triangle) {

        for(int i = 1; i < triangle.length; i++){
            //양쪽 끝은 연결된게 한개 밖에 없음
            triangle[i][0] += triangle[i-1][0];
            triangle[i][i] += triangle[i-1][i-1];
            
            for(int j = 1; j < triangle[i].length-1; j++){
                triangle[i][j] += Math.max(triangle[i-1][j], triangle[i-1][j-1]);
                //연결된 2개중 더 큰 걸 더함
            }
        }
        
        return Arrays.stream(triangle[triangle.length-1]).max().getAsInt();
    }
}