package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.*;

import org.junit.Assert;

public class _2047 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String sentence = "alice and  bob are playing stone-game10";
//            Assert.assertEquals(5, handler.countValidWords(sentence));
            sentence = "!this  1-s b8d!";
//            Assert.assertEquals(0, handler.countValidWords(sentence));
            sentence = "!this  a-s- b8d!";
//            Assert.assertEquals(0, handler.countValidWords(sentence));
            sentence = "he bought 2 pencils, 3 erasers, and 1  pencil-sharpener.";
//            Assert.assertEquals(6, handler.countValidWords(sentence));
//            sentence = " o6 t";
//            Assert.assertEquals(1, handler.countValidWords(sentence));
            sentence = " 62   nvtk0wr4f  8 qt3r! w1ph 1l ,e0d 0n 2v 7c.  n06huu2n9 s9   ui4 nsr!d7olr  q-, vqdo!btpmtmui.bb83lf g .!v9-lg 2fyoykex uy5a 8v whvu8 .y sc5 -0n4 zo pfgju 5u 4 3x,3!wl  fv4   s  aig cf j1 a i  8m5o1  !u n!.1tz87d3 .9    n a3  .xb1p9f  b1i a j8s2 cugf l494cx1! hisceovf3 8d93 sg 4r.f1z9w   4- cb r97jo hln3s h2 o .  8dx08as7l!mcmc isa49afk i1 fk,s e !1 ln rt2vhu 4ks4zq c w  o- 6  5!.n8ten0 6mk 2k2y3e335,yj  h p3 5 -0  5g1c  tr49, ,qp9 -v p  7p4v110926wwr h x wklq u zo 16. !8  u63n0c l3 yckifu 1cgz t.i   lh w xa l,jt   hpi ng-gvtk8 9 j u9qfcd!2  kyu42v dmv.cst6i5fo rxhw4wvp2 1 okc8!  z aribcam0  cp-zp,!e x  agj-gb3 !om3934 k vnuo056h g7 t-6j! 8w8fncebuj-lq    inzqhw v39,  f e 9. 50 , ru3r  mbuab  6  wz dw79.av2xp . gbmy gc s6pi pra4fo9fwq k   j-ppy -3vpf   o k4hy3 -!..5s ,2 k5 j p38dtd   !i   b!fgj,nx qgif ";
            Assert.assertEquals(49, handler.countValidWords(sentence));
        }


        public int countValidWords(String sentence) {
            int cnt = 0;
            String[] words = sentence.split("\\s+");
            for (String word : words) {
                if (check(word)) {
                    cnt++;
//                    System.out.println(word);
                }
            }
            return cnt;
        }


        List<Character> symbols = Arrays.asList('.', '!', ',');

        private boolean check(String word) {
            if (word.length() == 0) return false;
            char[] chs = word.toCharArray();
            int c = 0;
            for (int i = 0; i < chs.length; i++) {
                if (i != chs.length - 1 && symbols.contains(chs[i])) return false;
                if (!((chs[i] >= 'a' && chs[i] <= 'z') || symbols.contains(chs[i]) || chs[i] == '\\' || chs[i] == '-')) {
                    return false;
                }
                if (chs[i] == '-') {
                    c++;
                }
            }
            if (c > 0) {
                if (c > 1) return false;
                if (c == 1) {
                    String[] arr = word.split("-");
                    for (String s : arr) {
                        boolean scf = false;
                        for (char sc : s.toCharArray()) {
                            if (sc >= 'a' && sc <= 'z') scf = true;
                        }
                        if (!scf) return false;
                    }
                }
                if (word.charAt(0) == '-' || word.charAt(word.length() - 1) == '-') return false;
            }
            return true;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
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
