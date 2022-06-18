package com.frankcooper.platform.leetcode.bank._301_400;

import java.util.*;

/**
 * @Date 2020/9/11
 * @Author Frank Cooper
 * @Description
 */
public class _315 {


    static _315 handler = new _315();

    public static void main(String[] args) {
        int[] nums = new int[]{5, 2, 6, 1};
//        List<Integer> countSmaller = handler.countSmaller(nums);
//        System.out.println(countSmaller);
        handler.testTwo();
    }


    public void testTwo() {
        _2nd second = new _2nd();
        int[] nums = new int[]{7, 5, 6, 5, 2, 6, 5, 1};
        second.countSmaller(nums);
    }


    class _2nd {

        class FenwickTree {
            int n;
            int[] C;

            //初始化
            public FenwickTree(int n) {
                this.n = n;
                this.C = new int[n];
            }

            // 单点更新：将 i 位置与其直接关联的 C 都更新一遍
            public void update(int i) {
                while (i < n) {
                    C[i]++;
                    i += lowbit(i);
                }
            }

            //传进来的值-1过，查询之前有多少个数
            //区间查询：查询小于等于 i 的元素个数
            public int query(int i) {
                int sum = 0;
                while (i >= 1) {
                    sum += C[i];
                    i -= lowbit(i);
                }
                return sum;
            }

            //算lowbit
            public int lowbit(int x) {
                return x & (-x);
            }
        }

        public List<Integer> countSmaller(int[] nums) {
            List<Integer> res = new ArrayList<>();
            int len = nums.length;
            if (len == 0) return res;
            Set<Integer> treeSet = new TreeSet<>();
            for (int num : nums) treeSet.add(num);
            Map<Integer, Integer> map = new HashMap<>();
            int rank = 1;//相当于A数组的下标索引
            for (int num : treeSet) {
                map.put(num, rank++);
            }
            FenwickTree fenwickTree = new FenwickTree(treeSet.size() + 1);
            for (int i = nums.length - 1; i >= 0; i--) {
                rank = map.get(nums[i]);
                fenwickTree.update(rank);
                res.add(fenwickTree.query(rank - 1));
            }
            Collections.reverse(res);
            return res;
        }
    }

}


class _1st {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int len = nums.length;
        if (len == 0) {
            return res;
        }

        // 使用二分搜索树方便排序
        Set<Integer> set = new TreeSet();
        for (int i = 0; i < len; i++) {
            set.add(nums[i]);
        }

        // 排名表
        Map<Integer, Integer> map = new HashMap<>();
        int rank = 1;
        for (Integer num : set) {
            map.put(num, rank);
            rank++;
        }

        FenwickTree fenwickTree = new FenwickTree(set.size() + 1);
        // 从后向前填表
        for (int i = len - 1; i >= 0; i--) {
            // 1、查询排名
            rank = map.get(nums[i]);
            // 2、在树状数组排名的那个位置 + 1
            fenwickTree.update(rank, 1);
            // 3、查询一下小于等于“当前排名 - 1”的元素有多少
            res.add(fenwickTree.query(rank - 1));
        }
        Collections.reverse(res);
        return res;
    }


    private class FenwickTree {
        private int[] tree;
        private int len;

        public FenwickTree(int n) {
            this.len = n;
            tree = new int[n + 1];
        }

        // 单点更新：将 index 这个位置 + 1
        public void update(int i, int delta) {
            // 从下到上，最多到 size，可以等于 size
            while (i <= this.len) {
                tree[i] += delta;
                i += lowbit(i);
            }
        }


        // 区间查询：查询小于等于 index 的元素个数
        // 查询的语义是"前缀和"
        public int query(int i) {
            // 从右到左查询
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= lowbit(i);
            }
            return sum;
        }

        public int lowbit(int x) {
            return x & (-x);
        }

    }


    private void testOne() {

        for (int i = 1; i <= 10; i++) {
            int positive = i;
            int negative = -i;
            String nStr = Integer.toBinaryString(negative);
            System.out.println(String.format("%d:%s\t%d:%s\t%s",
                    positive, addZeroForNum(Integer.toBinaryString(positive), 4)
                    , negative, nStr.substring(nStr.length() - 4),
                    addZeroForNum(Integer.toBinaryString(positive & negative), 4)
                    )
            );
        }


    }


    public static String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(str);// 左补0
                // sb.append(str).append("0");//右补0
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }


}
