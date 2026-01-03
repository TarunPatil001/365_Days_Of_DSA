package codes;

// 15. 3Sum
// https://leetcode.com/problems/3sum/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution2 {

    // Helper: Find pairs that sum to -nums[f]
    public void twoSumHelper(int f, int[] nums, List<List<Integer>> res){
        // Step 1: Two pointers after fixed element
        int i = f + 1, j = nums.length-1;
        
        // Step 2: Standard two pointer loop
        while(i<j){
            int sum = nums[f] + nums[i] + nums[j];

            if(sum > 0){
                // Step 3a: Sum too big, decrease right
                j = j - 1;
            } else if(sum < 0){
                // Step 3b: Sum too small, increase left
                i = i + 1;
            } else {
                // Step 3c: Found triplet!
                res.add(Arrays.asList(nums[f], nums[i], nums[j]));
                i = i + 1;
                j = j - 1;

                // Step 4: Skip duplicates for left pointer
                while(i<j && nums[i] == nums[i-1]){
                    i = i + 1;
                }
                
                // Step 5: Skip duplicates for right pointer
                while(i<j && nums[j] == nums[j+1]){
                    j = j - 1;
                }   
            }
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        // Step 1: Sort the array
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
    
        // Step 2: Fix one element and find pairs
        for(int f=0; f<nums.length; f++){
            // Optimization: If fixed > 0, sum can't be 0
            if(nums[f] > 0){
                break;
            }

            // Step 3: Skip duplicates for fixed element
            else if(f == 0 || nums[f] != nums[f-1]){
                twoSumHelper(f, nums, res);
            }
        }

        return res;
    }
}