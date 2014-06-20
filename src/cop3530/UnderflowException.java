/* File: UnderflowException.java
 I affirm that this program is entirely my own work and
 none of it is the work of any other person.

 @author Fernando Campo 1299228 COP 3530 Data Structures MWF 10:45 Summer 2014
 */
package cop3530;

/**
 * Exception class that extends the RuntimeException class.
 *
 * @author Fernando
 */
public class UnderflowException extends RuntimeException
{

    /**
     * UnderflowException constructor.
     */
    public UnderflowException()
    {

    }

    /**
     * UnderflowException constructor with parameter.
     *
     * @param message String message for exception.
     */
    public UnderflowException( String message )
    {
        super( message );
    }

}
