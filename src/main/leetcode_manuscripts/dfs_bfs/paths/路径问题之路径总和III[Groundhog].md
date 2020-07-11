## 路径问题之路径总和III[Groundhog]



![scolet](C:\Users\FrankCooper\Desktop\scolet.jpg)





![1098_1](C:\Users\FrankCooper\Downloads\1098_1.jpg)

- 当前$queue$的队列头部节点并非是当前遍历的$num$的直接父级关系，而是其伯侄关系，举例：如上图：$110, 211, 227, 318, 328, 331, 349, 431, 448, 457, 478, 489$如图中的$A$与$B$是兄弟关系，$B$与$C$是父子关系， $A$与$C$是伯侄关系

```java
if (queue.peek().hasChild) {
    queue.poll();
    continue;
}
res += queue.poll().value;
```

那么问题来了，如何判断一个根节点的$seq$为$n$时，其左右两个子节点的序号？给出结论：

>  节点n的左子树和右子树分别是2*n、2*n+1 

限制条件容易得出：

```java
queue.peek().seq != (getSeq(curNum) + 1) / 2
```

几个注意的点：

- $level$从1开始，表示第1层，非0
- 因为已经排序后，根节点被加入到$queue$中，此为第1层，$curLevel$从2开始，遍历的索引下标$i$也从1开始

```java
  public int pathSumIV(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);//sort,set the start number and got it
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(getLevel(nums[0]), getSeq(nums[0]), getValue(nums[0])));//add the root node
        //i: 0...size-1
        int i = 1, size = nums.length;
        int curLevel = 2; //the first level begin with 1 ,then start with curLevel
        int res = 0;
        while (i < size) {
            while (i < size && getLevel(nums[i]) == curLevel) {
                int curNum = nums[i];
                //处理伯侄关系的节点，queue中的队头节点不是当前节点的父节点，弹出，收集
                while (queue.peek().seq != (getSeq(curNum) + 1) / 2) {
                    if (queue.peek().hasChild) {
                        queue.poll();
                        continue;
                    }
                    res += queue.poll().value;
                }
                //加入下一个新的节点，累加value
                Node tmp = queue.peek();
                queue.offer(new Node(curLevel, getSeq(curNum), tmp.value + getValue(curNum)));
                tmp.hasChild = true;
                i++;
            }
            //如果另外起了一层，弹出收集结果
            while (queue.peek().level == (curLevel - 1)) {
                if (queue.peek().hasChild) {
                    queue.poll();
                    continue;
                }
                res += queue.poll().value;
            }
            //进入下一层
            curLevel++;
        }
      	//保持队列为空
        while (!queue.isEmpty()) {
            res += queue.poll().value;
        }
        return res;
    }


    //get level : 1st number
    private int getLevel(int num) {
        return num / 100;
    }

    //get seq : 2nd number
    private int getSeq(int num) {
        return (num % 100) / 10;
    }

    //get value :3rd number
    private int getValue(int num) {
        return num % 10;
    }


    static class Node {

        int level;
        int seq;
        int value;
        boolean hasChild;

        public Node(int level, int seq, int value) {
            this.level = level;
            this.seq = seq;
            this.value = value;
        }
    }
```



