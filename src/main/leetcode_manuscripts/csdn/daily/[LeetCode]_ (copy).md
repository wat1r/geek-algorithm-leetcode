



### 题目

[剑指 Offer II 087. 复原 IP ](https://leetcode.cn/problems/0on3uN/)

```java
剑指 Offer II 087. 复原 IP 
给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。

有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。

例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。

 

示例 1：

输入：s = "25525511135"
输出：["255.255.11.135","255.255.111.35"]
示例 2：

输入：s = "0000"
输出：["0.0.0.0"]
示例 3：

输入：s = "1111"
输出：["1.1.1.1"]
示例 4：

输入：s = "010010"
输出：["0.10.0.10","0.100.1.0"]
示例 5：

输入：s = "10203040"
输出：["10.20.30.40","102.0.30.40","10.203.0.40"]
 

提示：

0 <= s.length <= 3000
s 仅由数字组成
```

### 解法

### 方法1：回溯

```java
List<String> res = new ArrayList<>();

public List<String> restoreIpAddresses(String s) {
    backtracing(s, "", 0);
    return res;
}


/**
 * @param s     当前字符还剩下的字符
 * @param seg   拼接的ip地址的字符
 * @param count 段数
 */
public void backtracing(String s, String seg, int count) {
    //出口条件：先|| 再 && 确保 空字符串s不会进入到下面的循环
    if (s.isEmpty() || count == 4) {
        //比如1111 到这里是 .1.1.1.1 因为一开始在头部追加了 . 此处在第二个字符开始往后切
        if (s.isEmpty() && count == 4) res.add(seg.substring(1));
        return;
    }
    //最多3个 包含前导0的话，就是1个
    int n = s.charAt(0) == '0' ? 1 : 3;
    for (int i = 1; i <= n && i <= s.length(); i++) {
        String t = s.substring(0, i);
        if (Integer.parseInt(t) <= 255) {
            backtracing(s.substring(i), seg + "." + t, count + 1);
        }
    }
}
```

