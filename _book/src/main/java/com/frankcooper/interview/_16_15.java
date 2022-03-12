package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _16_15 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int[] masterMind(String solution, String guess) {
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < solution.length(); i++) {
                char c = solution.charAt(i);
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            int y = 0;
            for (int i = 0; i < guess.length(); i++) {
                char c = guess.charAt(i);
                if (map.containsKey(c) && map.get(c) > 0) {
                    y++;
                    map.put(c, map.get(c) - 1);
                }
            }
            int x = 0;
            for (int i = 0; i < solution.length(); i++) {
                if (solution.charAt(i) == guess.charAt(i)) x++;
            }
            return new int[]{x, y - x};
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String solution = "RGBY", guess = "GGRR";
            handler.masterMind(solution, guess);
        }


        public int[] masterMind(String solution, String guess) {
            int x = 0, y = 0;
            int[] f = new int[26];
            for (int i = 0; i < solution.length(); i++) {
                char s = solution.charAt(i), g = guess.charAt(i);
                if (s == g) x++;
                else {
                    if (f[s - 'A'] < 0) y++;
                    f[s - 'A']++;
                    if (f[g - 'A'] > 0) y++;
                    f[g - 'A']--;

                }
            }
            return new int[]{x, y};
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
