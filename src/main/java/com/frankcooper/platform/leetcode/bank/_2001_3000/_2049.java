package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

public class _2049 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] parents = {-1, 2, 0, 2, 0};
            handler.countHighestScoreNodes(parents);
        }

        int n;
        long maxScore = 0;
        int cnt = 0;
        List<Integer>[] children;

        public int countHighestScoreNodes(int[] parents) {
            n = parents.length;
            children = new List[n];
            for (int i = 0; i < n; i++) children[i] = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (parents[i] != -1) children[parents[i]].add(i);
            }
            dfs(0);
            return cnt;
        }


        private int dfs(int node) {
            long score = 1;
            int size = n - 1;
            for (int child : children[node]) {
                int t = dfs(child);
                score *= t;
                size -= t;
            }
            if (node != 0) score *= size;
            if (score == maxScore) {
                cnt++;
            } else if (score > maxScore) {
                maxScore = score;
                cnt = 1;
            }
            return n - size;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        // 表示最高得分
        long maxScore = 0;
        // 记录最高的分的个数
        int cnt = 0;
        // 记录parents数组的个数
        int n;
        // 以数组的形式表示树结构
        List<Integer>[] children;

        public int countHighestScoreNodes(int[] parents) {
            n = parents.length;
            children = new List[n];
            // 初始化children数组
            for (int i = 0; i < n; i++) {
                children[i] = new ArrayList<Integer>();
            }
            // 利用parents得出第i个节点子节点为children[i]
            // 比如树：
            //     0
            //    / \
            //   2   4
            //  / \
            // 3   1
            // 注意这个数字代表的就是它是第几个节点，它的编号
            // 以数组形式表示的话就是
            // {
            //  // 第0个节点的子结点为2和4
            //  {2, 4}.
            //  // 第1个节点没有子结点
            //  {},
            //  // 第2个节点的子结点为3和1
            //  {3, 1},
            //  // 第3个节点没有子结点
            //  {},
            //  // 第4个子结点没有子节点
            //  {}
            // }
            for (int i = 0; i < n; i++) {
                int p = parents[i];
                if (p != -1) {
                    children[p].add(i);
                }
            }
            dfs(0);
            return cnt;
        }

        // 返回值为节点i下所有节点数量
        // 顺便计算每个节点的得分
        public int dfs(int node) {
            // 记录分数
            long score = 1;
            // 计算以这个节点为根结点的子树移除所形成的子树需要减去当前节点的，所以需要-1
            int size = n - 1;
            for (int c : children[node]) {
                // t就是c集合中子树的节点数量
                int t = dfs(c);
                // 计算当前节点乘子树个数得到的分数
                score *= t;
                // 减去所有子树的个数，当前size就是以这个节点为根结点的子树移除所形成的子树的个数了
                size -= t;
            }
            // 如果当前的node是不是第0个节点，那么计算分数的时候需要再乘上以这个节点为根结点的子树移除所形成的子树
            if (node != 0) {
                // 所以此时当前节点的分数就是score*=size
                score *= size;
            }
            // 如果当前的分数是等于最高的分数的，那么cnt个数就+1
            if (score == maxScore) {
                cnt++;
            } else if (score > maxScore) {
                // 如果还有比maxScore大的数，那么将新的最大的分数给maxScore
                maxScore = score;
                // 并且重置统计的cnt，毕竟最大值都变了，之前统计的也就不算了
                cnt = 1;
            }
            // 计算当前node为根节点的子树的节点数量，包括根节点
            return n - size;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] parents = {-1, 2, 0, 2, 0};
            handler.countHighestScoreNodes(parents);

        }

        int N = 100010, M = N * 2;
        int[] head = new int[N];//存储某个节点所对应的边的集合(链表)的头节点
        int[] to = new int[M];//某一条边的指向节点
        int[] next = new int[M];//指针数组，存储当前边的下一条边
        int idx = 0;
        int[] f = new int[N];

        private void add(int u, int v) {
            to[idx] = v;
            next[idx] = head[u];
            head[u] = idx;
            idx++;
        }


        public int countHighestScoreNodes(int[] parents) {
            Arrays.fill(head, -1);
            int n = parents.length;
            for (int i = 1; i < n; i++) {
                add(parents[i], i);
            }
            dfs(0);
            long maxScore = 0;
            int cnt = 0;
            for (int u = 0; u < n; u++) {
                long score = 1;
                for (int i = head[u]; i != -1; i = next[i]) {
                    score *= f[to[i]];
                }
                if (u != 0) {
                    score *= n - f[u];
                }
                if (score > maxScore) {
                    maxScore = score;
                    cnt = 1;
                } else if (score == maxScore) {
                    cnt++;
                }
            }
            return cnt;
        }


        private int dfs(int u) {
            int res = 1;
            for (int i = head[u]; i != -1; i = next[i]) {
                res += dfs(to[i]);
            }
            return f[u] = res;
        }


    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

//        https://leetcode-cn.com/problems/count-nodes-with-the-highest-score/solution/gong-shui-san-xie-jian-tu-dfs-by-ac_oier-ujfo/

        public int countHighestScoreNodes(int[] parents) {
            int n = parents.length;
            int[] outdegrees = new int[n];//记录每个节点的出度
            for (int i = 1; i < n; i++) outdegrees[parents[i]]++;
            Deque<Integer> q = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                if (outdegrees[i] == 0) q.offer(i);
            }
            //分别表示[i]节点的左右子树上节点的个数
            int[] left = new int[n], right = new int[n];
            while (!q.isEmpty()) {
                int t = q.poll();
                int fa = parents[t];
                outdegrees[fa]--;
                if (left[fa] == 0) left[fa] = left[t] + right[t] + 1;
                else right[fa] = left[t] + right[t] + 1;
                if (outdegrees[fa] == 0 && fa != 0) q.offer(fa);
            }
            long maxScore = 0;
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                long score = (long) Math.max(1, left[i]) * Math.max(1, right[i]);
                if (i != 0) {
                    score *= n - (left[i] + right[i] + 1);
                }
                if (score > maxScore) {
                    maxScore = score;
                    cnt = 1;
                } else if (score == maxScore) {
                    cnt++;
                }
            }
            return cnt;
        }
    }
}
