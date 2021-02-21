package amazon;

import java.util.*;

public class lc210 {
    private int[] res;
    private HashMap<Integer, List<Integer>> hashMap;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        res = new int[numCourses];
        if(numCourses <= 0 ) return res;
        int[] inDegree = new int[numCourses];
        hashMap = new HashMap<>();
        for(int [] pre : prerequisites){
            if(!hashMap.containsKey(pre[1])){
                hashMap.put(pre[1], new LinkedList<>());
            }
            hashMap.get(pre[1]).add(pre[0]);
            inDegree[pre[0]]++;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int index = 0;
        for(int i = 0; i < res.length; i++){
            if(inDegree[i] == 0) stack.addFirst(i);
        }
        while (!stack.isEmpty()){
            int sLen = stack.size();
            for(int i = 0; i < sLen; i++){
                int temp = stack.pollFirst();
                res[index] = temp;
                index++;
                List<Integer> list = hashMap.get(temp);
                if(list == null){
                    if(index == numCourses) return res;
                    break;
                }
                for(int num : list){
                    inDegree[num]--;
                    if(inDegree[num] == 0) {
                        stack.addFirst(num);
                    }
                }

            }
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[][] test = {{1,0}};
//        System.out.println(Arrays.toString(new lc210().findOrder(2,test)));
        System.out.println(Arrays.toString(new lc210().findOrder(1,null)));
    }
}
