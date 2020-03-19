package com.frankcooper.bank;

//55. 跳跃游戏 55. Jump Game Medium
public class _55 {

    public static void main(String[] args) {

    }


    /*
        ##### 方法1：递归回溯
        - 从当前的位置跳跃，一直跳到`nums`结尾的位置
        - 注意限制下出口条件：(当当前的位置到达结尾时，说明已经到达了结尾)
          ```java
          if (pos == nums.length - 1) return true;
          ```
        - 判断下最远到达的位置，`[pos+1,furtherPos]` 这其中的位置都需要`for loop`

        - 时间复杂度：`O(2^n)`，最多有 `2^n`
          种从第一个位置到最后一个位置的跳跃方式，其中 `n` 是数组 `nums `的元素个数
        - 空间复杂度：`O(n)`，回溯法只需要栈的额外空间。
     */
    public boolean canJump1st(int[] nums) {
        return helper1st(nums, 0);
    }

    private boolean helper1st(int[] nums, int pos) {
        if (pos == nums.length - 1) return true;
        int furtherPos = Math.min(pos + nums[pos], nums.length - 1);
        for (int i = pos + 1; i <= furtherPos; i++) {
            if (helper1st(nums, i)) return true;
        }
        return false;
    }


    /*
        ##### 方法2：记忆化递归+回溯(自顶向下) 从0->nums.length()-1
        - 用`Index`的`enum`来记录某个坐标是否可以到达最末尾的位置，有三类值：
          - `GOOD`:可以跳到末尾位置
          - `BAD`:不可以跳到末尾位置
          - `UNKNOWN`:不知道是否可以跳到末尾位置
        - 一开始的时候都是`UNKNOWN`状态，最末尾的位置是`GOOD`状态，因为可以由自己跳到自己的位置
        - 出口时判断，是否是`GOOD`状态，计算`memo`,返回`true`的时候记录`GOOD`状态，返回`false`时记录`BAD`状态
     */
    Index[] memo;

    public boolean canJump2nd(int[] nums) {
        memo = new Index[nums.length];
        for (int i = 0; i < nums.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[nums.length - 1] = Index.GOOD;
        return helper2nd(nums, 0);
    }

    private boolean helper2nd(int[] nums, int pos) {
        if (memo[pos] != Index.UNKNOWN) return memo[pos] == Index.GOOD;
        int furtherPos = Math.min(pos + nums[pos], nums.length - 1);
        for (int i = pos + 1; i <= furtherPos; i++) {
            if (helper2nd(nums, i)) {
                memo[pos] = Index.GOOD;
                return true;
            }
        }
        memo[pos] = Index.BAD;
        return false;
    }


    enum Index {
        GOOD, BAD, UNKNOWN
    }

    /*
        ##### 方法3：记忆化递归+回溯(自底向上) 从`nums.length()-1->0`
        - 每次从右边位置开始寻找，记录下`memo`
     */
    // Index[] memo;
    public boolean canJump3rd(int[] nums) {
        int n = nums.length;
        memo = new Index[n];
        for (int i = 0; i < n; i++) {
            memo[i] = Index.BAD;
        }
        memo[n - 1] = Index.GOOD;
        for (int i = n - 2; i >= 0; i--) {
            int furtherPos = Math.min(i + nums[i], n - 1);
            for (int j = i + 1; j <= furtherPos; j++) {
                if (memo[j] == Index.GOOD) {
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }
        return memo[0] == Index.GOOD;
    }


    /*

     */
    public boolean canJump4th(int[] nums) {
        int n = nums.length;
        int lastPos = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

}
