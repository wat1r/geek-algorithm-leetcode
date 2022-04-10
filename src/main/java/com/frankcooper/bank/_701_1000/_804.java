package com.frankcooper.bank._701_1000;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _804 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        String[] dict = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};


        public int uniqueMorseRepresentations(String[] words) {
            Set<String> set = new HashSet<>();
            for (String word : words) {
                StringBuilder sb = new StringBuilder();
                for (char c : word.toCharArray()) {
                    sb.append(dict[c - 'a']);
                }
                set.add(sb.toString());
            }
            return set.size();
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String[] words = {"gin", "zen", "gig", "msg"};
            handler.uniqueMorseRepresentations(words);
        }

        public static final int[] codes = {0b01, 0b1000, 0b1010, 0b100, 0b0, 0b0010, 0b110, 0b0000, 0b00, 0b0111, 0b101, 0b0100, 0b11, 0b10, 0b111, 0b0110, 0b1101, 0b010, 0b000, 0b1, 0b001, 0b0001, 0b011, 0b1001, 0b1011, 0b1100};

        public static final int[] lens = {2, 4, 4, 3, 1, 4, 3, 4, 2, 4, 3, 4, 2, 2, 3, 4, 4, 3, 3, 1, 3, 4, 3, 4, 4, 4};

        public int uniqueMorseRepresentations(String[] words) {
            Set<Integer> set = new HashSet<>();

            for (String word : words) {
                int num = 0;
                for (int i = 0; i < word.length(); i++) {
                    int index = word.charAt(i) - 97;
                    num = num << lens[index] | codes[index];
                }
//                num = num << 6 ^ totalLen;
                set.add(num);
            }
            return set.size();
        }


    }


    static class _3rd {

        public static final int[] codes = {0b01, 0b1000, 0b1010, 0b100, 0b0, 0b0010, 0b110, 0b0000, 0b00, 0b0111, 0b101, 0b0100, 0b11, 0b10, 0b111, 0b0110, 0b1101, 0b010, 0b000, 0b1, 0b001, 0b0001, 0b011, 0b1001, 0b1011, 0b1100};

        public static void main(String[] args) {
            _3rd handler = new _3rd();
            String[] words = {"gin", "zen", "gig", "msg"};
            handler.uniqueMorseRepresentations(words);
        }

        int[] bin = new int[26];
        String[] dict = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

        public int uniqueMorseRepresentations(String[] words) {
            //制作二进制的映射关系
            for (int i = 0; i < dict.length; i++) {
                encode(dict[i], i);
            }
            Set<Integer> set = new HashSet<>();
            for (String word : words) {
                int x = 0;
                for (int i = 0; i < word.length(); i++) {
                    //拿到word中当前字符的索引
                    int idx = word.charAt(i) - 'a';
                    //先将x向左移位len(dict[idx]) 将该字符对应的莫斯密码位的数拼接到x上
                    x = x << dict[idx].length() | bin[idx];
                }
                System.out.printf("%s->%4d->%10s", word, x, PrintUtils.toBinaryString(x, 10));
                set.add(x);
            }
            return set.size();
        }

        //将形如 -... 的莫斯密码 翻译成 二进制 -表示1 .表示0
        //-... -> 1000(2) -> 16(10)
        public int encode(String s, int idx) {
            int x = 0;
            int n = s.length();
            for (int i = n - 1; i >= 0; i--) {
                if (s.charAt(i) == '-') {
                    x |= (1 << (n - 1 - i));
                }
            }
            this.bin[idx] = x;
            return x;
        }


    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
