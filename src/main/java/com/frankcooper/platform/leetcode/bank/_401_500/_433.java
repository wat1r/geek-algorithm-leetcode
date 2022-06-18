package com.frankcooper.platform.leetcode.bank._401_500;

import java.util.*;

public class _433 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            String src = "AACCGGTT";
//            handler.transform(src);
            String start = "AAAAAAAA", end = "CCCCCCCC";
            String[] bank = {"AAAAAAAA", "AAAAAAAC", "AAAAAACC", "AAAAACCC", "AAAACCCC", "AACACCCC", "ACCACCCC", "ACCCCCCC", "CCCCCCCA"};
            handler.minMutation(start, end, bank);
        }

        Set<String> bankSet;

        public int minMutation(String start, String end, String[] bank) {
            bankSet = new HashSet<>(Arrays.asList(bank));
            if (!bankSet.contains(end)) return -1;
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


        Set<String> bankSet;

        public int minMutation(String start, String end, String[] bank) {
            bankSet = new HashSet<>(Arrays.asList(bank));
            if (!bankSet.contains(end)) return -1;
            Set<String> startSet = new HashSet<String>() {{
                add(start);
            }};
            Set<String> endSet = new HashSet<String>() {{
                add(end);
            }};
            Set<String> visSet = new HashSet<>();
            int step = 0;
            while (!startSet.isEmpty() && !endSet.isEmpty()) {
                step++;
                if (startSet.size() > endSet.size()) {
                    Set<String> t = startSet;
                    startSet = endSet;
                    endSet = t;
                }
                Set<String> tmpSet = new HashSet<>();
                for (String u : startSet) {
                    List<String> vs = transform(u);
                    for (String v : vs) {
                        if (endSet.contains(end)) return step;
                        if (!visSet.contains(v) && bankSet.contains(v)) {
                            visSet.add(v);
                            tmpSet.add(v);
                        }
                    }
                }
                startSet = tmpSet;
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


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            String start = "AACCGGTT";
            String end = "AAACGGTA";
            String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
            handler.minMutation(start, end, bank);
        }


        Set<String> bankSet;

        public int minMutation(String start, String end, String[] bank) {
            bankSet = new HashSet<>(Arrays.asList(bank));
            if (!bankSet.contains(end)) return -1;
            // int res = -1;
            int step = 1;
            Queue<String> q = new LinkedList<>();
            q.offer(start);
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    String cur = q.poll();
                    List<String> candidates = transform(cur);
                    for (String can : candidates) {
                        if (end.equals(can)) return step;
                        q.offer(can);
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

    //DFS
    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            String start = "AACCGGTT";
            String end = "AACCGGTA";
            String[] bank = {"AACCGGTA"};
            handler.minMutation(start, end, bank);
        }


        int minStep = Integer.MAX_VALUE;//最小的步数
        Set<String> bankSet;
        Set<String> pathSet;


        public int minMutation(String start, String end, String[] bank) {
            bankSet = new HashSet<>(Arrays.asList(bank));
            pathSet = new HashSet<>();
            if (!bankSet.contains(end)) return -1;
            backtracing(start, end, 0);
            return minStep == Integer.MAX_VALUE ? -1 : minStep;
        }

        private void backtracing(String start, String end, int step) {
            //找到了end基因，开始更新最小步数
            if (start.equals(end)) {
                minStep = Math.min(step, minStep);
                return;
            }
            //遍历可能的基因库
            for (String str : bankSet) {
                int diff = 0;//不同基因的数量
                for (int i = 0; i < str.length(); i++) {
                    if (start.charAt(i) != str.charAt(i)) {
                        diff++;
                        if (diff > 1) break;
                    }
                }
                //基因的变化是1且当前的基因没有出现在pathSet中，可以进入下一层
                if (diff == 1 && !pathSet.contains(str)) {
                    pathSet.add(str);//添加
                    backtracing(str, end, step + 1);//步数+1
                    pathSet.remove(str);//回溯
                }
            }
        }
    }
}
