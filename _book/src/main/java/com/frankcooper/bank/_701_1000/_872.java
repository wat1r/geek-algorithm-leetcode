package com.frankcooper.bank._701_1000;

import java.util.*;

import com.frankcooper.struct.TreeNode;

public class _872 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public boolean leafSimilar(TreeNode root1, TreeNode root2) {
            List<Integer> list1 = new ArrayList<>();
            getLeafList(root1, list1);
            List<Integer> list2 = new ArrayList<>();
            getLeafList(root2, list2);
            if(list1.size()!=list2.size()) return false;
            for (int i = 0; i < list1.size(); i++) {
                if (list1.get(i) != list2.get(i)) return false;
            }
            return true;
        }


        private void getLeafList(TreeNode root, List<Integer> list) {
            if (root == null) return;
            if (root.left == null && root.right == null) list.add(root.val);
            if (root.left == null) getLeafList(root.right, list);
            else getLeafList(root.left, list);
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
