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


    static class _2nd {

        public static void main(String[] args) {

        }

        List<String> seed = Arrays.asList("a", "e", "i", "o", "u");

        public int countVowelStrings(int n) {

            return dfs(n);
        }




    }


}
