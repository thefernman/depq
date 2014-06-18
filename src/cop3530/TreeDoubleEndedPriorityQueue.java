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
public class TreeDoubleEndedPriorityQueue<AnyType> implements DoubleEndedPriorityQueue<AnyType>
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
        else
        {
            int compareCheck = myCompare( x, t.items.data );
            if ( compareCheck < 0 )
            {
                t.left = add(x, t.left);
            }
            else if (compareCheck > 0)
            {
                t.right = add(x, t.right);
            }
            else 
            {
                if(x == t.items.data)
                {
                    t.items = new Node.ListNode<>(x, t.items);
                }
            }
        }
        return t;
    }

    @Override
    public AnyType deleteMin()
    {
        if ( isEmpty() )
        {
            throw new UnderflowException( "Its empty!" );
        }

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

        throw new UnsupportedOperationException( "Not supported yet." );
    }

    @Override
    public AnyType findMax()
    {
        if ( isEmpty() )
        {
            throw new UnderflowException( "Its empty!" );
        }

        throw new UnsupportedOperationException( "Not supported yet." );
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

        private static class ListNode<AnyType>
        {

            private AnyType data;
            private ListNode<AnyType> next;

            public ListNode( AnyType d, ListNode<AnyType> n )
            {
                data = d;
                next = n;
            }
        }
    }
}
