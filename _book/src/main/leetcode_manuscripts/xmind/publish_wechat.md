





## 图解804唯一摩尔斯密码词






> **欢迎阅读、点赞、转发、订阅，你的举手之间，我的动力源泉，欢评论区提供其他语言的版本**



## [804. 唯一摩尔斯密码词](https://leetcode-cn.com/problems/unique-morse-code-words/)

### 方法1:Hash

```java
        String[] dict = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};


        public int uniqueMorseRepresentations(String[] words) {
            Set<String> set = new HashSet<>();
            for (String word : words) {
                StringBuilder sb = new StringBuilder();
                for (char c : word.toCharArray()) {
                    sb.append(dict[c - 'a']);
                }
                set.add(sb.toString());
            }
            return set.size();
        }
```



### 方法2:位运算



![](/imgs/leetcode/classify/image-20220410085817240.png)



- 将当前的字符转成二进制，`-`表示二进制中的1，`0`表示二进制中的`0`,做法有点像[**二维矩阵的常见转换技巧**](https://cnwangzhou.gitbook.io/algorithm/zhuan-lan/er-wei-ju-zhen-de-chang-jian-zhuan-huan-ji-qiao)



```java
        int[] bin = new int[26];
        String[] dict = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

        public int uniqueMorseRepresentations(String[] words) {
            //只做二进制的映射关系
            for (int i = 0; i < dict.length; i++) {
                encode(dict[i], i);
            }
            Set<Integer> set = new HashSet<>();
            for (String word : words) {
                int x = 0;
                for (int i = 0; i < word.length(); i++) {
                    //拿到word中当前字符的索引
                    int idx = word.charAt(i) - 'a';
                    //先将x向左移位len(dict[idx]) 将该字符对应的莫斯密码位的数拼接到x上
                    x = x << dict[idx].length() | bin[idx];
                }
              //  System.out.printf("%s->%4d->%10s", word, x, PrintUtils.toBinaryString(x, 10));
                set.add(x);
            }
            return set.size();
        }

        //将形如 -... 的莫斯密码 翻译成 二进制 -表示1 .表示0
        //-... -> 1000(2) -> 16(10)
        public int encode(String s, int idx) {
            int x = 0;
            int n = s.length();
            for (int i = n - 1; i >= 0; i--) {
                if (s.charAt(i) == '-') {
                    x |= (1 << (n - 1 - i));
                }
            }
            this.bin[idx] = x;
            return x;
        }
```



### 更多阅读

- 算法题解的链接地址： [gitbook](https://cnwangzhou.gitbook.io/algorithm/)

- [个人主页【阿飞算法】](https://blog.csdn.net/wat1r/article/details/117533156) 加我好友，进群一起交流~
