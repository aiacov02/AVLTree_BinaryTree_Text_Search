package cy.ac.ucy.cs.epl231.ID1004416.homework2;

import java.util.Queue;
import java.util.LinkedList;
/**
 *  Implements a Binary tree.
 *  
 *  @author Andreas Iakovou
 *  @author Andreas Panteli
 */
public class BinaryTree_957228_1004416 {
  // Root node pointer. Will be null for an empty tree.
  private Node root;
 

  /*
   --Node--
   The binary tree is built using this nested node class.
   Each node stores one data element, and has left and right
   sub-tree pointer which may be null.
   The node is a "dumb" nested class -- we just use it for
   storage; it does not have any methods.
  */
  private class Node implements Comparable<Node> {
    

	Node left;
    Node right;
    Queue<Integer> queue;
    String data;
    int pos;

    Node(String newData,int numOfword) {
      left = null;
      right = null;
      queue = new  LinkedList<Integer>();
      
      pos=numOfword;
      data = newData;
    }
    
    public int compareTo(Node arg0) {
		return  data.compareTo(arg0.data);
	}
    
  }

  /**
   Creates an empty binary tree -- a null root pointer.
  */
  public BinaryTree_957228_1004416(){
    root = null;
  }
 
  /**
   * Returns true if the given target is in the binary tree.
   * Uses a recursive helper.
  */
  public boolean lookup(String data,int numOfword) {
	  Node newNode = new Node(data,numOfword);
	  return(lookup(root, newNode));
  }
 

  /**
   * Recursive lookup  -- given a node, recur
   * down searching for the given data.
   */
  
  public boolean print(String data,boolean DEBUG_BINARY){
	  Node newNode = new Node(data,0);
	  return (print(root,newNode,DEBUG_BINARY));
  }
  /**
   * prints the list of the newNode from the tree  
   * @param node is the head of the tree
   * @param newNode is the node we want to print
   * @param DEBUG_BINARY if it's true we won't print the results
   * @return true if it print and fount the node else false
   */
  private boolean print(Node node, Node newNode,boolean DEBUG_BINARY){
	  
	  if (node==null) {
		  if(DEBUG_BINARY){
			  System.out.println("The word: " + newNode.data + " was not found");
		  }
	      return(false);
	    }

	    if (newNode.compareTo(node)==0) {
	    	if(DEBUG_BINARY){
		    	int times = node.queue.size()+1;
		    	System.out.print("The word: " + node.data + " was found "+times+" times in pos: ");
		    	System.out.print(node.pos);
		    	for(int i=0; i<node.queue.size(); i++){
		    		int temp=node.queue.poll();
		    		System.out.print(", " + temp);
		    		node.queue.add(temp);
		    	}
		    	System.out.println();
	    	}
	    	return(true);
	    }
	    else if (newNode.compareTo(node)<0) {
	      return(print(node.left, newNode,DEBUG_BINARY));
	    }
	    else {
	      return(print(node.right, newNode,DEBUG_BINARY));
	    }
}
  
  /**
   * compare the newNode with the node
   * @param node is the head of the tree
   * @param newNode is the node with the new word
   * @return true if find the newNode in the tree else false
   */
  private boolean lookup(Node node, Node newNode) {
    if (node==null) {
      return(false);
    }

    if (newNode.compareTo(node)==0) {
      return(true);
    }
    else if (newNode.compareTo(node)<0) {
      return(lookup(node.left, newNode));
    }
    else {
      return(lookup(node.right, newNode));
    }
  }
 

  /**
   * Inserts the given data into the binary tree.
   * Uses a recursive helper.
   */
  public void insert(String data, int numOfword) {
      Node newNode = new Node(data,numOfword);
	  root = insert(root, newNode);
  }
 

  /**
   * Recursive insert -- given a node pointer, recur down and
   * insert the given data into the tree. Returns the new
   * node pointer (the standard way to communicate
   * a changed pointer back to the caller).
   */
  private Node insert(Node node, Node newNode) {
	  if (node==null) {
		  node = newNode;
	  }
	  else if (newNode.compareTo(node) < 0) {
		  node.left = insert(node.left, newNode);
	  }
	  else if(newNode.compareTo(node)>0) {
		  node.right = insert(node.right, newNode);
	  }  
	  else{
		  int temp = 0;
		  temp =  newNode.pos;	 
		  node.queue.add(temp);
  	  
	  }  

    return(node); // in any case, return the new pointer to the caller
  }
 
  public int getHeight() {
	  return getHeight(root);
  }
  /**
   * 
   * @param n the node a tree
   * @return the height of the node
   */
  private int getHeight(Node n) {
	  if (n != null && n.left == null && n.right == null) return 0;
	  return n == null ? 0 : 1 + Math.max( getHeight(n.left), getHeight(n.right) );
	  //OR null returns -1
	  //return n == null ? -1 : 1 + Math.max( getHeight(n.left), getHeight(n.right) );
  }  
  /**
   * @return the size of the tree
   */
  public int getSize() {
	  return getSize(root);
  }
  /**
   * @param n the node of the tree
   * @return the size of the tree
   */
  private int getSize(Node n) {
	  return (n==null) ? 0 : 1 + getSize(n.left) + getSize(n.right);
  }
  /**
   * @return the min node
   */
  public String getMin() {
	  return root == null ? "0" : getMin(root);
  }
  /**
   * @param n the node of the tree
   * @return the last node in the left side of the tree
   */
  private String getMin(Node n) {
	  return (n.left==null) ? n.data : getMin(n.left);
  }
  
  /**
   * @return the last node of the right side of the tree else 0 
   */
  public String getMax() {
	  return root == null ? "0" : getMax(root);
  }
  /**
   * @param n the node of the tree
   * @return the last node of the right side of the tree
   */
  private String getMax(Node n) {
	  return (n.right==null) ? n.data : getMax(n.right);
  }
}