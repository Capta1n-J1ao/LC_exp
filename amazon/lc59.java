package amazon;

/*
这道题没什么技巧，就是用常规方法来做，这个题解和我的思路最像：
https://leetcode-cn.com/problems/spiral-matrix-ii/solution/zhuang-tai-qie-huan-de-jie-fa-by-que-yi-h7766/
* */

import java.util.Arrays;

public class lc59 {
    public int[][] generateMatrix(int n) {
        int count = 1, row = 0, col = 0, status = 0, round = 1;
        int[][] res  = new int[n][n];
        while (count <= n * n){
            res[row][col] = count;
            count++;
            if(status == 0){
                col++;
                if(col == n - round) status = (status + 1) % 4;
            }else if(status == 1){
                row++;
                if(row == n - round) status = (status + 1) % 4;
            }else if(status == 2){
                col--;
                if(col == round - 1) status = (status + 1) % 4;
            }else if(status == 3){
                row--;
                if(row == round){
                    status = (status + 1) % 4;
                    round++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] tests = new lc59().generateMatrix(4);
        for(int[] test : tests){
            System.out.println(Arrays.toString(test));
        }
    }
}
