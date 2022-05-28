import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*

//회전하는 큐
//1. 첫번째 요소 뽑기
//2. 맨앞에꺼를 맨뒤로
//3. 맨뒤에꺼를 맨앞으로
//위의 3가지 연산을 통해 원하는 요소를 뽑는데 최소 몇번의 2,3 연산이 필요한가?
fun main( ) {
    //val bw = BufferedWriter(OutputStreamWriter(System.out))
    val input1 = readLine()!!.split(" ")[0].toInt()
    val numbers = LinkedList<Int>()
    val targets = readLine()!!.split(" ").map{it.toInt()}
    var cnt = 0

    for(i in 1..input1) numbers.add(i)

    repeat(targets.size){
        val a = numbers.indexOf(targets[it])
        //a : 현재 뽑아야하는 요소의 맨앞부터의 순서
        val b = numbers.size - numbers.indexOf(targets[it])
        //b : 현재 뽑아야하는 요소의 맨뒤부터의 순서
        if (a <= b){ //a가 더 작거나 같으면 a만큼 2번 연산 반복
            repeat(a){
                numbers.addLast(numbers.pollFirst())
                cnt += 1
            }
        }
        else{ //b가 더 작으면 3번 연산 반복
            repeat(b){
                numbers.addFirst(numbers.pollLast())
                cnt += 1
            }
        }
        numbers.pollFirst() //뽑아야하는 요소가 첫번째로 왔으므로 1번 연산 실행(뽑기)
    }
    println(cnt)
}