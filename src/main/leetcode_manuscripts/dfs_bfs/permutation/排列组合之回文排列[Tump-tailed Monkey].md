## 排列组合之回文排列[Tump-tailed Monkey]

![pexels-photo-134402](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\permutation\排列组合之回文排列[Tump-tailed Monkey].assets\pexels-photo-134402.jpeg)


> 欢迎阅读、点赞、转发、订阅，你的举手之间，我的动力源泉。

### 0.基础框架

- $DFS$: `Depth First Search`  深度优先搜索，简称深搜
- $BFS$：`Breadth First Search`  广度优先搜索，简称广搜

#### 0.1.$DFS$算法框架

```python
def dfs(n){                         //可以描述阶段的状态
	if(valid) {收集结果，返回}	        //出口条件
	if(pruning) return;             //剪枝，这一步是为了加快回溯过程，降低程序执行时间
	for(i:1~p){                      //选择该阶段的所有决策
		选择可行决策;                   //剪枝的一种 
		add;						  //标记已访问该点
		DFS(n+1);                     //进入下一阶段
		recover;                      //还原
	}
}
```

#### 0.2.$BFS$算法框架

```python
def bfs(){
    q.push(head);//一般为q这种优先队列来处理bfs问题
    while(!q.empty()){
        temp=q.front;//弹出元素
        q.pop(); 
        if(temp为目标状态)输出解 
        if(temp不合法)continue;
        if(temp合法)q.push(temp+Δ);
    }
}
一般也会设置一些visit[] 来记录元素访问与否，做剪枝
```

>  举个例子，假如你在学校操场，老师叫你去国旗那集合，你会怎么走？ 假设你是瞎子，你看不到周围，那如果你运气差，那你可能需要把整个操场走完才能找到国旗。这便是盲目式搜索，即使知道目标地点，你可能也要走完整个地图。 假设你眼睛没问题，你看得到国旗，那我们只需要向着国旗的方向走就行了，我们不会傻到往国旗相反反向走，那没有意义。 这种有目的的走法，便被称为启发式的 

- 下面左边图是$bfs$，右边图是$dfs$

- 有趣的链接：https://qiao.github.io/PathFinding.js/visual/ 

  ![dfs](C:\Users\lwh\Desktop\dfs.gif)


### 1.回文排列

![image-20200616192538097](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\permutation\排列组合之回文排列[Tump-tailed Monkey].assets\image-20200616192538097.png)

#### 方法1:统计中心回文数的个数

- 准备一个$helper$辅助数组，统计$s$的字符出现次数
- 判断条件:
  - 如果中心回文的个数为$0$个，说明是回文排列，如$aabb$可以形成$abba$或者$baab$这样的回文排列
  - 如果中心回文的个数为$1$个，说明是回文排列，如$aab$其中 $a$与$a$可以抵消，形成放置在回文中心的两端，$b$可做回文中心，形成$aba$这样的排列
  - 如果中心回文的个数为$2$个或以上，说明不是回文，如$aabc$，$b$和$c$无论如何都找不到一个相等的与其配对

```java
    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        char[] chas = s.toCharArray();
        int n = chas.length;
        int[] helper = new int[26];
        for (char c : chas) helper[c - 'a']++;
        int count = 0;
        for (int i : helper) {
            if ((i & 1) == 1 && ++count > 1) return false;
        }
        return true;
    }
```


#### 复杂度分析

- 时间复杂度：$O(N)$，$N$是$s$的长度，
- 空间复杂度：$O(1)$，只用了辅助数组$helper$

### 2.回文排列II

![image-20200616194455975](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\permutation\排列组合之回文排列[Tump-tailed Monkey].assets\image-20200616194455975.png)

> 本题有一些细节，需要细细扣一下

#### 方法1:$DFS$

- 这是全排列的核心代码，朴素版的

```java
  private void dfs2nd(int[] nums, List<List<Integer>> resList, List<Integer> levelList) {
        if (levelList.size() == nums.length) {
            resList.add(new ArrayList<>(levelList));
            return;
        }
        for (int num : nums) {
            if (levelList.contains(num)) continue;
            levelList.add(num);
            dfs2nd(nums, resList, levelList);
           levelList.remove(levelList.size()-1);
        }
    }
```

- 准备$Map<Character, Integer> counterMap$ 记录$chas$的每一个字符的个数，$k$表示字符，$v$表示该字符的个数，举例：$aabbc$的结果集是$a->2,b->2,c->1$
- 准备变量$mid$记录奇数回文的中心点的字符，初始化为空字符串，$odd$记录奇数回文字符的个数，当$odd>=2$时，无法形成回文排列，详细原因见上题的分析
- 准备$sb$作为种子字符，开始组装回文，注意遍历时，取一半，$num/2$
- 开始**$dfs$**
  - **出口条件**:当当前的索引已经走到$sb$的末尾时，开始收集，因为$sb$只有一半，结果集应该是$sb+mid+sb的翻转$，这样形成了一个排列回文，这里需要注意的是，因为$sb$已经被翻转过了，需要在返回的时候，将其复原，执行$sb.reverse()$
  - **$swap$函数**：当前的索引$index$与当前遍历的$i$在下一层$dfs$的前后需要交换
  - **去掉重复排列**：如果当前的不是$index$本身，且当前的$i$的字符与其相同，会产生重复的排列，例如$aaaa$
  - 也可以用$hashset$来收集结果，解决重复排列的问题

```java
 public List<String> generatePalindromes(String s) {
        List<String> resList = new ArrayList<>();
        if (s == null || s.length() == 0) return resList;
        Map<Character, Integer> counterMap = new HashMap<>();
        char[] chas = s.toCharArray();
        for (char c : chas) counterMap.put(c, counterMap.getOrDefault(c, 0) + 1);
        int odd = 0;
        String mid = "";
        for (char c : counterMap.keySet()) {
            if ((counterMap.get(c) & 1) == 1) {//奇数时
                if (++odd > 1) return resList;
                mid = String.valueOf(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : counterMap.keySet()) {
            int num = counterMap.get(c);
            for (int i = 0; i < (num / 2); i++) sb.append(c);
        }
        dfs(sb, 0, resList, mid);
        return resList;
    }

    /**
     *
     * @param sb 种子字符
     * @param index 当前的索引
     * @param resList 结果集
     * @param mid 奇回文中间的字符，偶回文为空字符
     */
    private void dfs(StringBuilder sb, int index, List<String> resList, String mid) {
        if (index + 1 == sb.length()) {
            String res = sb + mid + sb.reverse();
            resList.add(res);
            sb.reverse();
            System.out.println(res);
            return;
        }
        for (int i = index; i < sb.length(); i++) {
            if (i != index && sb.charAt(i) == sb.charAt(index)) continue;
            swap(sb, index, i);
            dfs(sb, index + 1, resList, mid);
            swap(sb, index, i);

        }

    }

    //交换sb的i与j位置的字符
    private void swap(StringBuilder sb, int i, int j) {
        char tmp = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(j));
        sb.setCharAt(j, tmp);
    }
```



### 推荐阅读














