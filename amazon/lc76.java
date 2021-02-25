package amazon;

/*
这道题目看官解下面第二页踏云的评论，和我的思路最像
* */

public class lc76 {
    public String minWindow(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        if(sLen == 0 || tLen == 0 || s == null || t == null) return "";
        char[] sChar = s.toCharArray(), tChar = t.toCharArray();
        int[] target = new int[128];
        int[] curRes = new int[128];
        int left = 0, right = 0, count = 0, start = 0, minLen = sLen + 1;
        for(char ch : tChar){
            target[ch]++;
        }
        while (right < sLen){
            char rightChar = sChar[right];
            curRes[rightChar]++;
//            下面这句很关键
            if(target[rightChar] == 0){
                right++;
                continue;
            }
            if(target[rightChar] >= curRes[rightChar])count++;


//            下面这个也易错
            while (count == tLen){
                char leftChar = sChar[left];
                if((right - left + 1) < minLen){
                    start = left;
                    minLen = right - left + 1;
                }
                if (target[leftChar] == 0) {
                    left++;
                    continue;
                }
//                下面这个语句只能这么写，否则就不对，一刷太晚了没搞清楚，就是想搞清楚为什么下面的if一定要，
//                进入这个while不是就说明一定是找到一个答案了，一定符合if里面的条件？
                if(curRes[leftChar] == target[leftChar]){
//                    curRes[leftChar]--;
                    count--;
//                    left++;
                }
//                count--;
                left++;
                curRes[leftChar]--;
            }
            right++;
        }
        if(minLen == sLen + 1) return "";
        return s.substring(start, start + minLen);
    }

    public static void main(String[] args) {
        System.out.println(new lc76().minWindow("ADOBECODEBANC","ABC"));
    }
}
