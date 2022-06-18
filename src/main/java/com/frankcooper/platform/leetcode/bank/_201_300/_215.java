package com.frankcooper.platform.leetcode.bank._201_300;


import java.util.PriorityQueue;
import java.util.Random;

//215. 数组中的第K个最大元素   Kth Largest Element in an Array
public class _215 {


    public static void main(String[] args) {
        _215 handler = new _215();
        handler.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2);

//        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);


    }

    /*

     */
    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (i < k || nums[i] > pq.peek()) pq.offer(nums[i]);
            if (pq.size() > k) pq.poll();
        }
        return pq.peek();
    }


    static class _2nd {
        public int findKthLargest(int[] nums, int k) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i = 0; i < nums.length; i++) {
                if (i < k || nums[i] > pq.peek()) pq.offer(nums[i]);
                if (pq.size() > k) pq.poll();
            }
            return pq.peek();
        }
    }

    static class _3rd {
        public int findKthLargest(int[] nums, int k) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i = 0; i < nums.length; i++) {
                if (i < k || nums[i] > pq.peek()) pq.offer(nums[i]);
                if (pq.size() > k) pq.poll();
            }
            return pq.peek();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        public int findKthLargest(int[] nums, int k) {
            int n = nums.length;
            int target = n - k, l = 0, r = n - 1;
            while (true) {
                //找到partition的索引
                int idx = partition(nums, l, r);
                //如果找到，返回
                if (idx == target) return nums[idx];
                else if (idx > target) r = idx - 1;
                else l = idx + 1;
            }
        }


        public int partition(int[] nums, int l, int r) {
            //随机化一个pivot
            if (l < r) {
                int rand = l + 1 + new Random().nextInt(r - l);
                swap(nums, l, rand);
            }
            int pivot = nums[l];
            int j = l;
            for (int i = l + 1; i <= r; i++) {
                //小于pivot的元素都移动到左侧
                if (nums[i] < pivot) {
                    j++;
                    swap(nums, j, i);
                }
            }
            //[l...j-1]的元素都小于 pivot， [j]=pivot [j+1...r]的元素>=pivot
            swap(nums, j, l);
            return j;
        }

        public void swap(int[] nums, int i, int j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }

    }

    static class _5th {
        public int findKthLargest(int[] nums, int k) {
            int n = nums.length;
            heapInsert(nums);
            for (int i = 0; i < k - 1; i++) {
                int tmp = nums[0];
                nums[0] = nums[n - 1 - i];
                nums[n - 1 - i] = tmp;
                heapify(nums, 0, n - 1 - i - 1);
            }
            return nums[0];
        }


        public void heapInsert(int[] nums) {
            int n = nums.length;
            for (int root = n / 2; root > -1; root--) {
                heapify(nums, root, n - 1);
            }
        }

        public void heapify(int[] nums, int root, int hi) {
            if (root > hi)
                return;
            int t = nums[root];
            int child = 2 * root + 1;
            while (child <= hi) {
                if (child + 1 <= hi && nums[child] < nums[child + 1])
                    child++;
                if (t > nums[child])
                    break;
                nums[root] = nums[child];
                root = child;
                child = 2 * root + 1;
            }
            nums[root] = t;
        }


    }
}
