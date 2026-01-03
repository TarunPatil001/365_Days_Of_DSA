package codes;
// 88. Merge Sorted Array
// https://leetcode.com/problems/merge-sorted-array/

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Step 1: Initialize pointers at end of valid elements
        int i = m - 1, j = n - 1;
        // Step 2: k points to last position in nums1
        int k = m + n - 1;

        // Step 3: Compare from back and fill largest first
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                // Step 4a: nums1 element is larger
                nums1[k] = nums1[i];
                i--;
            } else {
                // Step 4b: nums2 element is larger
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        // Step 5: Copy remaining nums2 elements (if any)
        while (j >= 0) {
            nums1[k] = nums2[j];
            k--;
            j--;
        }
    }
}