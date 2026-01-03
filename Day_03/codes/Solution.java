package codes;
// 167. Two Sum II - Input Array Is Sorted
// https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // Step 1: Initialize pointers at both ends
        int i = 0, j = numbers.length-1;
        
        // Step 2: Loop until pointers meet
        while(i<j){
            int sum = numbers[i] + numbers[j];
            
            if(sum > target){
                // Step 3a: Sum too big, decrease right pointer
                j--;
            } else if(sum < target){
                // Step 3b: Sum too small, increase left pointer
                i++;
            } else {
                // Step 3c: Found! Return 1-based indices
                return new int[] { (i+1), (j+1) };
            }
        }
        
        return new int[] { -1, -1 }; // No pair found
    }
}