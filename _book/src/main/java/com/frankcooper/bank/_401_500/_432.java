package com.frankcooper.bank._401_500;

import java.util.*;

import org.junit.Assert;

public class _432 {


    /*
 ["AllOne","inc","inc","getMaxKey","getMinKey","inc","getMaxKey","getMinKey"]
[[],["hello"],["hello"],[],[],["leet"],[],[]]
["AllOne","getMaxKey","getMinKey"]
[[],[],[]]
["AllOne","inc","inc","inc","inc","getMaxKey","inc","inc","inc","dec","inc","inc","inc","getMaxKey"]
[[],["hello"],["goodbye"],["hello"],["hello"],[],["leet"],["code"],["leet"],["hello"],["leet"],["code"],["code"],[]]
["AllOne","inc","inc","inc","inc","inc","dec","dec","getMaxKey","getMinKey"]
[[],["a"],["b"],["b"],["b"],["b"],["b"],["b"],[],[]]
["AllOne","inc","inc","inc","inc","inc","inc","dec", "dec","getMinKey","dec","getMaxKey","getMinKey"]
[[],["a"],["b"],["b"],["c"],["c"],["c"],["b"],["b"],[],["a"],[],[]]
-> [null,null,null,null,null,null,null,null,null,"a",null,"c","c"]
["AllOne","inc","inc","inc","dec","inc","inc","getMaxKey","dec","dec","dec","getMaxKey"]
[[],["hello"],["world"],["hello"],["world"],["hello"],["leet"],[],["hello"],["hello"],["hello"],[]]
->[null,null,null,null,null,null,null,"hello",null,null,null,"leet"]

     *
     */


    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            AllOne processor = new AllOne();
            String[] ops = new String[]{"AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"};
            String[][] values = new String[][]{{}, {"hello"}, {"hello"}, {}, {}, {"leet"}, {}, {}};
            ops = new String[]{"AllOne", "inc", "inc", "inc", "inc", "getMaxKey", "inc", "inc", "inc", "dec", "inc", "inc", "inc", "getMaxKey"};
            values = new String[][]{{}, {"hello"}, {"goodbye"}, {"hello"}, {"hello"}, {}, {"leet"}, {"code"}, {"leet"}, {"hello"}, {"leet"}, {"code"}, {"code"}, {}};
//            ops = new String[]{"AllOne", "inc", "inc", "inc", "inc", "inc", "dec", "dec", "getMaxKey", "getMinKey"};
//            values = new String[][]{{}, {"a"}, {"b"}, {"b"}, {"b"}, {"b"}, {"b"}, {"b"}, {}, {}};
            ops = new String[]{"AllOne", "inc", "inc", "inc", "inc", "inc", "inc", "dec", "dec", "getMinKey", "dec", "getMaxKey", "getMinKey"};
            values = new String[][]{{}, {"a"}, {"b"}, {"b"}, {"c"}, {"c"}, {"c"}, {"b"}, {"b"}, {}, {"a"}, {}, {}};
            ops = new String[]{"AllOne", "inc", "inc", "inc", "dec", "inc", "inc", "getMaxKey", "dec", "dec", "dec", "getMaxKey"};
            values = new String[][]{{}, {"hello"}, {"world"}, {"hello"}, {"world"}, {"hello"}, {"leet"}, {}, {"hello"}, {"hello"}, {"hello"}, {}};
            for (int i = 0; i < ops.length; i++) {
                String op = ops[i];
                switch (op) {
                    case "AllOne":
                        break;
                    case "inc":
                        processor.inc(values[i][0]);
                        break;
                    case "dec":
                        processor.dec(values[i][0]);
                        break;
                    case "getMaxKey":
                        System.out.println(processor.getMaxKey());
                        break;
                    case "getMinKey":
                        System.out.println(processor.getMinKey());
                        break;
                }
            }
        }


        static class AllOne {
            //WA 13/17
            Map<Integer, Set<String>> map = new HashMap<>();
            Map<String, Integer> counterMap = new HashMap<>();
            int maxx = Integer.MIN_VALUE;
            int minn = Integer.MAX_VALUE;

            public AllOne() {

            }

            public void inc(String key) {
                Integer count = counterMap.getOrDefault(key, 0);
                int incCount = count + 1;
                if (count != 0) {
                    map.get(count).remove(key);
                    if (map.get(count).size() == 0) map.remove(count);
                }
                counterMap.put(key, incCount);
                Set<String> set = map.getOrDefault(incCount, new HashSet<>());
                set.add(key);
                map.put(incCount, set);
                maxx = Math.max(maxx, incCount);
                if (map.size() == 1) minn = maxx;
                else {
                    minn = Math.min(minn, incCount);
                }

            }

            public void dec(String key) {
                Integer count = counterMap.getOrDefault(key, 0);
                int decCount = count - 1;
                if (count != 0) {
                    map.get(count).remove(key);
                    if (map.get(count).size() == 0) map.remove(count);
                }
                counterMap.put(key, decCount);
                Set<String> set = map.getOrDefault(decCount, new HashSet<>());
                set.add(key);
                if (decCount != 0) {
                    map.put(decCount, set);
                } else {
                    map.remove(decCount);
                }
                if (maxx == count) {
                    maxx = decCount;
                } else {
                    if (decCount != 0) maxx = Math.max(maxx, decCount);
                }
                if (map.size() == 1) minn = maxx;
                else {
                    if (decCount != 0) {
                        minn = Math.min(minn, decCount);
                    }
                }

            }

            public String getMaxKey() {
                Set<String> set = map.getOrDefault(maxx, new HashSet<>());
                for (String x : set) {
                    return x;
                }
                return "";
            }

            public String getMinKey() {
                Set<String> set = map.getOrDefault(minn, new HashSet<>());
                for (String x : set) {
                    return x;
                }
                return "";
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        class AllOne {
            class Node {
                int cnt;
                Set<String> set = new HashSet<>();
                Node left, right;

                Node(int _cnt) {
                    cnt = _cnt;
                }
            }

            Node hh, tt;
            Map<String, Node> map = new HashMap<>();

            public AllOne() {
                hh = new Node(-1000);
                tt = new Node(-1000);
                hh.right = tt;
                tt.left = hh;
            }

            void clear(Node node) {
                if (node.set.size() == 0) {
                    node.left.right = node.right;
                    node.right.left = node.left;
                }
            }

            public void inc(String key) {
                if (map.containsKey(key)) {
                    Node node = map.get(key);
                    node.set.remove(key);
                    int cnt = node.cnt;
                    Node next = null;
                    if (node.right.cnt == cnt + 1) {
                        next = node.right;
                    } else {
                        next = new Node(cnt + 1);
                        next.right = node.right;
                        next.left = node;
                        node.right.left = next;
                        node.right = next;
                    }
                    next.set.add(key);
                    map.put(key, next);
                    clear(node);
                } else {
                    Node node = null;
                    if (hh.right.cnt == 1) {
                        node = hh.right;
                    } else {
                        node = new Node(1);
                        node.right = hh.right;
                        node.left = hh;
                        hh.right.left = node;
                        hh.right = node;
                    }
                    node.set.add(key);
                    map.put(key, node);
                }
            }

            public void dec(String key) {
                Node node = map.get(key);
                node.set.remove(key);
                int cnt = node.cnt;
                if (cnt == 1) {
                    map.remove(key);
                } else {
                    Node prev = null;
                    if (node.left.cnt == cnt - 1) {
                        prev = node.left;
                    } else {
                        prev = new Node(cnt - 1);
                        prev.right = node;
                        prev.left = node.left;
                        node.left.right = prev;
                        node.left = prev;
                    }
                    prev.set.add(key);
                    map.put(key, prev);
                }
                clear(node);
            }

            public String getMaxKey() {
                Node node = tt.left;
                for (String str : node.set) return str;
                return "";
            }

            public String getMinKey() {
                Node node = hh.right;
                for (String str : node.set) return str;
                return "";
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
