package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.ArrayList;
import java.util.List;

public class _2055 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "**|**|***|";
            int[][] queries = {{2, 5}, {5, 9}};
//            handler.platesBetweenCandles(s, queries);
            s = "***|**|*****|**||**|*";
            queries = new int[][]{{1, 17}, {4, 5}, {14, 17}, {5, 11}, {15, 16}};
//            handler.platesBetweenCandles(s, queries);
            s = "***";
            queries = new int[][]{{2, 2}};
            handler.platesBetweenCandles(s, queries);


        }


        public int[] platesBetweenCandles(String s, int[][] qs) {
            char[] chas = s.toCharArray();
            //计算下标之前有多少个盘子
            int[] preSum = new int[chas.length + 1];
            //记录 '|'这个蜡烛的位置（下标索引）
            List<Integer> idxList = new ArrayList<>();
            for (int i = 0; i < chas.length; i++) {
                if (chas[i] == '|') idxList.add(i);
                preSum[i + 1] = preSum[i] + (chas[i] == '*' ? 1 : 0);
            }
            int[] res = new int[qs.length];//结果集
            if (idxList.size() == 0) return res;
            for (int i = 0; i < qs.length; i++) {
                int a = qs[i][0], b = qs[i][1];//[a, b ]的外区间，找[c,d]的内区间
                //找c：最靠近a的蜡烛
                int c = -1;
                //找idxList中的蜡烛，最靠近a的
                int l = 0, r = idxList.size() - 1;
                while (l < r) {
                    int mid = l + (r - l) / 2;//下取整
                    if (idxList.get(mid) >= a) r = mid;
                    else l = mid + 1;
                }
                c = idxList.get(l);
                //找d：最靠近d的蜡烛
                //找idxList中的蜡烛，最靠近b的
                int d = -1;
                l = 0;
                r = idxList.size() - 1;
                while (l < r) {
                    int mid = l + (r - l + 1) / 2;
                    if (idxList.get(mid) <= b) l = mid;
                    else r = mid - 1;

                }
                d = idxList.get(l);
                //[c,d]要符合区间范围
                if (c <= d) res[i] = preSum[d] - preSum[c];
            }
            return res;

        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        //https://leetcode-cn.com/problems/plates-between-candles/solution/gong-shui-san-xie-er-fen-qian-zhui-he-yu-0qt0/
        public int[] platesBetweenCandles(String s, int[][] qs) {
            char[] cs = s.toCharArray();
            int n = cs.length, m = qs.length;
            int[] l = new int[n], r = new int[n];
            int[] preSum = new int[n + 1];
            for (int i = 0, j = n - 1, p = -1, q = -1; i < n; i++, j--) {
                if (cs[i] == '|') p = i;
                if (cs[j] == '|') q = j;
                l[i] = p;
                r[j] = q;
                preSum[i + 1] = preSum[i] + (cs[i] == '*' ? 1 : 0);
            }
            int[] ans = new int[m];
            for (int i = 0; i < m; i++) {
                int a = qs[i][0], b = qs[i][1];
                int c = r[a], d = l[b];
                if (c != -1 && c <= d) ans[i] = preSum[d + 1] - preSum[c];
            }
            return ans;
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
