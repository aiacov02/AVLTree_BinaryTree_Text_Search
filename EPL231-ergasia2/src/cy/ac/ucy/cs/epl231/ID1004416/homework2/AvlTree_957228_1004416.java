package cy.ac.ucy.cs.epl231.ID1004416.homework2;

import java.util.Queue;
import java.util.LinkedList;

/**
 *  Implements an AVL tree.
 *  
 *  @author Andreas Panteli
 *  @author Andreas Iakovou
 */
public class AvlTree_957228_1004416{
    /**
     * Construct the tree.
     */
    public AvlTree_957228_1004416( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     * @param poss the position of the first time the word is found
     */
    public void insert( String x , int poss)
    {
    	AvlNode newNode = new AvlNode(x,poss);
        root = insert(root,newNode);
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if x is found.
     */
    public boolean print( String x, boolean DEBUG_AVL )
    {
    	AvlNode newNode = new AvlNode(x,0);
        return print( newNode, root, DEBUG_AVL );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    private static final int ALLOWED_IMBALANCE = 1;
    
    /**
     *  Assume t is either balanced or within one of being balanced
     *  @param t is the node of the tree
     */
    private AvlNode balance( AvlNode t )
    {
        if( t == null )
            return t;
        
        if( height( t.left ) - height( t.right ) > ALLOWED_IMBALANCE )
            if( height( t.left.left ) >= height( t.left.right ) )
                t = rotateWithLeftChild( t );
            else
                t = doubleWithLeftChild( t );
        else
        if( height( t.right ) - height( t.left ) > ALLOWED_IMBALANCE )
            if( height( t.right.right ) >= height( t.right.left ) )
                t = rotateWithRightChild( t );
            else
                t = doubleWithRightChild( t );

        t.height = Math.max( height( t.left ), height( t.right ) ) + 1;
        return t;
    }
    
    public void checkBalance( )
    {
        checkBalance( root );
    }
    /**
     * is checking the balance of the tree
     * @param t the node of the tree
     * @return -1 if the node is null or the height of the node
     */
    private int checkBalance( AvlNode t )
    {
        if( t == null )
            return -1;
        
        if( t != null )
        {
            int hl = checkBalance( t.left );
            int hr = checkBalance( t.right );
            if( Math.abs( height( t.left ) - height( t.right ) ) > 1 ||
                    height( t.left ) != hl || height( t.right ) != hr )
                System.out.println( "OOPS!!" );
        }
        
        return height( t );
    }
    
    
    /**
     * Internal method to insert into a subtree.
     * @param newNode the node to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private AvlNode insert(AvlNode t, AvlNode newNode )
    {
        if( t == null )
            return newNode;
        
        int compareResult = newNode.compareTo( t );
        
        if( compareResult < 0 )
            t.left = insert(t.left, newNode );
        else if( compareResult > 0 )
            t.right = insert(t.right, newNode );
        else
            t.queue.add(newNode.pos);
        
        return balance( t );
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @param DEBUG_AVL if it's true we won't print the results
     * @return true if x is found in subtree.
     */
    private boolean print( AvlNode x, AvlNode t, boolean DEBUG_AVL)
    {
    	
        while( t != null )
        {
            int compareResult = x.compareTo( t );
            
            if( compareResult < 0 )
                t = t.left;
            else if( compareResult > 0 )
                t = t.right;
            else{
            	if(DEBUG_AVL){
	            	int times = t.queue.size() + 1;
	            	System.out.print("The word: " + t.element + " was found " + times + "times in pos: ");
	            	System.out.print(t.pos);
	            	for(int i=1; i<=t.queue.size(); i++){
	            		int tmp = t.queue.poll();
	            		System.out.print(", " + tmp);
	            		t.queue.add(tmp);
	            	}
	            	System.out.println();
	            	return true;
            	}
            	return true;    // Match
            }
        }
        if(DEBUG_AVL){
        	System.out.println("The word " + x.element + " was not found");
        }
        
        return false;   // No match
        
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the tree.
     */
    private void printTree( AvlNode t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Return the height of node t, or -1, if null.
     */
    private int height( AvlNode t )
    {
        return t == null ? -1 : t.height;
    }

    /**
     * Rotate binary tree node with left child.
     * For AVL trees, this is a single rotation for case 1.
     * Update heights, then return new root.
     */
    private AvlNode rotateWithLeftChild( AvlNode k2 )
    {
        AvlNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = Math.max( height( k1.left ), k2.height ) + 1;
        return k1;
    }

    /**
     * Rotate binary tree node with right child.
     * For AVL trees, this is a single rotation for case 4.
     * Update heights, then return new root.
     */
    private AvlNode rotateWithRightChild( AvlNode k1 )
    {
        AvlNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = Math.max( height( k2.right ), k1.height ) + 1;
        return k2;
    }

    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child.
     * For AVL trees, this is a double rotation for case 2.
     * Update heights, then return new root.
     */
    private AvlNode doubleWithLeftChild( AvlNode k3 )
    {
        k3.left = rotateWithRightChild( k3.left );
        return rotateWithLeftChild( k3 );
    }

    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child.
     * For AVL trees, this is a double rotation for case 3.
     * Update heights, then return new root.
     */
    private AvlNode doubleWithRightChild( AvlNode k1 )
    {
        k1.right = rotateWithLeftChild( k1.right );
        return rotateWithRightChild( k1 );
    }

    private static class AvlNode implements Comparable<AvlNode>
    {
            // Constructors
        AvlNode( String theElement, int poss )
        {
            this( theElement, null, null, poss);
        }

        AvlNode( String theElement, AvlNode lt, AvlNode rt, int poss )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
            height   = 0;
            pos		 = poss;
            queue	 = new LinkedList<Integer>();
        }

        String element;      // The data in the node
        AvlNode left;         // Left child
        AvlNode right;        // Right child
        int               height;       // Height
        Queue<Integer> queue;
        int pos;
        
        public int compareTo(AvlNode x){
        	return element.compareTo(x.element);
        }
          
    }
    
      /** The tree root. */
    private AvlNode root;


    //Modification: getHeight()
    /**
     * @return the tree height
     */
    public int getHeight() {
    	return root.height;
  	  
    }
    

}