package amazon;

/*
这次做虽然AC了，但是有更优化的版本，下次要记得
* */

import java.util.Arrays;

public class lc238 {
    public int[] productExceptSelf(int[] nums) {
        int numLen = nums.length;
        int[] res = new int[numLen];
        int[] left = new int[numLen];
        int[] right = new int[numLen];
        int begin = 1;
        for(int i = 0; i < numLen; i++){
            begin = i == 0 ? nums[0] : begin * nums[i];
            left[i] = begin;
        }
        begin = 1;
        for(int i = numLen - 1; i >= 0; i--){
            begin = i == numLen - 1 ? nums[numLen - 1] : begin * nums[i];
            right[i] = begin;
        }
        for(int i = 0; i < numLen; i++){
            int leftSide = i == 0 ? 1 : left[i - 1];
            int rightSide = i == numLen - 1 ? 1 : right[i + 1];
            res[i] = leftSide * rightSide;
        }
        return res;
    }


    public static void main(String[] args) {
        int[] test = {1,2,3,4};
        System.out.println(Arrays.toString(new lc238().productExceptSelf(test)));
    }
}
