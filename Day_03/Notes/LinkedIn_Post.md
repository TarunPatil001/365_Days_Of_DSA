📅 Day 3 / 365 – DSA & Problem-Solving Patterns

Topic: Two Pointers on Sorted Arrays

🧠 What I learned today:
• 💡 If the array is already sorted, skip the HashMap — two pointers (start + end) moving inward is O(n) and O(1) space.
• 💡 3Sum is just Two Sum in a loop: fix one element, then find two others that sum to its negative.
• 💡 Duplicate handling is crucial — sort first, then skip identical values to avoid repeating triplets.

🛠️ Approach I applied:
• **Sort first:** Unlocks two-pointer technique and makes duplicate skipping trivial.
• **Fix + Solve:** For 3Sum, fix one number and reduce the problem to Two Sum.
• **Early break:** If smallest number > 0 in 3Sum, no valid triplet exists — stop early.

✅ Questions I solved:
🔹 Two Sum II - Input Array Is Sorted (Medium)
🔹 3Sum (Medium)

💭 Reflection: Breaking 3Sum into smaller pieces made it way less scary. Pattern recognition is becoming more natural. Day 3 done ✅

#DataStructures #Algorithms #Java #SoftwareEngineering #LeetCode #ProfessionalDevelopment #365DaysOfDSA
