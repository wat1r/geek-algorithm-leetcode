package com.frankcooper.platform.leetcode.bank._1001_1500;


import java.util.*;

import com.frankcooper.utils.PrintUtils;


public class _1229 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] slots1 = PrintUtils.processSymbol("[[10,50],[60,120],[140,210]]"), slots2 = PrintUtils.processSymbol("[[0,15],[60,70]]");
            int duration = 8;
            handler.minAvailableDuration(slots1, slots2, duration);

        }


        /*
         1.对slots1 和slots2 按start 从小到大排序
         2.准备两个指针 i 处理 slots1 j 处理slots2
            2.1 取当前两个slot的 [0]的最大值作为  start [1]的最小值作为end
            2.2 当start < end 也就是这两个区间有交集，并且 交集的差 end -start > =duration 的值，这个才可以安排会议时间，两个人的时间都ok
            2.2 否则的话，比较哪个会议先结束，先结束的进入下一个也就是 i j 跳到下一个
         */
        public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
            Arrays.sort(slots1, ((o1, o2) -> o1[0] - o2[0]));
            Arrays.sort(slots2, ((o1, o2) -> o1[0] - o2[0]));
            List<Integer> res = new ArrayList<>();
            int i = 0, j = 0;
            while (i < slots1.length && j < slots2.length) {
                int start = Math.max(slots1[i][0], slots2[j][0]);
                int end = Math.min(slots1[i][1], slots2[j][1]);
                if (start < end && end - start >= duration) {
                    res.add(start);
                    res.add(start + duration);
                    return res;
                } else {
                    if (slots1[i][1] < slots2[j][1]) i++;
                    else j++;
                }
            }
            return res;
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
