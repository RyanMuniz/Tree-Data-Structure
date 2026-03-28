// Name: Ryan Muniz
// Email: rmuniz15@student.cnm.edu
// Class: CSCI 2260: Section R01
// Assignment: Week 9: Tree Data Structure
// Purpose: Defines the required methods for a binary tree structure, including
// accessing nodes and inserting Person objects.
// FileName: "SortedTreeSetInterface.java"
// Date: March 19, 2026

public interface SortedTreeSetInterface {
    public Person getPerson();

    public boolean hasLeft();
    public void setLeft(SortedTreeSet left);
    public SortedTreeSet getLeft();

    public boolean hasRight();
    public void setRight(SortedTreeSet right);
    public SortedTreeSet getRight();

    public void add(Person p);
}
