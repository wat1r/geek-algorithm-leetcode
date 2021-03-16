package com.frankcooper.bank.bi_weekly;

import org.junit.Assert;

public class _BiWeek45 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = {1, -3, 2, 3, -4};
            Assert.assertEquals(handler.maxAbsoluteSum(nums),5);
            nums = new int[]{2, -5, 1, -4, 3, -2};
            Assert.assertEquals(handler.maxAbsoluteSum(nums),8);
            nums = new int[]{-7, -1, 0, -2, 1, 3, 8, -2, -6, -1, -10, -6, -6, 8, -4, -9, -4, 1, 4, -9};
            Assert.assertEquals(handler.maxAbsoluteSum(nums),44);

        }

        public int maxAbsoluteSum(int[] nums) {
            int[] preSum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) preSum[i + 1] = preSum[i] + nums[i];
//            int ans = 0;
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int i = 0; i <= nums.length; i++) {
                min = Math.min(min, preSum[i]);
                max = Math.max(max, preSum[i]);
            }
            return Math.abs(max - min);
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();

            Assert.assertEquals(handler.minimumLength("bbbbbbbbbbbbbbbbbbb"), 0);
            Assert.assertEquals(handler.minimumLength("ca"), 2);
            Assert.assertEquals(handler.minimumLength("cabaabac"), 0);
            Assert.assertEquals(handler.minimumLength("aabccabba"), 3);
            Assert.assertEquals(handler.minimumLength("cccccccccccccccccccccccccccccccccccccccccccccccccccccccccbbbbbbbbbbbbbbbbbbbaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbcccccccbcaabccbacaabcbaccaacccaabbcaabbbbcaccbbcbbbbbabcacbccbaaaccaaaabcacbccbbcaabccacbccbbcaaaccaaccbbcaabaabbcaccaabbcbaacbaccbaaabccbaabcacbabcabbccbacaabbcaacaaacaaacbabbcabccbbacaabacabcacacbcacaabbabcbaaaccccacbbabcbccbaaccbbbabbbbaabcccaabaacccccccbbabcbcbcbcbcbbbbccbbaaccaaaaccacabbaccbbabccaacbcbccaabcacacacaaabbbaaccccaccaabcabbabacacbbaacbcabbbcaccccacccbcaccccccccbbcccbbaabbcbcaabcccbabcbcbccacaccbcaacbaaaaaababbaaccbcccaccccababcccacbccbbacabcbbbccbcababbaaaacaabccbaaacbacbcaababbcbacccacaccbabcabbccaccacbccaaccabbabcbbccaabccaacabbaabccacbabcaababccbcaacababbabcacccaaabcaabcbbbbabcbccbbbbbbbbbbaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbcccccccccc"), 594);
        }


        public int minimumLength(String s) {
            int n = s.length(), l = 0, r = n - 1;
            char[] chas = s.toCharArray();
            int ans = n;
            while (l < r) {
//                System.out.printf("l:%d,r:%d\n", l, r);
                char prelc = chas[l], lc = chas[l], prerc = chas[r], rc = chas[r];
//                System.out.printf("prerc:%s,lc:%s,lr:%s,prerc:%s\n", prelc, chas[l], chas[r], prerc);
                if (lc != rc) break;
                while (lc == prelc && l < r) {
                    lc = chas[++l];
                }
                while (rc == prerc && l <= r) {
                    rc = chas[--r];
                }
                ans = r - l + 1;
            }
            return ans;
        }


    }
}
