package com.frankcooper.atcoder.abc;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import org.junit.Assert;

//https://atcoder.jp/contests/abc244/
public class abc244 {


//    import java.util.Scanner;//导入Scanner类

    public class Main {
//        public static void main(String[] args) {
//            Scanner in = new Scanner(System.in);//生成Scanner对象
//            while (in.hasNextInt()) {
//                int N = in.nextInt(); //读下一个整型字符串
//                String S = in.next();
//
//            }
//            in.close(); //用完后关闭扫描器是一个好的习惯
//         System.exit(0);
//        }
    }


    static class A {
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);//生成Scanner对象
            while (in.hasNextInt()) {
                int N = in.nextInt();
                String S = in.next();
                System.out.println(S.charAt(N - 1));
            }


        }


    }

    static class B {
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);//生成Scanner对象
            while (in.hasNextInt()) {
                int N = in.nextInt(); //读下一个整型字符串
                String S = in.next();
                process(N, S);
            }
            in.close(); //用完后关闭扫描器是一个好的习惯
        }

        private static void process(int N, String S) {
            //右下左上
            int[][] dirs = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
            int d = 0;
            int x = 0, y = 0;
            for (int i = 0; i < S.length(); i++) {
                if (S.charAt(i) == 'S') {
                    x += dirs[d % 4][0];
                    y += dirs[d % 4][1];
                } else if (S.charAt(i) == 'R') {
                    d++;
                    d %= 4;
                }
            }
            System.out.printf("%d %d", x, y);
        }

    }


    static class C {
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);//生成Scanner对象
            int N = in.nextInt(); //读下一个整型字符串
            TreeSet<Integer> ts = new TreeSet<>();
            for (int i = 1; i <= 2 * N + 1; i++) {
                ts.add(i);
            }
            int aoki = 0;
            while (true) {
                System.out.println(ts.pollFirst().intValue());
                aoki = in.nextInt();
                if (aoki == 0) break;
                ts.remove(aoki);
            }
            in.close(); //用完后关闭扫描器是一个好的习惯
            System.exit(0);
        }


    }

    static class D {
        public static void main(String[] args) {
            com.frankcooper.atcoder.abc.Main.FastReader fr = new com.frankcooper.atcoder.abc.Main.FastReader();
            String[] S = new String[3];
            for (int i = 0; i < 3; i++) S[i] = fr.next();
            String[] T = new String[3];
            for (int i = 0; i < 3; i++) T[i] = fr.next();
            Set<String[]> set = new HashSet<String[]>() {{
                add(new String[]{S[0], S[1], S[2]});
                add(new String[]{S[1], S[2], S[0]});
                add(new String[]{S[2], S[0], S[1]});
            }};
            for (String[] s : set) {
                if (Arrays.equals(s, T)) {
                    System.out.println("Yes");
                    return;
                }
            }
            System.out.println("No");
        }
    }

    static class E {
    }

    static class F {
    }

    static class G {
    }

    static class Ex {
    }
}
