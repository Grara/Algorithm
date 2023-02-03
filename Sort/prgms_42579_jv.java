package Sort;
import java.util.*;

class Music implements Comparable<Music>{
    int id;
    int cnt;
    Music(int id, int cnt){
        this.id = id;
        this.cnt = cnt;
    }
    
    public int compareTo(Music m){
        if(this.cnt < m.cnt) return 1; //플레이수 기준 내림차순
        else if(this.cnt == m.cnt) return this.id - m.id; //플레이수가 같으면 id기준 오름차순
        else return -1;
    }
}

class Solution {
    public static int[] solution(String[] genres, int[] plays) {
        List<Integer> ans = new ArrayList<>();
        Map<String, Integer> gTotal = new HashMap<>();
        Map<String, List<Music>> mList = new HashMap<>();
        
        for(int i = 0; i < genres.length; i++){
            //현재 음악의 장르가 이미 hashmap에 있다면 
            if(gTotal.containsKey(genres[i])){
                int currentCnt = gTotal.get(genres[i]);
                gTotal.put(genres[i], currentCnt + plays[i]);
                mList.get(genres[i]).add(new Music(i, plays[i]));
            }
            
            //현재 음악의 장르가 hashmap에 없다면
            else {
                gTotal.put(genres[i], plays[i]);
                mList.put(genres[i],new ArrayList<Music>());
                mList.get(genres[i]).add(new Music(i, plays[i]));
            }
        }

        List<String> genreNames = new ArrayList<>(gTotal.keySet()); //장르 이름으로 이루어진 리스트
        Collections.sort(genreNames, (o1, o2) -> gTotal.get(o2) - gTotal.get(o1)); 
        //플레이수를 기준으로 장르 내림차순 정렬
        
        for(String gName : genreNames){
            List<Music> currentGenreMusics = mList.get(gName); //현재 장르에 속한 음악들
            Collections.sort(currentGenreMusics); //플레이수 기준 내림차순 정렬
            ans.add(currentGenreMusics.get(0).id);
            if(currentGenreMusics.size() > 1) ans.add(currentGenreMusicss.get(1).id);
        }
        
        return ans.stream().mapToInt(i->i).toArray();
    }
}