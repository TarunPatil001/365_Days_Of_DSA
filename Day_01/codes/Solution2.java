// 344. Reverse String
// https://leetcode.com/problems/reverse-string/description/

package Day_01.codes;

class Solution2 {
    public void reverseString(char[] s) {
        // Step 1: Initialize pointers at both ends
        int i = 0, j = s.length-1;

        // Step 2: Loop until pointers meet in middle
        while(i<j){
            // Step 3: Swap characters at i and j
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;

            // Step 4: Move pointers toward center
            i = i+1;
            j = j-1;
        }
    }
}