/*
 * LinkedTree.java
 *
 * Computer Science 112
 *
 * Modifications and additions by:
 *     name: Olivia Bene
 *     username:livbene@bu.edu
 */

import java.util.*;

/*
 * LinkedTree - a class that represents a binary tree containing data
 * items with integer keys.  If the nodes are inserted using the
 * insert method, the result will be a binary search tree.
 */
public class LinkedTree {
    // An inner class for the nodes in the tree
    private class Node {
        private int key;         // the key field
        private LLList data;     // list of data values for this key
        private Node left;       // reference to the left child/subtree
        private Node right;      // reference to the right child/subtree
        
        private Node(int key, Object data){
            this.key = key;
            this.data = new LLList();
            this.data.addItem(data, 0);
            this.left = null;
            this.right = null;
        }
    }
    
    // the root of the tree as a whole
    private Node root;
    
    public LinkedTree() {
        root = null;
    }
    
    /*
     * Prints the keys of the tree in the order given by a preorder traversal.
     * Invokes the recursive preorderPrintTree method to do the work.
     */
    public void preorderPrint() {
        if (root != null) {
            preorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs a preorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void preorderPrintTree(Node root) {
        System.out.print(root.key + " ");
        if (root.left != null) {
            preorderPrintTree(root.left);
        }
        if (root.right != null) {
            preorderPrintTree(root.right);
        }
    }
    
    /*
     * Prints the keys of the tree in the order given by a postorder traversal.
     * Invokes the recursive postorderPrintTree method to do the work.
     */
    public void postorderPrint() {
        if (root != null) {
            postorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs a postorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void postorderPrintTree(Node root) {
        if (root.left != null) {
            postorderPrintTree(root.left);
        }
        if (root.right != null) {
            postorderPrintTree(root.right);
        }
        System.out.print(root.key + " ");
    }
    
    /*
     * Prints the keys of the tree in the order given by an inorder traversal.
     * Invokes the recursive inorderPrintTree method to do the work.
     */
    public void inorderPrint() {
        if (root != null) {
            inorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs an inorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void inorderPrintTree(Node root) {
        if (root.left != null) {
            inorderPrintTree(root.left);
        }
        System.out.print(root.key + " ");
        if (root.right != null) {
            inorderPrintTree(root.right);
        }
    }
    
    /* 
     * Inner class for temporarily associating a node's depth
     * with the node, so that levelOrderPrint can print the levels
     * of the tree on separate lines.
     */
    private class NodePlusDepth {
        private Node node;
        private int depth;
        
        private NodePlusDepth(Node node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
    
    /*
     * Prints the keys of the tree in the order given by a
     * level-order traversal.
     */
    public void levelOrderPrint() {
        LLQueue<NodePlusDepth> q = new LLQueue<NodePlusDepth>();
        
        // Insert the root into the queue if the root is not null.
        if (root != null) {
            q.insert(new NodePlusDepth(root, 0));
        }
        
        // We continue until the queue is empty.  At each step,
        // we remove an element from the queue, print its value,
        // and insert its children (if any) into the queue.
        // We also keep track of the current level, and add a newline
        // whenever we advance to a new level.
        int level = 0;
        while (!q.isEmpty()) {
            NodePlusDepth item = q.remove();
            
            if (item.depth > level) {
                System.out.println();
                level++;
            }
            System.out.print(item.node.key + " ");
            
            if (item.node.left != null) {
                q.insert(new NodePlusDepth(item.node.left, item.depth + 1));
            }
            if (item.node.right != null) {
                q.insert(new NodePlusDepth(item.node.right, item.depth + 1));
            }
        }
        System.out.println();
    }
    
    /*
     * Searches for the specified key in the tree.
     * If it finds it, it returns the list of data items associated with the key.
     * Invokes the recursive searchTree method to perform the actual search.
     */
    public LLList search(int key) {
        Node n = searchTree(root, key);
        if (n == null) {
            return null;
        } else {
            return n.data;
        }
    }
    
    /*
     * Recursively searches for the specified key in the tree/subtree
     * whose root is specified. Note that the parameter is *not*
     * necessarily the root of the entire tree.
     */
    private static Node searchTree(Node root, int key) {
        if (root == null) {
            return null;
        } else if (key == root.key) {
            return root;
        } else if (key < root.key) {
            return searchTree(root.left, key);
        } else {
            return searchTree(root.right, key);
        }
    }
    
    /*
     * Inserts the specified (key, data) pair in the tree so that the
     * tree remains a binary search tree.
     */
    public void insert(int key, Object data) {
        // Find the parent of the new node.
        Node parent = null;
        Node trav = root;
        while (trav != null) {
            if (trav.key == key) {
                trav.data.addItem(data, 0);
                return;
            }
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        
        // Insert the new node.
        Node newNode = new Node(key, data);
        if (parent == null) {    // the tree was empty
            root = newNode;
        } else if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }
    
    /*
     * FOR TESTING: Processes the integer keys in the specified array from 
     * left to right, adding a node for each of them to the tree. 
     * The data associated with each key is a string based on the key.
     */
    public void insertKeys(int[] keys) {
        for (int i = 0; i < keys.length; i++) {
            insert(keys[i], "data for key " + keys[i]);
        }
    }
    
    /*
     * Deletes the node containing the (key, data) pair with the
     * specified key from the tree and return the associated data item.
     */
    public LLList delete(int key) {
        // Find the node to be deleted and its parent.
        Node parent = null;
        Node trav = root;
        while (trav != null && trav.key != key) {
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        
        // Delete the node (if any) and return the removed data item.
        if (trav == null) {   // no such key    
            return null;
        } else {
            LLList removedData = trav.data;
            deleteNode(trav, parent);
            return removedData;
        }
    }
    
    /*
     * Deletes the node specified by the parameter toDelete.  parent
     * specifies the parent of the node to be deleted. 
     */
    private void deleteNode(Node toDelete, Node parent) {
        if (toDelete.left != null && toDelete.right != null) {
            // Case 3: toDelete has two children.
            // Find a replacement for the item we're deleting -- as well as 
            // the replacement's parent.
            // We use the smallest item in toDelete's right subtree as
            // the replacement.
            Node replaceParent = toDelete;
            Node replace = toDelete.right;
            while (replace.left != null) {
                replaceParent = replace;
                replace = replace.left;
            }
            
            // Replace toDelete's key and data with those of the 
            // replacement item.
            toDelete.key = replace.key;
            toDelete.data = replace.data;
            
            // Recursively delete the replacement item's old node.
            // It has at most one child, so we don't have to
            // worry about infinite recursion.
            deleteNode(replace, replaceParent);
        } else {
            // Cases 1 and 2: toDelete has 0 or 1 child
            Node toDeleteChild;
            if (toDelete.left != null) {
                toDeleteChild = toDelete.left;
            } else {
                toDeleteChild = toDelete.right;  // null if it has no children
            }
            
            if (toDelete == root) {
                root = toDeleteChild;
            } else if (toDelete.key < parent.key) {
                parent.left = toDeleteChild;
            } else {
                parent.right = toDeleteChild;
            }
        }
    }
    
    /*
     * "wrapper method" for the recursive depthInTree() method
     * from PS 7, Problem 6
     */
    public int depth(int key) {
        if (root == null) {    // root is the root of the entire tree
            return -1;
        }
        
        return depthInTree(key, root);
    }
    
    /*
     * original version of the recursive depthInTree() method  
     * from PS 7, Problem 6. You will write a more efficient version
     * of this method.
     */
    private static int depthInTree(int key, Node root) {
        if (key == root.key) {
            return 0;
        }
        
        if (root.left != null) {
            int depthInLeft = depthInTree(key, root.left);
            if (depthInLeft != -1) {
                return depthInLeft + 1;
            }
        }
        
        if (root.right != null) {
            int depthInRight = depthInTree(key, root.right);
            if (depthInRight != -1) {
                return depthInRight + 1;
            }
        }
        
        return -1;
    }

    /*
    *Takes an integer key as its only parameter and that uses uses iteration 
    to determine and return the depth of the node with that key. It's an iterative 
    method should take advantage of the fact that the tree is a binary search 
    tree, and should avoid considering subtrees that couldn’t contain the specified key. 
    It should return -1 if the specified key is not found in the tree.
    */
    public int depthIter(int key)
    {
        int depth = -1;

        if(root == null || root.key == key)
        {
            return 0;
        }

        int depthR = 0;
        int depthL = 0;
        Node trav = root;

        while (trav != null && trav.key != key) 
        {
            if (key < trav.key) 
            {
                trav = trav.left;
                depthL ++;
            } 
            else 
            {
                trav = trav.right;
                depthR++;
            }
        }

        if (trav == null) 
        {    
            return depth;
        }

        return depthL + depthR;
    }

    /*
        takes no parameters and that returns 
        the number of even keys in the entire 
        tree. 
    */
    public int numEvenKeys()
    {
        return numEvenKeysRec(root);
    }

    /*
    takes a reference to a Node object as its 
    only parameter; it should use recursion to 
    find and return the number of even keys in 
    the binary search tree or subtree whose root 
    node is specified by the parameter.
    */
    private static int numEvenKeysRec(Node n)
    {
        if(n  == null)
        {
            return 0;
        }

        int count = numEvenKeysRec(n.left) + numEvenKeysRec(n.right);

        if(n.key % 2 == 0)
        {
            return count +1;
        }

        return count;
    }

    /*
        takes no parameters and that uses iteration to find 
        and delete the node containing the largest key in the 
        tree; it should also return the value of the key whose
        node was deleted. If the tree is empty when the method 
        is called, the method should return -1.
    */
    public int deleteMax()
    {
        int value = -1;
        Node max = root;
        Node parent = root;
        int count = -1;

        if(root == null)
        {
            return value;
        }

        while(max.right != null) // find largest number (right most node)
        {
            max = max.right;
            count ++;
        }

        for(int i = 0; i< count; i++)
        {
            parent = parent.right;
        }


        //check if leaf
        if(max.left == null && max.right == null)
        {
            if(max != root)
            {
                if(parent.left == max)
                {
                    parent.left = null;
                }
                else
                {
                    parent.right = null;
                }
            }
            else
            {
                root = null;
            }
        }

        //one child
        else if(max.left == null)
        {
            if(max == root)
            {
                root = max.right;
            }

            else if(parent.left == max)
            {
                parent.left = max.right;
            }

            else
            {
                parent.right = max.right;
            }
        }

        else if(max.right == null)
        {
            if(max == root)
            {
                root = max.left;
            }

            else if(parent.left == max)
            {
                parent.left = max.left;
            }

            else
            {
                parent.right = max.left;
            }
        }

        return max.key;
    }




    
    public static void main(String[] args) 
    {
        System.out.println("--- Testing depth()/depthInTree() from Problem 7 ---");
        System.out.println();
        System.out.println("(0) Testing on tree from Problem 3, depth of 28 node");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {44, 35, 53, 23, 48, 62, 28, 57, 80};
            tree.insertKeys(keys);
            
            int results = tree.depth(28);
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(3);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 3);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests
                           
///////////////////////////////////////////////////////////////////////////////////////

        System.out.println("--- Testing depthIter() from Problem 7 ---");
        System.out.println();
        System.out.println("(0) Testing on {44, 35, 53, 23, 48, 62, 28, 57, 80}");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {44, 35, 53, 23, 48, 62, 28, 57, 80};
            tree.insertKeys(keys);


            System.out.println("actual results:");
            System.out.println( tree.depthIter(48) );
            System.out.println( tree.depthIter(28) );
            System.out.println( tree.depthIter(44) );
            System.out.println( tree.depthIter(30) );
    
            System.out.println("expected results:");
            System.out.println("2, 3, 0, -1");
            
        } 
        catch (Exception e) 
        {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                   

        System.out.println();    // include a blank line between tests


        System.out.println("(1) Testing on {44, 35, 53, 23, 48, 62, 28, 57, 80}");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {44, 35, 53, 23, 48, 62, 28, 57, 80};
            tree.insertKeys(keys);
            
            
            System.out.println("actual results:");
            System.out.println( tree.depthIter(0) );
            System.out.println( tree.depthIter(80) );
            System.out.println( tree.depthIter(62) );
            System.out.println( tree.depthIter(55) );
    
            System.out.println("expected results:");
            System.out.println("-1, 3, 2, -1 ");
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests
                           


        System.out.println("--- Testing numEvenKeys() from Problem 7 ---");
        System.out.println();
        System.out.println("(0) Testing on: {44, 35, 53, 23, 48, 62, 28, 57, 80}");
        try 
        {
            LinkedTree tree = new LinkedTree();
            int[] keys = {44, 35, 53, 23, 48, 62, 28, 57, 80};
            tree.insertKeys(keys);
            int answer = tree.numEvenKeys();
            System.out.println("Actual Results: ");
            System.out.println(answer);
            System.out.println("Expected Results: ");
            System.out.println(5);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(answer == 5);
            } 
            catch (Exception e) 
            {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

    
        System.out.println();
        System.out.println("(1) Testing on: {48, 35, 53, 21, 30, 28, 58, 1}");
        try 
        {
            LinkedTree tree1 = new LinkedTree();
            int[] keys1 = {48, 35, 53, 21, 30, 28, 58, 1};
            tree1.insertKeys(keys1);
            int answer1 = tree1.numEvenKeys();
            System.out.println("Actual Results: ");
            System.out.println(answer1);
            System.out.println("Expected Results: ");
            System.out.println(4);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(answer1 == 4);
            } 
            catch (Exception e) 
            {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println();    // include a blank line between tests


        System.out.println("--- Testing deleteMax() from Problem 7 ---");
        System.out.println();
        System.out.println("(0) Testing on: {44, 35, 53, 23, 48, 62, 28, 57, 80}");
        try 
        {
            LinkedTree tree = new LinkedTree();
            int[] keys = {44, 35, 53, 23, 48, 62, 28, 57, 80};
            tree.insertKeys(keys);
            int answer = tree.deleteMax();
            System.out.println("Actual Results: ");
            System.out.println(answer);
            System.out.println("Expected Results");
            System.out.println(80);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(answer == 80);
            } 
            catch (Exception e) 
            {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println();
        System.out.println("(1) Testing on: {44, 35, 53, 23, 48, 62, 28, 57}");
        try 
        {
            LinkedTree tree = new LinkedTree();
            int[] keys = {44, 35, 53, 23, 48, 62, 28, 57};
            tree.insertKeys(keys);
            int answer = tree.deleteMax();
            System.out.println("Actual Results: ");
            System.out.println(answer);
            System.out.println("Expected Results");
            System.out.println(62);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(answer == 62);
            } 
            catch (Exception e) 
            {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
    }
}
