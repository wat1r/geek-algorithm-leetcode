package com.frankcooper.struct;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/7/13 8:57
 * @description:
 */
public class ParentTreeNode {
    public int key;
    public ParentTreeNode parent, left, right;

    public ParentTreeNode(int val) {
        this.key = val;
    }
}
