## 畅游Trie之数组中两个数的最大异或值



```java
//3, 10, 5, 25, 2, 8
000011[2]  3 [10]
001010[2]  10[10]
000101[2]  5 [10]
011001[2]  25[10]
000010[2]  2 [10]
001000[2]  8 [10]
//对于011001[2]  25[10]来说
//为了最大化异或值，需要在每一步找到当前比特值的互补比特值。下图展示了 25 在每一步要怎么走才能得到最大异或值：
//位于bit5 的时候， bit= 0 只有0上有值 选0
//位于bit4 的时候, bit = 1 => bit^1 = 0 选0  1<<4 = 16
//位于bit3 的时候, bit = 1 => bit^1 = 0 选0  1<<3 = 8
//位于bit2 的时候, bit = 0 => bit^1 = 1 选1  1<<2 = 4
//位于bit1 的时候, bit = 0 => bit^1 = 1 想选1但没有1 只能选0  
//位于bit0 的时候, bit = 1 => bit^1 = 0 想选0但没有0 只能选1 
```



![image-20210322213228129](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\trie\畅游Trie之数组中两个数的最大异或值.assets\image-20210322213228129.png)



```java
        class Trie {
            Trie[] children;

            public Trie() {
                this.children = new Trie[2];
            }
        }

        public int findMaximumXOR(int[] nums) {
            Trie root = new Trie();
            for (int num : nums) {
                Trie curr = root;
                for (int i = 30; i >= 0; i--) {
                    int bit = (num >> i) & 1;
//                    System.out.printf("num:%d,bit:%d\n", num, bit);
                    if (curr.children[bit] == null) {
                        curr.children[bit] = new Trie();
                    }
                    curr = curr.children[bit];
                }
            }
            int ans = Integer.MIN_VALUE;
            for (int num : nums) {
                Trie curr = root;
                int currSum = 0;
                for (int i = 30; i >= 0; --i) {
                    int bit = (num >> i) & 1;
//                    System.out.printf("num:%d,bit:%d\n", num, bit);
                    if (curr.children[bit ^ 1] != null) {
                        curr = curr.children[bit ^ 1];
                        currSum += (1 << i);
                    } else {
                        curr = curr.children[bit];
                    }
                }
                System.out.println(currSum);
                ans = Math.max(ans, currSum);
            }

            return ans;
        }
```





