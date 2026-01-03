// 977. Squares of a Sorted Array
// https://leetcode.com/problems/squares-of-a-sorted-array/description/

package Day_01.codes;

class Solution3 {
    public int[] sortedSquares(int[] nums) {
        // Step 1: Create result array of same size
        int[] sqArr = new int[nums.length];

        // Step 2: Initialize pointers at both ends
        int i = 0, j = nums.length - 1;
                
        // Step 3: k points to where we fill next (from back)
        int k = nums.length - 1;

        // Step 4: Compare absolute values at both ends
        while (i <= j) {

            if (Math.abs(nums[i]) > Math.abs(nums[j])) {
                // Step 5a: Left has larger square, use it
                sqArr[k] = nums[i] * nums[i];
                i++;
            
            } else {
                // Step 5b: Right has larger square, use it
                sqArr[k] = nums[j] * nums[j];
                j--;
            }

            // Step 6: Move to next position in result
            k--;
        }

        return sqArr;
    }
}
