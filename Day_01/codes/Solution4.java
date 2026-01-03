// 680. Valid Palindrome II
// https://leetcode.com/problems/valid-palindrome-ii/

package Day_01.codes;

class Solution4 {

    // Helper: Check if substring is palindrome
    public boolean palindromHelper(int i, int j, String s){
        while(i<j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i = i+1;
            j = j-1;
        }
        return true;
    }

    public boolean validPalindrome(String s) {
        // Step 1: Initialize pointers at both ends
        int i = 0, j = s.length()-1;
        
        // Step 2: Loop until pointers meet
        while(i<=j){
            char left = s.charAt(i);
            char right = s.charAt(j);

            // Step 3: If mismatch, try skipping one character
            if(left != right){
                // Try skipping left OR skipping right
                return palindromHelper(i+1, j, s) || palindromHelper(i, j-1, s);
            } else {
                // Step 4: Characters match, move inward
                i = i+1;
                j = j-1;
            }
        }
        return true; // Valid palindrome
    }
}