package com.bidc.service;

import java.util.List;
import java.util.Map;

import com.bidc.data.BIDCAccount;
import com.bidc.data.BIDCTransaction;
import com.bidc.data.BIDCCustomer;

// Public API for all the services provided by BIDC Server

public interface BIDCService {

	/**
	 * Check if user from this provider is registered with system
	 * 
	 * @param providerId
	 * @param userId
	 * @return
	 */
	public boolean isUserRegistered(String providerId, String userId)
			throws BIDCException;

	/**
	 * Register new user with the system
	 * 
	 * Customer id and account is created within the system
	 * 
	 * @param bUser
	 * @param context
	 * @return
	 */
	public BIDCCustomer registerUser(BIDCCustomer bUser,
			Map<String, String> context) throws BIDCException;

	public BIDCCustomer getCustomer(String providerId, String socialId)
			throws BIDCException;

	public BIDCAccount getAccountDetails(BIDCCustomer customer) throws BIDCException;

	/**
	 * Create new account for user.
	 * 
	 * @param user
	 * @return
	 */

	public BIDCAccount createAccount(BIDCCustomer user) throws BIDCException;

	/**
	 * Return list of transactions for the account.
	 * 
	 * @param account
	 * @return
	 */
	public List<BIDCTransaction> getTransactions(BIDCAccount account)
			throws BIDCException;

	/**
	 * Transfer funds from one account to another.
	 * 
	 * @param fromAccount
	 * @param toAccount
	 * @param amount
	 * @return
	 * @throws BIDCException
	 */
	public boolean transferFunds(BIDCAccount fromAccount,
			BIDCAccount toAccount, Double amount, String note)
			throws BIDCException;

	/**
	 * Create new transaction for account
	 * 
	 * @param transaction
	 * @param account
	 * @return
	 */
	public boolean createTransaction(double amount, BIDCAccount account,
			String note,String type) throws BIDCException;

}
