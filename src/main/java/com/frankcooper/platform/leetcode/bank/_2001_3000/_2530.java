package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

import org.junit.Assert;

public class _2530 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {1, 10, 3, 3, 3};
            int k = 3;
            Assert.assertEquals(17, handler.maxKelements(nums, k));

        }

        public long maxKelements(int[] nums, int k) {
            PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
            for (int x : nums) {
                q.offer(x);
            }
            long res = 0;
            while (k-- > 0 && !q.isEmpty()) {
                int x = q.poll();
                res += x;
                q.offer((x + (3 - 1)) / 3);//向上取整
                //参考：https://blog.csdn.net/lanchunhui/article/details/51505671?%3E
            }
            return res;
        }

    }


    //        作者：灵茶山艾府
    //        链接：https://leetcode.cn/problems/maximal-score-after-applying-k-operations/
    //        来源：力扣（LeetCode）
    //        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public long maxKelements(int[] nums, int k) {
            heapify(nums); // 原地堆化（最大堆）
            long ans = 0;
            while (k-- > 0) {
                ans += nums[0]; // 堆顶
                nums[0] = (nums[0] + 2) / 3;
                sink(nums, 0); // 堆化（只需要把 nums[0] 下沉）
            }
            return ans;
        }

        // 原地堆化（最大堆）
        // 堆化可以保证 h[0] 是堆顶元素，且 h[i] >= max(h[2*i+1], h[2*i+2])
        private void heapify(int[] h) {
            // 下标 >= h.length / 2 的元素是二叉树的叶子，无需下沉
            // 倒着遍历，从而保证 i 的左右子树一定是堆，那么 sink(h, i) 就可以把左右子树合并成一个堆
            for (int i = h.length / 2 - 1; i >= 0; i--) {
                sink(h, i);
            }
        }

        // 把 h[i] 不断下沉，直到 i 的左右儿子都 <= h[i]
        private void sink(int[] h, int i) {
            int n = h.length;
            while (2 * i + 1 < n) {
                int j = 2 * i + 1; // i 的左儿子
                if (j + 1 < n && h[j + 1] > h[j]) { // i 的右儿子比 i 的左儿子大
                    j++;
                }
                if (h[j] <= h[i]) { // 说明 i 的左右儿子都 <= h[i]，停止下沉
                    break;
                }
                swap(h, i, j); // 下沉
                i = j;
            }
        }

        // 交换 h[i] 和 h[j]
        private void swap(int[] h, int i, int j) {
            int tmp = h[i];
            h[i] = h[j];
            h[j] = tmp;
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
