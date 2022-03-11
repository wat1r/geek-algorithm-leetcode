package com.frankcooper.bank._1001_1500;

import java.util.*;

public class _1311 {
    static _1311 handler = new _1311();

    public static void main(String[] args) {

        String[][] w = {{"A", "B"}, {"C"}, {"B", "C"}, {"D"}};
        int[][] friends = {{1, 2}, {0, 3}, {0, 3}, {1, 2}};
        List<List<String>> watchedVideos = twoDArrayToList(w);
        handler.watchedVideosByFriends(watchedVideos, friends, 0, 1);

    }


    public static <T> List<List<T>> twoDArrayToList(T[][] twoDArray) {
        List<List<T>> list = new ArrayList<>();
        for (T[] array : twoDArray) {
            list.add(Arrays.asList(array));
        }
        return list;
    }

    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        /**
         *Step1
         */
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(id);
        visited.add(id);
        for (int i = 0; i < level; i++) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                int curId = queue.poll();
                for (int k = 0; k < friends[curId].length; k++) {
                    if (!visited.contains(friends[curId][k])) {
                        queue.offer(friends[curId][k]);
                        visited.add(friends[curId][k]);
                    }
                }
            }
        }

        Map<String, Integer> map = new HashMap<>();
        while (!queue.isEmpty()) {
            int curId = queue.poll();
            List<String> curVideos = watchedVideos.get(curId);
            for (int i = 0; i < curVideos.size(); i++) {
                map.put(curVideos.get(i), map.getOrDefault(curVideos.get(i), 0) + 1);
            }
        }

        PriorityQueue<String> pq = new PriorityQueue<>((o1, o2) -> {
            if (map.get(o1).equals(map.get(o2))) return o1.compareTo(o2);
            return map.get(o1) - map.get(o2);
        });
        pq.addAll(map.keySet());
        List<String> res = new ArrayList<>();
        while (!pq.isEmpty()) res.add(pq.poll());
        return res;
    }


}
