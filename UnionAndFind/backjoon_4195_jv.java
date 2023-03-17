import java.io.*;
import java.util.*;
import java.util.stream.Stream;

//문제명 : 친구네트워크
//유니온&파인드 문제
//2명이 친구를 맺었을 때 친구 네트워크의 총 크기

class Person{
    Person parent; //부모
    String name; //이름
    int networkCnt = 1; //현재 포함된 네트워크의 사람 수

    Person(String name){
        this.parent = this; //생성시에는 자기자신을 부모로 설정
        this.name = name;
    }
}

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static Scanner sc =new Scanner(System.in);
    
    static Map<String, Person> personMap = new HashMap<>(); //테스트 케이스별로 객체를 저장하기위한 해쉬맵

    public static void main(String[] args)throws Exception{
    	
        int t = Integer.parseInt(br.readLine()); //테스트 케이스 수
        
        for(int i = 0; i < t; i++){
            
        	int f = Integer.parseInt(br.readLine()); //친구 횟수
            
            for(int j = 0; j < f; j++){
                
            	st = new StringTokenizer(br.readLine()); //이름 입력
                String first = st.nextToken();
                String second = st.nextToken();

                personMap.putIfAbsent(first, new Person(first)); //이름에 해당하는 Person객체가 없으면 새로 생성해서 넣기
                personMap.putIfAbsent(second, new Person(second));
                
                Person a = personMap.get(first); //이름으로 객체 가져오기
                Person b = personMap.get(second);

                union(a, b); //친구추가(합치기)
                
                int networkCnt = Math.max(a.parent.networkCnt, b.parent.networkCnt );
                
                //a랑 b는 이미 같은 네트워크에 속하기 떄문에 어느쪽이든 상관없음
                bw.append(Integer.toString(networkCnt) + "\n");
            }
            personMap.clear(); //맵 초기화
        }

        bw.close();
    } //End of main
    
    static void union(Person a, Person b){ //합치기
        a = find(a);
        b = find(b);
        
        if(a != b){
            if(a.name.hashCode() > b.name.hashCode()){ //이름을 해쉬코드로 변환 후 비교
            	b.networkCnt += a.parent.networkCnt; //네트워크수 병합
            	a.parent = b; //부모 설정, 윗라인의 코드와 위치가 바뀌면 정상적으로 동작 안함
            }
            else {
            	a.networkCnt += b.parent.networkCnt;
                b.parent = a;
            }
        }
    }

    static Person find(Person person){ //부모 찾기
        
        if(person.parent == person){
            return person;
        }
        
        return person.parent = find(person.parent);
    }

} //End of Main
