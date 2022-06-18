package com.frankcooper.platform.leetcode.bank._201_300;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by FrankCooper
 * Date 2020/3/5 23:09
 * Description
 */

/**
 *
 */


public class _207 {


    public static void main(String[] args) {
        _207 handler = new _207();
        int numCourses = 5;
        int[][] prerequisites = {{1, 0}, {3, 0}, {2, 1}, {3, 1}, {4, 2}};
        handler.canFinish1st(numCourses, prerequisites);

    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //1.计算入度表，[u,v] v->u
        //入度(indegree)就是有向图中指向这个点的边的数量，即有向图的某个顶点作为终点的次数和
        int[] indegrees = new int[numCourses];
        for (int[] p : prerequisites) {
            indegrees[p[0]]++;
        }
        //2.将入度为0的点放入queue中,queue中装的是点的下标索引，即是入度表中的索引
        //题意：你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。课程名称与索引是对应的
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0) queue.offer(i);
        }
        //3.轮转这个queue，这个queue中弹出的节点其实是v->u中的v
        //弹出一个节点，将课程数-1，在prerequisites中找到这个节点的指向节点，即通过v->u
        //如果u找到了，将其入度-1，如果入度为0了，将其加到queue中
        while (!queue.isEmpty()) {
            int pre = queue.poll();//这个是v->u的v
            numCourses--;
            for (int[] p : prerequisites) {
                if (p[1] != pre) continue;
                indegrees[p[0]]--;
                if (indegrees[p[0]] == 0) queue.offer(p[0]);
            }
        }
        //判断有没有剩余的课程数
        return numCourses == 0;
    }


    public boolean canFinish2nd(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        for (int[] p : prerequisites) {
            indegrees[p[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0) queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int pre = queue.poll();
            numCourses--;
            for (int[] p : prerequisites) {
                if (p[1] != pre) continue;
                indegrees[p[0]]--;
                if (indegrees[p[0]] == 0) queue.offer(p[0]);
            }
        }
        return numCourses == 0;
    }


    public boolean canFinish1st(int numCourses, int[][] prerequisites) {
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

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public boolean canFinish(int n, int[][] ps) {
            int[] indegrees = new int[n];
            for (int[] p : ps) {
                indegrees[p[0]]++;
            }
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < indegrees.length; i++) {
                if (indegrees[i] == 0) q.offer(i);
            }
            while (!q.isEmpty()) {
                int cur = q.poll();
                n--;
                for (int[] p : ps) {
                    if (p[1] != cur) continue;
                    indegrees[p[0]]--;
                    if (indegrees[p[0]] == 0) q.offer(p[0]);
                }
            }
            return n == 0;
        }


    }
}
