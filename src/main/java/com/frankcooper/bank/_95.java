package com.frankcooper.bank;

import com.alibaba.fastjson.JSON;
import com.frankcooper.struct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/7/29
 * @Author Frank Cooper
 * @Description
 */
public class _95 {


    static  _95 handler  = new _95();

    public static void main(String[] args) {
        handler.generateTrees(3);
    }


    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return helper(1, n);

    }
    private List<TreeNode> helper(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        if (start > end) {
            result.add(null);
            return result;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftPart = helper(start, i - 1);
            List<TreeNode> rightPart = helper(i + 1, end);
            for (TreeNode left : leftPart) {
                for (TreeNode right : rightPart) {
                    TreeNode curr = new TreeNode(i);
                    curr.left = left;
                    curr.right = right;
                    result.add(curr);
                }
            }
        }
//        System.out.println(JSON.toJSONString(result));
        return result;
    }
}
