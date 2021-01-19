package amazon;

/*
和我思路接近的题解：
https://leetcode-cn.com/problems/most-common-word/solution/java-hashmap-hashset-by-coolking/
* */

import java.util.HashMap;
import java.util.HashSet;

public class lc819 {
    private HashSet<String> banList = new HashSet<>();
    private HashMap<String, Integer> freq = new HashMap<>();
    public String mostCommonWord(String paragraph, String[] banned) {
        for (String ban : banned){
//            关键要全转为小写
            banList.add(ban.toLowerCase());
        }
        int sLen = paragraph.length();
        int index = 0;
        for(int i = 0; i < sLen; i++){
            if(paragraph.charAt(i)) continue;
            else {
                String temp = paragraph.substring(index, i + 1);
                if(!banList.contains(temp)){
                    freq.put(temp, freq.getOrDefault(temp, 0) + 1);
                }
            }
            index = i + 1;
        }
    }
}
