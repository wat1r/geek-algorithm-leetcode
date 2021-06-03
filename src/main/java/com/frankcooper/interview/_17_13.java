package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _17_13 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String[] dictionary = {"looked", "just", "like", "her", "brother"};
            String sentence = "jesslookedjustliketimherbrother";
            handler.respace(dictionary, sentence);
        }


        public int respace(String[] dictionary, String sentence) {
            //用set
            Set<String> dic = new HashSet<>(Arrays.asList(dictionary));
            int n = sentence.length();
            int[] dp = new int[n + 1];//dp[i]表示前i个字符内最少的未识别字符的个数
            for (int i = 1; i <= n; i++) {
                dp[i] = dp[i - 1] + 1;
                for (int j = 0; j < i; j++) {
                    if (dic.contains(sentence.substring(j, i))) {
                        dp[i] = Math.min(dp[i], dp[j]);
                    }
                }
            }
            System.out.printf("%d\n", 0);
            return dp[n];
        }





    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
