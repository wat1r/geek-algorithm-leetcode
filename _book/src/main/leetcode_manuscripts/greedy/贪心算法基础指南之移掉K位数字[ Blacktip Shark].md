## 贪心算法基础指南之移掉K位数字[ Blacktip Shark]

![shark-1626288_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\greedy\贪心算法基础指南之移掉K位数字[ Blacktip Shark].assets\shark-1626288_640.jpg)

![image-20200909120326641](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\greedy\贪心算法进阶指南I[Billhead Shark].assets\image-20200909120326641.png)

> 最近在刷贪心的题，看的大雪菜的课件，发现此题

证明过程，官方讲的很详细了，大体的结论是移除相邻的逆的降序对的左侧字符，不断重复这个过程

> 给出几个典型的case

```java
"1234567890" k=9
"100204"     k =1
"10"         k=1
```

#### 方法1:Stack

```java
    public String removeKdigits(String num, int k) {
        //初始化一个stack
        Stack<Character> stack = new Stack<>();
        for (char c : num.toCharArray()) {
            //当栈顶的元素大于即将要加入栈的元素，弹出栈顶元素（要在k不为0的前提下）
            while (!stack.isEmpty() && stack.peek() > c && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        //再次判断k是否为0，不为0的话，说明还有栈顶的元素需要被弹出
        while (k > 0) {
            stack.pop();
            k--;
        }
        //弹出栈内所有元素，装进sb中，这时的sb是逆序的，下面的变量过程中需要reverse
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.append(stack.pop());
        StringBuilder res = new StringBuilder();
        boolean headZero = true;//移除的是100204这个case中的前两个0的这种情况，204之间的0不需要被移除（k=1）
        for (char c : sb.reverse().toString().toCharArray()) {
            if (c == '0' && headZero) {
                continue;
            }
            res.append(c);
            headZero = false;
        }
        //再次判断下是否没有元素了
        return res.toString().equals("") ? "0" : res.toString();
    }
```

#### 方法2:LinkedList

```java
    public String removeKdigits(String num, int k) {
        //维护一个list，不像stack，其可以首尾操作
        LinkedList<Character> list = new LinkedList<>();
        for (char c : num.toCharArray()) {
            while (!list.isEmpty() && list.peekLast() > c && k > 0) {
                list.removeLast();
                k--;
            }
            list.addLast(c);
        }
        while (k > 0) {
            list.removeLast();
            k--;
        }
        StringBuilder res = new StringBuilder();
        boolean headZero = true;
        for (char c : list) {
            if (c == '0' && headZero) continue;
            res.append(c);
            headZero = false;
        }
        return res.toString().equals("") ? "0" : res.toString();
    }
```

#### 方法3:String

> 采用的String进行截取操作，效率嘛，感人

```java
    public String removeKdigits(String num, int k) {
        String res = "";
        for (char c : num.toCharArray()) {
            while (!res.equals("") && res.charAt(res.length() - 1) > c && k > 0) {
                res = res.substring(0, res.length() - 1);
                k--;
            }
            res += c;
        }
        while (k > 0) {
            res = res.substring(0, res.length() - 1);
            k--;
        }
        int i = 0;
        while (i < res.length() && res.charAt(i) == '0') i++;
        if (i == res.length()) return "0";
        return res.substring(i);
    }
```







