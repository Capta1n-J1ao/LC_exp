package amazon;

/*
https://leetcode-cn.com/problems/snakes-and-ladders/solution/ben-zhi-bfszhao-zui-duan-lu-jing-java-by-mattychen/
* */

import java.util.LinkedList;
import java.util.Queue;

public class lc909 {
    public int snakesAndLadders(int[][] board) {
        int row = board.length;
        int[] decodeBoard = new int[row * row + 1];
        boolean reverse = false;
        int index = 1;
        for(int i = row - 1; i >= 0; i--){
            for(int k = 0; k < row; k++){
                if(reverse){
//                    根据题解来的，太巧妙
                    decodeBoard[index] = board[i][(row - 1) - (k % row)];
                }else {
                    decodeBoard[index] = board[i][k];
                }
                index++;
            }
            reverse = !reverse;
        }

        Queue<Integer> queue = new LinkedList<>();
        int start = 1, depth = 0;
        boolean[] visited = new boolean[row * row + 1];
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()){
            int qLen = queue.size();
            for(int i = 0; i < qLen; i++){
                int temp = queue.poll();
                if(temp == row * row) return depth;
                for(int k = 1; k <= 6; k++){
                    if(temp + k > row * row) continue;
                    if(!visited[temp + k] && decodeBoard[temp + k] == -1){
                        queue.add(temp + k);
                        visited[temp + k] = true;
                    }else if(!visited[temp + k] && decodeBoard[temp + k] != -1){
                        queue.add(decodeBoard[temp + k]);
                        visited[decodeBoard[temp + k]] = true;
                    }
                }
            }
            depth++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] test = {{1,1,-1},{1,1,1},{-1,1,1}};
        System.out.println(new lc909().snakesAndLadders(test));
    }
}
