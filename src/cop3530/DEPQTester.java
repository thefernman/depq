/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cop3530;

class DEPQTester
{

    private static void test1( DoubleEndedPriorityQueue<String> q1 )
    {
        q1.add( "Afdc" );
        q1.add( "fda" );
        q1.add( "Da" );
        q1.add( "afdc" );
        q1.add( "Fda" );
        q1.add( "Da" );
        System.out.println( "q1: " + q1 );
    }

    private static void test2( DoubleEndedPriorityQueue<String> q2 )
    {
        q2.add( "Afdc" );
        q2.add( "fda" );
        q2.add( "Da" );
        q2.add( "afdc" );
        q2.add( "Fda" );
        q2.add( "Da" );

        System.out.println( "q2: " + q2 );
    }

    private static void test3( DoubleEndedPriorityQueue<Integer> q3, int N, int M )
    {
        for ( int i = N; i != 0; i = ( i + N ) % M )
        {
            q3.add( Integer.valueOf( i ) );
        }
        System.out.println( "Completed first round of addions" );
        for ( int j = 1; !q3.isEmpty(); j += 2 )
        {
            int minVal1 = ( (Integer) q3.findMin() ).intValue();
            q3.deleteMin();
            int minVal2 = ( (Integer) q3.deleteMin() ).intValue();
            if ( ( minVal1 != j ) || ( minVal2 != j + 1 ) )
            {
                System.out.println( "OOPS!!!! " + minVal1 + " " + minVal2 + " " + j + " " + ( j + 1 ) );
            }
            int maxVal1 = ( (Integer) q3.findMax() ).intValue();
            q3.deleteMax();
            int maxVal2 = ( (Integer) q3.deleteMax() ).intValue();
            if ( ( maxVal1 != M - j ) || ( maxVal2 != M - j - 1 ) )
            {
                System.out.println( "OOPS!!!! " + maxVal1 + " " + maxVal2 + " " + ( M - j ) + " " + ( M - j - 1 ) );
            }
        }
        System.out.println( "Completed first round of deletions" );
        for ( int i = N; i != 0; i = ( i + N ) % M )
        {
            q3.add( Integer.valueOf( i ) );
        }
        System.out.println( "Completed second round of addions" );
        for ( int i = 0; i < M - 5; i += 2 )
        {
            q3.deleteMin();
            q3.deleteMax();
        }
        for ( ; !q3.isEmpty(); q3.deleteMax() )
        {
            System.out.println( q3.findMin() + "\n" + q3.findMax() );
            q3.deleteMin();
        }
    }

    private static void test4( DoubleEndedPriorityQueue<Integer> q, int N, int M )
    {
        for ( int i = 0; i < M; i++ )
        {
            for ( int j = M; j != 0; j = ( j + M ) % N )
            {
                q.add( Integer.valueOf( j ) );
            }
        }
        for ( int j = 1; j < N; j++ )
        {
            for ( int i = 0; i < M; i++ )
            {
                if ( ( (Integer) q.deleteMin() ).intValue() != j )
                {
                    System.out.println( "OOPS!!!" );
                }
            }
        }
        System.out.println( "Completed duplicate test" );
    }

    private static void test5( DoubleEndedPriorityQueue<Integer> q, int N, int M )
    {
        for ( int i = N; i != 0; i = ( i + N ) % M )
        {
            q.add( Integer.valueOf( i ) );
        }
        System.out.println( q.toString().length() );
    }

    public static void main( String[] args )
    {
        long start = System.currentTimeMillis();

//        test1( new ListDoubleEndedPriorityQueue() );
//        test2( new ListDoubleEndedPriorityQueue( String.CASE_INSENSITIVE_ORDER ) );
//        test3( new ListDoubleEndedPriorityQueue(), 1787, 2357 );
//        test4( new ListDoubleEndedPriorityQueue(), 211, 101 );
//        test5( new ListDoubleEndedPriorityQueue(), 1787, 2357 );

        test1( new TreeDoubleEndedPriorityQueue() );
        test2( new TreeDoubleEndedPriorityQueue( String.CASE_INSENSITIVE_ORDER ) );
//        test3( new TreeDoubleEndedPriorityQueue(), 155579, 234589 );
//        test4( new TreeDoubleEndedPriorityQueue(), 1423, 1009 );
//        test5( new TreeDoubleEndedPriorityQueue(), 155579, 234589 );

        long end = System.currentTimeMillis();

        System.out.println( "Program reached end" );
        System.out.println( "ELAPSED TIME IS " + ( end - start ) + " MILLISECONDS" );
    }
}
