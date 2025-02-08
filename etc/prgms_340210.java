//프로그래머스 - 수식 복원하기
//A + B = C, A - B = C 식으로 수식 여러개가 주어진다. c에는 미지수 X가 들어갈 수도 있다.
//수식의 숫자들은 2~9진법 중 하나다.
//X에 들어갈 수 있는 값을 채워서 표현하고 X를 하나로 특정할 수 없으면 ?로 채워라
//A, B는 두 자릿수 이하의 정수
//C는 세 자릿수 이하의 정수, 수식이 안맞거나 X가 음수가 되는 경우는 없음

import java.util.*;

class Solution {
    
    int MAX_NUMERAL = 9; //진법 가짓수
    int[][] expArr; //각 수식의 숫자들을 저장
    boolean[] enableNum = new boolean[MAX_NUMERAL + 1]; //사용가능한 진법
    int minNumeral = -1; //최소한의 진법
    List<String> xArr = new ArrayList<>(); //x가 들어간 수식들을 담을 임시용 리스트
    
    public String[] solution(String[] inputs) {
        
        //사용가능한 진법 초기화
        Arrays.fill(enableNum, true);

        //숫자 저장 배열 초기화
        expArr = new int[inputs.length][3];
        for(int i = 0; i < expArr.length; i++){
            Arrays.fill(expArr[i], -1);
        }

        //일의 자리수를 보고 사용불가능한 진법들을 제외
        //ex) 일의 자리가 n이 나오면 n진법까지는 사용이 불가능함
        for(int i = 0; i < inputs.length; i++){
            String[] spltStr = inputs[i].split(" "); //{"1 + 1 = 2"} => {"1", "+", "1", "=", "2"}
            
            int A = Integer.parseInt(spltStr[0]);
            int sign = spltStr[1].equals("+") ? 1 : -1;
            int B = Integer.parseInt(spltStr[2]);
            
            String cStr = spltStr[4]; //수식의 우변
            if(cStr.equals("X")) xArr.add(inputs[i]); //x용 임시 리스트에 담기
            int C = cStr.equals("X") ? -1 : Integer.parseInt(cStr);

            //일의 자리 숫자를 보고 최소 진법 갱신
            minNumeral = Math.max(minNumeral, A % 10 + 1);
            minNumeral = Math.max(minNumeral, B % 10 + 1);
            minNumeral = Math.max(minNumeral, C % 10 + 1);

            //숫자 배열에 저장
            expArr[i][0] = A;
            expArr[i][1] = B * sign;
            expArr[i][2] = C;
        }

        //사용불가능한 진법 제외
        for(int j = 0; j < minNumeral; j++){
            enableNum[j] = false;
        }

        //현재 사용가능한 진법들로 수식들을 검증
        for(int[] exp : expArr){
            if(exp[2] == -1) continue; //우변이 X인 수식은 제외
            for(int i = 0; i < enableNum.length; i++){ //진법 순회
                if(!enableNum[i]) continue; //사용불가능한 진법은 제외
                if(!validateExp(i, exp)) enableNum[i] = false; //검증했는데 틀린 수식이면 해당 진법 제외
            }
        }
        
        int enableNumeralCnt = 0; //사용가능한 진법의 갯수
        int ansNumeral = 0; //사용가능한 진법이 1개일 때 유효함
        for(int i = 0; i < enableNum.length; i++){
            if(enableNum[i]) {
                enableNumeralCnt++;
                ansNumeral = i;
            }
        }

        //정답 배열 초기화
        String[] ans = new String[xArr.size()];
        for(int i = 0; i < ans.length; i++){
            ans[i] = xArr.get(i);
        }

        //X를 정답으로 바꾸기
        for(int i = 0; i < ans.length; i++){
            String[] spltStr = ans[i].split(" "); //{"1 + 1 = X"} => {"1", "+", "1", "=", "X"}
            int A = Integer.parseInt(spltStr[0]);
            int sign = spltStr[1].equals("+") ? 1 : -1;
            int B = Integer.parseInt(spltStr[2]) * sign;
            
            if(enableNumeralCnt == 1){ //사용가능한 진법이 단 1개일 때

                //숫자1과 숫자2를 10진법으로 변환시킨 후 더함
                int tmp = convertToDecimal(ansNumeral, A) + convertToDecimal(ansNumeral, B);
                //그 결과를 원래 진법으로 변환
                ans[i] = ans[i].replace("X", decimalToNumeral(ansNumeral, tmp));
                continue;

            }

            //사용가능한 진법이 여러개일 때

            int digit2A = A / 10; //숫자1 10의 자리
            int digit2B = B / 10; //숫자2 10의 자리
            int sumDigit2 = digit2A + digit2B;

            //10의 자리끼리 계산결과가 최소 진법의 숫자 이상이면
            //=>100의 자릿수가 추가됐을 때
            //=>정확히 알 수 없기 때문에 ? 로 표시
            if(sumDigit2 >= minNumeral) {
                ans[i] = ans[i].replace("X", "?");
                continue;
            }

            int sumDigit1 = (A % 10) + (B % 10);

            //10의 자리끼리 계산결과가 최소 진법의 숫자 이상이거나 0보다 작으면
            //=>10의 자리에 영향을 줄 때
            //=>정확히 알 수 없기 때문에 ? 로 표시
            if(sumDigit1 >= minNumeral || sumDigit1 < 0){
                ans[i] = ans[i].replace("X", "?");
                continue;
            }
            
            //위의 경우들이 아니면 그냥 계산결과대로 표시해주면 됨
            String n1 = Integer.toString(sumDigit2).equals("0") ? "" : Integer.toString(sumDigit2);
            String n2 = Integer.toString(sumDigit1);

            ans[i] = ans[i].replace("X", n1 + n2);
        }

        return ans; //문제 결과 리턴
    }
    
    //n진법이라고 가정하고 수식을 10진법으로 변환 후 계산이 맞는지 검증
    public boolean validateExp(int numeral, int[] exp){
        int A = convertToDecimal(numeral, exp[0]);
        int B = convertToDecimal(numeral, exp[1]);
        int C = convertToDecimal(numeral, exp[2]);

        if(A + B != C){
            return false;
        }

        return true;
    }

    //숫자를 10진법으로 변환
    public int convertToDecimal(int numeral, int num){
        int sign = 1;
        if(num < 0){
            num = num * -1;
            sign = -1;
        }
        
        int result = 0;
        
        int digit3 = num / 100;
        result += digit3 * (Math.pow(numeral, 2)); //백의 자리 숫자 * 진법의 2제곱
        num = num % 100;

        int digit2 = num / 10;
        result += digit2 * (Math.pow(numeral, 1)); //십의 자리 숫자 * 진법
        num = num % 10;
        
        result += num; //일의자리 더하기

        return result * sign;
    }

    //10진법의 숫자를 n진법의 문자열로 변환
    public String decimalToNumeral(int numeral, int num){
        StringBuilder sb = new StringBuilder();
        while(true){
            int quot = num / numeral; //몫
            int mod = num % numeral; //나머지
            if(quot >= numeral){ //몫이 진법 이상이면
                sb.insert(0, Integer.toString(mod)); //나머지를 맨앞에 추가
                num = quot;
            } else { //더이상 나눌 수 없으면
                sb.insert(0, Integer.toString(mod)); //나머지를 맨앞에 추가
                sb.insert(0, Integer.toString(quot)); //몫을 맨앞에 추가
                break; //종료
            }
        }

        //맨앞의 0제거
        if(sb.indexOf("0") == 0) sb.replace(0, 1, "");

        return sb.toString();
    }
}