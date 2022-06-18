package com.frankcooper.platform.leetcode.bank.bi_weekly;

import java.util.*;

public class BiWeek79 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public boolean digitCount(String num) {
            int n = num.length();
            int[] cnt = new int[10];
            for (int i = 0; i < n; i++) cnt[num.charAt(i) - '0']++;
            for (int i = 0; i < n; i++) {
                if (num.charAt(i) - '0' != cnt[i]) return false;
            }
            return true;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public String largestWordCount(String[] messages, String[] senders) {
            Map<String, Pair> map = new HashMap<>();
            int n = senders.length;
            int maxx = 0;
            String res = "";
            for (int i = 0; i < n; i++) {
                String[] arr = messages[i].split(" ");
                Pair pair = map.getOrDefault(senders[i], new Pair());
                pair.count += arr.length;
                pair.msg += " " + messages[i];
                map.put(senders[i], pair);
                if (pair.count > maxx) {
                    res = senders[i];
                    maxx = pair.count;
                } else if (pair.count == maxx) {
                    if (senders[i].compareTo(res) > 0) {
                        res = senders[i];
                    }
                }
            }
            return res;
        }

        class Pair {
            int count;
            String msg;

            public Pair() {
                this.count = 0;
                this.msg = "";
            }

            public Pair(int count, String msg) {
                this.count = count;
                this.msg = msg;
            }
        }
    }

    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int n = 5, roads[][] = {{0, 1}, {1, 2}, {2, 3}, {0, 2}, {1, 3}, {2, 4}};
            handler.maximumImportance(n, roads);
        }

        public long maximumImportance(int n, int[][] roads) {
            long res = 0;
            Integer[] cnt = new Integer[n];
            Arrays.fill(cnt, 0);
            for (int[] r : roads) {
                cnt[r[0]]++;
                cnt[r[1]]++;
            }
            Arrays.sort(cnt, (a, b) -> b - a);
            int t = n;
            for (int i = 0; i < n; i++) {
                res += 1L * cnt[i] * t--;
            }
            return res;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        //https://leetcode.cn/problems/booking-concert-tickets-in-groups/solution/by-endlesscheng-okcu/
        //Not Completed Understand
        class BookMyShow {


            int n, m;
            int[] min;
            long[] sum;


            public BookMyShow(int n, int m) {
                this.n = n;
                this.m = m;
                min = new int[n * 4];
                sum = new long[n * 4];
            }

            public int[] gather(int k, int maxRow) {
                int i = index(1, 1, n, maxRow + 1, m - k);
                if (i == 0) return new int[]{};
                int seats = (int) query_sum(1, 1, n, i, i);
                add(1, 1, n, i, k);//占了k个座位
                return new int[]{i - 1, seats};
            }

            public boolean scatter(int k, int maxRow) {
                long t = 1L * (maxRow + 1) * m - query_sum(1, 1, n, 1, maxRow + 1);
                if (t < k) return false;
                for (int i = index(1, 1, n, maxRow + 1, m - 1); ; i++) {
                    int left_seats = m - (int) query_sum(1, 1, n, i, i);
                    if (k <= left_seats) {
                        add(1, 1, n, i, k);
                        return true;
                    }
                    k -= left_seats;
                    add(1, 1, n, i, left_seats);
                }
            }

            //将idx索引上的元素+=val 更新的区间范围为[l,r] 根节点为o
            public void add(int o, int l, int r, int idx, int val) {
                if (l == r) {
                    min[o] += val;
                    sum[o] += val;
                    return;
                }
                int mid = (l + r) / 2;
                if (idx <= mid) add(o * 2, l, mid, idx, val);
                else add(o * 2 + 1, mid + 1, r, idx, val);
                min[o] = Math.min(min[o * 2], min[o * 2 + 1]);
                sum[o] = sum[o * 2] + sum[o * 2 + 1];
            }


            //返回区间[L,R]的区间和
            public long query_sum(int o, int l, int r, int L, int R) {
                if (L <= l && r <= R) return sum[o];
                long t = 0L;
                int mid = (l + r) / 2;
                if (L <= mid) t += query_sum(o * 2, l, mid, L, R);
                if (R > mid) t += query_sum(o * 2 + 1, mid + 1, r, L, R);
                return t;
            }

            //返回区间[L,R]中小于等于val的最靠左的位置，不存在时返回0
            //大写的R为常量，保持不变
            public int index(int o, int l, int r, int R, int val) {
                //整个区间的元素都大于扥与val
                if (min[o] > val) return 0;
                if (l == r) return l;
                int mid = (l + r) / 2;
                if (min[o * 2] <= val) return index(o * 2, l, mid, R, val);
                if (mid < R) return index(o * 2 + 1, mid + 1, r, R, val);
                return 0;
            }
        }


    }


}
