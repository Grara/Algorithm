import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

//연속된 문자열과 배열이 주어짐
//문자열의 R은 배열뒤집기 D는 첫번째 요소 뽑기
//모든 연산이 끝난 후 배열 출력

fun main( ) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    for(i in 0 until br.readLine().toInt()) {
        val operStr = br.readLine()
        val n = br.readLine().toInt()
        val numberInput = br.readLine().drop(1).dropLast(1).split(",").toMutableList()
        val numberArr = LinkedList<String>()
        var isReversed = false //현재 배열이 뒤집혔는가 여부
        numberArr.addAll(numberInput)
        
        //D의 갯수가 숫자보다 많으면 결과적으로 에러가 나므로 error추가하고 스킵
        if (operStr.count { c -> c == 'D' } > n) {
            bw.append("error\n")
            continue
        }
        
        //문제의 핵심은 실제로 배열을 뒤집을 필요는 없다는 것
        //덱의 특성을 이용해서 isReversed가 true일 때, 맨뒤에 요소를 뽑으면 결과적으로 같아짐
        for (op in operStr) {
            if (op == 'R') isReversed = !isReversed
            if (op == 'D') { //뒤집힘 여부에 따라 맨앞과 맨뒤를 뽑음
                if (!isReversed) numberArr.pollFirst() else numberArr.pollLast()
            }
        }
        
        //모든 연산이 끝난 후 isReversed가 true면 원활한 출력을 위해 실제로 뒤집음
        if (isReversed) numberArr.reverse()
        bw.append(numberArr.joinToString(",","[","]") + "\n")
    }
    bw.flush()
    bw.close()
}