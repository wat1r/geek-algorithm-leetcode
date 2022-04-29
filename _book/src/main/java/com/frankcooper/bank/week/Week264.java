package com.frankcooper.bank.week;

import java.util.*;

import org.junit.Assert;

public class Week264 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String sentence = " o6 t";
//            Assert.assertEquals(1, handler.countValidWords(sentence));
            sentence = " 62   nvtk0wr4f  8 qt3r! w1ph 1l ,e0d 0n 2v 7c.  n06huu2n9 s9   ui4 nsr!d7olr  q-, vqdo!btpmtmui.bb83lf g .!v9-lg 2fyoykex uy5a 8v whvu8 .y sc5 -0n4 zo pfgju 5u 4 3x,3!wl  fv4   s  aig cf j1 a i  8m5o1  !u n!.1tz87d3 .9    n a3  .xb1p9f  b1i a j8s2 cugf l494cx1! hisceovf3 8d93 sg 4r.f1z9w   4- cb r97jo hln3s h2 o .  8dx08as7l!mcmc isa49afk i1 fk,s e !1 ln rt2vhu 4ks4zq c w  o- 6  5!.n8ten0 6mk 2k2y3e335,yj  h p3 5 -0  5g1c  tr49, ,qp9 -v p  7p4v110926wwr h x wklq u zo 16. !8  u63n0c l3 yckifu 1cgz t.i   lh w xa l,jt   hpi ng-gvtk8 9 j u9qfcd!2  kyu42v dmv.cst6i5fo rxhw4wvp2 1 okc8!  z aribcam0  cp-zp,!e x  agj-gb3 !om3934 k vnuo056h g7 t-6j! 8w8fncebuj-lq    inzqhw v39,  f e 9. 50 , ru3r  mbuab  6  wz dw79.av2xp . gbmy gc s6pi pra4fo9fwq k   j-ppy -3vpf   o k4hy3 -!..5s ,2 k5 j p38dtd   !i   b!fgj,nx qgif ";
//            Assert.assertEquals(49, handler.countValidWords(sentence));
            sentence = "q-o  x-p! g-l- q-n  f-o, m-u. m-i! y-k, i-j, d-p! e-t, h-u  j-j- d-z- v-w, r-a  i-h. d-a! z-o, v-l, ";
            Assert.assertEquals(17, handler.countValidWords(sentence));

        }


        public int countValidWords(String sentence) {
            String[] words = sentence.split("\\s+");
            int res = 0;
            for (String word : words) {
                if (check(word)) {
//                    System.out.println(word);
                    res++;
                }
            }
            return res;
        }


        //
        private boolean check(String word) {
            if (word.equals("")) return false;
            List<Character> symbols = Arrays.asList('!', '.', ',');
            int n = word.length();
            int a = 0;//'-'的个数
            int b = 0;//symbol的个数
            for (int i = 0; i < n; i++) {
                char c = word.charAt(i);
                if (Character.isDigit(c)) return false;
                if ((i == 0 || i == n - 1) && c == '-') return false;
                if (c == '-') a++;
                if (a > 1) return false;
                if (c == '-' && !(isLowerLetter(word.charAt(i - 1)) && isLowerLetter(word.charAt(i + 1)))) return false;
                if (symbols.contains(c)) b++;
                if (b > 1) return false;
                if (b == 1 && symbols.contains(c) && i != n - 1) return false;
            }
            return true;
        }

        private boolean isLowerLetter(char c) {
            return c >= 'a' && c <= 'z';
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int n = 1;
            System.out.println(handler.nextBeautifulNumber(n));
        }

        public int nextBeautifulNumber(int n) {
            for (int x = n + 1; n <= 1000005; x++) {
                if (check(x)) return x;
            }
            return -1;
        }


        private boolean check(int x) {
            int[] cnt = new int[10];
            while (x > 0) {
                int t = x % 10;
                cnt[t]++;
                x /= 10;
            }
            for (int i = 0; i < 10; i++) {
                if (cnt[i] != 0 && i != cnt[i]) return false;
            }
            return true;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        int n;
        boolean[] vis;

        public int countHighestScoreNodes(int[] parents) {
            n = parents.length;
            vis = new boolean[n];


            return 0;
        }


        public void dfs(int[] parent, int cur) {
            if (vis[cur]) {
                return;
            }
            vis[cur] = true;
            dfs(parent, parent[cur]);
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
