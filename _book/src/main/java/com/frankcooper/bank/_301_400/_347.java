package com.frankcooper.bank._301_400;

import java.util.HashMap;
import java.util.Map;

import java.util.*;

import org.junit.Assert;

public class _347 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int[] topKFrequent(int[] nums, int k) {
            //k:nums的每一个数，v:nums中每一个数出现的次数
            Map<Integer, Integer> freqMap = new HashMap<>();
            for (int x : nums) {
                freqMap.put(x, freqMap.getOrDefault(x, 0) + 1);
            }
            //bucket[freq]出现的次数，哪些数出现了freq次
            List<Integer>[] bucket = new List[nums.length + 1];
            for (int x : freqMap.keySet()) {
                int freq = freqMap.get(x);
                if (bucket[freq] == null) bucket[freq] = new ArrayList<>();
                bucket[freq].add(x);
            }
            List<Integer> res = new ArrayList<>();
            //从高到低freq开始收集res
            for (int i = bucket.length - 1; i >= 0; --i) {
                if (bucket[i] != null) {
                    for (int j = 0; j < bucket[i].size() && res.size() < k; j++) {
                        res.add(bucket[i].get(j));
                    }
                }
            }
            int[] ans = new int[res.size()];
            for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
            return ans;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int[] topKFrequent(int[] nums, int k) {
            //k:nums的每一个数，v:nums中每一个数出现的次数
            Map<Integer, Integer> freqMap = new HashMap<>();
            for (int x : nums) {
                freqMap.put(x, freqMap.getOrDefault(x, 0) + 1);
            }
            //做一个大根堆
            PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
            for (Map.Entry<Integer, Integer> e : freqMap.entrySet()) {
                pq.offer(e);
            }
            List<Integer> res = new ArrayList<>();
            while (res.size() < k) {
                res.add(pq.poll().getKey());
            }
            int[] ans = new int[res.size()];
            for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
            return ans;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public int[] topKFrequent(int[] nums, int k) {
            //k:nums的每一个数，v:nums中每一个数出现的次数
            Map<Integer, Integer> freqMap = new HashMap<>();
            for (int x : nums) {
                freqMap.put(x, freqMap.getOrDefault(x, 0) + 1);
            }
            TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();
            for (int x : freqMap.keySet()) {
                int freq = freqMap.get(x);
                treeMap.putIfAbsent(freq, new ArrayList<>());
                treeMap.get(freq).add(x);
            }
            List<Integer> res = new ArrayList<>();
            while (res.size() < k) {
                Map.Entry<Integer, List<Integer>> e = treeMap.pollLastEntry();
                res.addAll(e.getValue());
            }
            int[] ans = new int[res.size()];
            for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
            return ans;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
