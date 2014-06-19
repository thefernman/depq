/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cop3530;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Fernando
 * @param <AnyType>
 */
public class TreeDoubleEndedPriorityQueue<AnyType>
        implements DoubleEndedPriorityQueue<AnyType>
{

    private Comparator<? super AnyType> cmp;
    private Node<AnyType> root = null;

    public TreeDoubleEndedPriorityQueue()
    {
        this( null );
    }

    public TreeDoubleEndedPriorityQueue( Comparator<? super AnyType> c )
    {
        cmp = c;
        root = null;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        toString( root, sb );
        return new String( sb );
    }

    private void toString( Node<AnyType> t, StringBuilder sb )
    {
        if ( t != null )
        {
            toString( t.left, sb );
            sb.append( t.items );
            toString( t.right, sb );
        }
    }

    @Override
    public void makeEmpty()
    {
        root = null;
    }

    private int myCompare( AnyType x, AnyType y )
    {
        if ( cmp == null )
        {
            return ( (Comparable) x ).compareTo( y );
        }
        return cmp.compare( x, y );
    }

    @Override
    public void add( AnyType x )
    {
        root = add( x, root );
    }

    private Node<AnyType> add( AnyType x, Node<AnyType> t )
    {
        if ( t == null )
        {
            return new Node<>( x );
        }
        int compareCheck = myCompare( x, t.items.data );
        if ( compareCheck < 0 )
        {
            t.left = add( x, t.left );
        }
        else if ( compareCheck > 0 )
        {
            t.right = add( x, t.right );
        }
        else if ( compareCheck == 0 )
        {
            t.items = new Node.ListNode<>( x, t.items );
        }
        return t;
    }

    @Override
    public AnyType deleteMin()
    {
        if ( isEmpty() )//empty tree
        {
            throw new UnderflowException( "Its empty!" );
        }
        if ( root.left == null ) //root has no left
        {
            if ( !root.hasMoreLinks() )//one link
            {
                AnyType rem = root.items.data;
                root = root.right;
                return rem;
            }
            else//more links
            {
                return root.removeLink();
            }
        }
        else //root has left
        {
            return deleteMin( root );
        }
    }

    private AnyType deleteMin( Node<AnyType> t )
    {
        if ( t.left.left == null )//t.left has no left
        {
            if ( !t.left.hasMoreLinks() )//one link
            {
                AnyType rem = t.left.items.data;
                t.left = t.left.right;
                return rem;
            }
            else//more links
            {
                return t.left.removeLink();
            }
        }
        else
        {
            return deleteMin( t.left );
        }
    }

    @Override
    public AnyType deleteMax()
    {
        if ( isEmpty() )//empty tree
        {
            throw new UnderflowException( "Its empty!" );
        }
        if ( root.right == null ) //root has no left
        {
            if ( !root.hasMoreLinks() )//one link
            {
                AnyType rem = root.items.data;
                root = root.left;
                return rem;
            }
            else//more links
            {
                return root.removeLink();
            }
        }
        else //root has left
        {
            return deleteMax( root );
        }
    }

    private AnyType deleteMax( Node<AnyType> t )
    {
        if ( t.right.right == null )//t.left has no left
        {
            if ( !t.right.hasMoreLinks() )//one link
            {
                AnyType rem = t.right.items.data;
                t.right = t.right.left;
                return rem;
            }
            else//more links
            {
                return t.right.removeLink();
            }
        }
        else
        {
            return deleteMax( t.right );
        }
    }

    @Override
    public AnyType findMin()
    {
        if ( isEmpty() )
        {
            throw new UnderflowException( "Its empty!" );
        }
        return findMin( root );
    }

    private AnyType findMin( Node<AnyType> t )
    {
        if ( t.left == null )
        {
            return t.items.data;
        }
        else
        {
            return findMin( t.left );
        }
    }

    @Override
    public AnyType findMax()
    {
        if ( isEmpty() )
        {
            throw new UnderflowException( "Its empty!" );
        }
        return findMax( root );
    }

    private AnyType findMax( Node<AnyType> t )
    {
        if ( t.right == null )
        {
            return t.items.data;
        }
        else
        {
            return findMax( t.right );
        }
    }

    @Override
    public boolean isEmpty()
    {
        return root == null;
    }

    private static class Node<AnyType>
    {

        private Node<AnyType> left;
        private Node<AnyType> right;
        private ListNode<AnyType> items;

        public Node( AnyType data )
        {
            left = right = null;
            items = new ListNode<>( data, null );
        }

        public boolean hasMoreLinks()
        {
            return items.next != null;
        }

        public AnyType removeLink()
        {
            items = items.next;
            return items.data;
        }

        private static class ListNode<AnyType>
        {

            private AnyType data;
            private ListNode<AnyType> next;

            public ListNode( AnyType d, ListNode<AnyType> n )
            {
                data = d;
                next = n;
            }

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

    private static void test1( TreeDoubleEndedPriorityQueue<String> q1 )
    {
        q1.add( "Afdc" );
        q1.add( "fda" );
        q1.add( "Da" );
        q1.add( "afdc" );
        q1.add( "Fda" );
        q1.add( "Da" );
        System.out.println( "1 Before - q1: " + q1 );
        System.out.println( "2 After - q1: " + q1 );
        q1.deleteMin();
        System.out.println( "3 After - q1: " + q1 );
        q1.deleteMin();
        q1.deleteMin();
//        q1.deleteMin();
        q1.deleteMax();
        System.out.println( "4 After - q1: " + q1 );
    }

    private static void test2( TreeDoubleEndedPriorityQueue<Integer> q1 )
    {
        Random r = new Random( 1 );
        for ( int i = 0; i < 10; ++i )
        {
            q1.add( r.nextInt( 20 ) );
        }

        System.out.println( "1 Before - q1: " + q1 );

        int num = q1.deleteMin();
        System.out.println( "Deleted " + num );
        
        System.out.println( "2 After - q1: " + q1 );
        
        num = q1.deleteMin();
        System.out.println( "Deleted " + num );
        
        System.out.println( "3 After - q1: " + q1 );
        
        num = q1.deleteMin();
        System.out.println( "Deleted " + num );
        
        System.out.println( "4 After - q1: " + q1 );
    }

    public static void main( String[] args )
    {
        test2( new TreeDoubleEndedPriorityQueue<>() );
    }
}//end of TreeDoubleEndedPriorityQueue class
