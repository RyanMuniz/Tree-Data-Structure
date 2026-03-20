// Name: Ryan Muniz
// Email: rmuniz15@student.cnm.edu
// Class: CSCI 2260: Section R01
// Assignment: Week 8: Linked List
// Purpose: 
// FileName: "IndexData.java"
// Date: March 19, 2026

/*
IndexData Class
Used by SortedTreeSet when retrieving a Person with get(int i).
Keeps track of:
1. Current in-order traversal index
2. Person found at the requested index
*/
public class IndexData {
    // Store the current traversal index
    public int currentIndex;
    // Store the Person found at the target index
    public Person foundPerson;

    /*
    IndexData Constructor method
    Initializes the helper object so traversal can begin at index
    0 and no Person has been found yet.
    */
   public IndexData() {
    // Start counting traversal positions at zero
    currentIndex = 0;
    // No Person has been found yet
    foundPerson = null;
   }
}
