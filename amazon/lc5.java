package amazon;

public class lc5 {
    public String longestPalindrome(String s) {
        int sLen = s.length();
        boolean[][] dp = new boolean[sLen][sLen];
        for(int i = 0; i < sLen; i++){
            dp[i][i] = true;
        }
        int count = 0, begin = 0, end = 0;
        StringBuilder res = new StringBuilder();
        for(int k = 0; k < sLen; k++){
            for(int i = 0; i <= k; i++){
//                ((k - i) < 2 || dp[i + 1][k - 1])这个判断很重要！
                if(s.charAt(i) == s.charAt(k) && ((k - i) < 2 || dp[i + 1][k - 1])){
                    dp[i][k] = true;
                    if(k - i + 1 > count){
                        count = k - i + 1;
                        begin = i;
                        end = k + 1;
                    }
                }
            }
        }
        return s.substring(begin, end);
    }
}
