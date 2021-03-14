package amazon;

/*
这道题目注意有一种情况就是points里面是会有重复点的，这个是很重要的。
然后这道题目用暴力法其实也可以做到很快，就是要用对方法

还有一个需要注意的就是计算斜率的精度问题：
double保留小数点后的位数有限，对于很大又很接近的数会出现误判，所以我们要人为提高double的精度，
将被除数10000000，相当于再提高了好多位的精度，这样对于int范围内的很大又很接近的数就不会出现相同的情况

题解：
https://leetcode-cn.com/problems/max-points-on-a-line/solution/mei-ju-jing-du-wen-ti-de-hua-jiang-bei-c-jaey/
* */

import java.util.HashMap;

public class lc149 {
    private HashMap<Double, Integer> slopes;
    public int maxPoints(int[][] points) {
        int pLen = points.length, res = 0;
        if(pLen < 2) return pLen;
        slopes = new HashMap<>();
        for(int i = 0; i < pLen; i++){
            int[] p1 = points[i];
            for(int k = 0; k < pLen; k++){
                if(i == k) continue;
                int[] p2 = points[k];
                double width = p2[0] - p1[0];
                double height = p2[1] - p1[1];
                double slope = (height * 1000000000) / width;
                slopes.put(slope, slopes.getOrDefault(slope, 0) + 1);
                res = Math.max(res, slopes.get(slope));
            }
        }
        return res;
    }
}
