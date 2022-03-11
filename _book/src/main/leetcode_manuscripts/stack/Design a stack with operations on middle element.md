## Design a stack with operations on middle element(设计一个操作中间元素的栈)

如何在`O(1)`的时间复杂度下实现支持以下操作的栈？

- 1).`push()`:将元素添加到栈顶部。

- 2).`pop()`:从栈顶部移除元素。
- 3).`findmidle()`:返回栈的中间元素。

- 4).`deleteMiddle()`:将删除中间元素。

> 推栈和弹栈是标准的栈操作。

重要的问题是，是否使用链表或数组来实现栈？

请注意，需要查找并删除中间元素。对于数组而言，从中间删除元素时间复杂度不是O(1)。此外，可能需要在`push()`时向上移动中间指针，在`pop()`时向下移动。在单链表中，无法在两个方向上移动中间指针。

其思想是使用双端链表(`DLL`)。通过维护中间指针，可以在`O(1)`时间内删除中间元素。可以使用`previous`和`next`指针在两个方向上移动中间指针。

下面是`push()`、`pop()`和`findmidle()`操作的实现。`deleteMidle()`的实现留作练习。如果栈中有偶数个元素，`findmidle()`将返回中间的第二个元素。例如，如果栈包含`{1，2，3，4}`，那么`findmidle()`将返回3。

### Code:

```java
static class _3rd {
    public static void main(String[] args) {
        _3rd handler = new _3rd();
        handler.test();
    }

    private void test() {
        MyStack myStack = new MyStack();
        myStack.push(11);
        myStack.push(22);
        myStack.push(33);
        myStack.push(44);
        myStack.push(55);
        myStack.push(66);
        myStack.push(77);
        System.out.println("Item popped is " + myStack.pop());
        System.out.println("Item popped is " + myStack.pop());
        System.out.println("Middle Element is " + myStack.findMiddle());
        System.out.println("Deleted Middle Element is  " + myStack.deleteMiddle());
        System.out.println("Middle Element is " + myStack.findMiddle());
    }


    class DLLNode {
        private DLLNode prev;
        private DLLNode next;
        private int data;

        public DLLNode(int data) {
            this.data = data;
        }
    }

    class MyStack {
        private DLLNode head;//头指针
        private DLLNode mid;//中间指针
        private int count;

        public MyStack() {
        }

        public void push(int data) {
            DLLNode node = new DLLNode(data);
            //当前的节点的next指针指向head 并将count++
            node.prev = null;
            node.next = head;
            count++;
            //如果只有一个元素，移动mid到当前节点
            if (count == 1) {
                mid = node;
            } else {//如果不止一个元素，将head指针的prev指针指向当前节点
                head.prev = node;
                if (count % 2 != 0) {//odd 奇数 移动mid指针
                    mid = mid.prev;
                }
            }
            //head指针前移
            head = node;
        }

        public int pop() {
            if (count == 0) {
                System.out.println("Stack is empty!");
                return -1;
            }
            int item = head.data;
            //移动head指针到下一个节点
            head = head.next;
            //head指针不为空的时候，prev指针置为空
            if (head != null) {
                head.prev = null;
            }
            count--;
            if (count % 2 == 0) {//数量-1 ，偶数时，移动到当前mid的下一个节点
                mid = mid.next;
            }
            return item;
        }


        public int findMiddle() {
            if (count == 0) {
                System.out.println("Stack is empty!");
                return -1;
            }
            return mid.data;
        }


        public int deleteMiddle() {
            if (count == 0) {
                System.out.println("Stack is empty!");
                return -1;
            }
            int item = mid.data;
            mid.next.prev = mid.prev;
            mid.prev.next = mid.next;
            mid = mid.next;
            count--;
            return item;

        }


    }
}
```

### Reference

- [Design a stack with operations on middle element](https://www.geeksforgeeks.org/design-a-stack-with-find-middle-operation/)

