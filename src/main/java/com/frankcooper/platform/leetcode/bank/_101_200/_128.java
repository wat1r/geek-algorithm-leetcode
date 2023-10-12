package com.frankcooper.platform.leetcode.bank._101_200;

import java.util.*;

public class _128 {
    static _128 handler = new _128();

    public static void main(String[] args) {
        handler.longestConsecutive(new int[]{0, -1});
    }


    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int n = nums.length;
        int max = 1, cur = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) {
                if (nums[i - 1] + 1 == nums[i]) cur++;
                else {
                    max = Math.max(max, cur);
                    cur = 1;
                }
            }
        }
        return Math.max(max, cur);
    }

    static class _2nd {

        public static void main(String[] args) {
            _2nd handler = new _2nd();
            handler.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2});
        }

        public int longestConsecutive(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int num : nums) set.add(num);
            int max = 0;
            for (int num : nums) {
                if (!set.contains(num - 1)) {//判断set不包含当前元素-1的值，跳过已经计算的最长递增序列
                    int curNum = num;
                    int curCnt = 1;
                    while (set.contains(curNum + 1)) {
                        curNum += 1;
                        curCnt += 1;
                    }
                    max = Math.max(max, curCnt);
                }
            }

            return max;
        }
    }


    static class _1st {

        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2});
        }

        public int longestConsecutive(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();//k：当前字符的下标索引，v：其能组成的最长的连续序列的长度
            int l = 0, r = 0;
            int ans = 0;
            for (int num : nums) {
                if (!map.containsKey(num)) {
                    l = map.getOrDefault(num - 1, 0);
                    r = map.getOrDefault(num + 1, 0);
                    int len = l + r + 1;//
                    if (len > ans) ans = len;
                    map.put(num, 1);//这个塞入什么值都无所谓
                    map.put(num - l, len);
                    map.put(num + r, len);
                }
            }
            return ans;
        }
    }

    static class _4th {
        public static void main(String[] args) {
//            handler.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2});
        }

        public int longestConsecutive(int[] nums) {
            if (nums == null || nums.length == 0) return 0;
            Arrays.sort(nums);
            int n = nums.length, res = 1, cur = 1;
            for (int i = 0; i < n - 1; i++) {
                if (nums[i + 1] != nums[i]) {
                    if (nums[i + 1] == nums[i] + 1) {
                        cur++;
                    } else {
                        res = Math.max(res, cur);
                        cur = 1;
                    }
                }
            }
            return Math.max(res, cur);
        }
    }

    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            handler.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2});
        }

        public int longestConsecutive(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int x : nums) {
                set.add(x);
            }
            int maxLen = 0;
            for (int x : nums) {
                if (!set.contains(x - 1)) {
                    int currNum = x;
                    int currLen = 1;
                    while (set.contains(currNum + 1)) {
                        currNum += 1;
                        currLen += 1;
                    }
                    maxLen = Math.max(maxLen, currLen);
                }
            }
            return maxLen;
        }
    }

    static class _5th {
        public int longestConsecutive(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int x : nums) {
                set.add(x);
            }
            int maxLen = 0;
            for (int x : nums) {
                if (!set.contains(x - 1)) {
                    int y = x + 1;
                    while (set.contains(y)) {
                        y++;
                    }
                    maxLen = Math.max(maxLen, y - x);
                }
            }
            return maxLen;
        }
    }
}
