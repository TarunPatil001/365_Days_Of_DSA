# 🚀 Day 1: Two Pointer Pattern & Basics

Welcome to my DSA journey! Today, I'm diving into the **Two Pointer** technique. It's a game-changer for optimizing solutions from $O(N^2)$ down to $O(N)$.

Here are my notes on the basics of Arrays, Strings, and Linked Lists in **Java**, and how to apply the Two Pointer pattern to them.

---

## 🧠 1. The Building Blocks

Before using the pattern, I need to understand the tools in my toolkit.

### 📦 Arrays
*   **What is it?** A block of memory where elements sit next to each other.
*   **Superpower:** Random Access! I can grab any element instantly using an index ($O(1)$).
*   **Two Pointer Strategy:**
    *   Since I have indices, I can easily put one pointer at the start (`0`) and one at the end (`n-1`).
    *   **Pro Tip:** If the array is **sorted**, Two Pointers is almost always the best approach (e.g., finding a target sum).

#### 🛠️ Common Java Array Methods
In Java, arrays have a fixed size. For dynamic arrays, we use `ArrayList`. Here are key methods for both:

| Method / Property | Description | Time Complexity |
| :--- | :--- | :--- |
| `arr.length` | Property to get the size of a primitive array. | $O(1)$ |
| `Arrays.sort(arr)` | Sorts the array in ascending order. | $O(N \log N)$ |
| `Arrays.toString(arr)` | Returns a string representation of the array. | $O(N)$ |
| `list.size()` | Returns the number of elements in an `ArrayList`. | $O(1)$ |
| `list.get(index)` | Accesses element at specific index in `ArrayList`. | $O(1)$ |
| `list.add(element)` | Appends element to the end of `ArrayList`. | $O(1)$ |
| `list.contains(obj)` | Checks if the list contains the object. | $O(N)$ |

#### 💻 Java Example
```java
import java.util.Arrays;

public class ArrayBasics {
    public static void main(String[] args) {
        // Primitive Array
        int[] nums = {5, 2, 9, 1, 6};
        
        // Sorting is crucial for many Two Pointer problems
        Arrays.sort(nums); 
        System.out.println("Sorted: " + Arrays.toString(nums)); // [1, 2, 5, 6, 9]

        // Two Pointer Setup
        int left = 0;
        int right = nums.length - 1;
        
        System.out.println("Start: " + nums[left] + ", End: " + nums[right]);
    }
}
```

### 🧵 Strings
*   **What is it?** Just an array of characters.
*   **Watch Out:** In Java, `String` objects are **immutable**. If you need to modify a string frequently (like in a loop), use `StringBuilder`.
*   **Two Pointer Strategy:**
    *   Works exactly like arrays.
    *   Great for: Checking Palindromes ("racecar"), reversing strings, or checking anagrams.

#### 🛠️ Common Java String Methods

| Method | Description | Time Complexity |
| :--- | :--- | :--- |
| `str.length()` | Returns the number of characters. | $O(1)$ |
| `str.charAt(i)` | Returns the character at index `i`. | $O(1)$ |
| `str.toCharArray()` | Converts string to a mutable char array. | $O(N)$ |
| `str.substring(i, j)` | Returns substring from `i` to `j-1`. | $O(N)$ |
| `str.equals(other)` | Compares two strings for value equality. | $O(N)$ |
| `str.indexOf(sub)` | Returns index of first occurrence of substring. | $O(N)$ |
| `sb.append(str)` | Appends to a `StringBuilder`. | $O(1)$ |

#### 💻 Java Example
```java
public class StringBasics {
    public static void main(String[] args) {
        String s = "racecar";
        
        // Convert to char array for easy swapping/access
        char[] chars = s.toCharArray();
        
        int left = 0;
        int right = s.length() - 1;
        
        boolean isPalindrome = true;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                isPalindrome = false;
                break;
            }
            left++;
            right--;
        }
        System.out.println("Is Palindrome? " + isPalindrome);
    }
}
```

### 🔗 Linked Lists
*   **What is it?** A chain of nodes. Each node holds data and points to the next one.
*   **The Catch:** No random access! I can't jump to index 5. I have to walk from the start.
*   **Two Pointer Strategy:**
    *   I can't use indices like `left` and `right`.
    *   Instead, I use **Node Pointers** (references).
    *   **The "Runner" Technique:** Use a `fast` pointer (moves 2 steps) and a `slow` pointer (moves 1 step). This is magic for detecting cycles or finding the middle.

#### 🛠️ Common Java LinkedList Methods
*Note: In DSA problems (like LeetCode), we usually use a custom `ListNode` class rather than `java.util.LinkedList`.*

| Method | Description | Time Complexity |
| :--- | :--- | :--- |
| `list.add(element)` | Appends element to the end. | $O(1)$ |
| `list.addFirst(e)` | Adds element to the beginning. | $O(1)$ |
| `list.removeFirst()` | Removes and returns the first element. | $O(1)$ |
| `list.get(index)` | Returns element at position (slow!). | $O(N)$ |
| `list.size()` | Returns number of elements. | $O(1)$ |
| `list.isEmpty()` | Checks if list is empty. | $O(1)$ |

#### 💻 Java Example (Custom Node)
```java
// Standard Definition for a Singly Linked List in DSA
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; next = null; }
}

public class LinkedListBasics {
    public static void main(String[] args) {
        // Creating a simple chain: 1 -> 2 -> 3 -> 4
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        // Two Pointer: Fast & Slow
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;       // Moves 1 step
            fast = fast.next.next;  // Moves 2 steps
        }
        
        // When fast reaches end, slow is at the middle!
        System.out.println("Middle Element: " + slow.val); // Output: 3
    }
}
```

---

## ⚡ 2. The Two Pointer Pattern: 3 Flavors

There are three main ways I'll see this pattern appear in LeetCode problems.

### 🅰️ Variation A: Converging (Meeting in the Middle)
*   **Visual:** `Start -> ... ... <- End`
*   **How it works:** One pointer at the start, one at the end. They move towards each other.
*   **When to use:**
    *   Reversing an array/string.
    *   Checking if a string is a Palindrome.
    *   **Two Sum (Sorted):** If sum is too small, move `left` up. If too big, move `right` down.

### 🅱️ Variation B: Fast & Slow (Tortoise & Hare)
*   **Visual:** `Start -> (Slow) ... (Fast) ->`
*   **How it works:** Both start at the beginning. One moves faster, or one waits for a condition.
*   **When to use:**
    *   **Linked List Cycles:** If `fast` catches `slow`, there's a loop!
    *   **Middle of List:** When `fast` hits the end, `slow` is exactly in the middle.
    *   **Remove Duplicates:** `slow` builds the clean list, `fast` scouts ahead.

### 🆎 Variation C: Merging (Two Inputs)
*   **Visual:** `List A ->` vs `List B ->`
*   **How it works:** I have two separate arrays/lists. I compare the heads of both and move the one I need.
*   **When to use:**
    *   Merging two sorted arrays into one.
    *   Finding the intersection of two arrays.

---

## 📝 3. My Cheat Sheet & Tips

1.  **Is it Sorted?** 🧐 If the problem involves a sorted array and finding pairs/triplets, **Two Pointers** is my first guess.
2.  **Save Space!** 💾 This pattern is great for $O(1)$ space complexity. It avoids using a Hash Map ($O(N)$ space).
3.  **Linked List Mantra:** 🏃‍♂️ `fast = fast.next.next`, `slow = slow.next`. Memorize this!
4.  **Watch the Loop:** 👀
    *   Use `left < right` if pointers shouldn't cross (e.g., Two Sum).
    *   Use `left <= right` if checking the middle element matters (e.g., Binary Search).
5.  **Sliding Window:** 🪟 If I'm looking at a *subarray* (contiguous part), Two Pointers evolves into the **Sliding Window** technique.

---

## 🏋️‍♀️ 4. Practice Problems (LeetCode)

Theory is good, but practice makes perfect. Here are the classic problems to master this pattern, sorted by difficulty.

### Easy (Warm-up)
| Problem | Variation | Key Insight |
| :--- | :--- | :--- |
| [125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/) | 🅰️ Converging | Ignore non-alphanumeric chars, compare ends. |
| [26. Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) | 🅱️ Fast & Slow | `slow` stores unique, `fast` finds new ones. |
| [977. Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/) | 🅰️ Converging | Compare absolute values at ends, fill result from back. |
| [141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/) | 🅱️ Fast & Slow | If `fast` == `slow`, there's a cycle. |
| [88. Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/) | 🆎 Merging | Fill from the **end** to avoid overwriting. |

### Medium (The Real Deal)
| Problem | Variation | Key Insight |
| :--- | :--- | :--- |
| [167. Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) | 🅰️ Converging | Sum > Target? `right--`. Sum < Target? `left++`. |
| [15. 3Sum](https://leetcode.com/problems/3sum/) | 🅰️ Converging | Fix one number, then do Two Sum on the rest. Skip duplicates! |
| [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water/) | 🅰️ Converging | Move the pointer with the **shorter** height to try and find a taller one. |
| [19. Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) | 🅱️ Fast & Slow | Move `fast` N steps ahead, then move both until `fast` hits end. |

### Hard (Mastery)
| Problem | Variation | Key Insight |
| :--- | :--- | :--- |
| [42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) | 🅰️ Converging | Track `maxLeft` and `maxRight` as you move inward. |

---
*Happy Coding!* 💻
