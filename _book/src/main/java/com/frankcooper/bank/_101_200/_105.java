package com.frankcooper.platform.leetcode.bank._101_200;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _105 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        Map<Integer, Integer> rootMap = new HashMap<>();

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder == null || inorder == null || preorder.length != inorder.length) {
                return null;
            }
            for (int i = 0; i < inorder.length; i++) rootMap.put(inorder[i], i);

            return buildTreeDFS(preorder, 0, preorder.length - 1
                    , inorder, 0, inorder.length);
        }


        public TreeNode buildTreeDFS(int[] preorder, int preStart, int preEnd,
                                     int[] inorder, int inStart, int inEnd) {
            if (preStart > preEnd) return null;
            TreeNode root = new TreeNode(preorder[preStart]);
            int mid = rootMap.get(preorder[preStart]);
            if (mid < 0) return null;
            root.left = buildTreeDFS(preorder, preStart + 1, preStart + (mid - inStart)
                    , inorder, inStart, mid - 1);
            root.right = buildTreeDFS(preorder, preStart + (mid - inStart) + 1, preEnd
                    , inorder, mid + 1, inEnd);
            return root;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        //二叉树问题 通过先序和中序数组生成后序数组...
        public static int[] getPosArray(int[] pre, int[] in) {
            if (pre == null || in == null) {
                return null;
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < in.length; i++) {
                map.put(in[i], i);
            }
            int[] pos = new int[in.length];
            preAndIn(pre, 0, pre.length - 1, in, 0, in.length - 1, pos, pos.length - 1, map);
            return pos;
        }

        /**
         * @param pre      前序数组
         * @param preStart 前序数组的开始节点下标
         * @param preEnd   前序节点的结束节点下标
         * @param in       中序数组
         * @param inStart  中序数组的开始节点下标
         * @param inEnd    中序节点的结束节点下标
         * @param pos
         * @param posEnd
         * @param map
         * @return
         */
        public static int preAndIn(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd, int[] pos, int posEnd, Map<Integer, Integer> map) {
            if (preStart > preEnd) {
                return posEnd;
            }
            //每一步按root节点进行切分
            int value = pre[preStart];
            pos[posEnd--] = value;
            int index = map.get(value);
            posEnd = preAndIn(pre, preStart + index - inStart + 1, preEnd, in, index + 1, inEnd, pos, posEnd, map);
            posEnd = preAndIn(pre, preStart + 1, preStart + index - inStart, in, inStart, index - 1, pos, posEnd, map);
            return posEnd;
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
