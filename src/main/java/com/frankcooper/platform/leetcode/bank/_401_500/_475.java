package com.frankcooper.platform.leetcode.bank._401_500;

import java.util.*;

public class _475 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] houses = new int[]{1, 2, 3, 4};
            int[] heaters = new int[]{1, 4};
            handler.findRadius(houses, heaters);

        }

        //这个case表示在1 2 3 4 位置都有热水器 但是只有1位置有房子，结果为0
        //[1]
        //[1,2,3,4]
        public int findRadius(int[] houses, int[] heaters) {
            Arrays.sort(houses);
            Arrays.sort(heaters);
            int l = 0, r = (int) 1e9;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (check(houses, heaters, mid)) r = mid;
                else l = mid + 1;
            }
            return l;
        }

        public boolean check(int[] houses, int[] heaters, int target) {
            int n = houses.length, m = heaters.length;
            for (int i = 0, j = 0; i < n; i++) {
                while (j < m && houses[i] > heaters[j] + target) {
                    j++;
                }
                if (j < m && heaters[j] - target <= houses[i] && houses[i] <= heaters[j] + target) {
                    continue;
                }
                return false;
            }
            return true;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int findRadius(int[] houses, int[] heaters) {
            Arrays.sort(heaters);
            int m = houses.length, n = heaters.length;
            int res = Integer.MIN_VALUE;
            for (int house : houses) {
                int idx = Arrays.binarySearch(heaters, house);
                if (idx < 0) {
                    idx = ~idx;
                    int dist1 = idx - 1 >= 0 ? house - heaters[idx - 1] : Integer.MAX_VALUE;
                    int dist2 = idx < n ? heaters[idx] - house : Integer.MAX_VALUE;
                    res = Math.max(res, Math.min(dist1, dist2));
                }
            }
            return res;
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
