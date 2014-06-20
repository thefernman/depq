/* File: ListDoubleEndedPriorityQueue.java
 I affirm that this program is entirely my own work and
 none of it is the work of any other person.

 @author Fernando Campo 1299228 COP 3530 Data Structures MWF 10:45 Summer 2014
 */
package cop3530;

import java.util.Comparator;

/**
 *
 * @author Fernando
 */
public class ListDoubleEndedPriorityQueue<AnyType>
        implements DoubleEndedPriorityQueue<AnyType>
{

    //private instance variables or fields
    private Comparator<? super AnyType> cmp;
    private Node<AnyType> first;
    private Node<AnyType> last;

    /**
     * ListDoubleEndedPriorityQueue constructor that creates an empty list.
     */
    public ListDoubleEndedPriorityQueue()
    {
        //calls the other constructor and passes null as the Comparator object.
        this( null );
    }

    /**
     * ListDoubleEndedPriorityQueue constructor that creates an empty list and
     * set the Comparator object.
     *
     * @param c Comparator object used to compare objects.
     */
    public ListDoubleEndedPriorityQueue( Comparator<? super AnyType> c )
    {
        first = last = null;
        cmp = c;
    }

    /**
     * Prints each node's data in the list.
     *
     * @return Returns a string of the list.
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder( "[ " );

        //Traverse the linked list.
        for ( Node<AnyType> p = first; p != null; p = p.next )
        {
            sb.append( p.data );
            sb.append( " " );
        }
        sb.append( "]" );

        return new String( sb );
    }

    /**
     * Method that makes the list empty. First and last to null.
     */
    @Override
    public void makeEmpty()
    {
        first = last = null;
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
     * Adds data to the linked list in the correct, sorted position. Compares
     * the data being added with the Node, if it insert before or after. Case 1:
     * Check if list is empty. Creates the first node in the list. Case 2: Check
     * if data goes before the first or after the last. Case 3: Data goes
     * somewhere between the first and last node. Tranverse the list and find
     * the correct position in the list.
     *
     * @param x Data to be added.
     */
    @Override
    public void add( AnyType x )
    {
        if ( isEmpty() ) //List is empty, create first node.
            first = last = new Node<>( x, null, null );
        else if ( myCompare( x, first.data ) <= 0 ) //data goes before first
            addFirst( x );
        else if ( myCompare( x, last.data ) >= 0 ) //data goes after last
            addLast( x );
        else //data goes between the first and last node
        {
            Node<AnyType> p = first.next; //temp pointer
            while ( myCompare( x, p.data ) > 0 ) //traverse list
            {
                p = p.next;
            }

            addAfter( p, x );
        }
    }

    /*
     * Utility method to add data before first node.
     */
    private void addFirst( AnyType x )
    {
        first = new Node<>( x, null, first );
        first.next.prev = first;
    }

    /*
     * Utility method to add data after last node.
     */
    private void addLast( AnyType x )
    {
        last = new Node( x, last, null );
        last.prev.next = last;
    }

    /*
     * Utility method to add data after a certain node.
     */
    private void addAfter( Node<AnyType> p, AnyType x )
    {
        p = new Node<>( x, p.prev, p );
        p.prev.next = p;
        p.next.prev = p;
    }

    /**
     * Deletes the minimum and returns the data removed. Case 1: List is empty.
     * Case 2: If list has only one node, it makes the list empty and returns
     * that one node. Case 3: List has more than one node and remove the first
     * node on the list and adjusts the list.
     *
     * @return Returns the minimum data that was removed.
     */
    @Override
    public AnyType deleteMin()
    {
        if ( isEmpty() )//empty list
            throw new UnderflowException( "Its empty!" );
        //one item, node, on the list
        else if ( first == last && ( ( first != null ) && ( last != null ) ) )
        {
            AnyType rem = first.data;
            makeEmpty();
            return rem;
        }
        else //more than one item, node, on the list
        {
            AnyType rem = first.data;
            first = first.next;
            first.prev.next = first.prev = null;
            return rem;
        }
    }

    /**
     * Deletes the maximum and returns the data removed. Case 1: List is empty.
     * Case 2: If list has only one node, it makes the list empty and returns
     * that one node. Case 3: List has more than one node and remove the first
     * node on the list and adjusts the list.
     *
     * @return Returns the maximum data that was removed.
     */
    @Override
    public AnyType deleteMax()
    {
        if ( isEmpty() )//empty list
            throw new UnderflowException( "Its empty!" );
        //one item, node, on the list
        else if ( first == last && ( ( first != null ) && ( last != null ) ) )
        {
            AnyType rem = last.data;
            makeEmpty();
            return rem;
        }
        else //more than one item, node, on the list
        {
            AnyType rem = last.data;
            last = last.prev;
            last.next.prev = last.next = null;
            return rem;
        }
    }

    /**
     * Find the minimum data in the list and returns it. Check if list is empty.
     * If its not, then it returns the first, minimum, data in the list.
     *
     * @return Returns the minimum data in the list.
     */
    @Override
    public AnyType findMin()
    {
        if ( isEmpty() )
            throw new UnderflowException( "Its empty!" );
        return first.data;
    }

    /**
     * Find the maximum data in the list and returns it. Check if list is empty.
     * If its not, then it returns the last, maximum, data in the list.
     *
     * @return Returns the maximum data in the list.
     */
    @Override
    public AnyType findMax()
    {
        if ( isEmpty() )
            throw new UnderflowException( "Its empty!" );
        return last.data;
    }

    /**
     * Check if the list is empty and return true.
     *
     * @return Return true if the list is empty, first and last null.
     */
    @Override
    public boolean isEmpty()
    {
        return first == null && last == null;
    }

    /*
     * Inner class of Node. Used to store the data, the first node and the last
     * node.
     */
    private static class Node<AnyType>
    {

        //private instance variables
        private AnyType data;
        private Node<AnyType> prev;
        private Node<AnyType> next;

        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        {
            data = d;
            prev = p;
            next = n;
        }
    }//end of Node class
}//end of ListDoubleEndedPriorityQueue.java
