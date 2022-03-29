package com.frankcooper.bank._101_200;

import java.util.*;

import com.alibaba.fastjson.JSON;
import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _124 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        int res = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            dfs(root);
            return res;
        }

        public int dfs(TreeNode root) {
            if (root == null) return 0;
            int lval = Math.max(dfs(root.left), 0);
            int rval = Math.max(dfs(root.right), 0);
            res = Math.max(res, root.val + lval + rval);
            return root.val + Math.max(lval, rval);
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        int res = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            dfs(root);
            return res;
        }

        private int dfs(TreeNode root) {
            if (root == null) return 0;
            int lval = Math.max(dfs(root.left), 0);
            int rval = Math.max(dfs(root.right), 0);
            res = Math.max(res, root.val + lval + rval);
            return root.val + Math.max(lval, rval);
        }
    }


    static class _3rd {
        /*
        https://leetcode.com/problems/binary-tree-maximum-path-sum/discuss/603423/Python-Recursion-stack-thinking-process-diagram
         */
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        int maxPath = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            dfs(root);
            return maxPath;
        }

        private int dfs(TreeNode root) {
            if (root == null) return 0;
            int leftGain = Math.max(dfs(root.left), 0);
            int rightGain = Math.max(dfs(root.right), 0);
            int currMaxPath = root.val + leftGain + rightGain;
            maxPath = Math.max(maxPath, currMaxPath);
            return root.val + Math.max(leftGain, rightGain);
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            TreeNode root = TreeNodeIOUtils.transform("[-100,9,20,16,17,15,7]");
             root = TreeNodeIOUtils.transform("[1,2,null,3,null,4,null,5]");

//            handler.maxPathSum(root);
        }


        int maxPath = Integer.MIN_VALUE;
        List<List<Integer>> resPath = new ArrayList<>();

        public int maxPathSum(TreeNode root) {
            dfs(root);
            System.out.println(JSON.toJSONString(resPath));
            return maxPath;
        }

        private Pair dfs(TreeNode root) {
            if (root == null) return new Pair(0, new ArrayList<>());
            Pair lp = dfs(root.left);
            Pair rp = dfs(root.right);
            int res = root.val;
            List<Integer> subPath = new ArrayList<>();
            if (lp.sum > 0 && lp.sum > rp.sum) {
                res += lp.sum;
                subPath.addAll(lp.path);
                subPath.add(root.val);
            } else if (rp.sum > 0 && rp.sum > lp.sum) {
                res += rp.sum;
                subPath.addAll(rp.path);
                subPath.add(root.val);
            } else {
                subPath.add(root.val);
            }
            if (res >= maxPath) {
                if (res > maxPath) resPath.clear();
                maxPath = res;
                resPath.add(new ArrayList<>(subPath));
            }
            if (lp.sum + rp.sum + root.val >= maxPath) {
                List<Integer> t = new ArrayList<>();
                t.addAll(lp.path);
                t.add(root.val);
                t.addAll(rp.path);
                if (lp.sum + rp.sum + root.val > maxPath) resPath.clear();
                maxPath = lp.sum + rp.sum + root.val;
                resPath.add(new ArrayList<>(t));
            }
            return new Pair(res, subPath);
        }


        static class Pair {
            int sum = 0;
            List<Integer> path;

            public Pair(int sum, List<Integer> path) {
                this.sum = sum;
                this.path = path;
            }
        }

    }


    static class _5th {

        public static void main(String[] args) {
            _5th handler = new _5th();
            TreeNode root = TreeNodeIOUtils.transform("[-10,9,20,null,null,15,7]");
            handler.maxPathSum(root);
        }

        private int ans = Integer.MIN_VALUE;
        private List<Integer> path = new ArrayList<>();

        public int maxPathSum(TreeNode root) {
            dfs(root);
            System.out.println(path);
            return ans;
        }

        private Pair dfs(TreeNode root) {
            if (root == null) return new Pair(0, new ArrayList<>());

            Pair l = dfs(root.left);
            Pair r = dfs(root.right);
            int res = root.val;
            List<Integer> path = new ArrayList<>();
            if (l.sum > 0 && l.sum > r.sum) {
                res += l.sum;
                path.addAll(l.path);
                path.add(root.val);
            } else if (r.sum > 0 && r.sum > l.sum) {
                res += r.sum;
                path.add(root.val);
                path.addAll(r.path);
            } else {
                path.add(root.val);
            }

            if (res > ans) {
                this.ans = res;
                this.path = path;
            }

            if (l.sum + r.sum + root.val > ans) {
                this.ans = l.sum + r.sum + root.val;
                List<Integer> tmp = new ArrayList<>();
                tmp.addAll(l.path);
                tmp.add(root.val);
                tmp.addAll(r.path);
                this.path = tmp;
            }

            return new Pair(res, path);
        }

        static class Pair {
            int sum;
            List<Integer> path;

            public Pair(int sum, List<Integer> path) {
                this.sum = sum;
                this.path = path;
            }
        }

    }
}
