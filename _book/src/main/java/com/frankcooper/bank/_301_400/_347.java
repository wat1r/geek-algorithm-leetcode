package com.frankcooper.platform.leetcode.bank._301_400;

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

        public int[] topKFrequent(int[] nums, int k) {
            //k: 元素num v:出现的频次
            Map<Integer, Integer> map = new HashMap<>();
            for (int x : nums) map.put(x, map.getOrDefault(x, 0) + 1);
            List<int[]> freqList = new ArrayList<>();
            for (Map.Entry<Integer, Integer> e : map.entrySet()) {
                int num = e.getKey(), freq = e.getValue();
                freqList.add(new int[]{num, freq});
            }
            int[] res = new int[k];
            quickSort(freqList, 0, freqList.size() - 1, res, 0, k);
            return res;

        }

        /**
         * @param freqList 频次的list [0]为num [1]为freq
         * @param start    list的开始位置
         * @param end      list的结束位置
         * @param res      结果数组
         * @param resIndex 结果数组的当前待添加的下标索引
         * @param k        k
         */
        private void quickSort(List<int[]> freqList, int start, int end,
                               int[] res, int resIndex, int k) {
            int pivotIndex = (int) (Math.random() * (end - start + 1)) + start;//随机选择哨兵
            Collections.swap(freqList, start, pivotIndex);//交换哨兵与start的位置
            int pivotFreq = freqList.get(start)[1];//当前的频次
            int index = start;
            for (int i = start + 1; i <= end; i++) {
                if (freqList.get(i)[1] >= pivotFreq) {//将频次高的放在左侧，频次低的放在右侧
                    Collections.swap(freqList, index + 1, i);
                    index++;
                }
            }
            Collections.swap(freqList, start, index);//将哨兵的位置放置在正确的位置
            if (index - start >= k) {//[start...index]段的元素比k多，需要在[start...index]段继续缩小范围
                quickSort(freqList, start, index - 1, res, resIndex, k);
            } else {
                for (int i = start; i <= index; i++) {//左侧部分即[start...index]都是需要的，开始收集
                    res[resIndex++] = freqList.get(i)[0];
                }
                if (index - start + 1 < k) { // 当pivot和起点间的个数小于k时，则从pivot到end再继续找剩下的前（k - （pivot - start + 1））大的元素
                    quickSort(freqList, index + 1, end, res, resIndex, k - (index - start + 1));
                }
            }

        }

    }
}
