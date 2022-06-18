package com.frankcooper.platform.leetcode.bank.week;

import java.util.*;

import org.junit.Assert;

public class Week243 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String firstWord = "aaa", secondWord = "a", targetWord = "aab";
//            Assert.assertFalse(handler.isSumEqual(firstWord, secondWord, targetWord));
            firstWord = "d";
            secondWord = "b";
            targetWord = "aaaaae";
//            Assert.assertTrue(handler.isSumEqual(firstWord, secondWord, targetWord));
            firstWord = "acb";
            secondWord = "cba";
            targetWord = "cdb";
//            Assert.assertTrue(handler.isSumEqual(firstWord, secondWord, targetWord));
            firstWord = "j";
            secondWord = "j";
            targetWord = "bi";
//            Assert.assertTrue(handler.isSumEqual(firstWord, secondWord, targetWord));
            firstWord = "h";
            secondWord = "fhjfdghj";
            targetWord = "fhjfdgig";
            Assert.assertTrue(handler.isSumEqual(firstWord, secondWord, targetWord));
        }


        public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
            int carry = 0;
            int m = targetWord.length();
            int n1 = firstWord.length(), n2 = secondWord.length();
            int p1 = n1 - 1, p2 = n2 - 1;
            int p = m - 1;
            while (p1 >= 0 || p2 >= 0) {
                int a = 0, b = 0, c = 0;
                if (p1 >= 0) a = firstWord.charAt(p1--) - 'a';
                if (p2 >= 0) b = secondWord.charAt(p2--) - 'a';
                if (p >= 0) c = targetWord.charAt(p--) - 'a';
                int t = a + b + carry;
                carry = 0;
                int y = t % 10;
                if (y != c) return false;
                if (t >= 10) {
                    t /= 10;
                    carry = t % 10;
                }
            }
            while (p >= 0) {
                if (targetWord.charAt(p--) - 'a' != carry) return false;
            }
            return true;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
//            Assert.assertEquals("999", handler.maxValue("99", 9));
            Assert.assertEquals("-123", handler.maxValue("-13", 2));
            Assert.assertEquals("-12324327", handler.maxValue("-1232432", 7));

        }

        public String maxValue(String n, int x) {
            int i = 0, sign = 1, len = n.length();
            if (n.charAt(0) == '-') {
                i = 1;
                sign = -1;
            }
            //对于正数，插入位置左侧的数字都必须  >=x
            //对于负数，插入位置左侧的数字都必须  <=x
            while (i < len && (sign == 1 && x <= n.charAt(i) - '0')
                    || (sign == -1 && i < len && x >= n.charAt(i) - '0')) {
                i++;
            }
            return n.substring(0, i) + x + n.substring(i);

        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();

            int[] servers = {3, 3, 2}, tasks = {1, 2, 3, 2, 1, 2};
            Assert.assertArrayEquals(new int[]{2, 2, 0, 2, 1, 2}, handler.assignTasks(servers, tasks));

//            servers = new int[]{10, 63, 95, 16, 85, 57, 83, 95, 6, 29, 71};
//            tasks = new int[]{70, 31, 83, 15, 32, 67, 98, 65, 56, 48, 38, 90, 5};
//            handler.assignTasks(servers, tasks);

//            servers = new int[]{338, 890, 301, 532, 284, 930, 426, 616, 919, 267, 571, 140, 716, 859, 980, 469, 628, 490, 195, 664, 925, 652, 503, 301, 917, 563, 82, 947, 910, 451, 366, 190, 253, 516, 503, 721, 889, 964, 506, 914, 986, 718, 520, 328, 341, 765, 922, 139, 911, 578, 86, 435, 824, 321, 942, 215, 147, 985, 619, 865};
//            tasks = new int[]{773, 537, 46, 317, 233, 34, 712, 625, 336, 221, 145, 227, 194, 693, 981, 861, 317, 308, 400, 2, 391, 12, 626, 265, 710, 792, 620, 416, 267, 611, 875, 361, 494, 128, 133, 157, 638, 632, 2, 158, 428, 284, 847, 431, 94, 782, 888, 44, 117, 489, 222, 932, 494, 948, 405, 44, 185, 587, 738, 164, 356, 783, 276, 547, 605, 609, 930, 847, 39, 579, 768, 59, 976, 790, 612, 196, 865, 149, 975, 28, 653, 417, 539, 131, 220, 325, 252, 160, 761, 226, 629, 317, 185, 42, 713, 142, 130, 695, 944, 40, 700, 122, 992, 33, 30, 136, 773, 124, 203, 384, 910, 214, 536, 767, 859, 478, 96, 172, 398, 146, 713, 80, 235, 176, 876, 983, 363, 646, 166, 928, 232, 699, 504, 612, 918, 406, 42, 931, 647, 795, 139, 933, 746, 51, 63, 359, 303, 752, 799, 836, 50, 854, 161, 87, 346, 507, 468, 651, 32, 717, 279, 139, 851, 178, 934, 233, 876, 797, 701, 505, 878, 731, 468, 884, 87, 921, 782, 788, 803, 994, 67, 905, 309, 2, 85, 200, 368, 672, 995, 128, 734, 157, 157, 814, 327, 31, 556, 394, 47, 53, 755, 721, 159, 843};
//            handler.assignTasks(servers, tasks);
        }


        public int[] assignTasks(int[] servers, int[] tasks) {
            int s = servers.length, t = tasks.length;
            int[] ans = new int[t];
            //服务器的优先队列，[0]表示权重，[1]表示下标索引，当权重相同，下标索引小的放前面
            PriorityQueue<int[]> sq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
            for (int i = 0; i < s; i++) sq.offer(new int[]{servers[i], i});
            //正在处理的任务的服务器队列，[2]表示这台服务器到期释放资源的时间，[0]表示权重，[1]表示下标索引
            PriorityQueue<int[]> rq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
            int cur = -1, i = 0;
            //i = 147 148
            while (i < t) {
                while (true) {
                    cur++;
                    while (!rq.isEmpty() && cur >= rq.peek()[2]) {
                        int[] o = rq.poll();
                        sq.offer(new int[]{o[0], o[1]});
                    }
                    if (!sq.isEmpty()) break;//如果当前的服务列表是不为空 可以分配
                    else {
                        cur = rq.peek()[2] - 1;//如果sq是空的，那跳到rq 栈顶的时间
                    }
                }
                while (!sq.isEmpty() && i <= cur) {//如果同一时刻存在多台空闲服务器，可以同时将多项任务分别分配给它们。
                    if (i >= t) break;
                    int[] server = sq.poll();//当前的服务器
                    int w = server[0], idx = server[1], expire = cur + tasks[i];
                    rq.offer(new int[]{w, idx, expire});
                    ans[i] = server[1];
                    i++;
                }
            }
            return ans;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
