package com.frankcooper.bank;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _1203 {


    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        List<List<Integer>> groupItem = new ArrayList<>();
        for (int i = 0; i < (n + m); i++) groupItem.add(new ArrayList<>());
        //组间与组内的依赖图
        List<List<Integer>> groupGraph = new ArrayList<>();
        for (int i = 0; i < (n + m); i++) groupGraph.add(new ArrayList<>());
        List<List<Integer>> itemGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) itemGraph.add(new ArrayList<>());
        //组间与组内的入度数组
        int[] groupDegree = new int[n + m];
        int[] itemDegree = new int[n];
        List<Integer> id = new ArrayList<>();
        for (int i = 0; i < n + m; i++) id.add(i);
        int leftId = m;
        //给未分配的item分配一个groupId
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = leftId++;
            }
            groupItem.get(group[i]).add(i);
        }
        //依赖关系图
        for (int i = 0; i < n; i++) {
            int currGroupId = group[i];
            for (int item : beforeItems.get(i)) {
                int beforeGroupId = group[item];
                if (beforeGroupId == currGroupId) {
                    itemDegree[i] += 1;
                    itemGraph.get(item).add(i);
                } else {
                    groupDegree[currGroupId] += 1;
                    groupGraph.get(beforeGroupId).add(currGroupId);
                }
            }
        }
        //组间拓扑关系排序
        List<Integer> groupTopSort = topSort(groupDegree, groupGraph, id);
        if (groupTopSort.size() == 0) return new int[0];
        int[] ans = new int[n];
        int index = 0;
        for (int currGroupId : groupTopSort) {
            int size = groupItem.get(currGroupId).size();
            if (size == 0) continue;
            List<Integer> res = topSort(itemDegree, itemGraph, groupItem.get(currGroupId));
            if (res.size() == 0) return new int[0];
            for (int item : res) ans[index++] = item;
        }
        return ans;
    }

    private List<Integer> topSort(int[] degree, List<List<Integer>> graph, List<Integer> items) {
        Queue<Integer> queue = new LinkedList<>();
        for (int item : items) {
            if (degree[item] == 0) queue.offer(item);
        }
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            res.add(u);
            for (int v : graph.get(u)) {
                if (--degree[v] == 0) queue.offer(v);
            }
        }
        return res.size() == items.size() ? res : new ArrayList<>();
    }


}
