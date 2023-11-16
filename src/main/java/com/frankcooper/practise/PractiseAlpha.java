package com.frankcooper.practise;

import com.alibaba.fastjson.JSONObject;

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

    static class _11_2 {

        public static void main(String[] args) {
            _11_2 handler = new _11_2();
            int[] obstacles = {3, 1, 5, 6, 4, 2};
            handler.longestObstacleCourseAtEachPosition(obstacles);
        }

        public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {

            List<Integer> cur = new ArrayList<>();
            List<Integer> ans = new ArrayList<>();


            for (int ob : obstacles) {
                int p = getPosition(cur, ob);
                if (cur.size() == p) {
                    cur.add(ob);
                } else {
                    cur.set(p, ob);
                }
                ans.add(p + 1);
            }

            System.out.println(JSONObject.toJSONString(cur));

            int[] res = new int[ans.size()];
            for (int i = 0; i < ans.size(); i++) {
                res[i] = ans.get(i);
            }
            return res;
        }

        private int getPosition(List<Integer> cur, int num) {

            int left = 0, right = cur.size() - 1;
            if (cur.size() == 0 || num >= cur.get(right)) {
                left = right + 1;
                right = -1;
            }

            while (left < right) {
                int mid = ((right - left) >> 1) + left;
                if (cur.get(mid) > num) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }
    }



    static class _0 {
        public static void main(String[] args) {

        }

    }

}
