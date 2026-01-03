# Day 03: Two Pointers & Sorted Arrays

Today's focus is on the **Two Pointers** technique applied to sorted arrays. This pattern is incredibly efficient for finding pairs or triplets that satisfy a specific condition (like summing to a target) because the sorted order allows us to make intelligent decisions about which elements to check next, avoiding brute-force $O(n^2)$ or $O(n^3)$ solutions.

---

## 1. Two Sum II - Input Array Is Sorted (LeetCode #167)

**Problem:** Given a **1-indexed** array of integers `numbers` that is already **sorted in non-decreasing order**, find two numbers such that they add up to a specific `target` number. Return their indices.

### 🧠 Intuition
Since the array is sorted, we have information about the magnitude of numbers.
- The smallest numbers are on the left.
- The largest numbers are on the right.

If we pick the smallest number (`numbers[0]`) and the largest number (`numbers[n-1]`) and sum them:
- If the sum is **too big**, we need a smaller number. The only way to get a smaller sum is to pick a smaller number from the right side (move `right` pointer left).
- If the sum is **too small**, we need a larger number. The only way to get a larger sum is to pick a larger number from the left side (move `left` pointer right).

### 📝 Algorithm Steps
1.  **Initialize Pointers:**
    -   `i` (left) at index `0`.
    -   `j` (right) at index `numbers.length - 1`.
2.  **Loop while `i < j`:**
    -   Calculate `sum = numbers[i] + numbers[j]`.
    -   **Case 1: `sum > target`**
        -   The current pair is too large. Since `numbers[i]` is the smallest available number on the left, we *must* decrease the larger number.
        -   **Action:** Decrement `j` (`j--`).
    -   **Case 2: `sum < target`**
        -   The current pair is too small. Since `numbers[j]` is the largest available number on the right, we *must* increase the smaller number.
        -   **Action:** Increment `i` (`i++`).
    -   **Case 3: `sum == target`**
        -   We found the pair!
        -   **Action:** Return `[i + 1, j + 1]` (adding 1 because the problem asks for 1-based indexing).
3.  **Fallback:** Return `[-1, -1]` (though the problem guarantees a solution exists).

### 🧪 Edge Cases & Examples
-   **Negative Numbers:** `[-5, -2, 0, 3]`, Target `-2`.
    -   `i=0 (-5)`, `j=3 (3)` -> Sum `-2`. Found immediately. The logic holds for negatives.
-   **Duplicates:** `[2, 2, 3]`, Target `4`.
    -   `i=0 (2)`, `j=2 (3)` -> Sum `5` (>4) -> `j--`.
    -   `i=0 (2)`, `j=1 (2)` -> Sum `4`. Found.
-   **Minimum Size:** Array with exactly 2 numbers. The loop runs once, checks sum, returns.

### ⏱️ Complexity
-   **Time:** $O(n)$ - We touch each element at most once.
-   **Space:** $O(1)$ - No extra data structures used.

### ✅ Code Verification
-   **Constraint Check:** The problem requires **constant extra space**. The solution uses only integer variables (`i`, `j`, `sum`) and no auxiliary data structures like HashMaps, satisfying the $O(1)$ space requirement.
-   **Logic Check:** The `while(i < j)` loop correctly implements the two-pointer logic. The return statement `new int[] { (i+1), (j+1) }` correctly handles the **1-based indexing** requirement.

---

## 2. 3Sum (LeetCode #15)

**Problem:** Given an integer array `nums`, return all the triplets `[nums[i], nums[j], nums[k]]` such that `i != j`, `i != k`, `j != k`, and `nums[i] + nums[j] + nums[k] == 0`. The solution set must not contain duplicate triplets.

### 🧠 Intuition
Finding three numbers `a + b + c = 0` is the same as fixing one number `a` and finding two other numbers `b + c = -a`.
This transforms the problem into **"Find two numbers that sum to `-a`"**, which is exactly the **Two Sum** problem we just solved!

To avoid duplicates and use the Two Pointer technique efficiently, we **must sort the array first**.

### 📝 Algorithm Steps

#### Phase 1: Preparation
1.  **Sort `nums`**: This groups duplicates together and allows us to use the two-pointer approach.

#### Phase 2: The "Fix One" Loop
2.  Iterate through the array with index `f` (fixed element) from `0` to `n-2`.
    -   **Optimization:** If `nums[f] > 0`, break. Since the array is sorted, if the first number is positive, all subsequent numbers are also positive, so their sum can never be 0.
    -   **Duplicate Check (Outer):** If `f > 0` and `nums[f] == nums[f-1]`, continue. We already processed this number as the first element of a triplet, so processing it again would yield duplicate triplets.

#### Phase 3: The Two Pointer Scan (`twoSumHelper`)
3.  Set `target = -nums[f]`.
4.  Initialize `i = f + 1` (element after fixed) and `j = n - 1` (last element).
5.  **Loop while `i < j`:**
    -   Calculate `sum = nums[f] + nums[i] + nums[j]`.
    -   **Case 1: `sum > 0`** (Too big) -> `j--`.
    -   **Case 2: `sum < 0`** (Too small) -> `i++`.
    -   **Case 3: `sum == 0`** (Found triplet!)
        -   Add `[nums[f], nums[i], nums[j]]` to results.
        -   Move pointers inward: `i++`, `j--`.
        -   **Duplicate Check (Inner Left):** While `i < j` and `nums[i] == nums[i-1]`, increment `i`. This skips over identical values we just processed to avoid duplicate triplets like `[-1, -1, 2]` and `[-1, -1, 2]`.
        -   **Duplicate Check (Inner Right):** While `i < j` and `nums[j] == nums[j+1]`, decrement `j`.

### 🧪 Edge Cases & Examples

#### Example 1: Zeros
`nums = [0, 0, 0, 0]`
1.  Sort: `[0, 0, 0, 0]`
2.  `f=0` (`nums[f]=0`):
    -   `i=1`, `j=3`. Sum `0+0+0=0`. Add `[0,0,0]`.
    -   `i` becomes 2, `j` becomes 2.
    -   Now `i` is not `< j`, so the two-pointer loop ends immediately. (In your code, the duplicate-skip loops run only while `i < j`.)
3.  `f=1` (`nums[f]=0`):
    -   `nums[1] == nums[0]`, so **continue** (skip outer duplicate).
4.  Result: `[[0, 0, 0]]`. Correct.

#### Example 2: Mixed
`nums = [-1, 0, 1, 2, -1, -4]`
1.  Sort: `[-4, -1, -1, 0, 1, 2]`
2.  `f=0` (`-4`): Search for `4` in `[-1...2]`. Max sum `-1+2=1`. No match.
3.  `f=1` (`-1`): Search for `1`.
    -   `i=2 (-1)`, `j=5 (2)`. Sum `-1 + (-1) + 2 = 0`. **Found `[-1, -1, 2]`**.
    -   `i` moves to 3 (`0`), `j` moves to 4 (`1`).
    -   `i=3 (0)`, `j=4 (1)`. Sum `-1 + 0 + 1 = 0`. **Found `[-1, 0, 1]`**.
4.  `f=2` (`-1`): `nums[2] == nums[1]`. **Skip**.
5.  `f=3` (`0`): Search for `0`. `i=4 (1)`, `j=5 (2)`. Sum `3`. Too big.
6.  `f=4` (`1`): `nums[f] > 0`. **Break**.

### ⏱️ Complexity
-   **Time:** $O(n^2)$
    -   Sorting takes $O(n \log n)$.
    -   The outer loop runs $n$ times.
    -   The inner `twoSumHelper` runs in $O(n)$ time.
    -   Total: $O(n \log n + n^2) \approx O(n^2)$.
-   **Space:** $O(1)$ or $O(n)$ depending on sorting implementation (ignoring output list).

### ✅ Code Verification
-   **Constraint Check:** The input size is up to `3000`. An $O(n^2)$ approach is the expected solution class for this problem and is generally acceptable for these constraints.
-   **Logic Check:**
    -   **Duplicate Triplets:** The solution correctly avoids duplicates by:
        1.  Sorting the array first.
        2.  Skipping the same "first element" in the outer loop (`nums[f] == nums[f-1]`).
        3.  Skipping identical "second" and "third" elements in the inner loop after finding a valid triplet.
    -   **Correctness:** The logic `sum > 0` -> `j--` and `sum < 0` -> `i++` is mathematically sound for a sorted array.

---

## 💡 Key Takeaways
1.  **Sorted = Two Pointers:** Whenever you see "sorted array" and "find pair/triplet", think Two Pointers immediately.
2.  **Handling Duplicates:**
    -   **Outer Loop:** Skip if `current == previous` (`nums[i] == nums[i-1]`).
    -   **Inner Loop:** After finding a valid pair, loop past any identical elements to ensure the next pair found is unique.
3.  **3Sum Reduction:** 3Sum is just running Two Sum $n$ times.
