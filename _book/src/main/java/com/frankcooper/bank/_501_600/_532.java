package com.frankcooper.bank._501_600;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _532 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int findPairs(int[] nums, int k) {
            Arrays.sort(nums);
            int res = 0, n = nums.length, i = 0, j = 1;
            while (i <= j && j < n) {
                //计算diff
                int diff = nums[j] - nums[i];
                if (diff == k) {
                    res++;
                    // System.out.printf("%diff %diff -> ",  nums[i] , nums[j]  );
                    //如果j重复出现，不再被统计
                    while (j < n - 1 && nums[j + 1] == nums[j]) j++;
                    j++;
                } else if (diff > k) i++;
                else if (diff < k) j++;
                if (i == j) j++;//强制性岔开1个数,否则比较的是两个相同数本身
            }
            return res;
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int nums[] = {3, 1, 4, 1, 5}, k = 2;
            handler.findPairs(nums, k);
        }

        //int nums[] = {3 1 4 1 5} k = 2;
        //							|
        //i=0				vis:3
        //					res:
        //i=1				vis:3 1
        //					res:1
        //i=2				vis:3 1 4
        //					res:1
        //i=3				vis:3 1 4  //这一步已经添加了元素1，两个set去重了
        //					res:1
        //i=4				vis:3 1 4 5
        //					res:1 3


        public int findPairs(int[] nums, int k) {
            Set<Integer> vis = new HashSet<>(), res = new HashSet<>();
            for (int x : nums) {
                //以x为起点，找其上，即[x,x+k]
                if (vis.contains(x + k)) {
                    res.add(x);//都是以下作为参考值
                }
                //以x为起点，找其下,即[x-k,x]
                if (vis.contains(x - k)) {
                    res.add(x - k);//都是以下作为参考值
                }
                vis.add(x);//x已经被统计过
            }
            return res.size();
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public int findPairs(int[] nums, int k) {
            Arrays.sort(nums);
            int n = nums.length, j = 0, res = 0;
            for (int i = 0; i < n; i++) {//定左区间
                //i==0 初始情况，进入
                //i这个数和之前的数相同，跳过
                if (i == 0 || nums[i] != nums[i - 1]) {
                    //选定j的边界
                    //[j]-[i]的 diff比 k小 要扩j
                    //i==j 说明i追上j了，错开1个位置
                    while (j < n && (nums[j] - nums[i] < k || j == i)) {
                        j++;
                    }
                    //统计
                    if (j < n && nums[j] - nums[i] == k) {
                        res++;
                    }
                }
            }
            return res;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
