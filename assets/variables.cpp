#include "globals.h"

char* input;
char* buffer;

bool error;
char* error_string;

int currentAccount_index;
Account* currentAccount;

std::vector<Account> accounts;
std::vector<Ticket> tickets;
TransactionFile* transactionFile;
