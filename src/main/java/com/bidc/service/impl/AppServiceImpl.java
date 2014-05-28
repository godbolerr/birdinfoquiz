/**
 * 
 */
package com.bidc.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import com.bidc.data.BirdInfo;
import com.bidc.data.Customer;
import com.bidc.data.EmfDao;
import com.bidc.service.AppService;
import com.bidc.service.BIDCException;

/**
 * @author Ravi
 *
 */
public class AppServiceImpl implements AppService {
	
	public static final String BASE_URL	 = "http://birdinfoquiz.appspot.com/images/";
	
	private final Logger logger = Logger.getLogger(AppServiceImpl.class
			.getName());

	AppDao dao = null;

	/**
	 * 
	 */
	public AppServiceImpl() {
		dao = EmfDao.INSTANCE;
	}

	/* (non-Javadoc)
	 * @see com.bidc.service.AppService#getBirdInfo(java.lang.String)
	 */
	@Override
	public BirdInfo getBirdInfo(String id) {		
		return dao.get(BirdInfo.class, id);

	}

	/* (non-Javadoc)
	 * @see com.bidc.service.AppService#getBirds(int)
	 */
	@Override
	public List<BirdInfo> getBirds(int size) {
		
		List<BirdInfo> bList = new ArrayList<BirdInfo>();
		
		List<BirdInfo> tempList = dao.getAll(BirdInfo.class);
		
		int count = 0;
		for (Iterator<BirdInfo> iterator = tempList.iterator(); iterator.hasNext();) {
			BirdInfo birdInfo = (BirdInfo) iterator.next();
			bList.add(birdInfo);
			if ( count++ > size ){
				break;
			}
		}
		
		
		return bList;
		
	}

	/* (non-Javadoc)
	 * @see com.bidc.service.AppService#getAllBirds()
	 */
	@Override
	public List<BirdInfo> getAllBirds() {
		
		
		List<BirdInfo>  birds = dao.getAll(BirdInfo.class);
		
		
		for (Iterator iterator = birds.iterator(); iterator.hasNext();) {
			BirdInfo birdInfo = (BirdInfo) iterator.next();
			
			String picUrl = birdInfo.getPicUrl();
			
			if ( picUrl == null ) {
				picUrl = BASE_URL + "bird.jpg";
			} else {
				
				// Prepend base url if we dont find http in the base url value.
				
				
				if ( picUrl.contains("http") == false ) {
					picUrl = BASE_URL + picUrl;					
				}
				
			}
	
			birdInfo.setPicUrl(picUrl);
				
		}
		

		
		return birds;
		
		
	}

	/* (non-Javadoc)
	 * @see com.bidc.service.AppService#addBird(com.bidc.data.BirdInfo)
	 */
	@Override
	public void addBird(BirdInfo info) {		
		dao.add(BirdInfo.class, info);
	}

	/* (non-Javadoc)
	 * @see com.bidc.service.AppService#updateBirdInfo(com.bidc.data.BirdInfo)
	 */
	@Override
	public void updateBirdInfo(BirdInfo info) {
		dao.update(BirdInfo.class, info);

	}

	/* (non-Javadoc)
	 * @see com.bidc.service.AppService#deleteBird(com.bidc.data.BirdInfo)
	 */
	@Override
	public void deleteBird(BirdInfo info) {
		//dao.get(BirdInfo.class, info.getId());
		dao.delete(info);
	}
	
	@Override
	public boolean isUserRegistered(String providerId, String userId)
			throws BIDCException {

		boolean result = false;

		List<Customer> cust = dao.getAll(Customer.class);

		for (Iterator iterator = cust.iterator(); iterator.hasNext();) {
			Customer bidcCustomer = (Customer) iterator.next();

			if (bidcCustomer.getProviderId().equalsIgnoreCase(providerId)
					&& bidcCustomer.getSocialId().equalsIgnoreCase(userId)) {
				return true;
			}

		}

		return result;
	}

	@Override
	public Customer registerUser(Customer customer,
			Map<String, String> context) throws BIDCException {

		dao.add(Customer.class, customer);
		

		return customer;
	}

	@Override
	public Customer getCustomer(String providerId, String socialId)
			throws BIDCException {

		List<Customer> cust = dao.getAll(Customer.class);

		for (Iterator iterator = cust.iterator(); iterator.hasNext();) {
			Customer bidcCustomer = (Customer) iterator.next();

			if (bidcCustomer.getProviderId().equalsIgnoreCase(providerId)
					&& bidcCustomer.getSocialId().equalsIgnoreCase(socialId)) {
				return bidcCustomer;
			}

		}

		return null;
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
