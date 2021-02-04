package amazon;

/*
在选择方式的时候要考虑两种情形 一种是一门课程是很多门课程的先行课，还有一种是很多门课程是一门课程的先行课，
要看自己选择的方式是不是能凸显这个特性

我一开始想用hashmap，但是根据上面的条件我发现是不合适的，所以要考虑其他办法。还是要看liweiwei的题解才行
https://leetcode-cn.com/problems/course-schedule/solution/tuo-bu-pai-xu-by-liweiwei1419/
* */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class lc207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(numCourses <= 0) return false;
        if(prerequisites.length < 2) return true;
        int[] inDegree = new int[numCourses];
        HashSet<Integer>[] hashSets = new HashSet[numCourses];

        for(int i = 0; i < numCourses; i++){
            hashSets[i] = new HashSet<>();
        }

        for(int[] courses : prerequisites){
            inDegree[courses[0]]++;
            hashSets[courses[1]].add(courses[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(inDegree[i] == 0) queue.add(i);
        }

        //下面这个数和lc210意义不一样，所以取名不一样
        int count = 0;
        while (!queue.isEmpty()){
            int temp = queue.poll();
            count++;
            for(int num : hashSets[temp]){
                inDegree[num]--;
                if(inDegree[num] == 0) queue.add(num);
            }
        }
        return count == numCourses;
    }

    public static void main(String[] args) {
        int[][] test = {{1, 0}, {2, 0}};
        System.out.println(new lc207().canFinish(3, test));
    }
}
