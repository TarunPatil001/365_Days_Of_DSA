package codes;
// 1. Two Sum
// https://leetcode.com/problems/two-sum/description/

import java.util.HashMap;
import java.util.Map;

class Solution3 {
    public int[] twoSum(int[] nums, int target) {
        // Step 1: Create a map to store number -> index
        Map<Integer, Integer> map = new HashMap<>();

        // Step 2: Loop through each number
        for(int i=0; i<nums.length; i=i+1){
            // Step 3: Calculate what number we need
            int lookingFor = target - nums[i];

            // Step 4: Check if that number exists in map
            if(map.containsKey(lookingFor)){
                // Found! Return both indices
                return new int[] {
                    map.get(lookingFor),
                    i
                };
            }

            // Step 5: Store current number and its index
            map.put(nums[i], i);
        }

        return new int[]{ -1, -1 }; // No pair found
    }
}