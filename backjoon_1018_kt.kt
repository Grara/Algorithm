import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Integer.min
import java.util.*


fun main( ) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map{it.toInt()}
    var ans = 9999
    val board = Array(n){"blank"}

    repeat(n){
        board[it] = br.readLine()
    }

    for(i in 0 until n-7){
        for(j in 0 until m-7){
            var cnt1 = 0
            var cnt2 = 0

            for(k in i until i + 8){
                for(l in j until j + 8) {
                    if (k + l == 0 || (k + l) % 2 == 0){
                        if (board[k][l] == 'B') cnt1 += 1 else cnt2 += 1
                    }
                    else{
                        if(board[k][l] == 'W') cnt1 += 1 else cnt2 += 1
                    }
                }
            }
            if(ans > min(cnt1, cnt2))
                ans = min(cnt1, cnt2)
        }
    }
    println(ans)
}