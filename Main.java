// Name: Ryan Muniz
// Email: rmuniz15@student.cnm.edu
// Class: CSCI 2260: Section R01
// Assignment: Week 9: Tree Data Structure
// Purpose: Creates a binary search tree of Person objects by reading employee
// data from a file, then displays the sorted results and tree properties.
// FileName: "Main.java"
// Date: March 19, 2026

// Import necessary utilities
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/*
Main class
This program reads the employee data from hr.txt and
stores each Person in a self-sorting binary tree set.
Then ignores duplicate names automatically, and displays
the results.

Includes the extra credit features:
1. Checks whether the tree is balanced.
2. Rebalances the tree if it is.
3. Use get(int i) to retrieve a Person by sorted index.
*/
public class Main {
    /*
    main method
    Creates the tree, opens hr.txt, reads each employee,
    inserts them into the tree in sorted order, and then
    prints the results to the command prompt.

    The tree stays sorted the entire time because each Person
    is inserted directly into the correct location as it's read.
    */
   public static void main(String[] args) {
    // Create the binary tree set that will store Person objects.
    SortedTreeSet people = new SortedTreeSet();
    // Create Scanner reference so it can be closed later.
    Scanner inputFile = null;

    try {
        // Open the hr.txt file for reading
        inputFile = new Scanner(new File("hr.txt"));
        // Check whether the file has at least one line before skipping the header.
        if (inputFile.hasNextLine()) {
            // Read and ignore the first line because it is the header row.
            inputFile.nextLine();
        }
        /*
        Read file one line at a time until there are no more lines.
        Each data line should have:
        name, height, and weight
        */
       while (inputFile.hasNextLine()) {
        // Read one full line from the file and remove extra spaces from both ends.
        String line = inputFile.nextLine().trim();
        // Skip blank lines so the program doesn't try to parse empty data.
        if (line.length() == 0) {
            // Move immediately to the next loop iteration.
            continue;
        }
        /*
        Split the line using one or more tab characters.
        The hr.txt file provided is tab-seperated so this breaks the line
        into seperate columns.
        */
       String[] parts = line.split("\\t+");
       
       // Make sure the line has at least 3 pieces of data before using it.
       if (parts.length >= 3) {
        // Save employee name from column 1.
        String name = parts[0].trim();
        // Convert the height text into a double value.
        double height = Double.parseDouble(parts[1].trim());
        double weight = Double.parseDouble(parts[2].trim());
        // Create a new Person object using the data from this line.
        Person employee = new Person(name, height, weight);

        /*
        Add the Person to the tree.
        The tree inserts alphabetically by name and ignores duplicates,
        so duplicate names are never stored.
        */
       people.add(employee);
        }
       }
       // Print title so the output can be read easily
       System.out.println("Nintendo Human Resources Tree Report");
       System.out.println("----------------------------------------");
       // Print the sorted contents of the tree
       System.out.println(people);
       // Print the number of unique employees stored in the tree
       System.out.printf("%-28s %d%n", "Unique Employee Count: ", people.size());
       // Print whether the tree is balanced before doing anything else
       System.out.printf("%-28s %b%n", "Is the tree balanced? ", people.isBalanced());
       /*
       If the tree is not balanced, rebalance it and show the results again.
       (Extra Credit)
       */
      if (!people.isBalanced()) {
        // Print a message explaining the process
        System.out.println("The tree is unbalanced, it will now be rebalanaced.");
        // Rebuild the tree into a balanced form.
        people.rebalance();
        // Print the new balance status after rebalancing.
        System.out.println("Is the tree balanced after rebalancing? " + people.isBalanced());
        // Print the balanced tree
        System.out.println(people);
      }
      /*
      Shows the get(int i) method
      (Extra Credit)
      The get method uses sorted in-order indexing, so:
      index 0 is the first name alphabetically
      index size - 1 is the last name alphabetically
      */
      if (people.size() > 0) {
        // Print the first Person in sorted order.
        System.out.printf("%-28s %s%n", "First person alphabetically: ", people.get(0));
        // Print the last Person in sorted order.
        System.out.printf("%-28s %s%n", "Last person alphabetically: ", people.get(people.size() - 1));
      }
      /*
      Demonstrate the extra credit parent reference.
      This prints the root's left child and that child's parent when possible.
      */
      if (people.hasLeft()) {
        // Print the left child of the root
        System.out.printf("%-28s %s%n", "Root's left child: ", people.getLeft().getPerson());
        // Print that left child's parent
        System.out.printf("%-28s %s%n", "That node's parent: ", people.getLeft().getParent().getPerson());
      }
    }
    catch (IOException e) {
        // Print an error message if file cannot be opened
        System.out.println("Error reading hr.txt: " + e.getMessage());
    }
    catch (NumberFormatException e) {
        // Print an error message if a number in the file is not valid
        System.out.println("Error parsing numeric data: " + e.getMessage());
    }
    finally {
        // Check whether the Scanner was succesfully created
        if (inputFile != null) {
            // Close the Scanner to release the file resource
            inputFile.close();
        }
    }
   }
}
