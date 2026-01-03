package codes;
// 2824. Count Pairs Whose Sum is Less than Target
// https://leetcode.com/problems/count-pairs-whose-sum-is-less-than-target/description/

import java.util.Collections;
import java.util.List;

class Solution2 {
    public int countPairs(List<Integer> nums, int target) {
        // Step 1: Sort the array first
        Collections.sort(nums);

        // Step 2: Initialize pointers at both ends
        int i = 0, j = nums.size() - 1;

        int count = 0;

        // Step 3: Loop until pointers meet
        while(i < j){
            int sum = nums.get(i) + nums.get(j);

            if(sum < target){
                // Step 4a: All pairs (i, i+1), (i, i+2)... (i, j) are valid
                count = count + (j-i);
                i = i+1; // Move left pointer
            } else {
                // Step 4b: Sum too big, need smaller numbers
                j = j-1;
            }
        }

        return count;
    }
}