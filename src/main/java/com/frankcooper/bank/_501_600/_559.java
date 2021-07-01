package com.frankcooper.bank._501_600;

import com.frankcooper.struct.Node;

public class _559 {

    static class _1st {
        public int maxDepth(Node root) {
            if (root == null) return 0;
            int depth = 0;
            for (Node node : root.children) depth = Math.max(depth, maxDepth(node));
            return depth + 1;

        }
    }
}
