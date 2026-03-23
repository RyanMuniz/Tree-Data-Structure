// Name: Ryan Muniz
// Email: rmuniz15@student.cnm.edu
// Class: CSCI 2260: Section R01
// Assignment: Week 8: Linked List
// Purpose: Stores subtree height and balance status to assist in determining
// whether the binary tree is balanced.
// FileName: "BalanceData.java"
// Date: March 19, 2026

/*
BalanceData Class
Used by SortedTreeSet when checking whether the tree is balanced.

It stores:
1. Height of a subtree
2. Whether that subtree is balanced
*/
public class BalanceData {
    // Store the height of a subtree
    public int height;
    // Store whether the subtree is balanced
    public boolean balanced;

    /*
    BalanceData Constructor method
    Stores both the subtree height and its balance status.
    */
    public BalanceData(int height, boolean balanced) {
        // Save the height value
        this.height = height;
        // Save the balanced value
        this.balanced = balanced;
    }
}