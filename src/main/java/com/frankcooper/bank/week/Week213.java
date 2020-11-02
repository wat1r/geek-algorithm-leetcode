package com.frankcooper.bank.week;

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

}
