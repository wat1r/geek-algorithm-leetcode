package com.frankcooper.bank._701_1000;

import java.util.*;

import org.junit.Assert;

public class _981 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            /**
             * []
             * [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
             */

            TimeMap timeMap = new TimeMap();
            String[] ops = new String[]{"TimeMap", "set", "get", "get", "set", "get", "get"};
            Object[][] vals = new Object[][]{{}, {"foo", "bar", "1"}, {"foo", "1"}, {"foo", "3"}, {"foo", "bar2", "4"}, {"foo", "4"}, {"foo", "5"}};

            ops = new String[]{"TimeMap", "set", "set", "get", "get", "get", "get", "get"};
            vals = new Object[][]{{}, {"love", "high", 10}, {"love", "low", 20}, {"love", 5}, {"love", 10}, {"love", 15}, {"love", 20}, {"love", 25}};

            for (int i = 0; i < ops.length; i++) {
                switch (ops[i]) {
                    case "set":
                        timeMap.set(String.valueOf(vals[i][0]), String.valueOf(vals[i][1]), Integer.parseInt(String.valueOf(vals[i][2])));
                        break;
                    case "get":
                        System.out.printf("%s\n", timeMap.get(String.valueOf(vals[i][0]), Integer.parseInt(String.valueOf(vals[i][1]))));
                        break;
                    default:
                        break;

                }
            }
        }


        static class TimeMap {


            Map<String, Map<Integer, String>> map = new HashMap<>();

            /**
             * Initialize your data structure here.
             */
            public TimeMap() {

            }

            public void set(String key, String value, int timestamp) {
                Map<Integer, String> tmp = map.getOrDefault(key, new HashMap<>());
                tmp.put(timestamp, value);
                map.put(key, tmp);
            }

            public String get(String key, int timestamp) {
                Map<Integer, String> tmp = map.get(key);
                ArrayList<Integer> timeList = new ArrayList<>(tmp.keySet());
                Collections.sort(timeList);
                int n = timeList.size();
                int l = 0, r = n;
                while (l < r) {
                    int mid = l + (r - l) >> 1;
                    if (mid == timestamp) return tmp.get(mid);
                    if (mid > timestamp) r = mid;
                    else l = mid + 1;
                }
                System.out.printf("%d\n", l);
                return l == n ? tmp.get(timeList.get(n - 1)) : tmp.get(timeList.get(l));
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
