package com.frankcooper.bank.week;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

import javax.swing.text.TabableView;

public class Week237 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public boolean checkIfPangram(String sentence) {
            int[] arr = new int[26];
            char[] chas = sentence.toCharArray();
            for (int i = 0; i < chas.length; i++) arr[chas[i] - 'a']++;
            for (int i = 0; i < arr.length; i++)
                if (arr[i] == 0) return false;
            return true;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int maxIceCream(int[] costs, int coins) {
            Arrays.sort(costs);
            int res = 0;
            for (int x : costs) {
                coins -= x;
                if (coins < 0) break;
                res++;
            }
            return res;

        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        class Task {
            int idx; //任务索引
            int start;//开始时间
            int end;//结束时间
            int duration;//耗时

            public Task(int idx, int start, int end, int duration) {
                this.idx = idx;
                this.start = start;
                this.end = end;
                this.duration = duration;
            }
        }


        public int[] getOrder(int[][] tasks) {


            PriorityQueue<Task> pq = new PriorityQueue<Task>((o1, o2) -> {
                return o1.start == o2.start ? o1.duration - o2.duration : o1.start - o2.start;
            });
            int n = tasks.length;
            int[] res = new int[n];
            int cur = 1;


            for (int i = 0; i < n; i++) {
                int[] t = tasks[i];
                cur += t[2];
                if (t[0] < cur) t[0] = cur;
                pq.offer(new Task(i, t[0], t[0] + t[1], t[1]));
            }
            int idx = 0;//当前时间
            while (!pq.isEmpty()) {
                res[idx++] = pq.poll().idx;
            }
            return res;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();

            int[] arr1 = {818492001, 823729238, 2261353, 747144854, 478230859, 285970256, 774747711, 860954509, 245631564, 634746160};
            int[] arr2 = {967900366, 340837476};
            handler.getXORSum(arr1, arr2);
        }

        public int getXORSum(int[] arr1, int[] arr2) {
            int m = arr1.length, n = arr2.length;
            long[][] f = new long[m][n];
            f[0][0] = arr1[0] & arr2[0];
            for (int i = 1; i < m; i++) {
                f[i][0] = (f[i - 1][0] ^ (arr1[i] & arr2[0]));
            }
            for (int j = 1; j < n; j++) {
                f[0][j] = (f[0][j - 1] ^ (arr1[0] & arr2[j]));
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    f[i][j] = (f[i][j - 1] ^ f[i - 1][j] ^ f[i - 1][j - 1]) ^ (arr1[i] & arr2[j]);
                }
            }
            return (int) f[m - 1][n - 1];
        }
    }

    static class _4th_1 {

        public static void main(String[] args) {
            int[] arr1 = {1, 2, 3};
            int[] arr2 = {6, 5};
            _4th_1 handler = new _4th_1();
            handler.getXORSum(arr1, arr2);
        }

        public int getXORSum(int[] arr1, int[] arr2) {
            int m = arr1.length, n = arr2.length;
            int max = Math.max(m, n);
            int min = Math.min(m, n);
            boolean isRowMore = max == m;//行数是不是大于等于列数
            long[] f = new long[min];
            f[0] = arr1[0] & arr2[0];
            for (int i = 1; i < min; i++) {
                f[i] = f[i - 1] ^ (isRowMore ? arr1[i] & arr2[0] : arr2[i] & arr1[0]);
            }
            for (int i = 1; i < max; i++) {
                f[0] = f[0] ^ (isRowMore ? arr1[i] & arr2[0] : arr2[i] & arr1[0]);
                for (int j = 1; j < min; j++) {
                    f[j] = f[j - 1] ^ f[j] ^ (isRowMore ? arr1[i] & arr2[j] : arr2[i] & arr1[j]);
                }
            }
            return (int) f[min - 1];
        }
    }

    static class _4th_2 {
        public int getXORSum(int[] arr1, int[] arr2) {
            int m = arr1.length, n = arr2.length;
            long[] f = new long[n];
            f[0] = arr1[0] & arr2[0];
            for (int j = 1; j < n; j++) {
                f[j] = (f[j - 1] ^ (arr1[0] & arr2[j]));
            }
            for (int i = 1; i < m; i++) {
                long[] nf = new long[n];
                nf[0] = f[0] ^ (arr1[i] & arr2[0]);
                for (int j = 1; j < n; j++) {
                    nf[j] = (nf[j - 1] ^ f[j] ^ f[j - 1] ^ (arr1[i] & arr2[j]));
                }
                f = nf;
            }
            return (int) f[n - 1];
        }
    }

    static class _4th_3 {
        public int getXORSum(int[] arr1, int[] arr2) {
            int res1 = 0, res2 = 0;
            for (int x : arr1) res1 ^= x;
            for (int x : arr2) res2 ^= x;
            return res1 & res2;
        }

    }


    static class _4th_4 {
        public int getXORSum(int[] arr1, int[] arr2) {
            int res1 = 0, res = 0;
            for (int x : arr1) res1 ^= x;
            for (int x : arr2) res ^= (x & res1);
            return res;
        }


    }
}
