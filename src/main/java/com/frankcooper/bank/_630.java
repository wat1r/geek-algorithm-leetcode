package com.frankcooper.bank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Date 2020/8/21
 * @Author Frank Cooper
 * @Description
 */
public class _630 {

    public static void main(String[] args) {

    }


    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int time = 0;
        for (int[] course : courses) {
            int duration = course[0];
            int deadline = course[1];
            if (time + duration <= deadline) {
                pq.offer(duration);
                time += duration;
            } else {
                if (!pq.isEmpty() && pq.peek() > duration) {
                    time += duration - pq.poll();
                    pq.offer(duration);
                }
            }
        }
        return pq.size();
    }


}
