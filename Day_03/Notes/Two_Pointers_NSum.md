# 📘 DSA Pattern: N-Sum (3Sum, 4Sum)

> **Purpose:** Finding three or more numbers that add up to a target by fixing one number and using Two Pointers for the rest.

---

## 🧩 1. Pattern Name

> **Fixed Point + Two Pointers (3Sum Pattern)**

*   **Category:** Array
*   **Difficulty Level:** Medium
*   **Use Case:** Finding triplets (a, b, c) such that a + b + c = 0 (or Target).

---

## 🧠 2. Intuition (Very Important)

> Reduce the problem to what you already know.

*   We know how to solve **Two Sum** (find 2 numbers).
*   How do we solve **3Sum** (find 3 numbers)?
*   **Trick:** Pick one number and fix it (hold it in your hand).
*   Now, you need to find **two other numbers** that add up to the remaining amount.
*   Example: Target is 0. You pick `-1`. Now you need two numbers that sum to `+1`.
*   This turns a 3-number problem into a simple **Two Pointer** problem!

---

## 🖼️ 3. Visual Explanation / Diagram

```
Array: [-1, 0, 1, 2, -1, -4]
Sorted: [-4, -1, -1, 0, 1, 2]

Step 1: Fix 'i' at index 0 (Value: -4)
Target for rest: +4
Search in: [-1, -1, 0, 1, 2] using Two Pointers.

Step 2: Fix 'i' at index 1 (Value: -1)
Target for rest: +1
Search in: [-1, 0, 1, 2] using Two Pointers.
Found: -1 + 2 = 1 (Match!) -> Triplet: [-1, -1, 2]
```

---

## 🧪 4. Dry Run (Step-by-Step)

**Input:** `[-1, 0, 1, 2, -1, -4]` -> Sorted: `[-4, -1, -1, 0, 1, 2]`

| Fixed (i) | Left (L) | Right (R) | Sum (i+L+R) | Action |
| :--- | :--- | :--- | :--- | :--- |
| -4 | -1 | 2 | -3 | Too small -> Move L |
| ... | ... | ... | ... | ... |
| -1 | -1 | 2 | 0 | **Found!** Save it. Move L & R. |
| -1 | 0 | 1 | 0 | **Found!** Save it. |

---

## 🧠 5. Algorithm Steps

1.  **Sort** the array (Crucial!).
2.  Loop `i` from `0` to `n-2` (This is the fixed number).
3.  If `nums[i]` is same as `nums[i-1]`, **skip it** (to avoid duplicate triplets).
4.  Set `left = i + 1` and `right = n - 1`.
5.  Run standard **Two Sum** logic:
    *   Sum < 0? `left++`
    *   Sum > 0? `right--`
    *   Sum == 0? Record triplet, then **skip duplicates** for `left` and `right`.

---

## 💻 6. Pseudocode

```text
Sort(nums)
For i from 0 to length-2:
    If i > 0 AND nums[i] == nums[i-1]: continue (Skip duplicates)
    
    left = i + 1
    right = length - 1
    
    While left < right:
        sum = nums[i] + nums[left] + nums[right]
        If sum == 0:
            Add {nums[i], nums[left], nums[right]} to result
            Skip duplicate lefts
            Skip duplicate rights
            left++, right--
        Else if sum < 0:
            left++
        Else:
            right--
```

---

## 🧾 7. Solved Problems

### 🔹 1. Two Sum II - Input Array Is Sorted (167)
> **Question:** Find two numbers in a sorted array that add up to target. Return their 1-based indices.
> [Link to Problem](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/)

**💡 Pattern Connection:** Standard sorted Two Pointers. Too small? Increase left. Too big? Decrease right.

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // Step 1: Initialize pointers at both ends
        int i = 0, j = numbers.length-1;
        
        // Step 2: Loop until pointers meet
        while(i < j){
            int sum = numbers[i] + numbers[j];
            
            if(sum > target){
                // Step 3a: Sum too big, decrease right
                j--;
            } else if(sum < target){
                // Step 3b: Sum too small, increase left
                i++;
            } else {
                // Step 3c: Found! Return 1-based indices
                return new int[] { (i+1), (j+1) };
            }
        }
        return new int[] { -1, -1 };
    }
}
```

### 🔹 2. 3Sum (15)
> **Question:** Find all unique triplets `[nums[i], nums[j], nums[k]]` such that they add up to zero.
> [Link to Problem](https://leetcode.com/problems/3sum/description/)

**💡 Pattern Connection:** We fix one number (`nums[f]`) and then use Two Pointers to find the other two. It's just "Two Sum" repeated `N` times.

```java
public class Solution2 {
    // Helper: Find pairs that sum to -nums[f]
    public void twoSumHelper(int f, int[] nums, List<List<Integer>> res){
        // Step 1: Two pointers after fixed element
        int i = f + 1, j = nums.length-1;
        
        // Step 2: Standard two pointer loop
        while(i < j){
            int sum = nums[f] + nums[i] + nums[j];

            if(sum > 0){
                // Step 3a: Sum too big, decrease right
                j--;
            } else if(sum < 0){
                // Step 3b: Sum too small, increase left
                i++;
            } else {
                // Step 3c: Found triplet!
                res.add(Arrays.asList(nums[f], nums[i], nums[j]));
                i++; j--;

                // Step 4: Skip duplicate left values
                while(i < j && nums[i] == nums[i-1]) i++;
                // Step 5: Skip duplicate right values
                while(i < j && nums[j] == nums[j+1]) j--;
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
            if(nums[f] > 0) break;

            // Step 3: Skip duplicates for fixed element
            if(f == 0 || nums[f] != nums[f-1]){
                twoSumHelper(f, nums, res);
            }
        }
        return res;
    }
}
```

---

## ⏱️ 8. Complexity Analysis

*   **Time Complexity:** **O(N²)**
    *   Sorting is `O(N log N)`.
    *   The loop runs `N` times, and inside is a Two Pointer pass `O(N)`. Total `O(N * N)`.
*   **Space Complexity:** **O(1)** (ignoring output list) or **O(log N)** for sorting.

---

## ⚠️ 9. Edge Cases

*   **Duplicates:** The biggest headache in 3Sum. You must skip duplicates for `i`, `left`, and `right` to get unique triplets.
*   **Fewer than 3 elements:** Impossible to find a triplet.

---

## 🔁 10. Similar Problems

| Problem Name | Platform | Link |
| :--- | :--- | :--- |
| Two Sum II | LeetCode | [Link](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) |
| 3Sum | LeetCode | [Link](https://leetcode.com/problems/3sum/) |
| 4Sum | LeetCode | [Link](https://leetcode.com/problems/4sum/) |

---

## 🧠 11. Pattern Recognition Tips

*   **Keywords:** "Unique triplets", "Sum to zero", "a + b + c = K".
*   **Strategy:** Fix one, solve for two. Fix two, solve for one.

---

## ❌ 12. Common Mistakes

*   **Not skipping duplicates:** This will result in output like `[-1, 0, 1]` and `[-1, 0, 1]` appearing twice.
*   **Wrong loop bounds:** `i` only needs to go to `length - 2`.

---

## 🧠 13. Active Recall Quiz (Try to answer without looking up!)

1.  **How** do we reduce 3Sum to a simpler problem?
    *   *Hidden Answer:* By fixing one number (`nums[i]`) and then solving Two Sum for the remaining part of the array.
2.  **Why** do we need `while(i < j && nums[i] == nums[i-1]) i++`?
    *   *Hidden Answer:* To skip duplicate numbers. If we don't, we'll generate the same triplet multiple times (e.g., `[-1, 0, 1]` twice).
3.  **What** is the time complexity of 3Sum?
    *   *Hidden Answer:* O(N^2). One loop for the fixed number (N) * one loop for the two pointers (N).

---

## 🏁 14. Final Takeaway

> "3Sum is just a loop wrapping around the Two Sum pattern. Fix one, move two."

---

## 📌 15. Revision Checklist ✅

*   [ ] I can handle duplicates correctly.
*   [ ] I understand why the time complexity is O(N²).
*   [ ] I can extend this logic to 4Sum (Fix two loops, then Two Pointers).

---

## 🔥 16. Practice Status

*   Problems Solved: 2 / 2
*   Confidence Level: ⭐⭐⭐☆☆
*   Last Revised On: Jan 3, 2026
