package com.frankcooper.platform.lintcode;


import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/*
同159
 */
public class _928 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "eceba";
            Assert.assertEquals(handler.lengthOfLongestSubstringTwoDistinct(s), 3);
            s = "aaa";
            Assert.assertEquals(handler.lengthOfLongestSubstringTwoDistinct(s), 3);

        }

        public int lengthOfLongestSubstringTwoDistinct(String s) {
            Map<Character, Integer> map = new HashMap<>();
            int l = 0, r = 0, n = s.length(), ans = 0;
            char[] chas = s.toCharArray();
            while (r < n) {
                map.put(chas[r], map.getOrDefault(chas[r], 0) + 1);
                if (map.keySet().size() <= 2) ans = Math.max(ans, r - l + 1);
                while (map.keySet().size() == 3) {
                    map.put(chas[l], map.get(chas[l]) - 1);
                    if (map.get(chas[l]) == 0) map.remove(chas[l]);
                    l++;
                }
                r++;
            }
            return ans;
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String s = "a";
            s = "eceba";
            handler.lengthOfLongestSubstringTwoDistinct(s);
        }

        public int lengthOfLongestSubstringTwoDistinct(String s) {
            int i = 0, j = 0;
            int[] dict = new int[256];
            int cnt = 0;
            int res = 0;
            for (; i < s.length(); i++) {
                while (j < s.length() && cnt <= 2) {
                    dict[s.charAt(j)]++;
                    if (dict[s.charAt(j)] == 1) cnt++;//第一次从0到1
                    if (cnt <= 2) res = Math.max(res, j - i + 1);
                    j++;
                }
                dict[s.charAt(i)]--;
                if (dict[s.charAt(i)] == 0) cnt--;
            }
            return res;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }
}
