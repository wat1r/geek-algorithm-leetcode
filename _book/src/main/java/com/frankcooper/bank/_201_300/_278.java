package com.frankcooper.bank._201_300;

/*import java.util.*;
import org.junit.Assert;*/
public class _278 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int firstBadVersion1(int n) {
            int l = 1, r = n;
            while (l < r) {
                int mid = l + (r - l) / 2;
                System.out.printf("%d ", mid);
                if (isBadVersion(mid)) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        }

        public int firstBadVersion(int n) {
            int l = 1, r = n;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (isBadVersion(mid)) {
                    r = mid;
                } else {
                    l = mid;
                }
            }
            return l;
        }

        private boolean isBadVersion(int mid) {
            return false;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int firstBadVersion(int n) {
            int l = 1, r = n;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (isBadVersion(mid)) r = mid;
                else l = mid + 1;
            }
            return l;
        }

        private boolean isBadVersion(int mid) {
            return false;
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
