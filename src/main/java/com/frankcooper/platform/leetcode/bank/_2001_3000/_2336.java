package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

import org.junit.Assert;

public class _2336 {

    static class _1st {

        public static void main(String[] args) {
            _1st handler = new _1st();

            String[] ops = {"SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"};

            Integer[][] vals = new Integer[][]{{}, {2}, {}, {}, {}, {1}, {}, {}, {}};
            SmallestInfiniteSet set = null;
            int i = 0;
            for (String op : ops) {
                switch (op) {
                    case "SmallestInfiniteSet":
                        set = new SmallestInfiniteSet();
                        break;
                    case "addBack":
                        set.addBack(vals[i][0]);
                        break;
                    case "popSmallest":
                        int ans = set.popSmallest();
                        System.out.println(ans);
                        return;
                }
                i++;
            }

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


    static class SmallestInfiniteSet {

        boolean[] vis = new boolean[1010];
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> a - b);
        int idx = 1;

        public int popSmallest() {
            int ans = -1;
            if (!q.isEmpty()) {
                ans = q.poll();
                vis[ans] = false;
            } else {
                ans = idx++;
            }
            return ans;
        }

        public void addBack(int x) {
            if (x >= idx || vis[x]) return;
            if (x == idx - 1) {
                idx--;
            } else {
                q.add(x);
                vis[x] = true;
            }
        }


    }

}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */


