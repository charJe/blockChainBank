# blockChainBank
Fully working app is not pushed yet

*OLD*
Clients use bank website to normally make a transaction.
Transaction are encrypted using the client's personal key.
The bank sends encrypted transaction to all miners.
Miners will store transaction directly on their block chain. block size is still > 1 transaction.

On a web application, clients can view the entire encrypted blockchain.
using flashdrive authentication. 
Upon visiting the web application, the bank server fetches blockchains from all miners, and decides what the true block chain is. 

Idealy, the bank would do background checks on and pay miners.

TODO  create desktop application for miners:
 • receive transactions from back server
 • receive request from bank server asking for block chain
 • send block chain to bank server
TODO create web application for clients to view blockchain:
 • receive block chains from miners
 • compare block chains to decide what is Truth
 • dycrypt selected entries using a personal key
 • Using flash drive key to unencrypt flies that are specific to user
TODO use bank api to get transactions on the block chain:
 • encrypt transactions
 • send transactions to miners

