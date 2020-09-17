package com.frankcooper.bank;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class _473 {


    static _473 handler = new _473();

    public static void main(String[] args) {


//        int[] a = {9, 8, 7, 2, 3, 4, 1, 0, 6, 5};
//        Arrays.sort(a,Collections.reverseOrder());

        int[] nums = {3, 3, 3, 3, 4};
//        nums = new int[]{2, 2, 2, 2, 2, 6};
        System.out.println(handler.makesquare(nums));


    }

    //目标正方形的边长
    int maxEdgeLen = 0;


    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length;
        boolean[] visited = new boolean[n];
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 4 != 0) return false;
        this.maxEdgeLen = sum / 4;
        return dfs(nums, 0, visited, 0, 0);
    }


    /**
     * @param nums        源数组
     * @param edgeIdx     当前边的索，一共4条边，0,1,2,3
     * @param visited     数组元素是否被访问
     * @param start       nums数组的位置索引
     * @param currEdgeLen 当前这条边已经累计的边长，不能超过maxEdgeLen
     * @return
     */
    private boolean dfs(int[] nums, int edgeIdx, boolean[] visited, int start, int currEdgeLen) {
        if (edgeIdx == 4) return true;
        //开始一条新的边，start 和 currEdgeLen 需要重置为0
        if (currEdgeLen == maxEdgeLen) {
            return dfs(nums, edgeIdx + 1, visited, 0, 0);
        }
        System.out.printf("edgeIdx:%d,start:%d,currEdgeLen:%d\n", edgeIdx, start, currEdgeLen);
        for (int i = start; i < nums.length; i++) {
            if (!visited[i] && currEdgeLen + nums[i] <= maxEdgeLen) {
                visited[i] = true;
                if (dfs(nums, edgeIdx, visited, i + 1, currEdgeLen + nums[i])) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }


}
