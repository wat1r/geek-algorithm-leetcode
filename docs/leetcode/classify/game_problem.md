## 博弈问题

> 





## [464. 我能赢吗](https://leetcode.cn/problems/can-i-win/)







## [2038. 如果相邻两个颜色均相同则删除当前颜色](https://leetcode-cn.com/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color/)

![](/imgs/leetcode/classify/image-20220322080710354.png)

- 1.Alice和Bob均不可以在两段拿走A或者B，需要判断是否在字符串中出现了连续的AAA或者BBB这样的字符的时候，拿走一个A或者B，分别统计拿走的次数分别记为a和b，返回a>b即可

```java
public boolean winnerOfGame(String colors) {
    int n = colors.length();
    char[] ch = colors.toCharArray();
    int a = 0, b = 0;
    for (int i = 1; i < n - 1; i++) {
        if (ch[i] == ch[i - 1] && ch[i] == ch[i + 1]) {
            if (ch[i] == 'A') a++;
            if (ch[i] == 'B') b++;
        }
    }
    return a > b;
}
```