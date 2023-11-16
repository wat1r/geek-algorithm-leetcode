package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

import org.junit.Assert;

public class _2127 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] favorite = {3, 0, 1, 4, 1};
            Assert.assertEquals(4, handler.maximumInvitations(favorite));

        }

        //https://leetcode.cn/problems/maximum-employees-to-be-invited-to-a-meeting/solutions/1187830/nei-xiang-ji-huan-shu-tuo-bu-pai-xu-fen-c1i1b/?envType=daily-question&envId=2023-11-01

        public int maximumInvitations(int[] favorite) {
            int n = favorite.length;
            int[] deg = new int[n];//节点的入度
            for (int f : favorite) {
                deg[f]++;
            }
            List<Integer>[] rg = new List[n];//反图
            Arrays.setAll(rg, e -> new ArrayList<>());
            Deque<Integer> q = new ArrayDeque<>();
            //找到入度为0的节点，即树枝节点
            for (int i = 0; i < n; i++) {
                if (deg[i] == 0) {
                    q.offer(i);
                }
            }
            //拓扑排序，剪掉图上所有树枝
            while (!q.isEmpty()) {
                int u = q.poll();
                int v = favorite[u];
                rg[v].add(u);
                if (--deg[v] == 0) {
                    q.offer(v);
                }
            }

            int maxRingSize = 0, sumChainSize = 0;
            for (int i = 0; i < n; i++) {
                if (deg[i] == 0) {
                    continue;
                }
                //遍历基环上的点
                deg[i] = 0;//将基环上的点的入度标记为0，避免重复访问
                int ringSize = 1;//基环长度
                for (int x = favorite[i]; x != i; x = favorite[x]) {
                    deg[x] = 0;//将基环上的点的入度标记为0，避免重复访问
                    ringSize++;
                }
                if (ringSize == 2) {
                    sumChainSize += rdfs(i, rg) + rdfs(favorite[i], rg);//累加两条最长链的长度
                } else {
                    maxRingSize = Math.max(maxRingSize, ringSize);//取所有基环长度的最大值
                }
            }

            return Math.max(maxRingSize, sumChainSize);
        }

        //通过反图rg寻找树枝上最深的链
        private int rdfs(int x, List<Integer>[] rg) {
            int maxDepth = 1;
            for (int y : rg[x]) {
                maxDepth = Math.max(maxDepth, rdfs(y, rg) + 1);
            }
            return maxDepth;
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
