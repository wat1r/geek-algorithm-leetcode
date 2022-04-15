package com.frankcooper.struct.sec;

import java.util.ArrayList;
import java.util.List;

public class NestedInteger {

    private int val;
    private List<NestedInteger> list;

    //
    public NestedInteger() {
        list = new ArrayList<>();
    }

    // Constructor initializes a single integer.
    public NestedInteger(int value) {
        this.val = value;
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return false;
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return null;
    }

    public void add(NestedInteger ni) {
        this.list.add(ni);
    }


    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value) {

    }


    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return new ArrayList<>();
    }
}
