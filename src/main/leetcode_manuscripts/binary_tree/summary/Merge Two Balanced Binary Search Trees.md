## Merge Two Balanced Binary Search Trees(合并两棵平衡二叉搜索树)

### Q:

有两棵二叉树，如AVL或红黑树。编写一个函数，将两个给定的平衡BST合并到一个平衡的二叉树中。第一棵树中有m个元素，另一棵树中有n个元素。合并函数为O（m+n）时间复杂度。

在以下解决方案中，假设树的大小也作为输入。如果没有给出大小，那么我们可以通过遍历树来获得大小。

### Solution:

#### 方法1（将第一棵树的元素插入第二棵树）

逐个获取第一个BST的所有元素，并将它们插入第二个BST。将元素插入自平衡BST需要Logn时间，其中n是BST的大小。因此，此方法的时间复杂度为Log(n)+Log(n+1)…Log(m+n-1)。此表达式的值将介于mLogn和mLog(m+n-1)之间。作为优化，我们可以选择较小的树作为第一棵树。

#### 方法2（按顺序合并遍历）

1） 按中序遍历第一棵树，并将遍历存储在一个临时数组arr1[]中。此步骤需要O(m)的时间。

2） 按中序遍历第二棵树，并将遍历存储在另一个临时数组arr2[]中。此步骤需要O(n)的时间。

3） 在步骤1和2中创建的数组是排序数组。将两个排序的数组合并为一个大小为m+n的数组。这一步需要O(m+n)时间。

4） 使用本文的方法从合并的数组构造一个平衡树。这一步需要O(m+n)时间。

> 该方法的时间复杂度为O(m+n)，优于方法1。即使输入BST不平衡，该方法也需要O(m+n)时间。

#### Code:

```java
    static class _2nd_2 {
        public static void main(String[] args) {
            _2nd_2 handler = new _2nd_2();
          /**
                   ┌── 300
              └── 100
                  │   ┌── 70
                  └── 50
                      └── 20
              │   ┌── 200
              └── 80
                  └── 40
              20 40 50 70 80 100 200 300 
						**/
            TreeNode node1 = TreeNodeIOUtils.transform("[100,50,300,20,70]");
            TreeNode node2 = TreeNodeIOUtils.transform("[80,40,200]");
            TreeNode resNode = handler.mergeTrees(node1, node2);
            handler.inorderUtil(resNode);

        }

        public List<Integer> storeInorder(TreeNode node) {
            List<Integer> list1 = new ArrayList<>();
            List<Integer> list2 = storeInorderUtil(node, list1);
            return list2;
        }

        //中序遍历存储BST的结果集
        public List<Integer> storeInorderUtil(TreeNode node, List<Integer> list) {
            if (node == null) return list;
            storeInorderUtil(node.left, list);
            list.add(node.val);
            storeInorderUtil(node.right, list);
            return list;
        }


        //合并list1和list2
        public List<Integer> merge(List<Integer> list1, List<Integer> list2, int m, int n) {
            List<Integer> list3 = new ArrayList<>();
            int i = 0, j = 0;
            while (i < m && j < n) {
                if (list1.get(i) < list2.get(j)) {
                    list3.add(list1.get(i));
                    i++;
                } else {
                    list3.add(list2.get(j));
                    j++;
                }
            }
            while (i < m) {
                list3.add(list1.get(i));
                i++;
            }
            while (j < n) {
                list3.add(list2.get(j));
                j++;
            }
            return list3;
        }

        //将list的start到end的元素转换成TreeNode
        public TreeNode arrayList2BST(List<Integer> list, int start, int end) {
            if (start > end) return null;//越界，返回
            int mid = (start + end) / 2;//中间点
            TreeNode node = new TreeNode(list.get(mid));//新建节点
            //构建左右子树节点
            node.left = arrayList2BST(list, start, mid - 1);
            node.right = arrayList2BST(list, mid + 1, end);
            return node;
        }

        //合并node1和node2的两棵BST
        public TreeNode mergeTrees(TreeNode node1, TreeNode node2) {
            List<Integer> list1 = storeInorder(node1);
            List<Integer> list2 = storeInorder(node2);
            int m = list1.size(), n = list2.size();
            List<Integer> list3 = merge(list1, list2, m, n);
            TreeNode resNode = arrayList2BST(list3, 0, list3.size() - 1);
            return resNode;
        }


        public void inorderUtil(TreeNode node) {
            if (node == null)
                return;
            inorderUtil(node.left);
            System.out.print(node.val + " ");
            inorderUtil(node.right);
        }

    }
```



#### 方法3（使用双端链表进行原地合并）

我们可以使用双端列表将树合并。以下是步骤：

1） 将给定的两个BST转换为双端列表；

2） 合并两个已排序的链表；

3） 从步骤2中创建的合并列表构建一个平衡BST；

该方法的时间复杂度也是O(m+n)，并且该方法在适当的位置进行转换。





### Reference

- [Merge Two Balanced Binary Search Trees](https://www.geeksforgeeks.org/merge-two-balanced-binary-search-trees/)



