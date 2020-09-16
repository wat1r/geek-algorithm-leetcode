## 动态规划解最后一块石头的重量[Pelican]











#### 方法1：优先队列

```java
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
```















#### 方法1:朴素版01背包

##### 定义状态

$f(i,j)$表示当遇到第i个物品时，背包容量为j时，能获得的最大价值

来到本题：$f(i,j)$表示遇到第i个石头时，









#### 方法2:压缩版01背包

