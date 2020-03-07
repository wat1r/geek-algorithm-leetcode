package com.frankcooper.bank;

import java.util.*;

//210. 课程表 II   210. Course Schedule II

public class _210 {

    public static void main(String[] args) {
        _210 handler = new _210();
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};
        handler.findOrder(numCourses, prerequisites);
    }




    /**
     * 1. 初始化一个入度表`indegrees` 也就是收集的是`v->w`的`w`, 题中的`prerequisites[0]`
     * 2. 准备一个` queue `，收集了入度为`0`的元素的位置，（`queue`存的是索引位置，也就是和`prerequisites[0]`相互映射的），`queue`可能收集不止一个入度为0的元素
     * 3. 转这个`queue`：
     * 1.先弹出`queue`中的元素，为`pre`，表示的是下一个`v-w`中的`w`的`v`
     * 2.当`pre`==p[1]时，说明这个`pre` 时当前的这个`cur`  也就是`p` 将 `p` 的入度-1，如果此时的入度为`0` 的 `p` 再次入队列`queue`  直到转完这个`queue`
     * <p>
     * <p>
     * > 扑排序中的第一个节点将是没有任何入边的节点。事实上，任何具有0入度的节点都可以开始拓扑排序。如果有多个这样的节点，它们的相对顺序并不重要，可以以任何顺序出现。
     */

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        int[] paths = new int[numCourses];
        for (int[] p : prerequisites) {
            indegrees[p[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) queue.offer(i);
        }
        int index = 0;
        while (!queue.isEmpty()) {
            int pre = queue.poll();
            paths[index++] = pre;
            numCourses--;
            for (int[] p : prerequisites) {
                if (p[1] != pre) continue;
                indegrees[p[0]]--;
                if (indegrees[p[0]] == 0) queue.offer(p[0]);
            }
        }
        return numCourses == 0 ? paths : new int[0];
    }


    /*
        - 主要思想与做法参见上面的思路，在做入度表的时候稍微不同
        - 准备一个`map` `key` 是 `v->w`中的`v` 也就是题目中的，`p[1]`，`value` 是个`list`  即是 `v->w`中的`w` ，会有多个组成
     */
    public int[] findOrder2nd(int numCourses, int[][] prerequisites) {

        int[] indegrees = new int[numCourses];
        int[] paths = new int[numCourses];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int dest = prerequisites[i][0];
            int src = prerequisites[i][1];
            List<Integer> list = map.getOrDefault(src, new ArrayList<Integer>());
            list.add(dest);
            map.put(src, list);
            indegrees[dest]++;
        }
        //找到入度为0的元素
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) queue.offer(i);
        }
        int index = 0;
        while (!queue.isEmpty()) {
            Integer pre = queue.poll();
            paths[index++] = pre;
            numCourses--;
            if (map.containsKey(pre)) {
                List<Integer> nextList = map.get(pre);
                for (Integer next : nextList) {
                    indegrees[next]--;
                    if (indegrees[next] == 0) queue.offer(next);
                }
            }
        }
        return numCourses == 0 ? paths : new int[0];
    }

}
