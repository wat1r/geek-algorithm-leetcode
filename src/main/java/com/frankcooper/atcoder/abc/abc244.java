package com.frankcooper.atcoder.abc;


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
            C handler = new C();
        }
    }

    static class D {
        public static void main(String[] args) {
            D handler = new D();
        }
    }
}
