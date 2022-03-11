package com.frankcooper.bank._701_1000;

import java.util.*;

/**
 * @Date 2020/8/31
 * @Author Frank Cooper
 * @Description
 */
public class _841 {


    static _841 handler = new _841();

    public static void main(String[] args) {

        List<List<Integer>> rooms = new ArrayList<>();
//        rooms.add(new ArrayList<Integer>() {{
//            add(1);
//            add(3);
//        }});
//        rooms.add(new ArrayList<Integer>() {{
//            add(3);
//            add(0);
//            add(1);
//        }});
//        rooms.add(new ArrayList<Integer>() {{
//            add(2);
//        }});
//        rooms.add(new ArrayList<Integer>() {{
//            add(0);
//        }});

        rooms.add(new ArrayList<Integer>() {{
            add(1);
        }});
        rooms.add(new ArrayList<Integer>() {{
            add(2);
        }});
        rooms.add(new ArrayList<Integer>() {{
            add(3);
        }});
        rooms.add(new ArrayList<Integer>() {{
        }});
        handler.canVisitAllRooms(rooms);


    }


    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Queue<Integer> queue = new LinkedList<>();
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        List<Integer> zeroRoom = rooms.get(0);
        visited[0] = true;
        for (int i : zeroRoom) queue.offer(i);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            visited[curr] = true;
            List<Integer> currRoom = rooms.get(curr);
            for (int c : currRoom) {
                if (!visited[c]) {
                    queue.offer(c);
                    visited[c] = true;
                }
            }
        }
        for (boolean b : visited) if (!b) return false;
        return true;
    }


    public boolean canVisitAllRooms1st(List<List<Integer>> rooms) {
        Set<Integer> set = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        int n = rooms.size();
        List<Integer> zeroRoom = rooms.get(0);
        set.add(0);
        for (int i : zeroRoom) {
            queue.offer(i);
            set.add(i);
        }
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            List<Integer> currRoom = rooms.get(curr);
            for (int c : currRoom) {
                if (!set.contains(c)) {
                    queue.offer(c);
                    set.add(c);
                }

            }
        }
        return set.size() == n;
    }


    public boolean canVisitAllRooms2nd(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        dfs(rooms, 0, visited);
        for (boolean b : visited) if (!b) return false;
        return true;
    }

    private void dfs(List<List<Integer>> rooms, int idx, boolean[] visited) {
        if (visited[idx]) return;
        visited[idx] = true;
        for (int i : rooms.get(idx)) dfs(rooms, i, visited);
    }


}
