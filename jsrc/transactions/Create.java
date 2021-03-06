package jsrc;

//library imports
import java.util.Vector;
import java.util.zip.DataFormatException;
//local imports
import assets.*;

/** @class Create class represents a 'create' transaction.
 *
 **/
public class Create extends Transaction {
	//static field that identifies the transaction to the server
	public static final int code = 1;
	
	//Create Transaction Parameters
	//username of new account to be created
	public String username;
	//usertype for the specified user to be created
	public int type;
	//initial account balance for the user account to be created
	public int credit;
	
	/**
	* Constructs a create transaction object with the necessary field attributes upon receiving and input 
	* string line parameter from the merged transaction file
	*/
	public Create( String line) throws DataFormatException {
		//current index in the line
		int i = 0;
		//extract
		this.username = line.substring( i, Account.username_size).trim();
		//validate
		if( ! this.username.matches(""))
			throw new DataFormatException("Invalid username field");
		//increment i
		i += Account.username_size + Account.token_size;
		
		//extract
		String typeString = line.substring( i, i + Account.type_size);
		if( typeString.equals("AA"))
			this.type = Account.Admin;
		else if( typeString.equals("BS"))
			this.type = Account.Buy;
		else if( typeString.equals("SS"))
			this.type = Account.Sell;
		else if( typeString.equals("FS"))
			this.type = Account.Full;
		//validate
		else
			throw new DataFormatException("Invalid type field");

		//extract dollars
		String dollars = line.substring( i, i + Account.dollars_size);
		i += Account.dollars_size + 1;
		//extract cents
		String cents = line.substring( i, i + Account.cents_size);
		try{
			this.credit =
				Integer.parseInt( dollars)*100 +
				Integer.parseInt( cents);
		}
		catch( NumberFormatException e){
			throw new DataFormatException("Invalid credit field");}
		//validate
		if( credit < 0)
			throw new DataFormatException("Credit field too small");
		if( credit > Math.pow(10,
				Account.dollars_size + Account.cents_size) - 1)
			throw new DataFormatException("Credit field too large");
	}
	
	public void applyTo (Vector<Account> accounts, Vector<Ticket> tickets)
			throws TransactionException{
		//validation?
		Account account = new Account(this.username, this.type, this.credit);
		if( ! accounts.contains( account))
			accounts.add(account);
	}
}
