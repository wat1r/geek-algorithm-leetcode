package com.frankcooper.bank._601_700;

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


    class _3rd {



        public int scheduleCourse(int[][] courses) {


            return 0;
        }


    }


    class _2nd {
        public int scheduleCourse(int[][] courses) {
            Arrays.sort(courses, Comparator.comparing(a -> a[1]));
            return backtrack(courses, 0, 0);
        }

        private int backtrack(int[][] courses, int duration, int index) {
            if (index == courses.length) return 0;

            int[] currCourse = courses[index];
            int currDuration = currCourse[0];
            int currDeadline = currCourse[1];
            int choose = 0;
            if (duration + currDuration <= currDeadline) {
                choose = 1 + backtrack(courses, duration + currDuration, index + 1);
            }
            int notChoose = backtrack(courses, duration, index + 1);
            return Math.max(choose, notChoose);
        }

    }


    public int scheduleCourse1st(int[][] courses) {
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
