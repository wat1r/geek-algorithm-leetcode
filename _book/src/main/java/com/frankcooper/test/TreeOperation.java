package com.frankcooper.test;

/**
 * smart哥
 */
public class TreeOperation {
    /*
    树的结构示例：
              1
            /   \
          2       3
         / \     / \
        4   5   6   7
    */

    // 用于获得树的层数
    public static int getTreeDepth(RBTree.RBNode root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.getLeft()), getTreeDepth(root.getRight())));
    }


    private static void writeArray(RBTree.RBNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        // 保证输入的树不为空
        if (currNode == null) return;
        // 0、默认无色
//       res[rowIndex][columnIndex] = String.valueOf(currNode.getValue());
        //1、颜色表示
        if (currNode.isColor()) {//黑色，加色后错位比较明显
            res[rowIndex][columnIndex] = ("\033[30;3m" + currNode.getValue() + "\033[0m");
        } else {
            res[rowIndex][columnIndex] = ("\033[31;3m" + currNode.getValue() + "\033[0m");
        }
        //2、R,B表示
//        res[rowIndex][columnIndex] = String.valueOf(currNode.getValue()+"-"+(currNode.isColor()?"B":"R")+"");

        // 计算当前位于树的第几层
        int currLevel = ((rowIndex + 1) / 2);
        // 若到了最后一层，则返回
        if (currLevel == treeDepth) return;
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel - 1;
        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
        if (currNode.getLeft() != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(currNode.getLeft(), rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }

        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
        if (currNode.getRight() != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currNode.getRight(), rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }


    public static void show(RBTree.RBNode root) {
        if (root == null) System.out.println("EMPTY!");
        // 得到树的深度
        int treeDepth = getTreeDepth(root);

        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i++) {
            for (int j = 0; j < arrayWidth; j++) {
                res[i][j] = " ";
            }
        }

        // 从根节点开始，递归处理整个树
        // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
        writeArray(root, 0, arrayWidth / 2, res, treeDepth);

        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        for (String[] line : res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2 : line[i].length() - 1;
                }
            }
            System.out.println(sb.toString());
        }
    }

    static class RBTree {
        static class RBNode {
            private RBNode left;
            private RBNode right;
            private int value;
            boolean color;

            public RBNode getLeft() {
                return left;
            }

            public void setLeft(RBNode left) {
                this.left = left;
            }

            public RBNode getRight() {
                return right;
            }

            public void setRight(RBNode right) {
                this.right = right;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public boolean isColor() {
                return color;
            }

            public void setColor(boolean color) {
                this.color = color;
            }
        }
    }
}


