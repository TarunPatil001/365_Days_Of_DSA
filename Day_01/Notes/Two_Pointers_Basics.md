# 📘 DSA Pattern: Two Pointers (Basics)

> **Purpose:** Master the art of manipulating arrays and strings by looking at them from both ends.

---

## 🧩 1. Pattern Name

> **Two Pointers (Opposite Ends)**

* **Category:** Array / String
* **Difficulty Level:** Easy
* **Use Case:** When you need to reverse something, check for symmetry (palindrome), or compare elements from both sides.

---

## 🧠 2. Intuition (Very Important)

> Imagine you are reading a book.

*   Usually, you read from left to right (one pointer).
*   But what if you want to check if the first page matches the last page?
*   You put one finger on the **first page** and one finger on the **last page**.
*   You compare them, then move your fingers **inwards** towards the middle.
*   This is exactly what the Two Pointer pattern does! It saves time because you process the list in just one pass.

---

## 🖼️ 3. Visual Explanation / Diagram

```
Checking if "LEVEL" is a palindrome

[ L, E, V, E, L ]
  ↑           ↑
 left       right
 (Match! Move in)

[ L, E, V, E, L ]
     ↑     ↑
    left right
    (Match! Move in)

[ L, E, V, E, L ]
        ↑
      left/right
      (Meet in middle -> Done!)
```

*   **Start:** One pointer at index `0`, one at index `n-1`.
*   **Move:** `left` goes forward (`+1`), `right` goes backward (`-1`).
*   **Stop:** When they meet or cross each other (`left >= right`).

---

## 🧪 4. Dry Run (Step-by-Step)

**Problem:** Reverse array `[1, 2, 3, 4]`

| Step | State | Action | Result |
| :--- | :--- | :--- | :--- |
| 1 | `L=0` (val 1), `R=3` (val 4) | Swap 1 and 4 | `[4, 2, 3, 1]` |
| 2 | Move pointers | `L` becomes 1, `R` becomes 2 | |
| 3 | `L=1` (val 2), `R=2` (val 3) | Swap 2 and 3 | `[4, 3, 2, 1]` |
| 4 | Move pointers | `L` becomes 2, `R` becomes 1 | `L > R` -> Stop |

---

## 🧠 5. Algorithm Steps

1.  Create a pointer `left` at the start (`0`).
2.  Create a pointer `right` at the end (`length - 1`).
3.  Loop while `left` is less than `right`.
4.  Do your job (compare, swap, check characters).
5.  Move `left` forward (`left++`).
6.  Move `right` backward (`right--`).

---

## 💻 6. Pseudocode

```text
left = 0
right = length - 1

while left < right:
    if condition_met(arr[left], arr[right]):
        do_something()
    
    left = left + 1
    right = right - 1
```

---

## 🧾 7. Solved Problems

### 🔹 1. Valid Palindrome (125)
> **Question:** Check if a string is a palindrome (reads same forward and backward), ignoring non-alphanumeric characters.
> [Link to Problem](https://leetcode.com/problems/valid-palindrome/description/)

**💡 Pattern Connection:** We compare characters from outside in. If they match, we shrink the window. If they don't, it's not a palindrome.

```java
class Solution {
    public boolean isPalindrome(String s) {
        // Step 1: Initialize two pointers at opposite ends
        int i = 0, j = s.length()-1;
        
        // Step 2: Loop until pointers meet
        while(i < j){
            char left = s.charAt(i);
            char right = s.charAt(j);

            // Step 3: Skip non-alphanumeric from left
            if(!Character.isLetterOrDigit(left)){
                i++; continue;
            }
            // Step 4: Skip non-alphanumeric from right
            if(!Character.isLetterOrDigit(right)){
                j--; continue;
            }

            // Step 5: Compare characters (case-insensitive)
            if(Character.toLowerCase(left) != Character.toLowerCase(right)){
                return false; // Mismatch found
            }
            
            // Step 6: Move both pointers inward
            i++; 
            j--;
        }
        return true; // All matched
    }
}
```
### 🔹 2. Reverse String (344)
> **Question:** Reverse an array of characters in-place.
> [Link to Problem](https://leetcode.com/problems/reverse-string/description/)

**💡 Pattern Connection:** Swapping the start and end elements effectively reverses the order as we move inward.

```java
class Solution2 {
    public void reverseString(char[] s) {
        // Step 1: Initialize pointers at both ends
        int i = 0, j = s.length-1;

        // Step 2: Loop until pointers meet
        while(i < j){
            // Step 3: Swap characters at i and j
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            
            // Step 4: Move pointers toward center
            i++; 
            j--;
        }
    }
}
```

### 🔹 3. Squares of a Sorted Array (977)
> **Question:** Given a sorted array, return squares of each number, also in sorted order.
> [Link to Problem](https://leetcode.com/problems/squares-of-a-sorted-array/description/)

**💡 Pattern Connection:** The largest squares are at the *ends* (very negative or very positive). We compare ends and fill the result from the back.

```java
class Solution3 {
    public int[] sortedSquares(int[] nums) {
        // Step 1: Create result array
        int[] sqArr = new int[nums.length];
        // Step 2: Initialize pointers at both ends
        int i = 0, j = nums.length - 1;
        // Step 3: k fills result from back
        int k = nums.length - 1;

        // Step 4: Compare absolute values at ends
        while (i <= j) {
            if (Math.abs(nums[i]) > Math.abs(nums[j])) {
                // Step 5a: Left is larger
                sqArr[k] = nums[i] * nums[i];
                i++;
            } else {
                // Step 5b: Right is larger
                sqArr[k] = nums[j] * nums[j];
                j--;
            }
            // Step 6: Move to next result position
            k--;
        }
        return sqArr;
    }
}
```

### 🔹 4. Valid Palindrome II (680)
> **Question:** Can you make it a palindrome by deleting **at most one** character?
> [Link to Problem](https://leetcode.com/problems/valid-palindrome-ii/)

```java
class Solution4 {
    // Helper: Check if substring is palindrome
    public boolean palindromHelper(int i, int j, String s){
        while(i < j){
            if(s.charAt(i) != s.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }

    public boolean validPalindrome(String s) {
        // Step 1: Initialize pointers at both ends
        int i = 0, j = s.length()-1;
        
        // Step 2: Loop until pointers meet
        while(i <= j){
            // Step 3: If mismatch, try skipping one
            if(s.charAt(i) != s.charAt(j)){
                return palindromHelper(i+1, j, s) || palindromHelper(i, j-1, s);
            }
            // Step 4: Move inward
            i++; j--;
        }
        return true;
    }
}
```

---

## ⏱️ 8. Complexity Analysis

*   **Time Complexity:** **O(N)**
    *   We touch every element exactly once (or half the elements twice). It's linear.
*   **Space Complexity:** **O(1)**
    *   We only used two variables (`left` and `right`). We didn't create a new array.

---

## ⚠️ 9. Edge Cases

*   **Empty String/Array:** Loop condition `left < right` fails immediately. Safe.
*   **Single Character:** `left` equals `right` initially. Loop doesn't run. Safe.
*   **Odd vs Even length:** The `while (left < right)` handles both perfectly.

---

## 🔁 10. Similar Problems

| Problem Name | Platform | Link |
| :--- | :--- | :--- |
| Valid Palindrome | LeetCode | [Link](https://leetcode.com/problems/valid-palindrome/) |
| Reverse String | LeetCode | [Link](https://leetcode.com/problems/reverse-string/) |
| Squares of Sorted Array | LeetCode | [Link](https://leetcode.com/problems/squares-of-a-sorted-array/) |

---

## 🧠 11. Pattern Recognition Tips

*   **Keywords:** "Reverse", "Palindrome", "Symmetric", "Swap ends".
*   **Input:** Array or String.
*   **Goal:** You need to compare or swap elements from opposite ends.

---

## ❌ 12. Common Mistakes

*   **Using `left <= right` unnecessarily:** For swapping, you don't need to swap the middle element with itself. `left < right` is enough.
*   **Forgetting to move pointers:** If you forget `left++` or `right--`, you get an infinite loop!

---

## 🧠 13. Active Recall Quiz (Try to answer without looking up!)

1.  **Why** is the time complexity O(N) and not O(N^2)?
    *   *Hidden Answer:* Because we only touch each element once (left moves right, right moves left, they never reset).
2.  **When** should you stop the loop? `left < right` or `left <= right`?
    *   *Hidden Answer:* Use `<` if the middle element doesn't matter (swapping). Use `<=` if you need to process the middle element (like in Squares of Sorted Array).
3.  **What** happens if you forget `i++` or `j--`?
    *   *Hidden Answer:* Infinite loop! The pointers never meet.

---

## 🏁 14. Final Takeaway

> "To process a list from both ends at once, use two pointers walking towards each other."

---

## 📌 15. Revision Checklist ✅

*   [ ] I understand why we start at 0 and n-1.
*   [ ] I can write the `while` loop condition correctly.
*   [ ] I know how to swap two variables.

---

## 🔥 16. Practice Status

*   Problems Solved: 4 / 4
*   Confidence Level: ⭐⭐⭐⭐⭐
*   Last Revised On: Jan 3, 2026
