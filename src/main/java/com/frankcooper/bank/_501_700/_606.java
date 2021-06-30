package com.frankcooper.bank._501_700;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _606 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public String tree2str(TreeNode t) {
            if (t == null) return "";//当前节点为空，返回""
            if (t.left == null && t.right == null) return t.val + "";//当前节点没有左右孩子节点，即叶子节点，返回这个值
            if (t.right == null) return t.val + "(" + tree2str(t.left) + ")";//当前节点只有左孩子，没有右孩子，给左孩子加上"()",右孩子不加
            return t.val + "(" + tree2str(t.left) + ")" + "(" + tree2str(t.right) + ")";//左孩子有或者没有都加 "()" || "(leftChild)"
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
