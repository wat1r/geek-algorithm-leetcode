package com.frankcooper.platform.leetcode.bank._501_600;

import java.util.*;

public class _528 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        //https://leetcode-cn.com/problems/random-pick-with-weight/solution/java-qian-zhui-he-er-fen-cha-zhao-zhu-xi-v6u2/
        class Solution {

            List<Integer> preSums = new ArrayList<>();//前缀和
            int total;//总的数量
            Random random = new Random();

            public Solution(int[] w) {
                for (int x : w) {
                    total += x;
                    preSums.add(total);
                }
            }

            public int pickIndex() {
                int target = random.nextInt(total);
                int lo = 0, hi = preSums.size() - 1;//找到一个
                while (lo < hi) {
                    int mid = lo + (hi - lo) / 2;
                    //如果前缀和的大小不能cover到target 要提高前缀和的区间，等于不行，会碰到下一个区间，左闭合区间
                    if (target >= preSums.get(mid)) lo = mid + 1;
                    else hi = mid;
                }
                return lo;
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        class Solution {


            int[] preSum;
            int n, sum;
            Random random = new Random();


            public Solution(int[] w) {
                n = w.length;
                preSum = new int[n];
                preSum[0] = sum = w[0];
                for (int i = 1; i < n; i++) {
                    sum += w[i];
                    preSum[i] = sum;
                }
            }

            public int pickIndex() {
                int target = random.nextInt(sum);
                int lo = 0, hi = n - 1;
                while (lo < hi) {
                    int mid = lo + (hi - lo) / 2;
                    if (preSum[mid] <= target) lo = mid + 1;
                    else hi = mid;
                }
                return lo;
            }
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
