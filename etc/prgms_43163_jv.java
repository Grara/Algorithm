package etc;
import java.util.*;

class Word{
    String str; //단어 내용
    int step; //시작부터 해당 단어로 변환하는데 걸린 최소 단계
    public Word(String str, int step){
        this.str = str;
        this.step = step;
    }
}

class Solution {
    public int solution(String begin, String target, String[] words){
        LinkedList<Word> qu = new LinkedList<>();
        int[] visited = new int[words.length];
        int ans = 0;
        
        qu.add(new Word(begin, 0));
        
        while(!qu.isEmpty()){
            Word w = qu.poll();
            
            if(w.str.equals(target)){ //꺼낸 단어가 target단어면 종료
                ans = w.step;
                break;
            }
            
            for(int i = 0; i < words.length; i++){
                if(visited[i] == 0){
                    int diffCnt = 0; //꺼낸 단어와 비교하는 단어의 다른 글자 수
                    
                    //단어 내 글자 순회하면서 비교
                    for(int j = 0; j < w.str.length(); j++){
                        if(w.str.charAt(j) != words[i].charAt(j)){
                            diffCnt++;
                            if(diffCnt > 1) break;
                        }
                    }
                    
                    //변환 가능한 단어면 해당 단어를 큐에 넣음
                    if(diffCnt <= 1){
                        visited[i] = w.step + 1;
                        qu.add(new Word(words[i], w.step + 1));
                    }
                }
            }
        }
        return ans;
    }
}