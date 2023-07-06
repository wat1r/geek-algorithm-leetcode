package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

import org.junit.Assert;

public class _2178 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            Assert.assertEquals(Arrays.asList(2, 4, 6), handler.maximumEvenSplit(28));
//            Assert.assertEquals(Arrays.asList(2, 4, 6), handler.maximumEvenSplit(12L));

        }

        List<Long> resultList = new ArrayList<>();
        long finalSum = 0;
//        Map<Long,Long> cache = new HashMap<>();

        public List<Long> maximumEvenSplit(long finalSum) {
            if (finalSum % 2 == 1) {
                return resultList;
            }
            this.finalSum = finalSum;
            dfs(finalSum, new ArrayList<>(), 1);
            return resultList;
        }


        public void dfs(long remain, List<Long> levelList, long start) {
//            if(cache.containsKey(remain),)
            if (remain < 0) {
                return;
            }
            if (remain == 0) {
                if (levelList.size() > resultList.size()) {
                    resultList = new ArrayList<>(levelList);
                    return;
                }
            }
            for (long i = start + 1; i <= finalSum / 2; i++) {
                if (i % 2 == 1) {
                    continue;
                }
                levelList.add(i);
                dfs(remain - i, levelList, i);
                levelList.remove(levelList.size() - 1);
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
