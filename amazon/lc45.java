package amazon;

/*
要思考清除用dp或者贪心，这个可以算是这个tag里面最难的了，主要是一些具体实施的细节难。

题解这个讲的最清楚：
https://leetcode-cn.com/problems/jump-game-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-10/
* */

public class lc45 {
    public int jump(int[] nums) {
        int numLen = nums.length;
        int step = 0;
//        注意下面两个参数的区别
//        maxPos代表的是在这一个step中能跳到的最远距离,在同一个step内，最远跳到maxPos
        int maxPos = 0;
//        end代表的是在上一个step内，能到达的最远距离，然后在这一个step中，由maxPos更新
        int end = 0;
        for(int i = 0; i < numLen - 1; i++){
//            这句话代表当前这个nums[i]最多能跳到的右边的值，也就是这一个step内能够到达的最大值
            maxPos = Math.max(maxPos, nums[i] + i);
//            如果for循环遍历到end，说明上一个step最多也只能跑到end，上一个step结束，
//            进入下一个step，此时需要由maxPos更新这一个step
            if(i == end){
                end = maxPos;
                step++;
            }
        }
        return step;
    }

    public static void main(String[] args) {
        int[] test = {2,3,1,1,4};
        System.out.println(new lc45().jump(test));
    }
}
