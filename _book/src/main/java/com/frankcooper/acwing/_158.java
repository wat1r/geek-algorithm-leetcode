package com.frankcooper.acwing;

import java.util.*;

import org.junit.Assert;

public class _158 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        static class Main {

            static Main main = new Main();

            public static void main(String[] args) {
                main.process();
            }


            private void process() {
                Scanner in = new Scanner(System.in);
                String a = in.next(), b = in.next();
                String aa = getMin(a), bb = getMin(b);
                if (aa.equals(bb)) {
                    System.out.println("Yes");
                    System.out.println(aa);
                } else {
                    System.out.println("No");
                }

            }

            public String getMin(String s) {
                int n = s.length();
                s = s + s;
                char[] ch = s.toCharArray();
                int i = 0, j = 1;
                while (i < n && j < n) {
                    int k = 0;//while的写法把k放在内侧
                    while (k < n && ch[i + k] == ch[j + k]) k++;
                    if (ch[i + k] > ch[j + k]) {
                        i += k + 1;
                    } else {
                        j += k + 1;
                    }
                    if (i == j) j++;
                }
                int t = Math.min(i, j);
                return s.substring(t, t + n);
            }
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
