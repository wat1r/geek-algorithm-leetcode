package com.frankcooper.bank._501_600;

import com.frankcooper.struct.Node;

import java.util.ArrayList;
import java.util.List;

public class _590 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        List<Integer> res = new ArrayList<>();

        public List<Integer> postorder(Node root) {
            dfs(root);
            return res;
        }

        private void dfs(Node root) {
            if (root == null) {
                return;
            }
            //  遍历root的所有的children
            for (Node child : root.children) {
                dfs(child);
            }
            // 后序遍历开始添加
            res.add(root.val);
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
