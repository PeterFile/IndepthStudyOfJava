package com.chen;

import java.util.Arrays;

/**
 * @author CWP
 * @version 1.0
 * @Title: Binary
 * @Package com.chen
 * @Description: 二分边界
 * @date 2023/9/5 23:26
 */
public class Binary {
    public static void main(String[] args) {
        int[] ary = new int[]{5,7,7,8,8,10};
        int[] res = new int[]{left_bound(ary, 8), right_bound(ary, 8)};
        System.out.println(Arrays.toString(res));
    }

    static int left_bound(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target)
                l = mid + 1;
            else
                r = mid - 1;
        }

        if (l >= nums.length || nums[l] != target) return -1;
        return l;
    }

    static int right_bound(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > target)
                r = mid - 1;
            else
                l = mid + 1;
        }

        if (r < 0 || nums[r] != target) return -1;
        return r;
    }
}