package com.frankcooper.bank._101_200;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _173 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        class BSTIterator {
            Stack<TreeNode> stk = new Stack<>();
            TreeNode cur;

            public BSTIterator(TreeNode root) {
                cur = root;
            }

            public int next() {
                int res = -1;
                while (cur != null || !stk.isEmpty()) {
                    while (cur != null) {
                        stk.push(cur);
                        cur = cur.left;
                    }
                    cur = stk.pop();
                    res = cur.val;
                    cur = cur.right;
                }
                return res;
            }

            public boolean hasNext() {
                return cur != null || !stk.isEmpty();
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
