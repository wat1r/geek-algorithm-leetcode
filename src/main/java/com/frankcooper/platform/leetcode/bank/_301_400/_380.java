package com.frankcooper.platform.leetcode.bank._301_400;

import java.util.*;

public class _380 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        class RandomizedSet {

            Map<Integer, Integer> map = new HashMap<>();//k:插入的值，v:list的size
            List<Integer> list = new ArrayList<>();
            Random random = new Random();

            /**
             * Initialize your data structure here.
             */
            public RandomizedSet() {

            }

            /**
             * Inserts a value to the set. Returns true if the set did not already contain the specified element.
             */
            public boolean insert(int val) {
                if (map.containsKey(val)) return false;
                map.put(val, list.size());
                list.add(val);
                return true;
            }

            /**
             * Removes a value from the set. Returns true if the set contained the specified element.
             */
            public boolean remove(int val) {
                if (!map.containsKey(val)) return false;
                int idx = map.get(val);//找到val的索引
                int lastEle = list.get(list.size() - 1);//list中的最后一个元素
                list.set(idx, lastEle);//当前的val用lastEle替换
                map.put(lastEle, idx);//更新关系
                //移除元素
                list.remove(list.size() - 1);
                map.remove(val);
                return true;
            }

            /**
             * Get a random element from the set.
             */
            public int getRandom() {
                int ranIdx = random.nextInt(list.size());
                return list.get(ranIdx);
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
