package com.frankcooper.utils;

import org.junit.Assert;

/**
 * @Date 2020/9/13
 * @Author Frank Cooper
 * @Description
 */
public class PrintUtils {


    public static void main(String[] args) {
/*        int[][] matrix = PrintUtils.processSymbol("[[7,3,6],[1,4,5],[9,8,2]]");
        PrintUtils.printMatrix(matrix);*/
        //----
        String s = "10111";
        s = "10011110000011";
//        transformBinStr2Int(s);
        toBinaryString(9,6);
        toBinaryString(18,6);
    }


    public static int[][] processSymbol(String str) {
        str = str.replace("[[", "").replace("]]", "");

        String[] arr = str.split("],\\[");
        int R = arr.length;
        int C = 0;
        if (arr[0] != null) C = arr[0].split(",").length;
        int[][] matrix = new int[R][C];
        for (int r = 0; r < R; r++) {
            String[] seg = arr[r].split(",");
            for (int c = 0; c < C; c++) {
                matrix[r][c] = Integer.parseInt(seg[c]);
            }
        }
        printMatrix(matrix, 6);
        return matrix;
    }


    public static void printMatrix(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                System.out.print(String.format("%3s", matrix[i][j]) + " ");
            }
            System.out.println();
        }
        System.out.println("--------------");
    }

    public static void printMatrix(long[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                System.out.print(String.format("%3s", matrix[i][j]) + " ");
            }
            System.out.println();
        }
        System.out.println("--------------");
    }


    public static void printMatrix(boolean[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        for (int j = 0; j < cols; ++j) {
            System.out.print(String.format("%5d", j));
        }
        System.out.println();
        for (int i = 0; i < rows; ++i) {
            System.out.printf("%d", i);
            for (int j = 0; j < cols; ++j) {
                System.out.print(String.format("%4s", matrix[i][j] ? "T" : "F") + " ");
            }
            System.out.println();
        }
        System.out.println("--------------");
    }


    public static void printMatrix(int[][] matrix, boolean bin) {
        int rows = matrix.length, cols = matrix[0].length;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (bin) {
                    System.out.printf("%3s", addZeroForNum(Integer.toBinaryString(matrix[i][j]), 2) + " ");
                }
            }
            System.out.println();
        }
        System.out.println("--------------");
    }


    public static void printMatrix(double[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                System.out.print(String.format("%3s", matrix[i][j]) + " ");
            }
            System.out.println();
        }
        System.out.println("--------------");
    }


    public static void printMatrix(int[][] matrix, int d) {
        int rows = matrix.length, cols = matrix[0].length;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
//                if (Integer.MAX_VALUE == matrix[i][j]) {
//                    System.out.print("INF ");
//                } else {
//                    System.out.print(String.format("%" + d + "s", matrix[i][j]) + " ");
//                }
                System.out.print(String.format("%" + d + "s", matrix[i][j]) + " ");
            }
            System.out.println();
        }
        System.out.println("--------------");
    }


    public static void printMatrix(int[][][] matrix) {
        int m = matrix.length, n = matrix[0].length, p = matrix[0][0].length;
        System.out.printf("m:%d,n:%d,p:%d\n", m, n, p);
        for (int i = 0; i < m; ++i) {
            System.out.printf("i:%d\n", i);
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < p; ++k) {
                    System.out.print(String.format("%3s", matrix[i][j][k]) + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println("--------------");
    }


    public static void printMatrix(int[][][] matrix, int sense) {
        int m = matrix.length, n = matrix[0].length, p = matrix[0][0].length;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {

                System.out.print(String.format("%6s", "(" + matrix[i][j][0] + "," + matrix[i][j][1] + ")") + " ");
            }
            System.out.println();
        }
        System.out.println("--------------");
    }


    public static String toBinaryString(int i, int strLen) {
        String res = addZeroForNum(Integer.toBinaryString(i), strLen);
        System.out.println(res);
        return res;
    }

    /**
     * 将一个01的二进制字符串转换成int。没有考虑数据溢出
     *
     * 如"10011110000011" (2进制) --> 10115 (10进制)
     * @param s
     * @return
     */
    public static int transformBinStr2Int(String s) {
        int ans = 0;
        int n = s.length();
        for (int i = n - 1; i >= 0; --i) {
            int cur = s.charAt(i) - '0';
            ans |= cur << (n - 1 - i);
        }
//        Assert.assertEquals(s, Integer.toBinaryString(ans));
        System.out.println(ans);
        return ans;

    }


    public static String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(str);// 左补0
                // sb.append(str).append("0");//右补0
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }
}
