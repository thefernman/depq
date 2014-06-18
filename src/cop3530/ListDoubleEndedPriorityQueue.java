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
 */
public class ListDoubleEndedPriorityQueue<AnyType> implements DoubleEndedPriorityQueue<AnyType>
{

    private Comparator<? super AnyType> cmp;
    private Node<AnyType> first;
    private Node<AnyType> last;

    public ListDoubleEndedPriorityQueue()
    {
        this( null );
    }

    public ListDoubleEndedPriorityQueue( Comparator<? super AnyType> c )
    {
        first = last = null;
        cmp = c;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder( "[ " );

        for ( Node<AnyType> p = first; p != null; p = p.next )
        {
            sb.append( p.data );
            sb.append( " " );
        }
        sb.append( "]" );

        return new String( sb );
    }

    @Override
    public void makeEmpty()
    {
        first = last = null;
    }

    private int myCompare( AnyType x, AnyType y )
    {
        if ( cmp == null )
            return ( (Comparable) x ).compareTo( y );
        return cmp.compare( x, y );
    }

    @Override
    public void add( AnyType x )
    {
        if ( isEmpty() )
            first = last = new Node<>( x, null, null );
        else if ( first == last )
        {
            int compareCheck = myCompare( first.data, x );
            if ( compareCheck >= 0 )
                addFirst( x );
            else if ( compareCheck <= 0 )
                addLast( x );
        }
        else
        {
            if ( myCompare( first.data, x ) >= 0 )
                addFirst( x );
            else if ( myCompare( last.data, x ) <= 0 )
                addLast( x );
            else
            {
                Node<AnyType> p = first.next;
                while ( myCompare( x, p.data ) > 0 )
                    p = p.next;
                addAfter( p, x );
            }
        }
    }

    private void addFirst( AnyType x )
    {
        first = new Node<>( x, null, first );
        first.next.prev = first;
    }

    private void addLast( AnyType x )
    {
        last = new Node( x, last, null );
        last.prev.next = last;
    }

    private void addAfter( Node<AnyType> p, AnyType x )
    {
        p = new Node<>( x, p.prev, p );
        p.prev.next = p;
        p.next.prev = p;
    }

    @Override
    public AnyType deleteMin()
    {
        if ( isEmpty() )
        {
            throw new UnderflowException( "Its empty!" );
        }
        else if ( first == last )
        {
            AnyType rem = first.data;
            makeEmpty();
            return rem;
        }
        else
        {
            AnyType rem = first.data;
            first = first.next;
            first.prev.next = first.prev = null;
            return rem;
        }
    }

    @Override
    public AnyType deleteMax()
    {
        if ( isEmpty() )
        {
            throw new UnderflowException( "Its empty!" );
        }
        else if ( first == last )
        {
            AnyType rem = last.data;
            makeEmpty();
            return rem;
        }
        else
        {
            AnyType rem = last.data;
            last = last.prev;
            last.next.prev = last.next = null;
            return rem;
        }
    }

    @Override
    public AnyType findMin()
    {
        if ( isEmpty() )
        {
            throw new UnderflowException( "Its empty!" );
        }
        return first.data;
    }

    @Override
    public AnyType findMax()
    {
        if ( isEmpty() )
        {
            throw new UnderflowException( "Its empty!" );
        }
        return last.data;
    }

    @Override
    public boolean isEmpty()
    {
        return first == null && last == null;
    }

    private static class Node<AnyType>
    {

        private AnyType data;
        private Node<AnyType> prev;
        private Node<AnyType> next;

        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        {
            data = d;
            prev = p;
            next = n;
        }
    }
}
