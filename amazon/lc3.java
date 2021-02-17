package amazon;

/*
代码写完测试最后那两个case，就能看出问题
* */

import java.util.HashMap;
import java.util.HashSet;

public class lc3 {
    private HashMap<Character, Integer> hashMap = new HashMap<>();
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0 || s == null) return 0;
        int res = 1;
        char[] sChar = s.toCharArray();
        int start = 0;
        for(int i = 0; i < sChar.length; i++){
            if(hashMap.containsKey(sChar[i])){
                start = Math.max(start, hashMap.get(sChar[i]) + 1);
            }
            hashMap.put(sChar[i], i);
            res = Math.max(res, i - start + 1);
        }
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(new lc3().lengthOfLongestSubstring("pwwkew"));
//        System.out.println(new lc3().lengthOfLongestSubstring("bbbbb"));
//        System.out.println(new lc3().lengthOfLongestSubstring("abcabcbb"));
//        System.out.println(new lc3().lengthOfLongestSubstring("au"));

//        下面这两个例子最能够测试出代码是否可行
//        System.out.println(new lc3().lengthOfLongestSubstring("dvdf"));
        System.out.println(new lc3().lengthOfLongestSubstring("abba"));
    }
}
