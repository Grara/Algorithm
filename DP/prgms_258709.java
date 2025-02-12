//프로그래머스 - 주사위 고르기
//n개의 육면체 주사위가 주어진다. 두사람이 각자 n/2개의 주사위를 가져가고 주사위를 던진다.
//던져서 나온 눈의 합으로 승부한다.
//승률을 최대로 높이는 주사위 조합을 구해라
//주사위의 눈은 랜덤한 숫자, 중복도 존재

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Solution {
    int fullBit;
    int bestCombBit = 0; //최대승을 기록한 조합 비트
    int maxWin = 0; //최대승
    int[][] dice;
    int[] visited; //조합 방문여부
    Map<Integer, Map<Integer, Integer>> combDp = new HashMap<>(); //dp용맵 Map<조합 비트, Map<눈의 합, 눈의 합의 갯수>>
    Map<Integer, Map<Integer, Integer>> useCases = new HashMap<>(); //dp중에서 n/2개 주사위를 사용한 조합만 저장

    public int[] solution(int[][] dice1) {
        dice = dice1;
        fullBit = (1 << dice.length) - 1; //주사위가 4개면 1111(15)
        visited = new int[fullBit + 1];

        calcCombSum(0, 0); //주사위 조합들의 경우의 수를 구함
        
        for(int i = 0; i < fullBit; i++){
            //n / 2개의 주사위를 사용한 조합이 아니거나 이미 방문했을 경우 스킵
            if(useCases.get(i) == null || visited[i] == 1) continue;
            int currCombBit = i;
            int vsCombBit = currCombBit ^ fullBit; //XOR연산 1100 xor 1111 = 0011, 내가 [1,2]를 사용하면 상대방은 [3,4]
            
            visited[currCombBit] = 1; //방문처리
            visited[vsCombBit] = 1;

            int win1 = 0;
            int win2 = 0;

            for(Entry<Integer, Integer> ent1 : useCases.get(currCombBit).entrySet()){
                for(Entry<Integer, Integer> ent2 : useCases.get(vsCombBit).entrySet()){
                    /**
                     * 합이 더 높은쪽은 서로의 갯수를 곱한만큼 승리
                     * ex) 3 네개와 2 네개가 붙으면 3 네개쪽의 조합이 16번 승리 추가
                    */
                    if(ent1.getKey() > ent2.getKey()){
                        win1 += ent1.getValue() * ent2.getValue();
                    } else if(ent1.getKey() < ent2.getKey()){
                        win2 += ent1.getValue() * ent2.getValue();
                    }
                }
            }

            int winnerBit;
            int winCnt;

            if(win1 > win2){
                winnerBit = currCombBit;
                winCnt = win1;
            } else {
                winnerBit = vsCombBit;
                winCnt = win2;
            }

            if(winCnt > maxWin){
                maxWin = winCnt;
                bestCombBit = winnerBit;
            }
        }

        List<Integer> ansList = new ArrayList<>();

        for(int i = 0; i < dice.length; i++){
            if((bestCombBit & (1 << i)) != 0) {
                ansList.add(i+1);
            }
        }

        return ansList.stream().mapToInt(n -> n).toArray();
    }

    public void calcCombSum(int currBit, int currCnt){
        if(currCnt >= (dice.length / 2)) return; //없어도 되긴하는데 불안해서 추가

        //현재 조합의 경우의 수
        Map<Integer, Integer> currCases = combDp.get(currBit);
        
        //dp에 아무것도 없을 때
        if(currCases == null){
            currCases = new HashMap<>();
            currCases.put(0, 1);
        }
        
        for(int i = 0; i < dice.length; i++){
            if((currBit & (1 << i)) != 0) { //이미 현재 조합에 현재 주사위가 포함됐으면 스킵
                continue;
            }

            int sumBit = currBit | (1 << i); //현재 주사위를 추가한 비트

            Map<Integer, Integer> newCases = new HashMap<>(); //현재 조합에 현재 주사위를 조합한 새로운 경우의 수
            
            for(int j = 0; j < 6; j++){
                for(Entry<Integer, Integer> ent : currCases.entrySet()){
                    int sum = ent.getKey() + dice[i][j]; //눈의 합
                    int accum = newCases.getOrDefault(sum, 0); //현재 눈의 합 누적된 갯수
                    newCases.put(sum, accum + ent.getValue()); //갯수 누적시키기
                }
            }
            combDp.put(sumBit, newCases); //구한 경우의 수를 dp에 추가

            if(currCnt + 1  == (dice.length / 2)) {
                useCases.put(sumBit, newCases); //조합에 사용한 주사위가 n / 2개를 넘어가면 대결용 map에 추가
                continue;
            }

            calcCombSum(sumBit, currCnt + 1);
        }
    }
}
