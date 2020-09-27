package com.frankcooper.bank;

import java.util.LinkedList;
import java.util.Queue;

public class _934 {


    static _934 handler = new _934();

    public static void main(String[] args) {

    }

    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    int R, C;
    Queue<Pos> queue = new LinkedList<>();
    int[][] A;

    public int shortestBridge(int[][] A) {
        R = A.length;
        C = A[0].length;
        this.A = A;
        dfsMain();
        getQueue();
        int dist = bfs();
        return dist;
    }

    private int bfs() {
        while (!queue.isEmpty()) {
            Pos curr = queue.poll();
            for (int[] dir : directions) {
                int nextR = curr.r + dir[0], nextC = curr.c + dir[1];
                if (!inArea(nextR, nextC) || A[nextR][nextC] == -1) {
                    continue;
                }
                if (A[nextR][nextC] == 2) {
                    return curr.step;
                }
                A[nextR][nextC] = -1;
                queue.offer(new Pos(nextR, nextC, curr.step + 1));
            }
        }
        return -1;
    }

    private void getQueue() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (A[r][c] == 1) {
                    queue.offer(new Pos(r, c, 0));
                    A[r][c] = -1;
                }
            }
        }
    }

    private void dfsMain() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if(A[r][c] ==1){
                    dfs(A, r, c);
                    return;
                }
            }
        }
    }

    private void dfs(int[][] A, int r, int c) {
        if (!inArea(r, c) || A[r][c] != 1) return;
        A[r][c] = 2;
        for (int[] dir : directions) {
            dfs(A, r + dir[0], c + dir[1]);
        }
    }


    private boolean inArea(int i, int j) {
        return i >= 0 && i < R && j >= 0 && j < C;
    }


    class Pos {
        int r;
        int c;
        int step;

        public Pos(int r, int c, int step) {
            this.r = r;
            this.c = c;
            this.step = step;
        }
    }

}
