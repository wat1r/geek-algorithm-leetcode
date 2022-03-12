## DFS_BFS之复原IP地址[Dragonfly]



![dragonfly-1508548_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\Untitled.assets\dragonfly-1508548_640.png)



![image-20200810203244946](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\Untitled.assets\image-20200810203244946.png)





### 方法1：回溯+剪枝



![image-20200810203034923](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\Untitled.assets\image-20200810203034923.png)

> 根据上图的描述.ip需要符合以下性质

- 总的段数为4段，如192.168.1.1  而，192.168.1.1.196则不是合法的ip段，因此，字符串最长不过12位
- 单段的数组应该介于0~255之间，而192.168.1.256 中的256非法，192.168.1.01中的01非法，因为0的组成只能是0本身，如192.168.0.1，在一个整数后面192.168.1.100

#### 解法思路

##### 主体

- 准备一个函数$backtrack(s,  segment, index)$,其中 $index$表示当前处理的字符串的下标索引，$segment$收集的是当前的$ip$所有分段

- 因为每个$ip$段只能是三位或者以下，套在一个$[1,3]$的循环内

##### 出口条件

- 当$segment$的大小恰好是$4$，也就是$4$段时，并且当前的$index$已经游走到字符串的末尾，开始收集

> 一个小函数:将一个数组或者List的各项通过分隔符连接成字符串 String.join("xxxx", segment)

##### 剪枝

- 当单个$ip$的数字大小大于$256$或者以$0$开头，但是位数超过$1$，继续找下一个
- 当前的索引+下个要调到的位置已经越界$s$字符串或者当前索引已经到达$s$的的末尾处时，当前的循环$break$掉

```java
    List<String> results = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        backtrack(s, new ArrayList<>(), 0);
        return results;
    }

    private void backtrack(String s, ArrayList<String> segment, int index) {
        if (segment.size() == 4 && index == s.length()) {
            results.add(String.join(".", segment));
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if (index + i > s.length() || segment.size() > 4) break;
            String curr = s.substring(index, index + i);
            if ((i == 3 && Integer.parseInt(curr) > 255) || (curr.startsWith("0") && curr.length() > 1)) continue;
            segment.add(curr);
            backtrack(s, segment, index + i);
            segment.remove(segment.size() - 1);
        }
    }
```



### 方法2：回溯+字符串追加

> 看到的另外的一种解法

#### 解法思路

##### 主体

- 准备一个函数$backtrack(s,  count, path, index)$,其中 $index$表示当前处理的字符串的下标索引，$path$是当前收集的$ip$,$count$表示当前的$ip$段数，根据$.$来判断
- 如果$count$为$3$个时，说明$.$够了

> 一开始String path写出StringBuilder 结果。。。

```java
    List<String> results1 = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        backtrack(s, 0, "", 0);
        System.out.println(JSON.toJSONString(results1));
        return results1;
    }

    private void backtrack(String s, int count, String path, int index) {
        if (count > 4) return;
        if (count == 4 && index == s.length()) {
            results1.add(path);
        }
        for (int i = 1; i <= 3; i++) {
            if (index + i > s.length()) break;
            String curr = s.substring(index, index + i);
            if ((Integer.parseInt(curr) > 255) || (curr.startsWith("0") && curr.length() > 1)) break;
            System.out.println(path.toString());
            backtrack(s, count + 1, path + curr + (count == 3 ? "" : "."), index + i);
        }
    }
```

