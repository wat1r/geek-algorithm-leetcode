## 动态规划解环绕字符串中唯一的子字符串[Mandarin Duck]

![duck-5385741_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\un-classify\动态规划解环绕字符串中唯一的子字符串[].assets\duck-5385741_640.jpg)

![image-20200914213906628](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\un-classify\动态规划解环绕字符串中唯一的子字符串[].assets\image-20200914213906628.png)

![image-20200914213831362](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\un-classify\动态规划解环绕字符串中唯一的子字符串[].assets\image-20200914213831362.png)

```java
    public int findSubstringInWraproundString(String p) {
        if (p == null || p.length() == 0) return 0;
        int[] dp = new int[26];
        char[] chas = (" " + p).toCharArray();
        int count = 1;//如果字符串连续被打断，count重新置为1
        for (int i = 1; i < chas.length; ++i) {
            int idx = chas[i] - 'a';
            if (isContinue(chas[i - 1], chas[i])) count++;
            else count = 1;
            dp[idx] = Math.max(dp[idx], count);
        }
        int res = 0;
        for (int num : dp) res += num;
        return res;
    }
    public boolean isContinue(char a, char b) {
        if (a == 'z' && b == 'a') return true;
        return b - a == 1;
    }
}
```

