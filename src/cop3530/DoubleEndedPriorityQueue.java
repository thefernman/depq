/* File: DoubleEndedPriorityQueue.java
 I affirm that this program is entirely my own work and
 none of it is the work of any other person.

 @author Fernando Campo 1299228 COP 3530 Data Structures MWF 10:45 Summer 2014
 */
package cop3530;

/**
 *
 * @author Fernando
 */
public interface DoubleEndedPriorityQueue<AnyType>
{

    void makeEmpty();

    void add( AnyType x );

    AnyType deleteMin();

    AnyType deleteMax();

    AnyType findMin();

    AnyType findMax();

    boolean isEmpty();
}
