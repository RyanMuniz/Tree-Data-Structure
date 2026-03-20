// Name: Ryan Muniz
// Email: rmuniz15@student.cnm.edu
// Class: CSCI 2260: Section R01
// Assignment: Week 8: Linked List
// Purpose: 
// FileName: "SortedTreeSet.java"
// Date: March 19, 2026

// Import necessary utilities
import java.util.ArrayList;

/*
SortedTreeSet Class
Stores Person objects inside a binary search tree.
Tree remains sorted at all times because each new Person
is inserted immediately into the correct location.
Duplicate names are ignored if added.

Includes the extra credit features:
1. Parent reference and getParent()
2. isBalanced()
3. rebalance()
4. get(int i)
*/
public class SortedTreeSet implements SortedTreeSetInterface, PersonList {
    // Store the Person at the current tree node
    private Person person;
    // Store the left and right child subtree
    private SortedTreeSet left;
    private SortedTreeSet right;
    // Store parent subtree reference
    // (Extra Credit)
    private SortedTreeSet parent;

    /*
    Default Constructor method
    Creates an empty tree node.
    Empty nodes are represented by a null person field. 
    */
    public SortedTreeSet() {
        // Mark this tree node as empty
        this.person = null;
        // Start with no left or right child
        this.left = null;
        this.right = null;
        // Start with no parent
        this.parent = null;
    }
    /*
    Person Constructor method
    Creates a non-empty node containing a copy of the provided Person object.
    */
    public SortedTreeSet(Person p) {
        // Store copy of the provided Person in this node
        this.person = new Person(p);
        // Start with no left or right child
        this.left = null;
        this.right = null;
        // Start with no parent until attached to another node
        this.parent = null;
    }
    /*
    getPerson method
    Returns the Person stored in current node.
    */
    public Person getPerson() {
        // Return the current node's Person
        return person;
    }
    /*
    hasLeft method
    Returns true if the current node has a left child.
    */
    public boolean hasLeft() {
        // Return true when left is not null
        return left != null;
    }
    /*
    setLeft method
    Assigns a left child to the current node.
    Updates the child's parent reference.
    */
    public void setLeft(SortedTreeSet left) {
        // Save the new left child reference
        this.left = left;
        // If the new left child exists, update the parent reference
        if (left != null) {
            // Point the child's parent back to this node
            left.parent = this;
        }
    }
    /*
    getLeft method
    Returns the left child subtree.
    */
   public SortedTreeSet getLeft() {
    return left;
   }
   /*
    hasRight method
    Returns true if the current node has a right child.
    */
    public boolean hasRight() {
        // Return true when right is not null
        return right != null;
    }
    /*
    setRight method
    Assigns a right child to the current node.
    Updates the child's parent reference.
    */
    public void setRight(SortedTreeSet right) {
        // Save the new left child reference
        this.right = right;
        // If the new left child exists, update the parent reference
        if (right != null) {
            // Point the child's parent back to this node
            right.parent = this;
        }
    }
    /*
    getRight method
    Returns the right child subtree.
    */
   public SortedTreeSet getRight() {
    return right;
   }
   /*
   getParent method
   (Extra Credit)
   Returns the parent of the current node.
   The root has no parent, so it returns null.
   */
   public SortedTreeSet getParent() {
    return parent;
   }
   /*
   isEmpty method
   Returns true if the current node doesn't store a Person
   */
   public boolean isEmpty() {
    return person == null;
   }
   /*
   adds method
   Inserts a Person into the binary search tree.
   The tree is sorted by the Person's name.
   If the name already exists in the tree, the duplicate
   is ignored so that dupes are never stored.
   */
   public void add(Person p) {
    // Do nothing if the provided Person reference is null
    if (p == null) {
        // Exit early because there is nothing to add
        return;
    }
    // If node is empty, place the new Person here
    if (isEmpty()) {
        // Store a copy of the new Person in this node
        this.person = new Person(p);
        // Exit because insertion is complete
        return;
    }
    // Compare the new name to the current node's name alphabetically
    int comparison = p.getName().compareToIgnoreCase(person.getName());
    // If names match perfectly, this is a duplicate and thus ignored
    if (comparison == 0) {
        // Exit without inserting
        return;
    }
    // If the new name comes before the current name, move left
    if (comparison < 0) {
        // If left child already exists, keep searching left subtree
        if (hasLeft()) {
            // Recursively insert into the left subtree
            left.add(p);
        }
        else {
            // Create a new left child node containing the Person
            setLeft(new SortedTreeSet(p));
        }
    }
    else {
        // Otherwise the new name comes after current, so move right
        if (hasRight()) {
            // Recursively insert into the right subtree
            right.add(p);
        }
        else {
            // Create a new right child node containing the Person
            setRight(new SortedTreeSet(p));
        }
    }
   }
   /*
   size method
   Counts how many Person objects are stored in the tree.
   */
   public int size() {
    // Return zero if node is empty
    if (isEmpty()) {
        return 0;
    }
    // Start count at 1 for current node
    int count = 1;
    // Add the size of the left subtree if it exists
    if (hasLeft()) {
        // Increase the count by the number of nodes on the left
        count += left.size();
    }
    // Add the size of the right subtree if it exists
    if (hasRight()) {
        // Increase the count by the number of nodes on the right
        count += right.size();
    }
    // Return the final total
    return count;
   }
   /*
   get method
   (Extra Credit)
   Returns the Person at a given sorted index using in-order traversal.
   Index 0 is the first Person alphabetically
   */
  public Person get(int i) {
    // Make sure the requested index is not negative
    if (i < 0) {
        // Throw an exception because negative indexes are invalid
        throw new IndexOutOfBoundsException("Index cannot be negative: " + i);
    }
    // Create a helper object to keep track of travel
    IndexData data = new IndexData();
    // Search the tree in sorted order for the requested index
    findByIndex(i, data);
    // If no Person was found, the index was outside the valid range
    if (data.foundPerson == null) {
        // Throw an exception to report the invalid index
        throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size());
    }
    // Return the Person found at requested index
    return data.foundPerson;
  }
  /*
  findByIndex method
  Performs an in-order traversal and uses the IndexData helper class
  to track the current sorted index.
  */
 private void findByIndex(int targetIndex, IndexData data) {
    // Stop if this node is empty
    if (isEmpty()) {
        return;
    }
    // Stop if target has been found
    if (data.foundPerson != null) {
        return;
    }
    // Search the left subtree first
    if (hasLeft()) {
        // Recursive search
        left.findByIndex(targetIndex, data);
    }
    // Stop if target was found
    if (data.foundPerson != null) {
        return;
    }
    // Check whether current traversal index matches desired index
    if (data.currentIndex == targetIndex) {
        // Save a copy of Person found at index
        data.foundPerson = new Person(person);
        // Exit, target found
        return;
    }
    // Move the next sorted position after visiting the current node
    data.currentIndex++;
    // Search the right subtree
    if (hasRight()) {
        // Recursive search
        right.findByIndex(targetIndex, data);
    }
 }
 /*
 isBalanced method
 (Extra Credit)
 Checks whether tree is balanced.
 Balanced happens when for every node the difference between the left and right
 subtree height is no more than 1.
 */
 public boolean isBalanced() {
    // Ask recursive helper for balance info about current tree
    BalanceData result = getBalanceData();
    // Return the balanced status stored in helper object
    return result.balanced;
 }
 /*
 getBalanceData method
 Returns a BalanceData object that has:
 1. subtree height
 2. subtree balanced status
 Using the BalanceData helper class allows the return of both pieces of
 info together.
 */
 private BalanceData getBalanceData() {
    // Empty nodes are balanced and have height 0
    if (isEmpty()) {
        // Return balance information for an empty subtree
        return new BalanceData(0, true);
    }
    // Prepare default balance info for left and right subtree
    BalanceData leftData = new BalanceData(0, true);
    BalanceData rightData = new BalanceData(0, true);

    // If a left child exists, gather its balance info recursively
    if (hasLeft()) {
        // Save balance result from left subtree
        leftData = left.getBalanceData();
    }
    // If a right child exists, gather its balance info recursively
    if (hasRight()) {
        // Save balance result from left subtree
        rightData = right.getBalanceData();
    }
    // Compute subtree's height using the larger value
    int currentHeight = 1 + Math.max(leftData.height, rightData.height);
    // Check whether current node satisfies balanced
    boolean currentBalanced =
        leftData.balanced &&
        rightData.balanced &&
        Math.abs(leftData.height - rightData.height) <= 1;
    // Return the height and balance result together
    return new BalanceData(currentHeight, currentBalanced);
 }
 /*
 rebalance method
 (Extra Credit)
 Rebuilds tree into balanced form.
 Collects all Person objects in sorted order, then rebuilds
 the tree by repeatedly choosing the middle item as the root.
 */
 public void rebalance() {
        // Create list to hold all Person objects in sorted order
        ArrayList<Person> orderedPeople = new ArrayList<Person>();
        // Fill list using an in-order traversal
        fillInOrderList(orderedPeople);
        // Build brand new balanced tree from the sorted list
        SortedTreeSet rebuiltTree = buildBalancedTree(orderedPeople, 0, orderedPeople.size() - 1);
        // If rebuilding returned null, clear tree completely
        if (rebuiltTree == null) {
            // Remove the current Person
            this.person = null;
            // Remove the left child
            this.left = null;
            // Remove the right child
            this.right = null;
            // Remove the parent reference
            this.parent = null;
        }
        else {
            // Copy the rebuilt root Person into this tree
            this.person = rebuiltTree.person;
            // Copy the rebuilt left and right subtree into this tree
            this.left = rebuiltTree.left;
            this.right = rebuiltTree.right;
            // The root of the rebuilt tree should not have a parent
            this.parent = null;
            // Fix the parent reference of the left child if it exists
            if (this.left != null) {
                // Point the left child's parent back to this root
                this.left.parent = this;
            }

            // Fix the parent reference of the right child if it exists
            if (this.right != null) {
                // Point the right child's parent back to this root
                this.right.parent = this;
            }
        }
    }
 /*
 fillInOrderList method
 Performs an in-order traversal and places the tree contents into a list 
 in alphabetical order.
 */
 private void fillInOrderList(ArrayList<Person> list) {
        // Stop immediately if node is empty
        if (isEmpty()) {
            return;
        }

        // Add the left subtree first
        if (hasLeft()) {
            // Recursively add all smaller names
            left.fillInOrderList(list);
        }

        // Add a copy of the current Person to the list
        list.add(new Person(person));

        // Add the right subtree last
        if (hasRight())
        {
            // Recursively add all larger names
            right.fillInOrderList(list);
        }
    }
 /*
 buildBalancedTree method
 Builds a balanced binary search tree from a sorted list of Person objects.
 Middle element becomes root of subtree.
 */
 private SortedTreeSet buildBalancedTree(ArrayList<Person> list, int start, int end) {
        // Stop recursion when the current range is invalid
        if (start > end) {
            return null;
        }
        // Find the middle position of the current range
        int middle = (start + end) / 2;
        // Create a new node from the middle Person
        SortedTreeSet node = new SortedTreeSet(list.get(middle));
        // Recursively build and attach the left half
        node.setLeft(buildBalancedTree(list, start, middle - 1));
        // Recursively build and attach the right half
        node.setRight(buildBalancedTree(list, middle + 1, end));
        // Return the completed subtree root
        return node;
    }
 /*
 toString method
 Returns the contents of the tree in sorted order.
 In-order traversal is used because it naturally prints a binary
 search tree in alphabetical order.
 */
    public String toString() {
        // Create a StringBuilder to efficiently build the output text
        StringBuilder output = new StringBuilder();
        // Add a heading line above the employee list
        output.append("People in sorted order:\n");
        // Append all tree contents in sorted order
        buildInOrderString(output);
        // Return the finished text.
        return output.toString();
    }
 /*
 buildInOrderString method
 Performs an in-order traversal and appends each Person to the provided StringBuilder.
 */
 private void buildInOrderString(StringBuilder output) {
        // Stop immediately if this node is empty
        if (isEmpty()) {
            return;
        }
        // Append the left subtree first.
        if (hasLeft()) {
            // Append all smaller names first
            left.buildInOrderString(output);
        }
        // Append the current Person and a new line
        output.append(person).append("\n");
        // Append the right subtree last
        if (hasRight()) {
            // Append all larger names after the current node
            right.buildInOrderString(output);
        }
    }
}
