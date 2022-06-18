package com.frankcooper.platform.nowcoder.swordoffer;

import java.util.*;

import com.frankcooper.struct.ListNode;
import org.junit.Assert;

public class JZ6 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public ArrayList<Integer> printListFromTailToHead(ListNode node) {
            ArrayList<Integer> res = new ArrayList<>();
            //当前节点只要不是null，就一直在循环里绕
            while (node != null) {
                //添加结果
                res.add(node.val);
                //遍历完当前节点后，将当前节点滑动到下一个节点
                node = node.next;
            }
            //翻转结果
            Collections.reverse(res);
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        ArrayList<Integer> res = new ArrayList<>();

        public ArrayList<Integer> printListFromTailToHead(ListNode node) {
            dfs(node);
            return res;
        }

        private void dfs(ListNode node) {
            if (node == null) return;
            dfs(node.next);
            res.add(node.val);
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
