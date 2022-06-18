package com.frankcooper.platform.leetcode.bank._2001_3000;

/*import java.util.*;
import org.junit.Assert;*/
public class _2043 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        class Bank {

            long[] balance;
            int capacity;

            public Bank(long[] balance) {
                this.balance = new long[balance.length + 1];
                this.capacity = balance.length + 1;
                for (int i = 0; i < balance.length; i++) {
                    this.balance[i + 1] = balance[i];
                }
            }

            public boolean transfer(int account1, int account2, long money) {
                if (account1 > capacity || account2 > capacity) return false;
                if (this.balance[account1] < money) return false;
                this.balance[account1] -= money;
                this.balance[account2] += money;
                return true;
            }

            public boolean deposit(int account, long money) {
                if (account > capacity) return false;
                this.balance[account] += money;
                return true;
            }

            public boolean withdraw(int account, long money) {
                if (account > capacity) return false;
                if (this.balance[account] < money) return false;
                this.balance[account] -= money;
                return true;
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
