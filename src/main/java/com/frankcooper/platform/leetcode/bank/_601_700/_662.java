package com.frankcooper.platform.leetcode.bank._601_700;

import java.util.*;

import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _662 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            TreeNode root = TreeNodeIOUtils.transform("[1,3,2,5,null,null,9,6,null,7]");
            Assert.assertEquals(7, handler.widthOfBinaryTree(root));

        }


        public int widthOfBinaryTree(TreeNode root) {
            if (root == null) return 0;
            LinkedList<TreeNode> q = new LinkedList<>();
            q.offer(root);
            int res = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                res = Math.max(res, q.getLast().val - q.getFirst().val + 1);
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    if (cur.left != null) {
                        cur.left.val = 2 * cur.val + 1;//存下来当前父节点的左孩子节点的下标索引
                        q.offer(cur.left);
                    }
                    if (cur.right != null) {
                        cur.right.val = 2 * cur.val + 2;//存下来当前父节点的右孩子节点的下标索引
                        q.offer(cur.right);
                    }
                }
                TreeNodeIOUtils.prettyPrintTree(root);
            }
            TreeNodeIOUtils.prettyPrintTree(root);
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        Map<Integer,Integer> depthMap = new HashMap<>();


        public int widthOfBinaryTree(TreeNode root) {
            return dfs(root,1,1);
        }


        public int dfs(TreeNode root,int depth ,int index ){
            if(root == null){
                return 0;
            }
            depthMap.putIfAbsent(depth,index);
            int ld = dfs(root.left,depth+1, index *2 +1);
            int rd = dfs(root.right,depth+1, index *2 +2);
            return Math.max(index-depthMap.get(depth)+1,Math.max(ld,rd));
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
