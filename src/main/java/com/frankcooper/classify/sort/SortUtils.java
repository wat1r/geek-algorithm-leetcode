package com.frankcooper.classify.sort;

import java.util.Arrays;

public class SortUtils {

    static SortUtils handler = new SortUtils();

    public static void main(String[] args) {
        int[] numbers = {20, 40, 50, 10, 60,};
        handler.mergeSort(numbers);

    }

    public void mergeSort(int[] arr) {
        System.out.println(Arrays.toString(arr));
        if (arr == null || arr.length < 2) return;
        mergeSortProcess(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }


    private void mergeSortProcess(int[] arr, int left, int right) {
        if (left == right) return;//递归的出口条件
        int mid = (right + left) / 2;//取mid，二分中的mid取值有很多讲究
        mergeSortProcess(arr, left, mid);//左半部分
        mergeSortProcess(arr, mid + 1, right);//右半部分
        merge(arr, left, mid, right);//对每个部分进行merge
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];//辅助数组，[left,right]
        //p1表示从[left,mid]的指针
        //p2表示从[mid+1,righ1]的指针
        //i表示辅助数组help的指针
        //套在两个while循环外的，当p1,p2均为到达边界的时候，谁小取谁，进辅助数组help
        int p1 = left, p2 = mid + 1, i = 0;
        while (p1 <= mid && p2 <= right) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        //下面的两个while只会发生一个，将剩余的部分移进辅助数组help
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= right) {
            help[i++] = arr[p2++];
        }
        //排序好的 辅助数组反向拷贝进原数组arr，注意下标
        for (int k = 0; k < help.length; k++) {
            arr[left + k] = help[k];
        }
    }


}
