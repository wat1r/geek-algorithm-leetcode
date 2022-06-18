package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.*;

public class _1552 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int maxDistance(int[] position, int m) {
            Arrays.sort(position);
            int lo = 1, hi = (int) 1e9 + 5;
            int res = 1;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (check(position, mid, m)) {
//                    res = mid;
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            return res;
        }

        //给定一个距离为dis的间距，放置完m个小球，能否放置完
        public boolean check(int[] pos, int dist, int m) {
            int cnt = 1;//当前已经放置了的球的数量
            for (int i = 0, j = 1; j < pos.length; j++) {
                if (pos[j] - pos[i] >= dist) //这个球是满足的
                {
                    i = j;//前面的已经考虑结束了
                    cnt++;
                    if (cnt == m) return true;//如果达到了给定的m个球，返回true
                }
            }
            return false;
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
