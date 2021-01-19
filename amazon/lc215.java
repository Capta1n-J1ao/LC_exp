package sort;
/*
*215. Kth Largest Element in an Array (Medium)

Leetcode / 力扣

Input: [3,2,1,5,6,4] and k = 2
Output: 5
题目描述：找到倒数第 k 个的元素。
*
*
* 这道题理解起来就非常费劲，所以一定要看视频，一共三种解法，用priorityqueue的解法必须会，
* 但是如果能会快速查询的办法那就最好，可以最大程度节省时间以及空间

*
* 还有就是对于大小顶堆讲的最好的出处： https://blog.csdn.net/qq_41682302/article/details/95910651

*
看下来最好的讲解出处如下，非常清晰,
https://www.bilibili.com/video/BV15Z4y1p7KR?t=412
*
三刷：
在做lc973的时候发现了用随机数的很多弊端，所以对模板进行了统一修改。这样一致性更好，更易做出题目。但是testcase在这道题目
鼓励随机数，所以性能稍差，但是也可接受。
* */


import java.util.Arrays;
import java.util.NoSuchElementException;

public class lc215 {
    public int findKthLargest(int[] nums, int k) {
        int start = 0, end = nums.length - 1;
        int target = nums.length - k;
        int curIndex = -1;

        while (curIndex != target){
            curIndex = partition(nums, start, end);
            if(curIndex > target){
                end = curIndex - 1;
            }else if(curIndex < target){
                start = curIndex + 1;
            }
        }
        return nums[curIndex];
    }

    private void swap(int[] nums, int start, int end){
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }

//    视频解，非常高效

    private int partition(int[] nums, int lo, int hi){
        if(hi == lo) return lo;

        int randomIndex = lo + (int)(Math.random() * (hi - lo + 1));
        int randomNum = nums[randomIndex];
        swap(nums, randomIndex, hi);

        //h的赋值是因为随机选的元素现在在nums[hi]的位置上
        int l = lo, h = hi - 1;
        while (l < h){
            //虽然上面的while已经有了，但是下面的没有的话还是会超，
            // 原因就是上一层的while每计算一次，下面这个while都要重新定位l和h，
            // 这个需要感受一下，如果下一层用if则不用，但是while就需要
            //while (nums[l] < randomNum){
            while (l < h && nums[l] <= randomNum){
                l++;
            }
            //while(nums[h] > randomNum){
            while(l < h && nums[h] > randomNum){
                h--;
            }
            //前面这个if可加可不加
            //if(l != r) swap(nums, l, h);
            swap(nums, l, h);
        }
        if(nums[l] > randomNum){
            swap(nums, l, hi);
            return l;
        }
        return hi;
    }


//    以下两个方法可以让快速排序模板一致，但是test case似乎为了让我们用随机数作pivot，
//    所以性能上差挺多

//    private int partition(int[] nums, int start, int end){
//        int lo = start, hi = end - 1, pivot = nums[end];
//        while (lo < hi){
////            参考973的debug步骤，下面两句话需要严格遵守顺序
//            while (lo < hi && nums[lo] <= pivot) lo++;
//            while(lo < hi && nums[hi] > pivot) hi--;
//            swap(nums, lo, hi);
//        }
//        if(nums[lo] > pivot){
//            swap(nums, lo, end);
//            return lo;
//        }
//        return end;
//    }

//    private int partition(int[] nums, int start, int end){
//        if(start == end) return start;
//        int lo = start + 1, hi = end, pivot = nums[start];
//        while (lo < hi){
////            参考973的debug步骤，下面两句话需要严格遵守顺序
//            while(lo < hi && nums[hi] > pivot) hi--;
//            while (lo < hi && nums[lo] <= pivot) lo++;
//            swap(nums, lo, hi);
//        }
//        if(nums[lo] < pivot){
//            swap(nums, lo, start);
//            return lo;
//        }
//        return start;
//    }


//    public int findKthLargest(int[] nums, int k) {
//        Arrays.sort(nums);
//        return nums[nums.length - k];
//    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int findLargest = new lc215().findKthLargest(nums, 2);
        System.out.println(findLargest);
    }


}
