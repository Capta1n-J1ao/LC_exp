package amazon;

/*
思路：
随机选一个单词，算出它和秘密单词的匹配度，
那么秘密单词一定在匹配度相同的那些单词里！
把匹配度相同的单词们选出来，再随机猜一个，算出匹配度。
这样不断缩小范围，直到找出秘密单词！

这个参考题解由于选的是随机数，性能可能不稳定。：
https://leetcode-cn.com/problems/guess-the-word/solution/chao-qing-xi-si-lu-pai-chu-fa-suo-xiao-c-5e0t/


* */

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class lc843 {
    private String[] wordlist;
    private int wLen;
    private List<String> res;
    public void findSecretWord(String[] wordlist, Master master) {
        this.wordlist = wordlist;
        int wLen = wordlist.length;
        if(wordlist == null || wLen == 0) return;
        res = new ArrayList<>();
        for(String str : wordlist) res.add(str);
        for(int i = 0; i < 10; i++){
//            这里random的写法一定要在后面加一个括号，否则就是错的。
//            下面这种写法index就一直为0，不会动的
//            int index = (int) Math.random() * res.size();
            int index = (int) (Math.random() * res.size());
//            记住这里一定要用res，而不能用wordlist就是因为res是不停在更新的
            String curStr = res.get(index);
            int distance = master.guess(curStr);
            List<String> curRes = new ArrayList<>();
            for(String str : res){
                if(sameCharNum(str, curStr) == distance) curRes.add(str);
            }
            res = curRes;
        }
    }

    private int sameCharNum(String strToCompare, String ref){
        int res = 0;
        for(int i = 0; i < strToCompare.length(); i++){
            if(strToCompare.charAt(i) == ref.charAt(i)) res++;
        }
        return res;
    }


    interface Master {
        public int guess(String word);
    }

    class MyMaster implements Master {

        String secret;
        MyMaster(String secret){
            this.secret = secret;
        }
        @Override
        public int guess(String word) {
            int ans = 0;
            for (int k = 0; k < 6; k++) {
                if (secret.charAt(k) == word.charAt(k)) {
                    ans++;
                }
            }
            if (ans == 6) {
                System.out.println("找到了：" + secret);
            }
            return ans;
        }
    }

    @Test
    public void test(){

        String[] wordlist = {"acckzz","ccbazz","eiowzz","abcczz"};
        findSecretWord(wordlist, new MyMaster("acckzz"));
    }
}
