package com.frankcooper;

public class BitOpUtils {

    static BitOpUtils handler = new BitOpUtils();


    public static void main(String[] args) {
//            handler.testBin2Ten();

//        int a = -8;
//        System.out.println(a >> 3);
//        int x = 1, y = 2;
//        x ^= y;
//        y ^= x;
//        x ^= y;
//
//        System.out.printf("x:%d,y:%d\n", x, y);
//        x = 1;
//        x = -(~x);

        handler.enumerateSubset();
    }


    int[] k = {1, 2, 3, 4, 5};


    private void enumerateSubset() {
        int s, n = 5;
        //
        for (s = 0; s < (1 << n); s++) {
            System.out.printf("%s--->", addZeroForNum(Integer.toBinaryString(s), 5));
            for (int i = 0; i < n; i++) {
                if ((s & (1 << i)) != 0) {
                    System.out.printf("%d ", i);
                }
            }
            System.out.printf("\n");
        }
    }


    private void testBin2Ten() {
        String[] arr = new String[]{"10011", "11001", "10001", "11011", "01100", "01010", "01000000"
        };
        for (String bin : arr) {
            bin2Ten(bin);
        }
    }


    private void testOne() {
        for (int i = 1; i <= 10; i++) {
            int positive = i;
            int negative = -i;
            String nStr = Integer.toBinaryString(negative);
            System.out.println(String.format("%d:%s\t%d:%s\t%s",
                    positive, addZeroForNum(Integer.toBinaryString(positive), 4)
                    , negative, nStr.substring(nStr.length() - 4),
                    addZeroForNum(Integer.toBinaryString(positive & negative), 4)
                    )
            );
        }
    }

    /**
     * 把2进制字符串key，转成10进制keys
     * 数字2代表进制，可以是各种进制，转换成10进制
     *
     * @param bin
     * @return
     */
    public static String bin2Ten(String bin) {
        String res = String.valueOf(Integer.parseInt(bin, 2));
        System.out.printf("binary:%s--->ten:%s\n", bin, res);
        return res;
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
