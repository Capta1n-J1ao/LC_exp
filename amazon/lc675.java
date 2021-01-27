package amazon;

/*
题目不难，就是代码很复杂，
还有要注意的是思考如果是 1 4 2 8 6 5 7这种没有顺序的情况需要怎么计算，这样更加能够充分思考

可以参考题解：
https://leetcode-cn.com/problems/cut-off-trees-for-golf-event/solution/java-bfs-you-xian-dui-lie-zhi-xing-yong-shi-435-ms/

有如下注意点：
1. 对 List<List<Integer>> 取size是有非常方便的方法的
2. 对于priorityQueue的定义函数也是有讲究的，要自己写一遍
* */

import java.util.*;

public class lc675 {
    private int row;
    private int col;
    private List<List<Integer>> forest;
    public int cutOffTree(List<List<Integer>> forest) {
        this.forest = forest;
        row = forest.size();
        col = forest.get(0).size();
        PriorityQueue<int[]> priorityQueue = new PriorityQueue(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return forest.get(o1[0]).get(o1[1]) - forest.get(o2[0]).get(o2[1]);
            }
        });

        for(int i = 0; i < row; i++){
            for(int k = 0; k < col; k++){
                if(forest.get(i).get(k) > 1) priorityQueue.add(new int[]{i, k});
            }
        }

        int res = 0;
        int[] begin = new int[]{0,0};
        while (!priorityQueue.isEmpty()){
            int[] temp = priorityQueue.poll();
            int curRes = BFS(begin, temp, new boolean[row][col]);
            if(curRes == -1) return -1;
            res += curRes;
            begin = temp;
        }
        return res;
    }

    private int BFS(int[] begin, int[] end, boolean[][] visited){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(begin);
        int depth = 0;
        while (!queue.isEmpty()){
            depth++;
            int qLen = queue.size();
        }
    }
}
