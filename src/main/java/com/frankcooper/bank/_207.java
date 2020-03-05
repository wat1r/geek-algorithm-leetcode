package com.frankcooper.bank;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by FrankCooper
 * Date 2020/3/5 23:09
 * Description
 */
public class _207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        for (int[] p : prerequisites) {
            indegrees[p[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);//入的是索引位置i,也就是前面出现的p[0]
            }
        }
        while (!queue.isEmpty()) {
            Integer pre = queue.poll();//弹出的是索引位置,也就是前面出现的p[0]
            numCourses--;
            for (int[] p : prerequisites) {
                if (p[1] != pre) continue;//当pre与cur的不一致，之前的作为目前的arr 第二个数
                indegrees[p[0]]--;
                if (indegrees[p[0]] == 0) queue.offer(p[0]);
            }
        }

        return numCourses == 0;
    }
}
