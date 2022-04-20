package com.frankcooper.bank._701_1000;

import java.util.*;

import org.junit.Assert;

public class _824 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String sentence = "Each word consists of lowercase and uppercase letters only";
            handler.toGoatLatin(sentence);

        }

        public String toGoatLatin(String sentence) {
            List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
            StringBuilder res = new StringBuilder();
            String[] words = sentence.split(" ");
            int idx = 1;
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                System.out.printf("%s\n", word.charAt(0));
                if (vowels.contains(word.charAt(0))) res.append(word);
                else res.append(word.substring(1)).append(word.charAt(0));
                res.append("ma");
                int t = idx;
                while (t-- > 0) res.append("a");
                idx++;
                if (i != words.length - 1) res.append(" ");
            }
            return res.toString();

        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String sentence = "I speak Goat Latin";
            handler.toGoatLatin(sentence);
        }


        public String toGoatLatin(String sentence) {
            Set<Character> vowels = new HashSet<Character>() {{
                add('a');
                add('e');
                add('i');
                add('o');
                add('u');
                add('A');
                add('E');
                add('I');
                add('O');
                add('U');
            }};

            int n = sentence.length();
            int i = 0, cnt = 1;
            StringBuffer ans = new StringBuffer();

            while (i < n) {
                int j = i;
                while (j < n && sentence.charAt(j) != ' ') {
                    ++j;
                }

                ++cnt;
                if (cnt != 2) {
                    ans.append(' ');
                }
                if (vowels.contains(sentence.charAt(i))) {
                    ans.append(sentence.substring(i, j));
                } else {
                    ans.append(sentence.substring(i + 1, j));
                    ans.append(sentence.charAt(i));
                }
                ans.append('m');
                for (int k = 0; k < cnt; ++k) {
                    ans.append('a');
                }

                i = j + 1;
            }

            return ans.toString();
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
