package com.frankcooper.bank._101_200;

/*import java.util.*;
import org.junit.Assert;*/
public class _172 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int trailingZeroes(int n) {
            int ans = 0;
            for (int i = 0; i <= n; i++) {
                int t = i;
                while (t > 0) {
                    if (t % 5 == 0) {
                        t /= 5;
                        ans++;
                    } else break;
                }
            }
            return ans;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int trailingZeroes(int n) {
            int res = 0;
            while (n > 4) {
                n /= 5;
                res += n;
            }
            return res;
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
