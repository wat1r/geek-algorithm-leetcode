package com.frankcooper.platform.leetcode.bank.week;

import java.util.*;

public class Week214 {

    public static void main(String[] args) {


    }


    static class _1st {
        static _1st handler = new _1st();

        public static void main(String[] args) {
//            handler.getMaximumGenerated(7);
            handler.getMaximumGenerated(15);
        }

        public int getMaximumGenerated(int n) {
            if (n == 0) return 0;
            if (n == 1) return 1;
            int max = 0;
            int[] nums = new int[n + 1];
            nums[1] = 1;
            for (int i = 1; 2 * i <= n; i++) {
                nums[2 * i] = nums[i];
                max = Math.max(nums[2 * i], max);
                if (2 * i + 1 <= n) {
                    nums[2 * i + 1] = nums[i] + nums[i + 1];
                    max = Math.max(nums[2 * i + 1], max);
                }
            }
            return max;
        }
    }

    static class _2nd {

        static _2nd handler = new _2nd();


        public static void main(String[] args) {
//            handler.minDeletions("aaabbbcc");//2
            handler.minDeletions("ceabaacb");//2
//            handler.minDeletions("accdcdadddbaadbc");
//            handler.minDeletions("abcabc");//3
//            handler.minDeletions1st("abcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwz");
        }


        public int minDeletions(String s) {
            int[] arr = new int[26];
            for (char c : s.toCharArray()) arr[c - 'a']++;
            Set<Integer> set = new HashSet<>();
            int ans = 0;
            for (int i : arr) {
                if (i != 0) {
                    while (set.contains(i)) {
                        i--;
                        ans++;
                    }
                    if (i != 0) set.add(i);
                }
            }
            return ans;
        }

        public int minDeletions1st(String s) {
            Map<Character, Integer> map = new HashMap<>();
            int[] arr = new int[26];
            for (char c : s.toCharArray()) {
                map.put(c, map.getOrDefault(c, 0) + 1);
                arr[c - 97]++;
            }
            Arrays.sort(arr);
            int target = 0, count = 1;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i - 1] == 0 || arr[i] == 0) continue;
                if (arr[i - 1] == arr[i]) {
                    target = arr[i];
                    count++;
                }
            }
            int res = 0;
            if (target != 0) {
                res = (count - 1) * target;
                while (map.containsValue(target) && target >= 0) {
                    target--;
                }
            }
//            System.out.printf("%d\n", res - target);
            return res - target;
        }

    }


    static class _3rd {
        static _3rd handler = new _3rd();

        public static void main(String[] args) {

            int[] inventory = {2, 5};
            int orders = 4;
            inventory = new int[]{2, 8, 4, 10, 6};
            orders = 20;
            inventory = new int[]{1000000000};
            orders = 1000000000;
            handler.maxProfit(inventory, orders);
        }

        long MOD = 1_00_000_007;

        public int maxProfit(int[] inventory, int orders) {
            Integer[] nums = new Integer[inventory.length];
            for (int i = 0; i < inventory.length; i++) nums[i] = inventory[i];
            Arrays.sort(nums, (o1, o2) -> o2 - o1);
            long res = 0;
            int j = 0;
            int n = nums.length;
            while (orders > 0) {
                while (j < n && nums[j] >= nums[0]) {
                    j++;
                }
                int next = 0;
                if (j < n) {
                    next = nums[j];
                }
                long bucks = j, delta = nums[0] - next;
                long rem = bucks * delta;
                if (rem > orders) {
                    long dec = orders / bucks;
                    long a1 = nums[0] - dec + 1;
                    long an = nums[0];
                    res += (((a1 + an) * dec) / 2) * bucks;
                    res += (nums[0] - dec) * (orders % bucks);
                } else {
                    long a1 = next + 1;
                    long an = nums[0];
                    res += (((a1 + an) * delta) / 2) * bucks;
                    nums[0] = next;
                }
                orders -= rem;
                res %= MOD;
            }
            return (int) res;
        }


        public int maxProfit1st(int[] inventory, int orders) {
            int ans = 0;
            PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
            for (int i : inventory) pq.offer(i);
            while (!pq.isEmpty()) {
                int curr = pq.poll();
                if (orders == 0) return ans;
                orders--;
                ans += curr--;
                pq.offer(curr);
            }
            return ans;
        }
    }


}
