> 贪心



## [678. 有效的括号字符串](https://leetcode.cn/problems/valid-parenthesis-string/)

```java
public boolean checkValidString(String s) {
    int n = s.length();
    char[] ch = s.toCharArray();
    int l = 0;//从左到右遍历 遇到( +1 遇到）-1 遇到* +1 要求l>=0 保证(足够
    for (int i = 0; i < n; i++) {
        if (ch[i] == '(' || ch[i] == '*') l++;
        else if (ch[i] == ')') l--;
        if (l < 0) return false;
    }
    int r = 0;//从右到左遍历 遇到( -1 遇到）+1 遇到* +1 要求r>=0 保证)足够
    for (int i = n - 1; i >= 0; i--) {
        if (ch[i] == ')' || ch[i] == '*') r++;
        else if (ch[i] == '(') r--;
        if (r < 0) return false;
    }
    return true;
}
```