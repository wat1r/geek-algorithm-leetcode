package com.frankcooper.bank.bi_weekly;

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


    }


}
