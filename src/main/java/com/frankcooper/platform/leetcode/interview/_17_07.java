package com.frankcooper.platform.leetcode.interview;

import java.util.*;

public class _17_07 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        /**
         * bfs
         */
        public String[] trulyMostPopular(String[] names, String[] synonyms) {
            Map<String, Integer> nodes = new HashMap<>();
            Map<String, List<String>> graph = new HashMap<>();
            for (String name : names) {
                String x = name.substring(0, name.indexOf("("));
                Integer cnt = Integer.valueOf(name.substring(name.indexOf("(") + 1, name.indexOf(")")));
                nodes.putIfAbsent(x, cnt);
            }
            for (String syn : synonyms) {
                String u = syn.substring(1, syn.indexOf(","));
                String v = syn.substring(syn.indexOf(",") + 1, syn.indexOf(")"));
                graph.getOrDefault(u, new ArrayList<>());
                graph.get(u).add(v);
                graph.getOrDefault(v, new ArrayList<>());
                graph.get(v).add(u);
            }
            List<String> res = new ArrayList<>();
            Map<String, Integer> tmps = new HashMap<>();
            for (String key : graph.keySet()) {
                if (!nodes.containsKey(key)) continue;
                int t = nodes.get(key);
                Queue<String> q = new LinkedList<>();
                q.offer(key);
                while (!q.isEmpty()) {
                    String cur = q.poll();
                    for (String next : graph.get(cur)) {
                        if (nodes.containsKey(next)) {
                            q.offer(next);
                            key = key.compareTo(next) > 0 ? next : key;
                            t += nodes.get(next);
                            nodes.remove(next);
                        }
                    }
                    graph.put(cur, null);
                    nodes.remove(cur);
                }
                tmps.put(key, t);
            }
            //todo
            return res.toArray(new String[res.size()]);
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();

            String[] names = {"John(15)", "Jon(12)", "Chris(13)", "Kris(4)", "Christopher(19)"};
            String[] synonyms = {"(Jon,John)", "(John,Johnny)", "(Chris,Kris)", "(Chris,Christopher)"};
            handler.trulyMostPopular(names, synonyms);
        }

        /**
         * 并查集: 改并查集模板
         **/


        public String[] trulyMostPopular(String[] names, String[] synonyms) {
            UnionFind uf = new UnionFind();
            for (String name : names) {
                String x = name.substring(0, name.indexOf("("));
                Integer cnt = Integer.valueOf(name.substring(name.indexOf("(") + 1, name.indexOf(")")));
                uf.parents.put(x, x);
                uf.size.put(x, cnt);
            }

            for (String syn : synonyms) {
                String u = syn.substring(1, syn.indexOf(","));
                String v = syn.substring(syn.indexOf(",") + 1, syn.indexOf(")"));
                if (!uf.parents.containsKey(u)) {//需要处理不存在的，并初始化size
                    uf.parents.put(u, u);
                    uf.size.put(u, 0);
                }
                if (!uf.parents.containsKey(v)) {
                    uf.parents.put(v, v);
                    uf.size.put(v, 0);
                }
                uf.union(u, v);
            }
            List<String> res = new ArrayList<>();
            for (String name : names) {
                String x = name.substring(0, name.indexOf("("));
                String rootX = uf.find(x);
                if (x.equals(rootX)) {
                    res.add(x + "(" + uf.getSize(x) + ")");
                }
            }
            return res.toArray(new String[res.size()]);
        }

        class UnionFind {

            Map<String, String> parents;//当前节点的父节点
            Map<String, Integer> size;//当前节点的人数


            public UnionFind() {
                parents = new HashMap<>();
                size = new HashMap<>();
            }

            //找到x的根节点
            public String find(String x) {
                if (!x.equals(parents.get(x))) {
                    parents.put(x, find(parents.get(x)));
                }
                return parents.get(x);
            }

            public boolean union(String x, String y) {
                String rootX = find(x), rootY = find(y);
                if (rootX.equals(rootY)) return false;
                //字典序小的做根
                if (rootX.compareTo(rootY) > 0) {//rootY的字典序小
                    parents.put(rootX, rootY);
                    size.put(rootY, size.get(rootX) + size.get(rootY));
                } else {
                    parents.put(rootY, rootX);
                    size.put(rootX, size.get(rootY) + size.get(rootX));
                }
                return true;
            }

            /**
             * 返回x对应的根节点包含的人数
             */
            public int getSize(String x) {
                String rootX = find(x);
                return size.get(rootX);
            }

        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        public String[] trulyMostPopular(String[] names, String[] synonyms) {
            int n = names.length;
            String[] xs = new String[n];//出现的name组成的keys
            int[] cnts = new int[n];//name对应的value
            Map<String, Integer> idxMap = new HashMap<>();//name对应的序号

            int idx = 0;
            for (String name : names) {
                String x = name.substring(0, name.indexOf("("));
                int cnt = Integer.parseInt(name.substring(name.indexOf("(") + 1, name.indexOf(")")));
                xs[idx] = x;
                cnts[idx] = cnt;
                idxMap.put(x, idx);
                idx++;
            }
            UnionFind uf = new UnionFind(n);
            for (String syn : synonyms) {
                String u = syn.substring(1, syn.indexOf(","));
                String v = syn.substring(syn.indexOf(",") + 1, syn.indexOf(")"));
                Integer uIdx = idxMap.get(u);
                Integer vIdx = idxMap.get(v);
                if (uIdx != null && vIdx != null) {
                    uf.union(uIdx, vIdx);
                }
            }
            Map<Integer, List<String>> map = new HashMap<>();
            for (int x = 0; x < n; x++) {
                int rootX = uf.find(x);
                map.putIfAbsent(rootX, new ArrayList<>());
                map.get(rootX).add(names[x]);
                if (rootX != x) {
                    cnts[rootX] += cnts[x];
                }
            }
            List<String> res = new ArrayList<>();
            for (int key : map.keySet()) {
                int cnt = cnts[key];
                List<String> tmp = map.get(cnt);
                Collections.sort(tmp);
                res.add(tmp.get(0) + "(" + cnt + ")");
            }
            return res.toArray(new String[res.size()]);
        }


        class UnionFind {
            int[] parents;
            int[] ranks;

            public UnionFind(int n) {
                parents = new int[n];
                ranks = new int[n];
                for (int i = 0; i < n; i++) parents[i] = i;
            }

            public int find(int x) {
                if (x != parents[x]) {
                    parents[x] = find(parents[x]);
                }
                return parents[x];
            }

            public boolean union(int x, int y) {
                int rootX = find(x), rootY = find(y);
                if (rootX == rootY) return false;
                if (ranks[rootX] > ranks[rootY]) parents[rootY] = rootX;
                if (ranks[rootX] < ranks[rootY]) parents[rootX] = rootY;
                if (ranks[rootX] == ranks[rootY]) {
                    parents[rootY] = rootX;
                    ranks[rootY]++;
                }
                return true;
            }

            public boolean connect(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                return rootX == rootY;
            }

        }


    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
