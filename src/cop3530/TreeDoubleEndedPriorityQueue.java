/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
            sb.append( " " );
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
        return deleteMin( root );
    }

    private AnyType deleteMin( Node<AnyType> t )
    {
        if(t.left == null)
        throw new UnsupportedOperationException( "Not supported yet." );
    }

    @Override
    public AnyType deleteMax()
    {
        if ( isEmpty() )
        {
            throw new UnderflowException( "Its empty!" );
        }

        throw new UnsupportedOperationException( "Not supported yet." );
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
        
        public AnyType removeLink (Node<AnyType> p)
        {
            if(items.next != null)
            {
                ListNode<AnyType> rem = items;
                items = items.next;
                rem.next = null;
                return rem.data;
            }
            
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
        }
    }
}
