package com.frankcooper.bank.bi_weekly;

import org.junit.Assert;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BiWeek80 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public boolean strongPasswordCheckerII(String password) {
            char[] ch = password.toCharArray();
            if (ch.length < 8) return false;
            List<Character> symbols = Arrays.asList('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+');
            int a = 0, A = 0, num = 0, symbol = 0;
            for (int i = 0; i < ch.length; i++) {
                if (Character.isDigit(ch[i])) num++;
                else if (Character.isLowerCase(ch[i])) a++;
                else if (Character.isUpperCase(ch[i])) A++;
                else if (symbols.contains(ch[i])) symbol++;
                if (i > 0) {
                    if (ch[i - 1] == ch[i]) return false;
                }
            }
            return a >= 1 && A >= 1 && num >= 1 && symbol >= 1;
        }

    }

    static class _1st_1 {
        public boolean strongPasswordCheckerII(String password) {
            String reg1 = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\+]).{8,}|((.)\1)";
            Matcher matcher = Pattern.compile(reg1).matcher(password);
            boolean x = matcher.find();
            String reg2 = "(.)\\1";
            matcher = Pattern.compile(reg2).matcher(password);
            boolean y = !matcher.find();
            return x && y;
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] s = {15, 8, 19};
            int[] p = {38, 36, 23};
            long success = 328;
            //[3,0,3]
            handler.successfulPairs(s, p, success);
        }

        public int[] successfulPairs(int[] spells, int[] potions, long success) {
            int n = spells.length, m = potions.length;
            Arrays.sort(potions);
            int[] pairs = new int[n];
            for (int i = 0; i < n; i++) {
                int x = spells[i];
                int l = 0, r = m - 1;
                while (l < r) {
                    int mid = l + (r - l + 1) / 2;
                    if (potions[mid] * (long) x >= success) r = mid - 1;
                    else l = mid;
                }
                pairs[i] = m - l;
                if ((long) x * potions[l] < success) {
                    pairs[i] -= 1;
                }
            }
            return pairs;
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            String s = "llllllllllllllllllllllllllllllllrllllllllllllllllllllllllllllllllrllllllllllllllllllllllllllllllllrllllllllllllllllllllllllllllllllrllllllllllllllllllllllllllllllllrllllllllllllllllllllllllllllllllrllllllllllllllllllllllllllllllllrllllllllllllllllllllllllllllllllrllllllllllllllllllllllllllllllllrlllllllllllllllllllllllllllllllll";
            String sub = "lllllllllllllllllllllllllllllllll";
            char[][] mappings = {{'l', 'a'}, {'l', 'b'}, {'l', 'c'}, {'l', 'd'}, {'l', 'e'}, {'l', 'f'}, {'l', 'g'}, {'l', 'h'}, {'l', 'i'}, {'l', 'j'}, {'l', 'k'}, {'l', 'm'}, {'l', 'n'}, {'l', 'o'}, {'l', 'p'}, {'l', 'q'}, {'l', 's'}, {'l', 't'}, {'l', 'u'}, {'l', 'v'}, {'l', 'w'}, {'l', 'x'}, {'l', 'y'}, {'l', 'z'}, {'l', '0'}, {'l', '1'}, {'l', '2'}, {'l', '3'}, {'l', '4'}, {'l', '5'}, {'l', '6'}, {'l', '7'}, {'l', '8'}, {'l', '9'}, {'r', 'a'}, {'r', 'b'}, {'r', 'c'}, {'r', 'd'}, {'r', 'e'}, {'r', 'f'}, {'r', 'g'}, {'r', 'h'}, {'r', 'i'}, {'r', 'j'}, {'r', 'k'}, {'r', 'm'}, {'r', 'n'}, {'r', 'o'}, {'r', 'p'}, {'r', 'q'}, {'r', 's'}, {'r', 't'}, {'r', 'u'}, {'r', 'v'}, {'r', 'w'}, {'r', 'x'}, {'r', 'y'}, {'r', 'z'}, {'r', '0'}, {'r', '1'}, {'r', '2'}, {'r', '3'}, {'r', '4'}, {'r', '5'}, {'r', '6'}, {'r', '7'}, {'r', '8'}, {'r', '9'}};
            Assert.assertTrue(handler.matchReplacement(s, sub, mappings));

        }


        //用list会TLE，得用set
        public boolean matchReplacement(String s, String sub, char[][] mappings) {
            int n = s.length(), m = sub.length();
            Map<Character, Set<Character>> map = new HashMap<>();
            for (char[] ma : mappings) {
                char k = ma[0], v = ma[1];
                Set<Character> set = map.getOrDefault(k, new HashSet<>());
                set.add(v);
                map.put(k, set);
            }
            //注意此处的-1
            for (int i = 0; i + m - 1 < n; i++) {
                boolean flag = true;
                for (int j = 0; j < m; j++) {
                    //对比s[i+j]与 sub[j]
                    if (s.charAt(i + j) != sub.charAt(j)) {
                        Set<Character> set = map.getOrDefault(sub.charAt(j), new HashSet<>());
                        if (!set.contains(s.charAt(i + j))) {
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag) return true;
            }
            return false;
        }


    }


    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            int nums[] = {2, 1, 4, 3, 5}, k = 10;
            Assert.assertEquals(6, handler.countSubarrays(nums, k));
            nums = new int[]{9, 5, 3, 8, 4, 7, 2, 7, 4, 5, 4, 9, 1, 4, 8, 10, 8, 10, 4, 7};
            k = 4;
            Assert.assertEquals(3, handler.countSubarrays(nums, k));
        }

        public long countSubarrays(int[] nums, long k) {
            int n = nums.length;
            long[] pre = new long[n + 1];
            for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + nums[i];
            long res = 0;
            for (int i = 0; i < n; i++) {
                int x = nums[i];
                int l = i, r = n - 1;
                while (l < r) {
                    int mid = l + (r - l + 1) / 2;
                    long sum = pre[mid + 1] - pre[i];
                    int count = (mid - i + 1);
                    if (sum * count >= k) r = mid - 1;
                    else l = mid;
                }
                res += l - i + 1;
                if (l == i && nums[l] >= k) res -= 1;
            }
            return res;
        }


    }

}
