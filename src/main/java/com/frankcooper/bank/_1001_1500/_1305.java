package com.frankcooper.bank._1001_1500;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _1305 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
            List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>(), res = new ArrayList<>();
            inOrder(root1, list1);
            inOrder(root2, list2);
            int p1 = 0, p2 = 0;
            while (true) {
                if (p1 == list1.size()) {
                    res.addAll(list2.subList(p2, list2.size()));
                    break;
                }
                if (p2 == list2.size()) {
                    res.addAll(list1.subList(p1, list1.size()));
                    break;
                }
                if (list1.get(p1) <= list2.get(p2)) {
                    res.add(list1.get(p1++));
                } else {
                    res.add(list2.get(p2++));
                }
            }
            return res;
        }


        private void inOrder(TreeNode root, List<Integer> list) {
            if (root != null) {
                inOrder(root.left, list);
                list.add(root.val);
                inOrder(root.right, list);
            }
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
            List<Integer> res = new ArrayList<>();
            Deque<TreeNode> stk1 = new ArrayDeque<>(), stk2 = new ArrayDeque<>();
            TreeNode cur1 = root1, cur2 = root2;
            while (cur1 != null || cur2 != null || !stk1.isEmpty() || !stk2.isEmpty()) {
                while (cur1 != null) {
                    stk1.push(cur1);
                    cur1 = cur1.left;
                }
                while (cur2 != null) {
                    stk2.push(cur2);
                    cur2 = cur2.left;
                }
                //注意当stk2是空的时候，将stk1的元素全部pop出来即可
                if (stk2.isEmpty() || !stk1.isEmpty() && stk1.peek().val < stk2.peek().val) {
                    cur1 = stk1.pop();
                    res.add(cur1.val);
                    cur1 = cur1.right;
                } else {
                    cur2 = stk2.pop();
                    res.add(cur2.val);
                    cur2 = cur2.right;
                }
            }
            return res;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
            List<Integer> res = new ArrayList<>();
            Deque<TreeNode> stk1 = new ArrayDeque<>(), stk2 = new ArrayDeque<>();
            pushLeft(stk1, root1);
            pushLeft(stk2, root2);
            while (!stk1.isEmpty() || !stk2.isEmpty()) {
                Deque<TreeNode> stk;
                if (stk1.isEmpty()) stk = stk2;
                else if (stk2.isEmpty()) stk = stk1;
                else stk = stk1.peek().val < stk2.peek().val ? stk1 : stk2;
                TreeNode cur = stk.pop();
                res.add(cur.val);
                pushLeft(stk, cur.right);
            }
            return res;
        }

        private void pushLeft(Deque<TreeNode> stk, TreeNode root) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
            List<Integer> res = new ArrayList<>();
            Deque<TreeNode> stk1 = new ArrayDeque<>(), stk2 = new ArrayDeque<>();
            pushLeft(stk1, root1);
            pushLeft(stk2, root2);
            while (!stk1.isEmpty() && !stk2.isEmpty()) {
                if (stk1.peek().val < stk2.peek().val) {
                    res.add(next(stk1));
                } else {
                    res.add(next(stk2));
                }
            }
            while (!stk1.isEmpty()) res.add(next(stk1));
            while (!stk2.isEmpty()) res.add(next(stk2));
            return res;
        }


        private void pushLeft(Deque<TreeNode> stk, TreeNode root) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
        }

        //先存右节点，再一直压左子树节点
        private int next(Deque<TreeNode> stk) {
            TreeNode cur = stk.pop();
            int res = cur.val;
            pushLeft(stk, cur.right);
            return res;
        }

    }
}
