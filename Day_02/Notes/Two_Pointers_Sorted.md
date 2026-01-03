# 📘 DSA Pattern: Two Pointers (Sorted Arrays)

> **Purpose:** Leverage the power of **sorted data** to find pairs or merge lists efficiently.

---

## 🧩 1. Pattern Name

> **Two Pointers (On Sorted Data)**

*   **Category:** Array
*   **Difficulty Level:** Easy / Medium
*   **Use Case:** Finding a pair of numbers that sum to a target, or merging two already sorted lists.

---

## 🧠 2. Intuition (Very Important)

> Think about a game of "Hot and Cold".

*   You want two numbers that add up to a `Target`.
*   The array is **sorted** (small to big).
*   You pick the smallest number (`left`) and the biggest number (`right`).
*   **Sum too small?** You need a bigger number. Move `left` forward.
*   **Sum too big?** You need a smaller number. Move `right` backward.
*   **Sum matches?** You found it!

Because it's sorted, you can make smart decisions without checking every single pair.

---

## 🖼️ 3. Visual Explanation / Diagram

```
Target = 9
Array = [ 2, 7, 11, 15 ]

1. Sum = 2 + 15 = 17 (Too Big!)
   [ 2, 7, 11, 15 ]
     ↑          ↑
     L          R
   (Move R back to decrease sum)

2. Sum = 2 + 11 = 13 (Too Big!)
   [ 2, 7, 11, 15 ]
     ↑      ↑
     L      R
   (Move R back)

3. Sum = 2 + 7 = 9 (Target Found!)
   [ 2, 7, 11, 15 ]
     ↑  ↑
     L  R
```

---

## 🧪 4. Dry Run (Step-by-Step)

**Problem:** Count pairs with sum < 5 in `[-1, 1, 2, 3]`

| Step | State | Sum | Action | Result |
| :--- | :--- | :--- | :--- | :--- |
| 1 | `L=0` (-1), `R=3` (3) | 2 (< 5) | **Valid!** | All pairs from L to R work! Count += (R-L) = 3. Move L. |
| 2 | `L=1` (1), `R=3` (3) | 4 (< 5) | **Valid!** | Count += (R-L) = 2. Move L. |
| 3 | `L=2` (2), `R=3` (3) | 5 (Not < 5) | Too Big | Move R back. |

---

## 🧠 5. Algorithm Steps

1.  **Sort** the array (if not already sorted).
2.  Initialize `left = 0` and `right = n - 1`.
3.  Calculate `current_sum = arr[left] + arr[right]`.
4.  **Compare** with Target:
    *   If `current_sum < Target`: We need more sum -> Move `left` forward.
    *   If `current_sum > Target`: We need less sum -> Move `right` backward.
    *   If `current_sum == Target`: Success!

---

## 💻 6. Pseudocode

```text
Sort(array)
left = 0
right = length - 1

while left < right:
    sum = array[left] + array[right]
    
    if sum == target:
        return true
    else if sum < target:
        left++  // Need bigger sum
    else:
        right-- // Need smaller sum
```

---

## 🧾 7. Solved Problems

### 🔹 1. Merge Sorted Array (88)
> **Question:** Merge two sorted arrays `nums1` and `nums2` into `nums1`. `nums1` has extra space at the end.
> [Link to Problem](https://leetcode.com/problems/merge-sorted-array/)

**💡 Pattern Connection:** Since both are sorted, the largest elements are at the ends. We fill `nums1` from the back to avoid overwriting data.

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Step 1: Pointers at end of valid elements
        int i = m - 1, j = n - 1;
        // Step 2: k points to last position
        int k = m + n - 1;

        // Step 3: Compare from back, fill largest first
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                // Step 4a: nums1 element is larger
                nums1[k] = nums1[i];
                i--;
            } else {
                // Step 4b: nums2 element is larger
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        // Step 5: Copy remaining nums2 elements
        while (j >= 0) {
            nums1[k] = nums2[j];
            k--; j--;
        }
    }
}
```

### 🔹 2. Count Pairs Whose Sum is Less than Target (2824)
> **Question:** Find the number of pairs `(i, j)` such that `nums[i] + nums[j] < target`.
> [Link to Problem](https://leetcode.com/problems/count-pairs-whose-sum-is-less-than-target/description/)

**💡 Pattern Connection:** Sorting allows us to count *many* pairs at once. If `left + right < target`, then `left` + *anyone smaller than right* is also valid!

```java
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
                // Step 4a: All pairs (i,i+1)...(i,j) valid
                count = count + (j-i);
                i++;
            } else {
                // Step 4b: Sum too big, decrease right
                j--;
            }
        }
        return count;
    }
}
```

### 🔹 3. Two Sum (1) (HashMap Approach)
> **Question:** Find indices of two numbers that add up to target. (Note: This solution uses HashMap, not Two Pointers, because the array is not sorted and we need original indices).
> [Link to Problem](https://leetcode.com/problems/two-sum/description/)

```java
class Solution3 {
    public int[] twoSum(int[] nums, int target) {
        // Step 1: Map to store number -> index
        Map<Integer, Integer> map = new HashMap<>();

        // Step 2: Loop through each number
        for(int i=0; i<nums.length; i++){
            // Step 3: Calculate complement needed
            int lookingFor = target - nums[i];

            // Step 4: Check if complement exists
            if(map.containsKey(lookingFor)){
                return new int[] { map.get(lookingFor), i };
            }
            // Step 5: Store current number
            map.put(nums[i], i);
        }
        return new int[]{ -1, -1 };
    }
}
```

---

## ⏱️ 8. Complexity Analysis

*   **Time Complexity:** **O(N log N)**
    *   Sorting takes `O(N log N)`. The two-pointer pass takes `O(N)`. Total is dominated by sorting.
    *   If already sorted, it's just **O(N)**.
*   **Space Complexity:** **O(1)** or **O(log N)**
    *   Depends on the sorting algorithm used.

---

## ⚠️ 9. Edge Cases

*   **No pairs found:** Return 0 or -1.
*   **Negative numbers:** Sorting handles this correctly (e.g., -5 is smaller than -1).

---

## 🔁 10. Similar Problems

| Problem Name | Platform | Link |
| :--- | :--- | :--- |
| Merge Sorted Array | LeetCode | [Link](https://leetcode.com/problems/merge-sorted-array/) |
| Count Pairs < Target | LeetCode | [Link](https://leetcode.com/problems/count-pairs-whose-sum-is-less-than-target/) |
| Two Sum II | LeetCode | [Link](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) |

---

## 🧠 11. Pattern Recognition Tips

*   **Keywords:** "Sorted array", "Find pair", "Sum equal to X", "Merge two lists".
*   **Hint:** If the input is sorted, **always** think Two Pointers or Binary Search.

---

## ❌ 12. Common Mistakes

*   **Forgetting to Sort:** This logic **only** works on sorted arrays. If you don't sort, moving `left` doesn't guarantee a bigger number.
*   **Double Counting:** Be careful when counting pairs.

---

## 🧠 13. Active Recall Quiz (Try to answer without looking up!)

1.  **Why** must the array be sorted for this pattern?
    *   *Hidden Answer:* So we know that moving `left` *increases* the sum and moving `right` *decreases* the sum. Without sorting, movement is random.
2.  **In "Count Pairs"**, why do we do `count += (j - i)`?
    *   *Hidden Answer:* Because if `nums[i] + nums[j] < target`, and the array is sorted, then `nums[i]` plus *any number smaller than `nums[j]`* (which are all numbers between `i` and `j`) will also be less than target.
3.  **When** do we move `left` vs `right`?
    *   *Hidden Answer:* Sum too small? Move `left` (to increase). Sum too big? Move `right` (to decrease).

---

## 🏁 14. Final Takeaway

> "Sorting organizes the chaos. Once sorted, `left` increases value and `right` decreases value."

---

## 📌 15. Revision Checklist ✅

*   [ ] I understand why sorting is required.
*   [ ] I know when to move `left` vs `right`.
*   [ ] I can handle the "Merge Sorted Array" problem (filling from the back).

---

## 🔥 16. Practice Status

*   Problems Solved: 3 / 3
*   Confidence Level: ⭐⭐⭐⭐☆
*   Last Revised On: Jan 3, 2026
