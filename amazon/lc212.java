package amazon;

/*
这道题是目前搜索里面最难的题目，没有之一，个人认为需要留一个小时做本题才比较合适，建议做此题前先做一下lc46找找感觉，
看看纯BackTracking怎么做，然后再看添加DFS怎么做。

这道题目需要结合DFS和回溯才能够做出来，一开始只用了DFS，但是会有test case过不去，所以想到还要用回溯。
但是回溯的时候还要结合DFS，所以debug了很久

这个方法是自己想的，没有看题解，最容易想到,但是效率偏低，所以建议还会一种Trie的方法，并且还可以当做复习。

当然我这个方法还可以先预处理下数据，这样会快很多：
https://leetcode-cn.com/problems/word-search-ii/solution/yu-chu-li-shen-du-you-xian-sou-suo-by-zh-3upr/

* */

import java.util.ArrayList;
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
//    方法一： 自己想的方法，效率勉强
//    public List<String> findWords(char[][] board, String[] words) {
//        this.board = board;
//        this.words = words;
//        row = board.length;
//        col = board[0].length;
//        if(board == null || row == 0) return res;
//        for(String word : words){
//            for(int i = 0; i < row; i++){
//                for(int k = 0; k < col; k++){
//                    boolean[][] visited = new boolean[row][col];
//                    if(!hashSet.contains(word)) BackTracking(word, 0, i, k, visited);
//                }
//            }
//        }
//        return res;
//    }

//    方法二：带预处理，效率高很多，原理就是先遍历board，把每个格子里面的字母进行排序和编号，
//    然后对照words里面每个word的首字母进行寻找，
//    这样可以省掉三个for循环,很巧妙，其实速度也已经和Trie差不多了
    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        this.words = words;
        row = board.length;
        col = board[0].length;
        List[] preTreatment = new List[26];
        if(board == null || row == 0) return res;
        for(int i = 0; i < row; i++){
            for(int k = 0; k < col; k++){
                int index = board[i][k] - 'a';
                if(preTreatment[index] == null){
                    preTreatment[index] = new ArrayList<>();
                }
                int[] pos = new int[2];
                pos[0] = i;
                pos[1] = k;
                preTreatment[index].add(pos);
            }
        }
        for(String word : words){
            int head = word.charAt(0) - 'a';
            if(preTreatment[head] == null) continue;
            int headSize = preTreatment[head].size();
            for(int i = 0; i < headSize; i++){
                int[] pos = (int[]) preTreatment[head].get(i);
                boolean[][] visited = new boolean[row][col];
                if(!hashSet.contains(word)) BackTracking(word, 0, pos[0], pos[1], visited);
            }
        }
        return res;
    }

    private void BackTracking(String word, int index, int row1, int col1, boolean[][] visited){
        char[] wChar = word.toCharArray();
        int wLen = wChar.length;
        if(row1 < 0 || row1 >= row || col1 < 0 || col1 >= col || visited[row1][col1] || index >= wLen) return;
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
                BackTracking(word, index, x, y, visited);

            }
            visited[row1][col1] = false;
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
