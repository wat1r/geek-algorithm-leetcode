## Connect nodes at same level(连接同一层的节点)

### Q:

```java
Input Tree
       A
      / \
     B   C
    / \   \
   D   E   F


Output Tree
       A--->NULL
      / \
     B-->C-->NULL
    / \   \
   D-->E-->F-->NULL
```

### 方法1（拓展层序遍历或BFS）

参考**层序遍历**，可以很容易地扩展到连接相同层的节点。可以增加队列条目以包含节点级别，也就是0表示根，1表示根的子节点，依此类推。因此，队列节点现在将包含指向树节点和整数层级的指针。当我们让一个节点排队时，确保在队列中为节点设置了正确的层级。为了设置nextRight，对于每个节点N，我们将下一个节点从队列中出列，如果下一个节点的级别数相同，我们将N的nextRight设置为出列节点的地址，否则我们将N的nextRight设置为NULL。

我们初始化一个指向前一个节点的节点Prev。在同一级别中遍历节点时，我们跟踪上一个节点，并在每次迭代中将nextRight指针指向当前节点。

#### Code:

```java
   public void connect(ConnectTreeNode root) {
            Queue<ConnectTreeNode> q = new LinkedList<>();
            q.offer(root);
            ConnectTreeNode cur = null;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    ConnectTreeNode prev = cur;
                    cur = q.poll();
                    if (i > 0) prev.nextRight = cur;
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                }
                cur.nextRight = null;
            }
        }
```

### 方法2（拓展先序遍历）

这种方法只适用于完全二叉树。在该方法中，我们以先序顺序设置nextRight，以确保父级的nextRight设置在其子级之前。当我们在节点p时，我们设置其左、右子节点的nextRight。由于树是完全二叉树，p的左子节点（p->left->nextRight）的nextRight将始终是p的右子节点，p的右子节点（p->right->nextRight）的nextRight将始终是p的nextRight的左子节点（如果p不是其级别最右边的节点）。如果p是最右边的节点，那么p的右子节点的nextRight将为NULL。

#### Code:

```java
       public void connectII(ConnectTreeNode root) {
            //出口条件
            if (root == null) return;
            //当前节点的左子节点，执行当前节点的右子节点
            if (root.left != null) {
                root.left.nextRight = root.right;
            }
            //当前节点的右子节点指向当前节点的下一个节点的左子节点或者null
            if (root.right != null) {
                root.right.nextRight = (root.nextRight != null ? root.nextRight.left : null);
            }
            //处理当前节点的左右子节点
            connectII(root.left);
            connectII(root.right);
        }
```

#### Test:

```java
  // Populates nextRight pointer in all nodes
            handler.connectII(root);

            // Let us check the values
            // of nextRight pointers
            System.out.print("Following are populated nextRight pointers in the tree"
                    + " (-1 is printed if there is no nextRight)\n");
            System.out.print(
                    "nextRight of " + root.val + " is " + (root.nextRight != null ? root.nextRight.val : -1) + "\n");
            System.out.print("nextRight of " + root.left.val + " is "
                    + (root.left.nextRight != null ? root.left.nextRight.val : -1) + "\n");
            System.out.print("nextRight of " + root.right.val + " is "
                    + (root.right.nextRight != null ? root.right.nextRight.val : -1) + "\n");
            System.out.print("nextRight of " + root.left.left.val + " is "
                    + (root.left.left.nextRight != null ? root.left.left.nextRight.val : -1) + "\n");

//output
Following are populated nextRight pointers in the tree (-1 is printed if there is no nextRight)
nextRight of 10 is -1
nextRight of 8 is 2
nextRight of 2 is -1
nextRight of 3 is -1
```



### Reference

- [Connect nodes at same level](https://www.geeksforgeeks.org/connect-nodes-at-same-level/)

