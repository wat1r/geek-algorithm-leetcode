package com.frankcooper.practise;

import java.util.*;

public class PractiseAlpha {

    static class _1 {
        public static void main(String[] args) {

        }

    }


    static class _10_1 {
        public static void main(String[] args) {

        }


        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(target - nums[i])) {
                    return new int[]{map.get(target - nums[i]), i};
                }
                map.put(nums[i], i);
            }
            return new int[]{};
        }
    }


    static class _11_1 {
        public static void main(String[] args) {

        }


        public int[] twoSum(int[] numbers, int target) {

            for (int i = 0; i < numbers.length; i++) {
                //注意lo的下标
                int lo = i + 1, hi = numbers.length - 1;
                while (lo <= hi) {
                    int mid = lo + (hi - lo) / 2;
                    if (numbers[mid] == target - numbers[i]) {
                        return new int[]{i + 1, mid + 1};
                    } else if (numbers[mid] > target - numbers[i]) {
                        hi = mid - 1;
                    } else {
                        lo = mid + 1;
                    }
                }
            }
            return new int[]{0, 0};
        }
    }

    static class _0 {
        public static void main(String[] args) {

        }

    }

}
