package amazon;

public class lc215 {
    private int[] nums;
    private int numLen;
    public int findKthLargest(int[] nums, int k) {
        this.nums = nums;
        numLen = nums.length;
        int target = numLen - k;
        int left = 0, right = numLen - 1;
        int index = -1;
        while (index != target){
            index = patition(left, right);
            if(index < target){
                left = index + 1;
            }else if(index > target){
                right = index - 1;
            }else break;
        }

        return nums[target];
    }

//    private int patition(int left, int right){
//        int mid = nums[right];
//        int lo = left;
//        int hi = right - 1;
//        while (lo < hi){
//            while (lo < hi && nums[lo] <= mid) lo++;
//            while (lo < hi && nums[hi] >= mid) hi--;
//            swap(lo, hi);
//        }
//        if(nums[lo] < mid) {
//            return right;
//        }
//        swap(lo, right);
//        return lo;
//    }


//  随机数版本
    private int patition(int left, int right){
        int lo = left, hi = right - 1, pivot = left  + (int)(Math.random() * (right - left + 1));
        swap(pivot, right);
        while (lo < hi){
            while (lo < hi && nums[lo] <=  nums[right]) lo++;
            while (lo < hi && nums[hi] >  nums[right]) hi--;
            swap(lo, hi);
        }
        if(nums[lo] > nums[right]){
            swap(lo, right);
            return lo;
        }

        return right;
    }

    private void swap(int start, int end){
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }

    public static void main(String[] args) {
//        int[] nums = {3, 2, 1, 5, 6, 4};
//        int findLargest = new lc215().findKthLargest(nums, 2);
//        System.out.println(findLargest);

//        int[] nums = {3, 3, 3, 3, 3, 3, 3, 3, 3};
//        int findLargest = new lc215().findKthLargest(nums, 1);
//        System.out.println(findLargest);

//        int[] nums = {104, 80, 149, 65};
//        int findLargest = new lc215().findKthLargest(nums, 3);
//        System.out.println(findLargest);

        int[] nums = {-1,2,0};
        int findLargest = new lc215().findKthLargest(nums, 2);
        System.out.println(findLargest);
    }
}
