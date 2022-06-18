package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2020/3/20 21:36
 * Description
 */
//1345. 跳跃游戏 IV 1345. Jump Game IV Hard
public class _1345 {
    public static void main(String[] args) {

    }

    /*

     */
    public int minJumps1st(int[] arr) {
        int MAX = Integer.MAX_VALUE;
        int n = arr.length;
        Queue<Integer> queue = new LinkedList<>();
        int[] dis = new int[n];
        Arrays.fill(dis, MAX);
        dis[n - 1] = 0;
        queue.offer(n - 1);
        boolean[] visited = new boolean[n];
        while (!queue.isEmpty()) {
            int i = queue.poll();
            if (i - 1 >= 0 && !visited[i - 1] && dis[i - 1] == MAX) {
                dis[i - 1] = Math.min(dis[i - 1], dis[i] + 1);
                visited[i - 1] = true;
                queue.offer(i - 1);
            }
            if (i + 1 < n && !visited[i + 1] && dis[i + 1] == MAX) {
                dis[i + 1] = Math.min(dis[i + 1], dis[i] + 1);
                visited[i + 1] = true;
                queue.offer(i + 1);
            }
            for (int j = 0; j < n; j++) {
                if (arr[i] == arr[j] && !visited[j] && dis[j] == MAX) {
                    dis[j] = Math.min(dis[j], dis[i] + 1);
                    visited[j] = true;
                    queue.offer(j);
                }
            }
        }
        return dis[0];
    }


    /*

     */
    public int minJumps2nd(int[] arr) {
        int MAX = Integer.MAX_VALUE;
        int n = arr.length;
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
            list.add(i);
            map.put(arr[i], list);
        }
        int[] dis = new int[n];
        Arrays.fill(dis, MAX);
        dis[n - 1] = 0;
        queue.offer(n - 1);
        boolean[] visited = new boolean[n];
        while (!queue.isEmpty()) {
            int i = queue.poll();
            if (i - 1 >= 0 && !visited[i - 1] && dis[i - 1] == MAX) {
                dis[i - 1] = Math.min(dis[i - 1], dis[i] + 1);
//                visited[i - 1] = true;
                queue.offer(i - 1);
            }
            if (i + 1 < n && !visited[i + 1] && dis[i + 1] == MAX) {
                dis[i + 1] = Math.min(dis[i + 1], dis[i] + 1);
//                visited[i + 1] = true;
                queue.offer(i + 1);
            }
            if (!visited[i]) {
                for (int j : map.get(arr[i])) {
                    if (dis[j] == MAX && !visited[j]) {
                        dis[j] = Math.min(dis[j], dis[i] + 1);
                        visited[j] = true;
                        queue.offer(j);
                    }
                }
            }
        }
        return dis[0];
    }
}
