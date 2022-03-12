package com.frankcooper.bank.week;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Week215 {


    static Week215 handler = new Week215();


    public static void main(String[] args) {

    }


    static class _1st {


        public static void main(String[] args) {
            OrderedStream os = new OrderedStream(5);
            String[] ops = {"insert", "insert", "insert", "insert", "insert"};
            String[][] values = new String[][]{
                    {"3", "ccccc"}, {"1", "aaaaa"}, {"2", "bbbbb"}, {"5", "eeeee"}, {"4", "ddddd"}
            };
            for (String[] value : values) {
                os.insert(Integer.parseInt(value[0]), value[1]);
            }


        }


        static class OrderedStream {

            int N = 1005;
            String[] arr = null;
            int ptr = 1;


            public OrderedStream(int n) {
                arr = new String[N];
            }

            public List<String> insert(int id, String value) {
                List<String> ans = new ArrayList<>();
                arr[id] = value;
                if (id == ptr) {
                    ans.add(value);
                    ptr++;
                    while (arr[ptr] != null) {
                        ans.add(arr[ptr++]);
                    }
                }
                return ans;
            }
        }

    }

    static class _2nd {

        static _2nd handler = new _2nd();

        public static void main(String[] args) {

            handler.closeStrings("abc", "bca");
        }


        public boolean closeStrings(String word1, String word2) {
            int n1 = word1.length(), n2 = word2.length();
            if (n1 != n2) return false;
            int[] arr1 = new int[26];
            int[] arr2 = new int[26];
            for (int i = 0; i < n1; i++) {
                arr1[word1.charAt(i) - 'a']++;
                arr2[word2.charAt(i) - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                if ((arr1[i] == 0 && arr2[i] != 0)||(arr1[i] != 0 && arr2[i] == 0)) return false;
            }
            Arrays.sort(arr1);
            Arrays.sort(arr2);
            for (int i = 0; i < 26; i++) {
                if (arr1[i] != arr2[i]) return false;
            }
            return true;
        }

    }

    static class _3rd {
        static _3rd handler = new _3rd();

        public static void main(String[] args) {
//            handler.minOperations(new int[]{1, 1, 4, 2, 3}, 5);
//            handler.minOperations(new int[]{1, 1}, 3);
            handler.minOperations(new int[]{8828, 9581, 49, 9818, 9974, 9869, 9991, 10000, 10000, 10000, 9999, 9993, 9904, 8819, 1231, 6309}, 134365);
        }


        /**
         * 76/78 tle
         *
         * @param nums
         * @param x
         * @return
         */
        public int minOperations1st(int[] nums, int x) {
            int INF = Integer.MAX_VALUE >> 1;
            int ans = INF;
            int n = nums.length;
            int left = 0, right = n;
            int sum = 0;
            int steps = 0;
            int rightSum = 0, rightSteps = 0;
            while (right >= 0) {
                if (right < n) {
                    rightSum += nums[right];
                    rightSteps++;
                    sum += rightSum;
                    steps += rightSteps;
                }
                if (sum > x) break;
                while (left <= right) {
                    if (sum == x) ans = Math.min(ans, steps);
                    if (left >= n) return ans;
                    sum += nums[left++];
                    steps++;
                    if (sum > x) {
                        steps = 0;
                        sum = 0;
                        left = 0;
                        break;
                    }
                }
                right--;
            }
            return ans == INF ? -1 : ans;
        }


        /**
         * 反向思考的
         * 186周周赛的第二题可获得的最大点数
         * https://leetcode-cn.com/problems/maximum-points-you-can-obtain-from-cards/
         *
         * @param nums
         * @param x
         * @return
         */
        public int minOperations(int[] nums, int x) {
            int INF = Integer.MAX_VALUE >> 1;
            int ans = INF;
            int n = nums.length;
            int sum = 0;
            for (int num : nums) sum += num;
            if (sum == x) return n;
            int delta = sum - x;
            int left = 0, right = 0;
            int currSum = 0;
            while (right < n) {
                currSum += nums[right];
                if (currSum > delta) {
                    while (left < right && currSum > delta) {
                        currSum -= nums[left++];
                    }
                }
                if (currSum == delta) ans = Math.min(ans, left + n - right - 1);
                right++;
            }

            return ans == INF ? -1 : ans;
        }

    }
}
