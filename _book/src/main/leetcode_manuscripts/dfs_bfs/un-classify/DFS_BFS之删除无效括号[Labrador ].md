## DFS_BFS之删除无效括号[Labrador ]

![labrador](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\un-classify\DFS_BFS之删除无效括号[].assets\labrador.jpg)

![image-20200925085450231](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\un-classify\DFS_BFS之删除无效括号[].assets\image-20200925085450231.png)

### 方法1:DFS

#### 思路

> 1. 表达式有效或无效
> 2. 当前递归中删除的括号总数是否小于当前为止的全局最小值。

让我们先看看如何找出给定表达式中左括号和右括号放错的数目，然后我们将稍微修改原始算法以合并这些计数。

- 我们从左边开始，一次处理一个括号的表达式。

- 假设我们遇到一个左括号，即 ( ，它可能会导致或可能不会导致一个无效的表达式，因为表达式的其余部分的某个地方可能有一个匹配的右括号。这里，我们只需增加计数器来跟踪左括号的出现次数。left += 1
- 如果我们遇到一个右括号，这有两个含义：
  - 要么是这个右括号没有匹配的左括号，在这种情况下，该表达式是无效的。当 left == 0 时，即没有不匹配的左括号可用时，就是这种情况。在这种情况下，我们增加另一个计数器叫 right+=1来表示放错了位置的右括号。
  - 或者，我们有一些未匹配的左括号来匹配这个右括号。当 left > 0 时就是这样。在这种情况下，我们只需减小我们使用的 left 计数器，即 left -= 1。
- 继续处理字符串，直到处理完所有括号。
- 最后，左括号和右括号的值分别告诉我们不匹配 ( 和 ) 括号的数目。

```java
    Set<String> resSet = new HashSet<>();

    public List<String> removeInvalidParentheses1st(String s) {
        int[] arr = counter(s);
        int leftRemove = arr[0], rightRemove = arr[1];
        backtracing(s, new StringBuilder(), 0, 0, 0, leftRemove, rightRemove);
        return new ArrayList<>(resSet);
    }

    /**
     * @param s           源字符
     * @param sb          当前收集到的字符
     * @param idx         当前处理到的字符串的索引
     * @param leftCount   已经收集到的左括号的数量
     * @param rightCount  已经收集到的有括号的数量
     * @param leftRemove  当前还剩余的应该要移除的左括号的数量
     * @param rightRemove 当前还剩余的应该要移除的右括号的数量
     */
    public void backtracing(String s, StringBuilder sb, int idx,
                            int leftCount, int rightCount,
                            int leftRemove, int rightRemove
    ) {
        if (idx == s.length()) {
            if (leftRemove == 0 && rightRemove == 0) {
                System.out.println(sb.toString());
                resSet.add(sb.toString());
            }
            return;
        }

        char currChar = s.charAt(idx);
        //当前的字符（ 或者 ） 丢掉，不考虑此字符
        if (currChar == '(' && leftRemove > 0 || currChar == ')' && rightRemove > 0) {
            backtracing(s, sb, idx + 1,
                    leftCount, rightCount,
                    currChar == '(' ? leftRemove - 1 : leftRemove,
                    currChar == ')' ? rightRemove - 1 : rightRemove
            );
            //下面的方法会给leftRemove，rightRemove赋值，错误的
//            backtracing(s, sb, idx + 1,
//                    leftCount, rightCount,
//                    currChar == '(' ? --leftRemove : leftRemove,
//                    currChar == ')' ? --rightRemove : rightRemove
//            );
        }
        //添加
        sb.append(currChar);
        System.out.printf("idx:%d--->%s\n", idx, sb.toString());
        //当前字符加入到最终的结果集
        //1.既不是左括号也不是右括号，什么都不做，跳到下一个索引
        if (currChar != '(' && currChar != ')') {
            backtracing(s, sb, idx + 1, leftCount, rightCount, leftRemove, rightRemove);
        }
        //2.左括号，leftCount+1，跳到下一个索引
        else if (currChar == '(') {
            backtracing(s, sb, idx + 1, leftCount + 1, rightCount, leftRemove, rightRemove);
        }
        //3.右括号，并且要求，左边的括号数量大于右边的括号数量，否则当前的表达式无意义，rightCount+1，跳到下一个索引
        else if (currChar == ')' && rightCount < leftCount) {
            backtracing(s, sb, idx + 1, leftCount, rightCount + 1, leftRemove, rightRemove);
        }
        //回溯
        sb.deleteCharAt(sb.length() - 1);
    }

    /**
     * 拿到当前字符中需要删除的左右括号的数量
     * @param s
     * @return
     */
    public int[] counter(String s) {
        int[] arr = new int[2];
        int leftRemove = 0, rightRemove = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') leftRemove++;
            else if (c == ')') {
                if (leftRemove == 0) rightRemove++;
                else if (leftRemove > 0) leftRemove--;
            }
        }
        arr[0] = leftRemove;
        arr[1] = rightRemove;
        return arr;
    }
```



### Reference

- [官方链接](https://leetcode-cn.com/problems/remove-invalid-parentheses/solution/shan-chu-wu-xiao-de-gua-hao-by-leetcode/)