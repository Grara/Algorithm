import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Integer.min
import java.util.*


fun main( ) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val countList = Array(10001){0}
    repeat(n){
        val num = br.readLine().toInt()
        countList[num] += 1
    }

    for(i in 1 until countList.size){
        while(countList[i] != 0) {
            bw.append(i.toString() + "\n")
            countList[i] -= 1
        }
    }
    bw.close()
}