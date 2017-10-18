// Simple binary tree class that includes methods to construct a
// tree of ints, to print the structure, and to print the data
// using a preorder, inorder or postorder traversal.  The trees
// built have nodes numbered starting with 1 and numbered
// sequentially level by level with no gaps in the tree.  The
// documentation refers to these as "sequential trees."
//
// from buildingjavaprograms.com


import java.util.*;

public class IntTree {
    private IntTreeNode overallRoot;

    // pre : max > 0
    // post: constructs a sequential tree with given number of
    //       nodes
    public IntTree(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("max: " + max);
        }
        overallRoot = buildTree(1, max);
    }
    
    public IntTree() {
        overallRoot = null;
    }
    
    // constructor added so we can build page 1029 reference trees
    public IntTree(IntTreeNode given) {
        overallRoot = given;
    }
    /**
     * Student: Yauheniya Zapryvaryna
     * Instructor: Bill Iverson
     * Bellevue College
     * CS 211
     * June 10, 2014
     * Assignment IntTree Chapter 17 BJP
     */
    /******************************MY SOLUTION**********************************/
    
    //ex.4
    //returns the number of branch nodes in a binary tree that contain even numbers
  	public int countEvenBranches() {
  		return countEvenBranches(overallRoot);
  	}
  	//helper method
    private int countEvenBranches(IntTreeNode root){
        if(root==null) { //base case: empty tree has zero branches
      		return 0;
      	}
      	else if(root.data%2==0 && root.left!=null||root.data%2==0 && root.right!=null) {
      		return 1+countEvenBranches(root.left)+countEvenBranches(root.right);
      	} else
      		return countEvenBranches(root.left)+countEvenBranches(root.right);
    }
    
    //ex.6
    //outputs the leaves of a binary tree from right to left (in the reverse order)
    public void printLeaves () {
      	IntTreeNode temp = overallRoot;
      	if(temp==null) { //if tree is empty
      		System.out.println("no leaves");
      	} else {
      	    String s = "leaves: ";
      	    System.out.print(s);
      	    printLeaves(overallRoot);
      	}
    }
    //helper method
    private void printLeaves(IntTreeNode root) {
      	if(root!=null) {
      		if (root.left==null&&root.right==null) {
      		    System.out.print(root.data+" ");
      	    } else {
      		    printLeaves(root.right);
      	        printLeaves(root.left);
      	    }
      	}
    }
    
    //ex.8
    //returns a parenthesized String that has three elements separated by commas: data at the root, 
    //String representation of the left subtree, String representation of the right subtree.
    public String toString() {
        return toString(overallRoot);
    }
    //helper method
    private String toString(IntTreeNode root) {
        if(root == null) { //for an empty tree
      	    return "empty";
      	}
      	else if (root.left==null&&root.right==null) {
      	    return "" + root.data;
      	}
      	else 
      	    return "(" + root.data + ", " + toString(root.left)
      	    			+ ", " + toString(root.right) + ")";
    }
    
  	//ex.12
    //removes the leaf nodes from a tree
    public void removeLeaves() {
    	//reassigning the value, updating it to avoid the use of the old one
    	overallRoot = removeLeaves(overallRoot);
    }
    //helper method, inside of which root.right and root.left
    //get updated to avoid the repeated use of the same values
    private IntTreeNode removeLeaves(IntTreeNode root) {   
      	if(root!=null) {//if tree is not empty
            if(root.left!=null||root.right!=null) {
      		    root.left = removeLeaves(root.left);
      		    root.right = removeLeaves(root.right);
      	    } else {
      	        root = null;     
      	    }
      	}
      	return root;
    }
    
    //ex.16
    //eliminates branch nodes that have only one child
    public void tighten() {
    	//reassigning the value, updating it to avoid the use of the old one
      	overallRoot = tighten(overallRoot);
    }
    //helper method, inside of which root, root.right and root.left
    //get updated to avoid the repeated use of the same values
    private IntTreeNode tighten(IntTreeNode root) {
      	if (root!=null) { //if tree is not empty
      		if(root.left==null&&root.right!=null) {
      			root=tighten(root.right);
      			root.right = tighten(root.right);
      		}
      		else if(root.right==null&&root.left!=null) {
      			root = tighten(root.left);
      			root.left = tighten(root.left);
      		} else {
      			root.right = tighten(root.right);
      			root.left = tighten(root.left);
      		}
      	}
      	return root;
    }
      
    /***************************END OF MY SOLUTION*************************/

      
    //Exercise #7, Chapter 17
    public boolean isFull() {
    	return (overallRoot == null || isFull(overallRoot));
	}

	private boolean isFull(IntTreeNode root) {
    	if(root.left == null && root.right == null) {
        	return true;
    	} else {
        	return (root.left != null && root.right != null && isFull(root.left) && isFull(root.right));
    	}
	}  

    // post: returns a sequential tree with n as its root unless
    //       n is greater than max, in which case it returns an
    //       empty tree
    private IntTreeNode buildTree(int n, int max) {
        if (n > max) {
            return null;
        } else {
            return new IntTreeNode(n, buildTree(2 * n, max),
                                   buildTree(2 * n + 1, max));
        }
    }

    // post: prints the tree contents using a preorder traversal
    public void printPreorder() {
        System.out.print("preorder:");
        printPreorder(overallRoot);
        System.out.println();
    }

    // post: prints the tree contents using a preorder traversal
    // post: prints in preorder the tree with given root
    private void printPreorder(IntTreeNode root) {
        if (root != null) {
            System.out.print(" " + root.data);
            printPreorder(root.left);
            printPreorder(root.right);
        }
    }

    // post: prints the tree contents using a inorder traversal
    public void printInorder() {
        System.out.print("inorder:");
        printInorder(overallRoot);
        System.out.println();
    }

    // post: prints in inorder the tree with given root
    private void printInorder(IntTreeNode root) {
        if (root != null) {
            printInorder(root.left);
            System.out.print(" " + root.data);
            printInorder(root.right);
        }
    }

    // post: prints the tree contents using a postorder traversal
    public void printPostorder() {
        System.out.print("postorder:");
        printPostorder(overallRoot);
        System.out.println();
    }

    // post: prints in postorder the tree with given root
    private void printPostorder(IntTreeNode root) {
        if (root != null) {
            printPostorder(root.left);
            printPostorder(root.right);
            System.out.print(" " + root.data);
        }
    }

    // post: prints the tree contents, one per line, following an
    //       inorder traversal and using indentation to indicate
    //       node depth; prints right to left so that it looks
    //       correct when the output is rotated.
    public void printSideways() {
        printSideways(overallRoot, 0);
    }

    // post: prints in reversed preorder the tree with given
    //       root, indenting each line to the given level
    private void printSideways(IntTreeNode root, int level) {
        if (root != null) {
            printSideways(root.right, level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.println(root.data);
            printSideways(root.left, level + 1);
        }
    }
}