package com.frankcooper.bank._401_500;

import java.util.*;

import com.frankcooper.swordoffer.utils.PrintUtils;

public class _477 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            handler.calHD(4, 14);
        }


        HashMap<int[], Integer> map = new HashMap<>();


        public int totalHammingDistance(int[] nums) {
            int res = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                for (int j = i; j < nums.length; j++) {
                    int a = nums[i], b = nums[j];
                    int[] uv = new int[]{a, b}, vu = new int[]{b, a};
                    if (map.containsKey(uv)) {
                        res += map.get(uv);
                        continue;
                    }
                    if (map.containsKey(vu)) {
                        res += map.get(vu);
                        continue;
                    }
                    int hd = calHD(a, b);
                    res += hd;
                    map.put(uv, hd);
                    map.put(vu, hd);
                }
            }
            return res;
        }


        public int calHD(int a, int b) {
            int x = a ^ b;
            int res = 0;
            while (x > 0) {
                if ((x & 1) == 1) res++;
                x >>= 1;
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = new int[]{4, 14, 2};
//            handler.totalHammingDistance(nums);

            int MOD = (int) 1e9;
            System.out.printf("%s,%d\n", PrintUtils.toBinaryString(MOD, 30), PrintUtils.toBinaryString(MOD, 30).length());
            MOD = Integer.MAX_VALUE;
            System.out.printf("%d\n", MOD);
            System.out.printf("%d\n", (1 << 31) - 1);
            System.out.printf("%s,%d\n", PrintUtils.toBinaryString(MOD, 30), PrintUtils.toBinaryString(MOD, 30).length());
            MOD = 1 << 30;
            System.out.printf("%d\n", MOD);
            System.out.printf("%s,%d\n", PrintUtils.toBinaryString(MOD, 30), PrintUtils.toBinaryString(MOD, 30).length());
        }


        int totalHammingDistance(int[] nums) {
            int res = 0;
            for (int b = 0; b < 32; b++) {
                int ones = 0;
                for (int x : nums) {
                    if (((x >> b) & 1) == 1) ones++;
                }
                res += ones * (nums.length - ones);//1的个数与剩下的非1的个数组合
            }
            return res;
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        int totalHammingDistance(int[] nums) {
            int[] cnt = new int[32];
            for (int b = 0; b < 32; b++) {
                for (int x : nums) {
                    if ((x >> b & 1) == 1) cnt[b]++;
                }
            }
            int res = 0;
            for (int c : cnt) {
                res += c * (nums.length - c);
            }
            return res;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
