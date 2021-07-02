package com.frankcooper.bank._401_500;

import java.util.*;

import org.junit.Assert;

public class _433 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String src = "AACCGGTT";
            handler.transform(src);

        }

        Set<String> bankSet;

        public int minMutation(String start, String end, String[] bank) {
            bankSet = new HashSet<>(Arrays.asList(bank));
            int step = 0;
            Queue<String> q = new LinkedList<>();
            q.offer(start);
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    String u = q.poll();
                    List<String> vs = transform(u);
                    for (String v : vs) {
                        if (v.equals(end)) return step;
                        q.offer(v);
                    }
                }
                step++;
            }
            return -1;
        }

        private List<String> transform(String src) {
            List<String> res = new ArrayList<>();
            char[] gen = {'A', 'C', 'G', 'T'};
            for (int i = 0; i < src.length(); i++) {
                char c = src.charAt(i);
                for (int j = 0; j < gen.length; j++) {
                    if (gen[j] == c) continue;
                    String s = src.substring(0, i) + gen[j] + src.substring(i + 1);
                    if (!bankSet.contains(s)) continue;
                    res.add(s);
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
