#include "../Account.h"
#include "../Transaction.h"
#include "../commands.h"
#include "../globals.h"
#include "../errors.h"
#include <cstdio>
#include <vector>
#include <ctype.h>
#include <string>
 #include <iostream>

using namespace std;

//enum usertype_to_enum(char* usertype) {
//	if ((strcmp (usertype,"AA"))
//		return;
//	else if ((strcmp (usertype,"FS"))
//		return;
//	else if ((strcmp (usertype,"BS"))
//		return;
//	else if ((strcmp (usertype,"SS"))
//		return;
//}

void command_create(){
//basic command pseudocode:
	//get all inputs
		//stop only if input line cannot be parsed
	//validate all inputs
		//e.g. username is in the accounts list
	//if relevant to command, confirm execution of command should proceed
		//only command this applies to is buy
	//prepare relevant objects
		//e.g. new account, new tickets, etc
	//perform internal actions
		//e.g. accounts.add(newAccount) or tickets[4].quantity-=4
	//construct transaction
		//e.g. Transaction transaction(Transaction.create);
			//transaction.username = username;
	//push transaction
	//transactions.add(transaction);
	//clean up
	//terminate
	//return 0;
	//end basic command pseudocode


	Transaction transaction;
	transaction.code = Transaction::Create;

	if (accounts[currentAccount_index].type != Account::Admin){
		printf( "%s\n", Error::unprivilegedUserError);
		return;
	}
	printf("Enter user name:\n");
	char* new_username = format(getLine());

	//check for null, empty input
	if( std::cin.eof() |  strlen(new_username) == 0){
		printf( "%s\n", Error::badParameterError);
		return;}

	//check if input > required size
	if( strlen(new_username) > username_size){
		printf("%s\n", Error::LineTooLongError);
		return;}

	strcpy( transaction.username, new_username);

	//check for bad characters
	std::string badChars(" \t\f\v\n\r");
	for( int i = 0; transaction.username[i] != '\0'; i++)
	if( badChars.find(transaction.username[i]) != std::string::npos){
		printf("%s\n", Error::invalidUsernameCharactersError);
		return;}

	//check if username exists
	for( int i = 0; i < accounts.size(); i++){
		if( strcmp( transaction.username, accounts[i].username)==0){
			printf("[Fail] User account already exists. Please specify a new username");
			return;
		}
	}

	printf("Enter account type:");
	char* new_accountType = format(getLine());

	//check for null, empty input
		if( std::cin.eof() |  strlen(new_accountType) == 0){
			printf( "%s\n", Error::badParameterError);
			return;}

	//check input for correct account type size
	if (strlen(new_accountType) != code_size){
		printf("%s\n", Error::LineTooLongError);
		return;}

	if (!isalpha(new_accountType[0]) && !isalpha(new_accountType[1])){
		printf("%s\n", Error::InvalidAccountType);
		return;}
	//turn account type code to uppercase
	for(int i = 0; new_accountType[i] != '\0'; i++){
		new_accountType[i] = toupper(new_accountType[i]);
	}
	//if input != one of the four account types
	if ((strcmp (new_accountType,"AA") != 0) && (strcmp (new_accountType,"FS") != 0) && (strcmp (new_accountType,"SS") != 0) && (strcmp (new_accountType,"BS") != 0)){
		printf("%s\n", Error::InvalidAccountType);
		return;
	}

	//transaction.type = usertype_to_enum(new_accountType);
	printf("Enter credit amount:");

	int new_accountcredit = atoi(format(getLine()));

	//(0 | [1-9][0-9]*) . (0 | [0-9]*[1-9])
	//cout << "entered " << new_accountcredit << "\n";

	//validate and then
	transaction.totalCredits = new_accountcredit;

	transactionFile->add(transaction);
	printf("[Success] User created\n");
	return;

}
