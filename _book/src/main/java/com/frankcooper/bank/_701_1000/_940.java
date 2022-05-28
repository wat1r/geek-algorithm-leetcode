package com.frankcooper.bank._901_1000;

import java.util.Arrays;

/*import java.util.*;
import org.junit.Assert;*/
public class _940 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "zchmliaqdgvwncfatcfivphddpzjkgyyerrr";
//            1st-> f[35]=   334116041,   f[last[17]-1]=   333529012
//            2nd-> f[35]=      587029,   f[last[17]-1]=   333529012
//            1st-> f[36]=     1174058,   f[last[17]-1]=   667058024
//            2nd-> f[36]=  -665883966,   f[last[17]-1]=   667058024
            handler.distinctSubseqII(s);

        }


        //f[i]表示s[0...i]之间的字符组成的不同子序列的数量
        public int distinctSubseqII(String s) {
            int MOD = (int) 1e9 + 7;
            int N = s.length();
            int[] f = new int[N + 1];
            f[0] = 1;
            int[] last = new int[26];
            Arrays.fill(last, -1);
            for (int i = 1; i <= N; i++) {
                int x = s.charAt(i - 1) - 'a';
                f[i] = 2 * f[i - 1] % MOD;//前一个需序列的每一个末尾追加一个当前字符
                if (last[x] >= 0) {
                    System.out.printf("1st-> f[%d]=%12d,   f[last[%d]-1]=%12d\n", i, f[i], x, f[last[x] - 1]);
                    f[i] -= f[last[x] - 1];//有重复的字符需要移除到上一个出现该字符时的数量
                    System.out.printf("2nd-> f[%d]=%12d,   f[last[%d]-1]=%12d\n", i, f[i], x, f[last[x] - 1]);
                }
                f[i] %= MOD;
                last[x] = i;
            }
            f[N]--;
            if (f[N] < 0) f[N] += MOD;
            return f[N];
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
