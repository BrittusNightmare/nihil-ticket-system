package transaction;

//library imports
import java.util.Vector;
import java.util.zip.DataFormatException;
//local imports
import assets.*;

/** @class Delete
 * @brief Represents a 'delete' transaction.
 **/
public class Delete extends Transaction {
	public static final int code = 2;
	public Delete( String s) throws DataFormatException {
		System.out.println(s);}
	public void applyTo (
		Vector<Account> accounts, Vector<Ticket> tickets)
			throws TransactionException{}
}