/**
 * 
 */
package com.bidc.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.bidc.data.AppDao;
import com.bidc.data.BIDCAccount;
import com.bidc.data.BIDCCustomer;
import com.bidc.data.BIDCTransaction;
import com.bidc.data.EmfDao;
import com.bidc.service.BIDCException;
import com.bidc.service.BIDCService;

/**
 * @author
 * 
 */
public class BIDCServiceImpl implements BIDCService {

	private final Logger logger = Logger.getLogger(BIDCServiceImpl.class
			.getName());

	AppDao dao = null;

	/**
	 * 
	 */
	public BIDCServiceImpl() {
		dao = EmfDao.INSTANCE;

	}

	@Override
	public boolean isUserRegistered(String providerId, String userId)
			throws BIDCException {

		boolean result = false;

		List<BIDCCustomer> cust = dao.getAll(BIDCCustomer.class);

		for (Iterator iterator = cust.iterator(); iterator.hasNext();) {
			BIDCCustomer bidcCustomer = (BIDCCustomer) iterator.next();

			if (bidcCustomer.getProviderId().equalsIgnoreCase(providerId)
					&& bidcCustomer.getSocialId().equalsIgnoreCase(userId)) {
				return true;
			}

		}

		return result;
	}

	@Override
	public BIDCCustomer registerUser(BIDCCustomer customer,
			Map<String, String> context) throws BIDCException {

		dao.add(BIDCCustomer.class, customer);
		createAccount(customer);

		return customer;
	}

	@Override
	public BIDCAccount createAccount(BIDCCustomer customer)
			throws BIDCException {

		BIDCAccount account = new BIDCAccount();
		account.setCustomerId(customer.getId());
		account.setBalance(1000.0d);

		dao.add(BIDCAccount.class, account);

		createTransaction(1000.0d, account, "Account opening balance ", "C");

	//	sendEmail(customer.getEmail(), "New Account",
	//			"New Account created with bank", "Dear User",null);

		return account;
	}

	@Override
	public List<BIDCTransaction> getTransactions(BIDCAccount account)
			throws BIDCException {

		return dao.getTransactionsForAccount(account.getId());
	}

	@Override
	public boolean transferFunds(BIDCAccount fromAccount,
			BIDCAccount toAccount, Double amount, String note)
			throws BIDCException {

		boolean result = false;

		BIDCAccount frmA = dao.get(BIDCAccount.class, fromAccount.getId());

		BIDCAccount toA = dao.get(BIDCAccount.class, toAccount.getId());

		if (frmA.getBalance() < amount) {
			return result;
		}

		createTransaction(amount, frmA, "Transferred to " + toA.getId() + " : "
				+ note, "D");

		createTransaction(amount, toA, "Transferred from " + frmA.getId()
				+ " : " + note, "C");

		dao.update(BIDCAccount.class, frmA);

		dao.update(BIDCAccount.class, toA);

		result = true;

		return result;
	}

	@Override
	public boolean createTransaction(double amount, BIDCAccount account,
			String note, String type) throws BIDCException {

		BIDCTransaction transactionTo = new BIDCTransaction();

		transactionTo.setAmount(amount);
		transactionTo.setAccountId(account.getId());
		transactionTo.setNote(note);
		transactionTo.setType(type);
		dao.add(BIDCTransaction.class, transactionTo);

		// Update the balance as well based on type of transaction.

		if ("C".equals(type)) {
			account.setBalance(account.getBalance() + amount);
		}

		if ("D".equals(type)) {
			account.setBalance(account.getBalance() - amount);
		}

		// dao.add(BIDCAccount.class, account);

		return true;
	}

	@Override
	public BIDCCustomer getCustomer(String providerId, String socialId)
			throws BIDCException {

		List<BIDCCustomer> cust = dao.getAll(BIDCCustomer.class);

		for (Iterator iterator = cust.iterator(); iterator.hasNext();) {
			BIDCCustomer bidcCustomer = (BIDCCustomer) iterator.next();

			if (bidcCustomer.getProviderId().equalsIgnoreCase(providerId)
					&& bidcCustomer.getSocialId().equalsIgnoreCase(socialId)) {
				return bidcCustomer;
			}

		}

		return null;
	}

	public BIDCAccount getAccountDetails(BIDCCustomer customer)
			throws BIDCException {

		return dao.getAccountDetails(customer.getId());

	}

	public void sendEmail(String emailId, String subject, String message,
			String userName, byte[] bytes) {

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("dbidc.bank@gmail.com",
					"DBIDC Bank Admin"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					"ravindra.godbole@cognizant.com", userName));
			msg.setSubject(subject);
			msg.setText(message);

			// // Add the jpg file
			//
			if (bytes != null) {
				 Multipart mp = new MimeMultipart();

				MimeBodyPart attachment = new MimeBodyPart();
				attachment.setFileName("promo.png");
			//	attachment.setContent(bytes, "");
			    DataSource src = new ByteArrayDataSource(bytes, "image/png");
			    attachment.setDataHandler(new DataHandler(src));
				mp.addBodyPart(attachment);
				msg.setContent(mp);

			}
			Transport.send(msg);
			logger.severe("Mail sent with message " + msg);
		} catch (AddressException e) {
			logger.severe("Email error" + e.getMessage());
		} catch (MessagingException e) {
			logger.severe("Email error" + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			logger.severe("Email error" + e.getMessage());
		} 
	}

}
