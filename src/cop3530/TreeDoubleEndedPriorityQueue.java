/* File: TreeDoubleEndedPriorityQueue.java
 I affirm that this program is entirely my own work and
 none of it is the work of any other person.

 @author Fernando Campo 1299228 COP 3530 Data Structures MWF 10:45 Summer 2014
 */
package cop3530;

import java.util.Comparator;

/**
 *
 * @author Fernando
 * @param <AnyType>
 */
public class TreeDoubleEndedPriorityQueue<AnyType>
        implements DoubleEndedPriorityQueue<AnyType>
{

    //private instance variables or fields
    private Comparator<? super AnyType> cmp;
    private Node<AnyType> root = null;

    /**
     * TreeDoubleEndedPriorityQueue constructor that creates an empty tree.
     */
    public TreeDoubleEndedPriorityQueue()
    {
        //calls the other constructor and passes null as the Comparator object.
        this( null );
    }

    /**
     * TreeDoubleEndedPriorityQueue constructor that creates an empty tree and
     * set the Comparator object.
     *
     * @param c Comparator object used to compare objects.
     */
    public TreeDoubleEndedPriorityQueue( Comparator<? super AnyType> c )
    {
        cmp = c;
        root = null;
    }

    /**
     * Prints each node's data in the binary tree inorder notation.
     * (Left-Middle-Right).
     *
     * @return Returns a string contain the data from the tree.
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder( "[ " );
        toString( root, sb );
        sb.append( "]" );
        return new String( sb ); // recursive calls and passes root node.
    }

    private void toString( Node<AnyType> t, StringBuilder sb )
    {
        if ( t != null )
        {
            toString( t.left, sb ); //recursive call for left substree.
            sb.append( t.items ); //prints current tree node.
            toString( t.right, sb ); //recursive call for right subtree.
        }
    }

    /**
     * Method that makes the tree empty. Root to null.
     */
    @Override
    public void makeEmpty()
    {
        root = null;
    }

    /*
     * Private utility method to compare objects. Uses the Comparator object if
     * objects aren't "Comparable". If not, it casts them as Comparable.
     */
    private int myCompare( AnyType x, AnyType y )
    {
        if ( cmp == null )
            return ( (Comparable) x ).compareTo( y );

        return cmp.compare( x, y );
    }

    /**
     * Add data to the binary tree. Receive an object and places to the left if
     * its less than, the right if its greater than or adds the duplicate to the
     * current tree node's linked list.
     *
     * @param x Data to be added to the binary tree.
     */
    @Override
    public void add( AnyType x )
    {
        root = add( x, root ); //recursive call and passes data and root.
    }

    /*
     * Recursive method to add data to the tree by comparing the data received
     * verus the data current in the tree nodes.
     */
    private Node<AnyType> add( AnyType x, Node<AnyType> t )
    {
        if ( t == null ) //tree is empty, create the first root node.
            return new Node<>( x );

        //compare check, negative if less, positive if greater, and 0 if equal.
        int compareCheck = myCompare( x, t.items.data );

        //recursive call for left substree. 
        if ( compareCheck < 0 )
            t.left = add( x, t.left );

        //recursive call for right substree
        else if ( compareCheck > 0 )
            t.right = add( x, t.right );

        //duplicate data, add to node's linked list
        else if ( compareCheck == 0 )
            t.items = new Node.ListNode<>( x, t.items );

        return t;
    }

    /**
     * Deletes the minimum and returns the data removed. Check the root cases
     * first. Case 1: Tree is empty, throw UnderflowException. Case 2: Root has
     * no left, root is the minimum. Check if root's data has duplicates. Remove
     * it and make root's right the new root. Case 3: Root has a left, recursive
     * call deleteMin for root's left substree.
     *
     * @return Returns the minimum data that was removed.
     */
    @Override
    public AnyType deleteMin()
    {
        if ( isEmpty() )//empty tree
            throw new UnderflowException( "Its empty!" );

        if ( root.left == null ) //root has no left
            if ( !root.hasMoreLinks() )//no duplicates
            {
                AnyType rem = root.items.data; //save data before delete
                root = root.right; //new root
                return rem;
            }
            else//duplicates found
                return root.removeLink();

        else //root has left
            //recursive call passing root to delete minimum
            return deleteMin( root );
    }

    /*
     * Deletes the minimum after the root and returns the data removed. Checks
     * the Node cases. Case 1: Node doesn't have a left and is the minimum.
     * Checks for duplicates. Case 2: Node has a left and a recursive call is
     * made to continue finding the minimum.
     */
    private AnyType deleteMin( Node<AnyType> t )
    {
        if ( t.left.left == null )//Node (t.left) has no left
            if ( !t.left.hasMoreLinks() )//no duplicates
            {
                AnyType rem = t.left.items.data; //save data before delete
                t.left = t.left.right;
                return rem;
            }
            else//duplicates founds
                return t.left.removeLink();

        else//Node (t.left) has left
            //recursive call passing t.left to find minimum to delete
            return deleteMin( t.left );
    }

    /**
     * Deletes the maximum and returns the data removed. Check the root cases
     * first. Case 1: Tree is empty, throw UnderflowException. Case 2: Root has
     * no right, root is the maximum. Check if root's data has duplicates.
     * Remove it and make root's left the new root. Case 3: Root has a right,
     * recursive call deleteMax for root's right substree.
     *
     * @return Returns the maximum data that was removed.
     */
    @Override
    public AnyType deleteMax()
    {
        if ( isEmpty() )//empty tree
            throw new UnderflowException( "Its empty!" );
        if ( root.right == null ) //root has no right
            if ( !root.hasMoreLinks() )//no duplicates
            {
                AnyType rem = root.items.data; //save data before delete
                root = root.left; //new root
                return rem;
            }
            else//duplicates found
                return root.removeLink();

        else //root has right
            //recursive call passing root to delete maximum
            return deleteMax( root );
    }

    /*
     * Deletes the maximum after the root and returns the data removed. Checks
     * the Node cases. Case 1: Node doesn't have a right and is the maximum.
     * Checks for duplicates. Case 2: Node has a right and a recursive call is
     * made to continue finding the maximum.
     */
    private AnyType deleteMax( Node<AnyType> t )
    {
        if ( t.right.right == null )//Node (t.right) has no right
            if ( !t.right.hasMoreLinks() )//no duplicates
            {
                AnyType rem = t.right.items.data; //save data before delete
                t.right = t.right.left;
                return rem;
            }
            else//duplicates founds
                return t.right.removeLink();
        else//Node (t.right) has right
            //recursive call passing t.rightto find maximum to delete
            return deleteMax( t.right );
    }

    /**
     * Find the minimum data in the tree and returns it. Check if tree is empty.
     * If its not, then a recursive call is made from root.
     *
     * @return Returns the minimum data in the tree.
     */
    @Override
    public AnyType findMin()
    {
        if ( isEmpty() )
            throw new UnderflowException( "Its empty!" );
        return findMin( root ); //recursive call for root
    }

    /*
     * Finds the minimum in the tree. If the node is the minimum because of no
     * left substres, the it return the data in the that node. Else it recursive
     * calls findMin for its left substree.
     */
    private AnyType findMin( Node<AnyType> t )
    {
        if ( t.left == null ) //No left subtree. Node is the minimum.
            return t.items.data;
        else
            return findMin( t.left ); //recursive call for Node's left
    }

    /**
     * Find the maximum data in the tree and returns it. Check if tree is empty.
     * If its not, then a recursive call is made from root.
     *
     * @return Returns the maximum data in the tree.
     */
    @Override
    public AnyType findMax()
    {
        if ( isEmpty() )
            throw new UnderflowException( "Its empty!" );
        return findMax( root ); //recursive call for root
    }

    /*
     * Finds the maximum in the tree. If the node is the maximum because of no
     * right substres, the it return the data in the that node. Else it
     * recursive calls findMax for its right substree.
     */
    private AnyType findMax( Node<AnyType> t )
    {
        if ( t.right == null ) //No right subtree. Node is the maximum.
            return t.items.data;
        else
            return findMax( t.right ); //recursive call for Node's right
    }

    /**
     * Checks if tree is empty and returns true.
     *
     * @return Return true if tree is empty, root is null.
     */
    @Override
    public boolean isEmpty()
    {
        return root == null;
    }

    /*
     * Inner class for the Node. Used to store the left, right and the first
     * item in the data list.
     */
    private static class Node<AnyType>
    {

        //private instance variables
        private Node<AnyType> left;
        private Node<AnyType> right;
        private ListNode<AnyType> items;

        public Node( AnyType data )
        {
            left = right = null;
            items = new ListNode<>( data, null );
        }

        /*
         * Check if the data list has more than one item or duplicate.
         */
        public boolean hasMoreLinks()
        {
            return items.next != null;
        }

        /*
         * Utility method to remove and returnt the first duplicate item on the
         * list.
         */
        public AnyType removeLink()
        {
            AnyType rem = items.data;
            items = items.next;
            return rem;
        }

        /*
         * Inner class for the ListNode. Used to store the data and the next
         * item on the list.
         */
        private static class ListNode<AnyType>
        {

            //private instance varibles
            private AnyType data;
            private ListNode<AnyType> next;

            public ListNode( AnyType d, ListNode<AnyType> n )
            {
                data = d;
                next = n;
            }

            /*
             * Prints out the Node's linked list and returns the list of items.
             */
            @Override
            public String toString()
            {
                StringBuilder sb = new StringBuilder();

                for ( ListNode<AnyType> p = this; p != null; p = p.next )
                {
                    sb.append( p.data );
                    sb.append( " " );
                }
                return new String( sb );
            }
        }//end of ListNode class
    }//end of Node class
}//end of TreeDoubleEndedPriorityQueue.java
