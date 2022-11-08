package com.frankcooper.platform.lintcode;


import org.junit.Assert;

import java.util.Collections;
import java.util.HashMap;
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
//            System.out.printf("%d\n", res);
            return res;
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int lengthOfLongestSubstringKDistinct(String s, int k) {
            int n = s.length();
            if (n * k == 0) return 0;

            // sliding window left and right pointers
            int left = 0;
            int right = 0;
            // hashmap character -> its rightmost position
            // in the sliding window
            HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();

            int max_len = 1;

            while (right < n) {
                // add new character and move right pointer
                hashmap.put(s.charAt(right), right++);

                // slidewindow contains 3 characters
                if (hashmap.size() == k + 1) {
                    // delete the leftmost character
                    int del_idx = Collections.min(hashmap.values());
                    hashmap.remove(s.charAt(del_idx));
                    // move left pointer of the slidewindow
                    left = del_idx + 1;
                }

                max_len = Math.max(max_len, right - left);
            }
            return max_len;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public int lengthOfLongestSubstringKDistinct(String s, int k) {

            if (s.length() == 0 || k == 0) {
                return 0;
            }

            int left = 0, right = 0, cnt = 0;
            int charSet[] = new int[256];
            int ans = 0;

            while (right < s.length()) {
                // 统计right指向的字符
                // 当字符在窗口内第一次出现时，字符种类数+1，该字符出现次数+1
                if (charSet[s.charAt(right)] == 0) {
                    cnt++;
                }
                charSet[s.charAt(right)]++;
                right++;

                // 向右移动left，保持窗口内只有k种不同的字符
                while (cnt > k) {
                    charSet[s.charAt(left)]--;
                    // 当该字符在本窗口不再出现时，字符种类数-1
                    if (charSet[s.charAt(left)] == 0) {
                        cnt--;
                    }
                    left++;
                }

                // 更新答案
                ans = Math.max(ans, right - left);
            }
            return ans;
        }
    }
}
