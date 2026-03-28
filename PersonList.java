// Name: Ryan Muniz
// Email: rmuniz15@student.cnm.edu
// Class: CSCI 2260: Section R01
// Assignment: Week 9: Tree Data Structure
// Purpose: Defines a method for retrieving a Person from the tree based
// on its position in sorted order.
// FileName: "PersonList.java"
// Date: March 19, 2026

/*
PersonList Interface
(Extra Credit)
Allows a class to return a Person based on an index.
*/
public interface PersonList {
    /*
    get method
    Returns the Person stored at the given index.
    The index is based on sorted in-order traversal.
    */ 
    public Person get(int i);
}