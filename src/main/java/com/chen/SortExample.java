package com.chen;

import java.util.Arrays;

/**
 * @author CWP
 * @version 1.0
 * @Title: SortExample
 * @Package com.chen
 * @Description: 十大排序算法
 * @date 2023/8/24 10:51
 */
public class SortExample {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 38, 15, 74, 16};

//        int[] bubble = bubbleSort(arr);
//        System.out.println("冒泡排序：" + Arrays.toString(bubble));

//        int[] selection = selectionSort(arr);
//        System.out.println("选择排序：" + Arrays.toString(selection));

        int[] insertion = insertionSort(arr);
        System.out.println("插入排序：" + Arrays.toString(insertion));

        int[] shell = shellSort(arr);
        System.out.println("希尔排序：" + Arrays.toString(shell));

        int[] merge = mergeSort(arr);
        System.out.println("归并排序：" + Arrays.toString(merge));

        int[] quick = quickSort(arr);
        System.out.println("快速排序：" + Arrays.toString(quick));

        int[] heap = heapSort(arr);
        System.out.println("堆排序：" + Arrays.toString(heap));
    }

    /**
     * 冒泡排序
     * 比较相邻元素，第一个比第二个大则交换，对每一对相邻元素进行该操作，在最后得出最大元素。对每个元素重复上面的操作。
     * 稳定性：稳定
     * 时间复杂度：最坏O(n)，最差(O^2)，平均O(n^2)
     * 空间复杂度：O(1)
     *
     * @param arr
     * @return arr
     */
    public static int[] bubbleSort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            boolean flag = true;

            for (int j = 0; j < n - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;
                }
            }

            if (flag) break;
        }

        return arr;
    }

    /**
     * 选择排序
     * 在未排序序列中找到最小的元素放到起始位置，继续从未排序序列中找到最小值排到已排列顺序的末尾，不断重复直到排序结束
     * 稳定性：不稳定
     * 时间复杂度：O(n^2)
     * 空间复杂度：o(1)
     *
     * @param arr
     * @return
     */
    public static int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
        return arr;
    }

    /**
     * 插入排序
     * 从第一个元素开始，该元素认为已经被排序，取出下一个元素在已排序序列中从后向前扫描，已排序的元素中比新元素小的都往后移，
     * 直到出现小于或等于新元素的元素，将新元素插入到该元素后面，重复上面步骤。
     * 稳定性：稳定
     * 时间复杂度：最佳：O(n)，最差：O(n^2)，平均O(n^2)
     * 时间复杂度：O(1)
     *
     * @param arr
     * @return
     */
    public static int[] insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int preIndex = i - 1;
            int current = arr[i];

            while (preIndex >= 0 && current < arr[preIndex]) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }

            arr[preIndex + 1] = current;
        }
        return arr;
    }

    /**
     * 希尔排序
     * 选择增量gap=length/2，增量选择可以用一个序列来表示（增量序列）。先将待排序的序列分割成若干个子序列进行插入排序。
     * 稳定性：不稳定
     * 时间复杂度：最佳：O(nlogn)，最差：O(n^2)，平均：O(nlogn)
     * 空间复杂度：O(1)
     *
     * @param arr
     * @return
     */
    public static int[] shellSort(int[] arr) {
        int n = arr.length;
        int gap = n / 2;
        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                int current = arr[i];
                int preIndex = i - gap;

                while (preIndex >= 0 && current < arr[preIndex]) {
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                arr[preIndex + gap] = current;
            }

            gap /= 2;
        }
        return arr;
    }

    /**
     * 归并排序
     * 是一个递归过程，输入序列只有一个元素时返回，不断将n长度的序列分为两个n/2的子序列，分别调用合并数组的算法排序。
     * 稳定性：稳定
     * 时间复杂度：最佳：O(nlogn)，最差：O(nlogn)，平均：O(nlogn)
     * 空间复杂度：O(n)
     *
     * @param arr
     * @return
     */
    public static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) return arr;
        int mid = arr.length / 2;
        int[] arr_1 = Arrays.copyOfRange(arr, 0, mid);
        int[] arr_2 = Arrays.copyOfRange(arr, mid, arr.length);
        return merge(mergeSort(arr_1), mergeSort(arr_2));
    }

    /**
     * 合并数组，小的在前大的在后
     *
     * @param arr_1
     * @param arr_2
     * @return sorted_arr
     */
    public static int[] merge(int[] arr_1, int[] arr_2) {
        int[] sorted_arr = new int[arr_1.length + arr_2.length];
        int idx = 0, idx_1 = 0, idx_2 = 0;

        while (idx_1 < arr_1.length && idx_2 < arr_2.length) {
            if (arr_1[idx_1] < arr_2[idx_2]) {
                sorted_arr[idx] = arr_1[idx_1];
                idx_1++;
            } else {
                sorted_arr[idx] = arr_2[idx_2];
                idx_2++;
            }
            idx++;
        }

        if (idx_1 < arr_1.length) {
            while (idx_1 < arr_1.length) {
                sorted_arr[idx] = arr_1[idx_1];
                idx_1++;
                idx++;
            }
        } else {
            while (idx_2 < arr_2.length) {
                sorted_arr[idx] = arr_2[idx_2];
                idx_2++;
                idx++;
            }
        }

        return sorted_arr;
    }

    /**
     * 快速排序
     * 从序列中随机挑选一个元素作为“基准”，我的代码选的是最后一个元素。重新排列元素，比基准小的在前，比基准大的在后，通过递归不断快排。
     * 稳定性：不稳定
     * 时间复杂度：最佳：O(nlogn)， 最差：O(nlogn)，平均：O(nlogn)
     *
     * @param arr
     * @return arr
     */
    public static int[] quickSort(int[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
        return arr;
    }

    public static void quickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            int position = partition(arr, low, high);
            quickSortHelper(arr, low, position - 1);
            quickSortHelper(arr, position + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int pointer = low;

        for (int i = low; i < high; i++) {
            if (arr[i] <= pivot) {
                int temp = arr[i];
                arr[i] = arr[pointer];
                arr[pointer] = temp;
                pointer++;
            }
        }

        int temp = arr[pointer];
        arr[pointer] = arr[high];
        arr[high] = temp;
        return pointer;
    }

    static int n;
    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
    private static void heapify(int[] arr, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest])
            largest = left;

        if (right < n && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {
            swap(arr, largest, i);
            heapify(arr, largest);
        }
    }

    static void buildMaxHeap(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, i);
        }
    }

    public static int[] heapSort(int[] arr) {
        n = arr.length;
        buildMaxHeap(arr);
        for (int i = arr.length - 1; i > 0 ; i--) {
            swap(arr, 0, i);
            n--;
            heapify(arr, 0);
        }

        return arr;
    }
}