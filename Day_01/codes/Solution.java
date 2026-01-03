// 125. Valid Palindrome
// https://leetcode.com/problems/valid-palindrome/description/

package Day_01.codes;
class Solution {
    public boolean isPalindrome(String s) {
        // Step 1: Initialize two pointers at opposite ends
        int i = 0, j = s.length()-1;
        
        // Step 2: Loop until pointers meet
        while(i<j){
            char left = s.charAt(i);
            char right = s.charAt(j);

            // Step 3: Skip non-alphanumeric characters from left
            if(!Character.isLetterOrDigit(left)){
                i = i+1;
                continue;
            }

            // Step 4: Skip non-alphanumeric characters from right
            if(!Character.isLetterOrDigit(right)){
                j = j-1;
                continue;
            }

            // Step 5: Compare characters (case-insensitive)
            if(Character.toLowerCase(left) != Character.toLowerCase(right)){
                return false; // Mismatch found
            }

            // Step 6: Move both pointers inward
            i = i+1;
            j = j-1;
        }
        return true; // All characters matched
    }
}