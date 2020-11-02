package com.frankcooper.bank.week;

import com.frankcooper.bank._1;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2020/11/1 16:42
 * Description
 */
public class Week213 {

    static Week213 handler = new Week213();


    static class _1st {

        static _1st handler = new _1st();

        public static void main(String[] args) {
            int[] arr = {85};
            int[][] pieces = {{85}};
            handler.canFormArray(arr, pieces);

        }

        public boolean canFormArray(int[] arr, int[][] pieces) {
            Map<Integer, int[]> map = new HashMap<>();
            for (int[] piece : pieces) {
                map.put(piece[0], piece);
            }

            int idx = 0;
            while (idx < arr.length) {
                if (map.containsKey(arr[idx])) {
                    for (int val : map.get(arr[idx])) {
                        if (val != arr[idx++]) {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
            return true;
        }
    }


    /**
     * ETL
     */
    static class _2nd_1 {

        static _2nd_1 handler = new _2nd_1();


        public static void main(String[] args) {
//            handler.countVowelStrings(2);
//            handler.countVowelStrings(1);
//            handler.countVowelStrings(33);
            handler.countVowelStrings(50);
        }

        List<String> seed = Arrays.asList("a", "e", "i", "o", "u");
        List<List<String>> resList = new ArrayList<>();

        public int countVowelStrings(int n) {
            dfs(new ArrayList<>(), n);
            return resList.size();
        }

        private void dfs(List<String> levelList, int n) {
            if (levelList.size() == n) {
                resList.add(new ArrayList<>(levelList));
                return;
            }
            for (String s : seed) {
                if (!levelList.isEmpty() && levelList.get(levelList.size() - 1).compareTo(s) > 0) continue;
                levelList.add(s);
                dfs(levelList, n);
                levelList.remove(levelList.size() - 1);

            }
        }
    }

    /**
     * 双百
     */
    static class _2nd_2 {
        static _2nd_2 handler = new _2nd_2();

        public static void main(String[] args) {
            handler.countVowelStrings(2);
            handler.countVowelStrings(1);
            handler.countVowelStrings(33);
//            handler.countVowelStrings(50);
        }


        List<Character> seed = Arrays.asList('a', 'e', 'i', 'o', 'u');
        int ans = 0;

        public int countVowelStrings(int n) {
            dfs(n, 'a');
            return ans;
        }

        private void dfs(int n, char last) {
            if (n == 0) {
                ans++;
                return;
            }
            for (char curr : seed) {
                if (curr < last) continue;
                dfs(n - 1, curr);
            }
        }


    }

    static class _3rd_1 {

        static _3rd_1 handler = new _3rd_1();

        public static void main(String[] args) {

            int[] heights = {4, 2, 7, 6, 9, 14, 12};
            int bricks = 5;
            int ladders = 1;

            heights = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            bricks = 1;
            ladders = 1;
            handler.furthestBuilding(heights, bricks, ladders);

        }


        public int furthestBuilding(int[] heights, int bricks, int ladders) {
            if (heights == null || heights.length == 0) return 0;
            if (bricks == 0 && ladders == 0) return heights[1] - heights[0] <= 0 ? 1 : 0;
            Queue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
            int idx = 1;
            int sum = 0;
            while (idx < heights.length) {
                int delta = heights[idx] - heights[idx - 1];
                if (delta > 0) {
                    sum += delta;
                    pq.offer(delta);
                }
                if (sum > bricks) {
                    sum -= pq.poll();
                    ladders--;
                }
                if (ladders < 0) return idx - 1;
                if (ladders == 0 && sum > bricks) break; //写了个= 第53个用例没过
                idx++;
            }
            return idx - 1;
        }
    }

    /**
     * 先选砖头 再选梯子
     */
    static class _3rd_2 {
        static _3rd_2 handler = new _3rd_2();

        public static void main(String[] args) {

        }


        public int furthestBuilding(int[] heights, int bricks, int ladders) {
            int n = heights.length, sum = 0;
            int i = 1;
            Queue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1); //大顶堆
            while (i < n) {
                int delta = heights[i] - heights[i - 1];
                if (delta > 0) {
                    sum += delta;
                    pq.offer(delta);
                    if (sum > bricks) {
                        sum -= pq.poll();
                        ladders--;
                    }
                    if (ladders < 0) return i - 1;
                }
                i++;
            }
            return i - 1;
        }
    }


    /**
     * 小顶堆
     */
    static class _3rd_3 {
        static _3rd_3 handler = new _3rd_3();

        public static void main(String[] args) {

        }

        public int furthestBuilding(int[] heights, int bricks, int ladders) {
            int n = heights.length, sum = 0;
            int i = 1;
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            while (i < n) {
                int delta = heights[i] - heights[i - 1];
                if (delta > 0) {
                    pq.offer(delta);
                    if (pq.size() > ladders) {
                        sum += pq.poll();
                    }
                    if (sum > bricks) return i - 1;
                }
                i++;
            }
            return n - 1;
        }
    }


    /**
     * https://leetcode-cn.com/problems/kth-smallest-instructions/solution/javazu-he-shu-qiu-jie-si-lu-fen-xiang-by-hello-ang/
     */
    static class _4th_1 {

        static _4th_1 handler = new _4th_1();


        public static void main(String[] args) {

//            Math.

        }

        public String kthSmallestPath(int[] destination, int k) {

            return null;
        }

    }

}
