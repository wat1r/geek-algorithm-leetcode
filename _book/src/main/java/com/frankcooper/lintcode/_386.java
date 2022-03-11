package com.frankcooper.lintcode;


import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * 同 _340
 */
public class _386 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "eceba";
            int k = 3;
            Assert.assertEquals(handler.lengthOfLongestSubstringKDistinct(s, k), 4);
            s = "WORLD";
            k = 4;
            Assert.assertEquals(handler.lengthOfLongestSubstringKDistinct(s, k), 4);
            /**
             * 下面的case没过
             */
            s = "eqgkcwGFvjjmxutystqdfhuMblWbylgjxsxgnoh";
            k = 16;
            Assert.assertEquals(handler.lengthOfLongestSubstringKDistinct(s, k), 27);


        }

        public int lengthOfLongestSubstringKDistinct(String s, int k) {
            int l = 0, r = 0, n = s.length();
            System.out.printf("n:%d\n", n);
            char[] chas = s.toCharArray();
            int cnt = 0, res = 0;
            Set<Character> set = new HashSet<>();
            int[] arr = new int[256];
            while (r < n) {
                set.add(chas[r]);
                arr[chas[r]] = r;
                if (set.size() <= k) res = Math.max(res, r - l + 1);
                if (set.size() == k + 1) {
                    set.remove(chas[l]);
                    l = arr[chas[l]] + 1;
                }
                r++;
            }
            System.out.printf("%d\n", res);
            return res;
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
}
