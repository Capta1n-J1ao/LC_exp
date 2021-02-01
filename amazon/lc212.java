package amazon;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class lc212 {
    private List<String> res = new LinkedList<>();
    private int[][] dirs = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
    private int row, col;
    char[][] board;
    String[] words;
    private HashSet<String> hashSet = new HashSet<>();
    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        this.words = words;
        row = board.length;
        col = board[0].length;
        if(board == null || row == 0) return res;
        for(String word : words){
            for(int i = 0; i < row; i++){
                for(int k = 0; k < col; k++){
                    boolean[][] visited = new boolean[row][col];
                    if(!hashSet.contains(word)) DFS(word, 0, i, k, visited);
                }
            }
        }
        return res;
    }

    private void DFS(String word, int index, int row1, int col1, boolean[][] visited){
        if(index == wLen && !hashSet.contains(word)) {
                hashSet.add(word);
                res.add(word);
                return;
        }
        char[] wChar = word.toCharArray();
        int wLen = wChar.length;
        if(!visited[row1][col1]){
            visited[row1][col1] = true;
        }
        
        
        if(row1 < 0 || row1 >= row || col1 < 0 || col1 >= col || visited[row1][col1]) return;
        char[] wChar = word.toCharArray();
        int wLen = wChar.length;

        if(board[row1][col1] == wChar[index] && !visited[row1][col1]){
            index++;
            if(index == wLen && !hashSet.contains(word)) {
                hashSet.add(word);
                res.add(word);
                return;
            }
            visited[row1][col1] = true;
//            System.out.println(visited[2][0]);
            for(int[] dir : dirs){
                int x = row1 + dir[0];
                int y = col1 + dir[1];
                if(x < 0 || x >= row || y < 0 || y >= col || visited[x][y]) continue;
                DFS(word, index, x, y, visited);
                visited[x][y] = false;
            }
        }
    }

    public static void main(String[] args) {
//        char[][] test = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'}};
//        String[] test1 = {"oath","pea"};
//        System.out.println(new lc212().findWords(test, test1));

        char[][] test = {{'o','a','b','n'},{'o','t','a','e'},{'a','h','k','r'},{'a','f','l','v'}};
        String[] test1 = {"oa","oaa"};
        System.out.println(new lc212().findWords(test, test1));

//        char[][] test = {{'a','b','c'},{'a','e','d'},{'a','f','g'}};
//        String[] test1 = {"eaabcdgfa"};
//        System.out.println(new lc212().findWords(test, test1));
    }
}
