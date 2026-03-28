// Name: Ryan Muniz
// Email: rmuniz15@student.cnm.edu
// Class: CSCI 2260: Section R01
// Assignment: Week 9: Tree Data Structure
// Purpose: Represents an employee by sorting their name, height, and weight
// then provides methods to access and compare that data
// FileName: "Person.java"
// Date: March 19, 2026

/*
Person Class
Stores one employee record from hr.txt.
Each Person has a name, height (cm), and weight (kg).
Person objects are stored in the SortedTreeSet.
*/
public class Person {
    // Store the employee name
    private String name;
    // Store employee height (cm)
    private double height;
    // Store employee weight (kg)
    private double weight;

    /*
    Person Constructor method
    Creates a new Person object and stores the name, height, and weight values.
    */
   public Person(String name, double height, double weight) {
    // Save the given info into the corresponding object's field
    this.name = name;
    this.height = height;
    this.weight = weight;
   }
   /*
   Copy constructor
   Creates a new Person object by copying the data from another Person object.
   */
  public Person(Person other) {
    // Copy the other object's info
    this.name = other.name;
    this.height = other.height;
    this.weight = other.weight;
  }
  /*
  getName method
  This accessor method returns the employee's name.
  */
 public String getName() {
    return name;
 }
 /*
 getHeight method
 This accessor method returns the employee's height.
 */
public double getHeight() {
    return height;
 }
 /*
 getWeight method
 Returns the employee's weight.
 */
 public double getWeight() {
    return weight;
 }
 /*
 equals method
 Checks for duplicate names by seeing if the names match
 while ignoring upper or lowercase letters.
 */
 public boolean equals(Object obj) {
    // Check whether the object is null
    if (obj == null) {
        // A null object cannot be equal to this Person
        return false;
    }
    // Check whether the other object is actually a Person
    if (!(obj instanceof Person)) {
        // A different object type cannot be equal to this Person
        return false;
    }
    // Convert the generic Object reference into a Person reference
    Person other = (Person) obj;
    // Return true if the names match ignoring case
    return this.name.equalsIgnoreCase(other.name);
 }
 /*
 toString method
 Returns a clean text version of the Person object.
 It is useful when printing a Person directly.
 */
 public String toString() {
    // Return a formatted string showing the employee's data
    return String.format("%-10s Height: %6.1f cm  Weight: %6.1f kg", name, height, weight);
 }
}
