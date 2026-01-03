
# Day 02 Notes (Beginner Friendly)

Day 02 covers **3 common array problems** and introduces two very important patterns:

- **HashMap lookup** (for “find complement” style problems)
- **Two pointers** (often used after sorting, or when arrays are already sorted)

---

## 1) LeetCode #1 — Two Sum

**Goal:** Given an array `nums` and a `target`, return indices of two numbers such that they add up to `target`.

### Key idea
While scanning the array, for each number `x` we ask:

- “What number do I need with `x` to reach `target`?”
- That missing number is `target - x` (called the **complement**).

We store previously seen numbers in a `HashMap`:

- **key** = number value
- **value** = its index

If the complement already exists in the map, we found the answer.

### Steps (matching `Solution3.java`)
1. Create `Map<Integer, Integer> map`.
2. Loop `i` from `0` to `nums.length - 1`.
3. Compute `lookingFor = target - nums[i]`.
4. If `map` contains `lookingFor`, return `[map.get(lookingFor), i]`.
5. Otherwise, store current number: `map.put(nums[i], i)`.

### Why it works
At index `i`, the map contains all numbers from indices `0..i-1`.
So if the complement exists, we immediately know there is a previous index that pairs with `i`.

### Complexity
- Time: `O(n)` (single pass, map lookups are average `O(1)`)
- Space: `O(n)` (map can store up to `n` elements)

### Common pitfalls
- Don’t add the current element to the map *before* checking, or you might use the same element twice.
- If the problem guarantees an answer, you don’t need the `[-1, -1]` fallback, but it’s okay to keep.

---

## 2) LeetCode #88 — Merge Sorted Array

**Goal:** You are given two sorted arrays `nums1` and `nums2`.

- `nums1` has extra space at the end to hold all elements.
- `m` = number of real elements in `nums1`
- `n` = number of elements in `nums2`

You must merge them into `nums1` in sorted order.

### Key idea
Merge from the **end** so you don’t overwrite useful values in `nums1`.

Use three pointers:

- `i = m - 1` (last real element in `nums1`)
- `j = n - 1` (last element in `nums2`)
- `k = m + n - 1` (last position in `nums1` total space)

### Steps (matching `Solution.java`)
1. While both arrays still have elements (`i >= 0 && j >= 0`):
	- Put the larger of `nums1[i]` and `nums2[j]` into `nums1[k]`.
	- Move the pointer you used (`i--` or `j--`).
	- Move `k--`.
2. If `nums2` still has remaining elements (`j >= 0`), copy them into `nums1`.
	- (No need to copy leftover `nums1` elements; they are already in place.)

### Why it works
The largest remaining element among both arrays must go to the last remaining slot (`k`).
Filling from the end avoids overwriting the “unmerged” part of `nums1`.

### Complexity
- Time: `O(m + n)`
- Space: `O(1)` extra space (in-place merge)

### Common pitfalls
- Merging from the start can overwrite `nums1` before you read it.
- Don’t forget the final loop that copies remaining `nums2` elements.

---

## 3) LeetCode #2824 — Count Pairs Whose Sum is Less than Target

**Goal:** Count the number of index pairs `(i, j)` with `i < j` such that:

`nums[i] + nums[j] < target`

### Key idea
1. **Sort the list**.
2. Use **two pointers**:
	- `i` at the start
	- `j` at the end

If `nums[i] + nums[j]` is already `< target`, then:

- `nums[i] + nums[i+1]`, `nums[i] + nums[i+2]`, ..., `nums[i] + nums[j]` are **also** `< target` (because the list is sorted and all elements between `i` and `j` are ≤ `nums[j]`).
- That means you can add **`(j - i)` pairs at once**.

### Steps (matching `Solution2.java`)
1. Sort `nums`.
2. Set `i = 0`, `j = nums.size() - 1`, `count = 0`.
3. While `i < j`:
	- Let `sum = nums.get(i) + nums.get(j)`.
	- If `sum < target`:
	  - `count += (j - i)`
	  - Move `i++` (try a bigger left value)
	- Else:
	  - Move `j--` (sum too large, try smaller right value)
4. Return `count`.

### Complexity
- Time: `O(n log n)` because of sorting, then `O(n)` for the two-pointer scan
- Space: `O(1)` extra space (ignoring sorting implementation details)

### Common pitfalls
- You must sort first for the `(j - i)` shortcut to be valid.
- Ensure you always keep `i < j`.

---

## Quick pattern summary

- **HashMap (Two Sum):** “Have I already seen the complement?”
- **Two pointers (sorted data):** Move pointers inward to control sum/ordering efficiently
- **Merge from end:** Classic trick for in-place merge when the first array has extra space

