## Level Order Binary Tree Traversal

### 方法1：利用函数打印当前层

有两种方式打印，printCurrentLevel是打印当前层的方法，从root节点开始，可挨层打印，printLevelorder是打印整个树的节点

#### 思路

```java
/*Function to print level order traversal of tree*/
printLevelorder(tree)
for d = 1 to height(tree)
   printCurrentLevel(tree, d);

/*Function to print all nodes at a current level*/
printCurrentLevel(tree, level)
if tree is NULL then return;
if level is 1, then
    print(tree->data);
else if level greater than 1, then
    printCurrentLevel(tree->left, level-1);
    printCurrentLevel(tree->right, level-1);
```



方法2：