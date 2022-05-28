import java.io.*
import java.lang.*
import java.util.*
import kotlin.math.*

fun main( ) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    var sum = 0
    var mode = LinkedList<Int>()
    val countList = Array(8001){0}
    var midian = 0
    var cnt = 0
    var min = 5000
    var max = -5000

    repeat(n){
        val num = br.readLine().toInt()
        countList[num+4000] += 1
        sum += num
        min = min(min, num)
        max = max(max, num)
    }

    //최빈값 구하기
    //최빈값이 한개라면 한개 구하고 끝, 두개라면 for문 돌리기
    //최빈값 중 두번째로 작은값을 구해야하므로 mode에 두개가 들어가면 종료
    if(countList.count{it == countList.maxOrNull()} == 1){
        mode.add(countList.indexOf( countList.maxOrNull() ) - 4000)
    }
    else{
        for (i in countList.indices) {
            if (countList[i] == countList.maxOrNull()) {
                mode.add(i - 4000)
                if (mode.size == 2) break
            }
        }
    }
    
    //중앙값 구하기
    //countList[i]만큼 cnt증가
    //cnt가 n//2+1과 같거나 커지면 해당 숫자가 중앙값
    for(i in countList.indices){
        cnt += countList[i]
        if(cnt >= (n/2) + 1){
            midian = i-4000
            break
        }
    }

    bw.append("${(sum.toFloat() / n).roundToInt()}\n")
    //산술평균
    bw.append(midian.toString()+"\n")
    //중앙값
    bw.append(mode.last().toString()+"\n")
    //최반값
    bw.append((max-min).toString()+"\n")
    //범위
    bw.close()
}