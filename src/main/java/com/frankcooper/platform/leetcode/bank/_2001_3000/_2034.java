package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

public class _2034 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            String[] ops = {"StockPrice", "update", "update", "current", "maximum", "update", "maximum", "update", "minimum"};
            Integer[][] vals = new Integer[][]{{1, 10}, {2, 5}, {}, {}, {1, 3}, {}, {4, 2}, {}};
            StockPrice stockPrice = null;
            int i = 0;
            for (String op : ops) {
                switch (op) {
                    case "StockPrice":
                        stockPrice = new StockPrice();
                        i--;
                        break;
                    case "update":
                        stockPrice.update(vals[i][0], vals[i][1]);
                        break;
                    case "current":
                        System.out.println(stockPrice.current());
                        break;
                    case "maximum":
                        System.out.println(stockPrice.maximum());
                        break;
                    case "minimum":
                        System.out.println(stockPrice.minimum());
                        break;
                }
                i++;
            }


        }


        static class StockPrice {

            Map<Integer, Integer> map;
            int curr;
            TreeMap<Integer, Integer> treeMap;//k:price,v:出现的次数

            public StockPrice() {
                map = new HashMap<>();
                treeMap = new TreeMap<>();
            }

            public void update(int timestamp, int price) {
                curr = Math.max(curr, timestamp);//最大的时间点
                if (map.containsKey(timestamp)) {
                    int prev = map.get(timestamp);//老的price
                    int cnt = treeMap.get(prev);
                    if (cnt == 1) {
                        treeMap.remove(prev);
                    } else {
                        treeMap.put(prev, cnt - 1);
                    }
                }
                map.put(timestamp, price);
                treeMap.put(price, treeMap.getOrDefault(price, 0) + 1);
            }

            public int current() {
                return map.get(curr);
            }

            public int maximum() {
                return treeMap.lastKey();
            }

            public int minimum() {
                return treeMap.firstKey();
            }
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
