package com.frankcooper.swordoffer_II;

import java.util.*;

public class Sword_II_114 {
    //["z","x"]
    //["aabc"]
    //["z","x","z"]
    //["abc","ab"]
    //["ab","ab"]
    //["z","ab","ab"]
    //["z","ab","abc"]
    //["ab","abc"]
    //["z","ab","ab","cd","cdef"]
    //["w","z","x","z"]
    //["z","abc","ab"]
    //["zy","zx"]


    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
            words = new String[]{"z", "x", "a", "zb", "zx"};
            handler.alienOrder(words);
        }


        Map<Character, List<Character>> edges = new HashMap<>();//字符间边的关系
        Map<Character, Integer> indegrees = new HashMap<>();//统计某个字符的入度
        boolean valid = true;//判断是否需要提前退出

        public String alienOrder(String[] words) {
            //建图，完成拓扑排序的准备工作
            int n = words.length;
            for (String word : words) {
                for (char c : word.toCharArray()) {
                    edges.putIfAbsent(c, new ArrayList<>());//给每个字符添加一个相邻边
                }
            }
            for (int i = 1; i < n && valid; i++) {
                addEdge(words[i - 1], words[i]);
            }
            if (!valid) return "";
            //bfs
            Queue<Character> q = new LinkedList<>();
            for (char u : edges.keySet()) {//将入度为0的字符加入到队列中
                if (!indegrees.containsKey(u)) {
                    q.offer(u);
                }
            }
            StringBuilder sb = new StringBuilder();//记录弹出的顺序
            while (!q.isEmpty()) {
                char u = q.poll();
                sb.append(u);
                for (char v : edges.get(u)) {//遍历u的邻居
                    indegrees.put(v, indegrees.get(v) - 1);
                    if (indegrees.get(v) == 0) {//入度为0后，该节点转入队列中
                        q.offer(v);
                    }
                }
            }
            //["z","x","a","zb","zx"]
            //对于前四个字符串 排序是zxab 但是来了zx后 x又得排序到b之后，但是b之前已经出现了x，是矛盾的
            //这个case范围的是"" 如果不加上判断，返回的是"b", "b"的邻居"x"在入度减一后并没有立马减少为0，bfs提前结束
            return sb.length() == edges.size() ? sb.toString() : "";
        }


        public void addEdge(String prev, String cur) {
            int m = prev.length(), n = cur.length();
            int len = Math.min(m, n);
            int i = 0;
            for (; i < len; i++) {
                char u = prev.charAt(i), v = cur.charAt(i);
                if (u != v) {
                    if (u == 'b') {
                        System.out.println();
                    }
                    edges.get(u).add(v);
                    indegrees.put(v, indegrees.getOrDefault(v, 0) + 1);
                    break;
                }
            }
            //如果"abc" "ab"的这种case，提前返回""
            if (i == len && m > n) {
                valid = false;
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        static final int VISITING = 1, VISITED = 2;
        Map<Character, List<Character>> edges = new HashMap<>();//字符间边的关系
        Map<Character, Integer> states = new HashMap<>();//统计某个字符的状态
        boolean valid = true;
        char[] paths;
        int index;

        public String alienOrder(String[] words) {
            //建图，完成拓扑排序的准备工作
            int n = words.length;
            for (String word : words) {
                for (char c : word.toCharArray()) {
                    edges.putIfAbsent(c, new ArrayList<>());//给每个字符添加一个相邻边
                }
            }
            for (int i = 1; i < n && valid; i++) {
                addEdge(words[i - 1], words[i]);
            }
            if (!valid) return "";
            //dfs
            paths = new char[edges.size()];
            index = edges.size() - 1;
            for (char u : edges.keySet()) {
                if (!states.containsKey(u)) {
                    dfs(u);
                }
            }
            if (!valid) return "";
            return String.valueOf(paths);
        }

        private void dfs(char u) {
            states.put(u, VISITING);//当前节点标记为「访问中」
            for (char v : edges.get(u)) {
                if (!states.containsKey(v)) {//节点v没有被访问
                    dfs(v);//继续遍历v
                    if (!valid) return;//如果发现有不符合条件的 ，提前结束
                } else if (states.get(v) == VISITING) //第二次进入v，说明有环
                {
                    valid = false;
                    return;
                }
            }
            states.put(u, VISITED);//u这个节点是安全的，标记为「已访问」
            paths[index--] = u;//记录u在栈的位置
        }


        public void addEdge(String prev, String cur) {
            int m = prev.length(), n = cur.length();
            int len = Math.min(m, n);
            int i = 0;
            for (; i < len; i++) {
                char u = prev.charAt(i), v = cur.charAt(i);
                if (u != v) {
                    edges.get(u).add(v);
                    break;
                }
            }
            if (i == len && m > n) {
                valid = false;
            }
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
