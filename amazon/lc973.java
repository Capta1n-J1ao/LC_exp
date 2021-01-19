package amazon;

/*
我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。

（这里，平面上两点之间的距离是欧几里德距离。）

你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。

 

示例 1：

输入：points = [[1,3],[-2,2]], K = 1
输出：[[-2,2]]
解释：
(1, 3) 和原点之间的距离为 sqrt(10)，
(-2, 2) 和原点之间的距离为 sqrt(8)，
由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
示例 2：

输入：points = [[3,3],[5,-1],[-2,4]], K = 2
输出：[[3,3],[-2,4]]
（答案 [[-2,4],[3,3]] 也会被接受。）

方法一： 使用PriorityQueue的方法
带priorityqueue的自定义排序器设置如下，查看lc215可以看到详细的设置方法：
https://leetcode-cn.com/problems/k-closest-points-to-origin/solution/priorityqueuezui-xiao-dui-by-volcanno/

时间复杂度：O(n) = N * log(N) 或者 N * log(k)。
其中 nn 是数组 \textit{points}points 的长度。由于优先队列维护的是前 KK 个距离最小的点，
因此弹出和插入操作的单次时间复杂度均为 O(\log K)O(logK)。
在最坏情况下，数组里 nn 个点都会插入，因此时间复杂度为 O(n\log K)O(nlogK)。

空间复杂度：O(n) = O(k)。

方法二： 快速排序
找遍题解也没有和我一样的，总是有几个case会错，建议不要排查了，或者以后有时间在说
* */

import java.util.*;

public class lc973 {
    /*
    public int[][] kClosest(int[][] points, int K) {
//        通过看源文件可以知道，新建比较器的时候，那个K代表的是initialCapacity,但是我试了下，写一个大于1的都可以
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(K, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int dis1 = o1[0] * o1[0] + o1[1] * o1[1];
                int dis2 = o2[0] * o2[0] + o2[1] * o2[1];
//                大根堆
                return dis2 - dis1;
//                小根堆
//                return dis2 - dis1;
            }
        });

//        时间复杂度 O(n) = N * log(N) 的方法，要记得把上面的构造器改成return dis2 - dis1;
//        for(int[] point : points){
//            priorityQueue.add(point);
//        }

//        时间复杂度 O(n) = N * log(k) 的方法
        for(int[] point : points){
            if(priorityQueue.size() < K){
                priorityQueue.add(point);
            }else if(priorityQueue.comparator().compare(point, priorityQueue.peek()) > 0){
                priorityQueue.poll();
                priorityQueue.add(point);
            }

        }

        int[][] res = new int[K][2];
        for(int i = 0; i < K; i++){
            res[i] = priorityQueue.poll();
        }
        return res;
    }

     */

    private int[][] points;
    public int[][] kClosest(int[][] points, int K) {
        this.points = points;
        int pLen = points.length;
        int start = 0, end = pLen - 1, cur = -1;
        while (cur != K){
            cur = patition(start, end);
            if(cur > K){
                end = cur - 1;
            }else if(cur < K){
                start = cur + 1;
            }else break;
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    private int patition(int start, int end){
        if(start == end) return start;
        int lo = start, hi = end, pivot = distance(start);
        while (lo < hi){
            while (lo < hi && distance(lo) <= pivot) lo++;
            while (lo < hi && distance(hi) > pivot) hi--;
            swap(lo, hi);
        }
        swap(lo, start);
        return lo;
    }

    private void swap(int start, int end){
        int[] temp = points[start];
        points[start] = points[end];
        points[end] = temp;
    }

    private int distance(int index){
        return points[index][0] * points[index][0] + points[index][1] * points[index][1];
    }

    public static void main(String[] args) {
//        int[][] test = {{3,3},{5,-1},{-2,4}};
        int[][] test1 = {{2,2},{2,2},{2,2},{2,2},{2,2},{2,2},{1,1}};
//        int[][] test2 = {{-4,-7},{6,7},{-8,-5},{6,9},{-9,-4},{-9,-8},{-10,-6}};
//        System.out.println(Arrays.toString(new lc973().kClosest(test, 2)));
        System.out.println(Arrays.toString(new lc973().kClosest(test1, 1)));
//        System.out.println(Arrays.toString(new lc973().kClosest(test2, 6)));
    }
}
