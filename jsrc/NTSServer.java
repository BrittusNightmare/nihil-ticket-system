//library imports
import java.io.IOException;
import java.util.Vector;
//local imports
import assets.*;
import transaction.*;

public class NTSServer {
	public static void main( String[] args){
		//local variables
		Vector<Account> accounts = new Vector<Account>();
		Vector<Ticket> tickets = new Vector<Ticket>();
		Vector<Transaction> transactions = new Vector<Transaction>();
		String accountsFile = args.length > 0 ?
			args[2] : "resources/data.cua";
		String ticketsFile = args.length > 1 ?
			args[2] : "resources/data.atf";
		String transactionsFile = args.length > 2 ?
			args[2] : "output.dtf";

		//try to load the accounts file
		try { loadAccounts( accountsFile, accounts);}
		catch ( IOException e){
			System.out.println(e);}
		//try to load the tickets file
		try { loadTickets( ticketsFile, tickets);}
		catch ( IOException e){
			System.out.println(e);}
		//try to load the transactions file
		try { loadTransactions( transactionsFile, transactions);}
		catch ( IOException e){
			System.out.println(e);}

		//aply every transaction
		for( Transaction t : transactions)
			try{ t.applyTo( accounts, tickets);}
			catch( TransactionException e){}

		//try to write the accounts file
		try { writeAccounts( accountsFile, accounts);}
		catch ( IOException e){
			System.out.println(e);}
		//try to write the tickets file
		try { writeTickets( ticketsFile, tickets);}
		catch ( IOException e){
			System.out.println(e);}
	}

	//data load functions
	private static void loadAccounts(
		String file, Vector<Account> accounts)
			throws IOException {}
	private static void loadTickets(
		String file, Vector<Ticket> tickets)
			throws IOException {}
	private static void loadTransactions(
		String file, Vector<Transaction> transactions)
			throws IOException {}

	//data write functions
	private static void writeAccounts(
		String file, Vector<Account> accounts)
			throws IOException {}
	private static void writeTickets(
		String file, Vector<Ticket> tickets)
			throws IOException {}
}