package com.frankcooper.platform.lintcode;

import com.frankcooper.utils.PrintUtils;

public class _1897 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] intervals = PrintUtils.processSymbol("[[1,2],[4,5],[8,10]]");
            int rooms = 1;
            int[][] ask = PrintUtils.processSymbol("[[2,3],[3,4]]");
            handler.meetingRoomIII(intervals, rooms, ask);
        }


        public boolean[] meetingRoomIII(int[][] intervals, int rooms, int[][] ask) {
            int[] sum = new int[50005];//根据数据范围设定，否则的话则是下面的maxn
            int[] vis = new int[50005];
            int n = ask.length;
            int maxn = 0;//最大的天数，所有会议结束的最大数
            for (int i = 0; i < intervals.length; i++) {
                vis[intervals[i][0]]++;
                vis[intervals[i][1]]--;
                maxn = Math.max(maxn, intervals[i][1]);
            }
            //遍历ask更新 maxn
            for (int i = 0; i < n; i++) maxn = Math.max(maxn, ask[i][1]);
            int temp = 0;
            for (int i = 1; i <= maxn; i++) {
                temp += vis[i];
                if (temp < rooms) sum[i] = 0;
                else sum[i] = 1;
            }
            for (int i = 1; i <= maxn; i++) sum[i] += sum[i - 1];
            boolean[] ans = new boolean[n];
            for (int i = 0; i < n; i++) {
                int s = ask[i][0], e = ask[i][1];
                if (sum[s - 1] == sum[e - 1]) ans[i] = true;
                else ans[i] = false;
            }
            return ans;
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
