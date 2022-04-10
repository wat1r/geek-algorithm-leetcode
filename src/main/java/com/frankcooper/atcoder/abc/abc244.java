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
        static int MOD = 998244353;

        public static void main(String[] args) {
            com.frankcooper.atcoder.abc.Main.FastReader fr = new com.frankcooper.atcoder.abc.Main.FastReader();
            String[] arr = fr.nextLine().split("\\s+");
            int N = Integer.parseInt(arr[0]);
            int M = Integer.parseInt(arr[1]);
            int K = Integer.parseInt(arr[2]);
            int S = Integer.parseInt(arr[3]);
            int T = Integer.parseInt(arr[4]);
            int X = Integer.parseInt(arr[5]);
//        System.out.printf("N=%d M=%d K=%d S=%d T=%d X=%d\n", N, M, K, S, T, X);
            int[][] uvs = new int[M][2];
            for (int i = 0; i < M; i++) {
                arr = fr.nextLine().split("\\s+");
                int u = Integer.parseInt(arr[0]), v = Integer.parseInt(arr[1]);
//            System.out.printf("%d %d\n", u, v);
                uvs[i][0] = u;
                uvs[i][1] = v;

            }
            System.out.println(process(N, M, K, S, T, X, uvs));
        }


        public static long process(int N, int M, int K, int S, int T, int X, int[][] uvs) {
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
            for (int[] uv : uvs) {
                int u = uv[0], v = uv[1];
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
            long[][] dp = new long[N + 1][2];
            dp[S][0] = 1;
            for (int i = 0; i < K; i++) {
                long[][] nextDp = new long[N + 1][2];
                for (int src = 1; src <= N; src++) {
                    for (int j = 0; j < 2; j++) {
                        for (int dest : graph.get(src)) {
                            int nextJ = dest == X ? 1 - j : j;
                            nextDp[dest][nextJ] += dp[src][j];
                            nextDp[dest][nextJ] %= MOD;
                        }
                    }
                }
                dp = nextDp;
            }
            return dp[T][0];
        }

    }


    static class E_1 {
        //https://atcoder.jp/contests/abc244/editorial/3619
        static int MOD = 998244353;

        public static void main(String[] args) {
            com.frankcooper.atcoder.abc.Main.FastReader fr = new com.frankcooper.atcoder.abc.Main.FastReader();
            String[] arr = fr.nextLine().split("\\s+");
            int N = Integer.parseInt(arr[0]);
            int M = Integer.parseInt(arr[1]);
            int K = Integer.parseInt(arr[2]);
            int S = Integer.parseInt(arr[3]);
            int T = Integer.parseInt(arr[4]);
            int X = Integer.parseInt(arr[5]);
            S--;
            T--;
            X--;
//        System.out.printf("N=%d M=%d K=%d S=%d T=%d X=%d\n", N, M, K, S, T, X);
            int[][] uvs = new int[M][2];
            for (int i = 0; i < M; i++) {
                arr = fr.nextLine().split("\\s+");
                int u = Integer.parseInt(arr[0]), v = Integer.parseInt(arr[1]);
//            System.out.printf("%d %d\n", u, v);
                u--;
                v--;
                //从0开始
                uvs[i][0] = u;
                uvs[i][1] = v;

            }
            System.out.println(process(N, M, K, S, T, X, uvs));
        }


        /*
            dp[0][j][1] =0
         */
        public static long process(int N, int M, int K, int S, int T, int X, int[][] uvs) {
            //dp[i][j][x] 从点S到点j使用i条边，访问X点的次数mod2的情况下的路径数量
            long[][][] dp = new long[K + 1][N][2];
            dp[0][S][0] = 1;
            for (int i = 0; i < K; i++) {
                for (int[] uv : uvs) {
                    int U = uv[0], V = uv[1];
                    for (int x = 0; x < 2; x++) {
                        int y = V == X ? 1 : 0;
                        int z = U == X ? 1 : 0;
                        dp[i + 1][V][x ^ y] += dp[i][U][x] % MOD;
                        dp[i + 1][U][x ^ z] += dp[i][V][x] % MOD;
                    }
                }
            }
            return dp[K][T][0] % MOD;
        }
    }

    static class E_a {
        //https://atcoder.jp/contests/abc242/editorial/3525
        static int MOD = 998244353;

        public static void main(String[] args) {
            com.frankcooper.atcoder.abc.Main.FastReader fr = new com.frankcooper.atcoder.abc.Main.FastReader();
            int N = fr.nextInt();
            process(N);
        }


        private static void process(int N) {
            int K = 10;
            //f dp[n][k] 是 f(k???…???)的值，n表示当前的数字的位数，k表示的是首位的数字
            long[][] dp = new long[N + 1][K];
            for (int k = 1; k <= 9; k++) dp[1][k] = 1;
            for (int i = 2; i <= N; i++) {
                for (int k = 1; k <= 9; k++) {
                    //k是[i-1]的 [k-1,k,k+1] 是[i]的三个数字
                    for (int j = Math.max(1, k - 1); j <= Math.min(9, k + 1); j++) {
//                    System.out.printf("j=%d,k=%d\n", j, k);
                        dp[i][j] += dp[i - 1][k];
                        System.out.printf(" dp[%d][%d] <-dp[%d][%d] \n", i, j, i - 1, k);
                        dp[i][j] %= MOD;
                    }
                    /* 观察   dp[4][7]  也就是 f(7???)=f(6??)+f(7??)+f(8??).
                     dp[4][6] <-dp[3][6]
                     dp[4][7] <-dp[3][6]
                     dp[4][6] <-dp[3][7]
                     dp[4][7] <-dp[3][7]
                     dp[4][8] <-dp[3][7]
                     dp[4][7] <-dp[3][8]
                     */
                }
            }
            int res = 0;
            for (int k = 1; k <= 9; k++) {
                res += dp[N][k];
                res %= MOD;
            }
            System.out.println(res);
        }

    }

    static class F {
    }

    static class G {
    }

    static class Ex {
    }
}
