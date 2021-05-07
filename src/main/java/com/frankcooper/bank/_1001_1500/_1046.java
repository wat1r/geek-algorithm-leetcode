package com.frankcooper.bank._1001_1500;

import java.util.PriorityQueue;

/**
 * @Date 2020/9/16
 * @Author Frank Cooper
 * @Description
 */
public class _1046 {

    static _1046 handler = new _1046();

    public static void main(String[] args) {

        int[] stones = {2, 7, 4, 1, 8, 1};
        handler.lastStoneWeight(stones);
    }

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        for (int stone : stones) pq.offer(stone);
        while (!pq.isEmpty() && pq.size() >= 2) {
            int first = pq.poll(), second = pq.poll();
            if (first != second) {
                pq.offer(first - second);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }


}
