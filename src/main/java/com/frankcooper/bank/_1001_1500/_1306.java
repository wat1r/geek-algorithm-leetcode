package com.frankcooper.bank._1001_1500;

import java.util.LinkedList;

//1306. 跳跃游戏 III 1306. Jump Game III    Medium
public class _1306 {

    public static void main(String[] args) {

    }

    /*
        ##### 方法1：`DFS`
- 准备一个函数：`dfs(int[] arr, int curPos, boolean[] visited)`
  - 其中`curPos`表示当前访问的位置
  - `visited`表示当前的`curPos`位置有无被访问过
- 出口条件：
  - 当前`curPos`越界了，也就是不在`[0,len-1]`范围内时，返回`false`
  - 当前`curPos`的访问过，返回`false`
  - 当`arr[curPos]==0`时，表示找到了，返回`true`
- 探索左边和右边位置
     */
    public boolean canReach1st(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        return dfs(arr, start, visited);
    }

    private boolean dfs(int[] arr, int curPos, boolean[] visited) {
        if (curPos < 0 || curPos >= arr.length || visited[curPos]) return false;
        if (arr[curPos] == 0) return true;
        visited[curPos] = true;
        return dfs(arr, curPos - arr[curPos], visited) || dfs(arr, curPos + arr[curPos], visited);
    }


    /*

##### 方法2：`BFS`

- 准备一个`bool`类型的数组`visited`表示当前的下标有无被访问过
- 准备一个`queue`，转这个`queue`
  - 取到这一轮的总的`size`大小，进行`for loop`
  - 弹出当前的`curPos`,如果`arr[curPos]== 0`说明找到了，返回`true`
  - 分别渠道左右两边去找，`curPos`的位置不越界并且`leftPos`和`rightPos`未被访问过
  - 访问后要设置下`visited`的属性，并且将位置放置于`queue`中
     */
    public boolean canReach2nd(int[] arr, int start) {
        LinkedList<Integer> queue = new LinkedList<>();
        int n = arr.length;
        boolean[] visited = new boolean[n];
        queue.add(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curPos = queue.removeFirst();
                int curValue = arr[curPos];
                if (curValue == 0) return true;
                int leftPos = curPos - curValue;
                if (leftPos >= 0 && !visited[leftPos]) {
                    visited[leftPos] = true;
                    queue.addFirst(leftPos);
                }
                int rightPos = curPos + curValue;
                if (rightPos < n && !visited[rightPos]) {
                    visited[rightPos] = true;
                    queue.addFirst(rightPos);
                }
            }
        }
        return false;
    }


    static class _3rd {
        public static void main(String[] args) {

        }




    }
}
