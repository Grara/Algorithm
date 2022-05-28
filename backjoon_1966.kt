import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*

fun main( ) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    repeat(readLine()!!.toInt()){
        val (n, m) = readLine()!!.split(" ").map { it.toInt() }
        var DocList = readLine()!!.split(" ").map { it.toInt() }
        val DocQu = LinkedList<Array<Int>>()
        val tiers = LinkedList<Int>()
        var cnt = 0 //뽑히는 순서

        //문서의 인덱스와 중요도를 저장
        for(i in 0 until n){
            DocQu.add(arrayOf(i, DocList[i]))
        }

        //모든 문서의 중요도를 내림차순으로 정리한 후 큐에 넣는다.
        DocList = DocList.sortedDescending()
        tiers.addAll(DocList)

        while (true){
            //현재 첫번째 문서의 중요도가 중요도순서 1순위라면
            if (DocQu.peek()[1] == tiers.peek()){
                //그리고 이 문서가 현재 뽑아야하는 문서라면 뽑고 다음 케이스로
                if(DocQu.peek()[0] == m){
                    cnt += 1
                    break
                }
                //아니면 문서를 뽑고 중요도큐에서도 1순위 중요도를 뽑는다.
                else{
                    DocQu.poll()
                    tiers.poll()
                    cnt += 1
                }
            }
            //중요도순서 1순위가 아니라면 문서를 맨뒤로 보낸다.
            else{
                DocQu.add(DocQu.poll())
            }
        }
        bw.append("${cnt}\n")
    }
    bw.flush()
    bw.close()
}