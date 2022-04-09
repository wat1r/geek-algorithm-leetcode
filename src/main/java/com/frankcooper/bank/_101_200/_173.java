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
            Stack<TreeNode> stack = new Stack<>();
            TreeNode cur = null;


            public BSTIterator(TreeNode root) {
                cur = root;
            }

            /**
             * @return the next smallest number
             */
            public int next() {
                int res = -1;
                while (cur != null || !stack.isEmpty()) {
                    while (cur != null) {
                        stack.push(cur);
                        cur = cur.left;
                    }
                    cur = stack.pop();
                    res = cur.val;
                    cur = cur.right;
                    break;
                }
                return res;


            }

            /**
             * @return whether we have a next smallest number
             */
            public boolean hasNext() {
                return (cur != null || !stack.isEmpty());
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
