import java.io.*;
import java.util.*;
import java.util.stream.Stream;

//문제명 : 트리의 지름
//트리문제
//가장 거리가 먼 정점 찾기
//트리는 모두 이어져있다
//모든 트리로부터 공통적으로 먼 정점을 찾은 후 그 정점에서 가장 먼 정점을 찾으면됨

class Node{
    int right;
    int left;

    Node(int left, int right){
        this.right = right;
        this.left = left;
    }
}

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static Scanner sc =new Scanner(System.in);
    
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args)throws Exception{
    	
        int n = Integer.parseInt(br.readLine());
        
        //노드간 연결을 저장할 리스트
        graph = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>()); //초기화
        }

        //노드 연결 입력 받기
        for(int i = 0; i < n; i++){
            String[] input = br.readLine().split(" ");
            int root = input[0].charAt(0) - 'A' + 1;
            int left = input[1].charAt(0) - 'A' + 1;
            int right = input[2].charAt(0) - 'A' + 1;

            graph.get(root).add(new Node(left, right));
        }

        preorder(1);
        sb.append("\n");
        inorder(1);
        sb.append("\n");
        postorder(1);
        System.out.println(sb.toString());

    } //End of main
    
    //전위 순회 : 출력순서 root -> left -> right
    static void preorder(int start){
        
        for(Node node : graph.get(start)){
            int left = node.left;
            int right = node.right;

            sb.append((char)(start+'A'-1));
            if(left != -18) preorder(left);
            if(right != -18) preorder(right);
        }
    } //End of preorder

    //중위 순회 : 출력순서 left -> root -> right
    static void inorder(int start){
        
        for(Node node : graph.get(start)){
            int left = node.left;
            int right = node.right;

            if(left != -18) inorder(left);
            sb.append((char)(start+'A'-1));
            if(right != -18) inorder(right);
        }
    } //End of inorder

    //후위 순회 : 출력순서 left -> right -> root
    static void postorder(int start){
        
        for(Node node : graph.get(start)){
            int left = node.left;
            int right = node.right;

            if(left != -18) postorder(left);
            if(right != -18) postorder(right);
            sb.append((char)(start+'A'-1));
        }
    } //End of postorder


} //End of Main
