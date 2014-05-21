/*
 ===========================================================================
 Copyright (c) 2010 BrickRed Technologies Limited

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sub-license, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 ===========================================================================

 */

package com.threepillar.labs.socialauthsample.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Contact;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.spring.bean.SocialAuthTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bidc.data.AppDao;
import com.bidc.data.BIDCAccount;
import com.bidc.data.BIDCCustomer;
import com.bidc.data.BIDCMerchant;
import com.bidc.data.BIDCTransaction;
import com.bidc.data.EmfDao;
import com.bidc.service.BIDCException;
import com.bidc.service.BIDCService;
import com.bidc.service.impl.BIDCServiceImpl;
import com.threepillar.labs.socialauthsample.bean.User;
import com.threepillar.labs.socialauthsample.util.Constants;

@Controller
public class SuccessController {
	
	private final Logger logger = Logger.getLogger(SuccessController.class.getName());

	AppDao dao = null;

	@Autowired
	private SocialAuthTemplate socialAuthTemplate;

	@RequestMapping(value = "/sendoffer")
	public ModelAndView sendoffer(final HttpServletRequest request)
			throws Exception {
		SocialAuthManager manager = socialAuthTemplate.getSocialAuthManager();
		AuthProvider provider = manager.getCurrentAuthProvider();
		
		HttpSession session = request.getSession();
		

		//new BIDCServiceImpl().sendEmail(provider.getUserProfile().getEmail(), "Offer Details", "Please find the QR code attached.", "Mr Customer",buffer.toByteArray());

	
		return registration(provider);
	}

	@RequestMapping(value = "/authSuccess")
	public ModelAndView getRedirectURL(final HttpServletRequest request)
			throws Exception {
		SocialAuthManager manager = socialAuthTemplate.getSocialAuthManager();
		AuthProvider provider = manager.getCurrentAuthProvider();
		HttpSession session = request.getSession();
		session.setAttribute("OFFER", null);
		String type = null;
		if (session.getAttribute(Constants.REQUEST_TYPE) != null) {
			type = (String) session.getAttribute(Constants.REQUEST_TYPE);
		}
		if (type != null) {
			if (Constants.REGISTRATION.equals(type)) {
				return registration(provider);
			} else if (Constants.IMPORT_CONTACTS.equals(type)) {
				return importContacts(provider);
			} else if (Constants.SHARE.equals(type)) {
				return new ModelAndView("shareForm", "connectedProvidersIds",
						manager.getConnectedProvidersIds());
			}
		}

		return null;
	}

	private ModelAndView registration( final AuthProvider provider)
			throws Exception {
		
		
		Profile profile = provider.getUserProfile();

		dao = EmfDao.INSTANCE;

		ModelAndView view = null;

		String providerId = provider.getProviderId();

		String socialId = profile.getValidatedId();

		// Create user and populate it with data.

		BIDCService service = new BIDCServiceImpl();

		if (service.isUserRegistered(providerId, socialId) == false) {

			BIDCCustomer customer = new BIDCCustomer();
			customer.setProviderId(provider.getProviderId());
			customer.setSocialId(profile.getValidatedId());
			customer.setEmail(profile.getEmail());

			service.registerUser(customer, null);

			view = new ModelAndView("registrationForm", "profile", profile);
			
			
			if (profile.getFullName() == null) {
				String name = null;
				if (profile.getFirstName() != null) {
					name = profile.getFirstName();
				}
				if (profile.getLastName() != null) {
					if (profile.getFirstName() != null) {
						name += " " + profile.getLastName();
					} else {
						name = profile.getLastName();
					}
				}
				if (name == null && profile.getDisplayName() != null) {
					name = profile.getDisplayName();
				}
				if (name != null) {	
					profile.setFullName(name);
				}

				
			}

		} else {

			// Retrieve details from our database.

			User user = new User();

			BIDCCustomer cust = service.getCustomer(providerId, socialId);

			if (cust != null) {
				user.setEmail(cust.getEmail());
			}

			Map<String, Object> model = new HashMap<String, Object>();

			// Get his account information and transactions as well.
			
			List<BIDCMerchant> merchants = dao.getAll(BIDCMerchant.class);
			
			if ( merchants == null ) {
				merchants = new ArrayList<BIDCMerchant>();
			}

			BIDCAccount account = service.getAccountDetails(cust);

			List<BIDCTransaction> transactions = service
					.getTransactions(account);

			model.put("user", user);
			model.put("account", account);
			model.put("transactions", transactions);
			model.put("merchants", merchants);
			model.put("merchant", new BIDCMerchant());	

			view = new ModelAndView("registrationSuccess", model);
		}

		return view;
	}

	private ModelAndView importContacts(final AuthProvider provider)
			throws Exception {
		List<Contact> contactsList = new ArrayList<Contact>();
		contactsList = provider.getContactList();
		if (contactsList != null && contactsList.size() > 0) {
			for (Contact p : contactsList) {
				if (!StringUtils.hasLength(p.getFirstName())
						&& !StringUtils.hasLength(p.getLastName())) {
					p.setFirstName(p.getDisplayName());
				}
			}
		}
		ModelAndView view = new ModelAndView("showImportContacts", "contacts",
				contactsList);
		return view;
	}
	
	@RequestMapping(value = "/submitRegistration")
	public ModelAndView showRegistrationPage(
			@ModelAttribute("user") final User user,
			final HttpServletRequest request) {
		HttpSession session = request.getSession();
		SocialAuthManager manager = socialAuthTemplate.getSocialAuthManager();
		AuthProvider provider = manager.getCurrentAuthProvider();
		session.setAttribute("OFFERID", "O" + System.currentTimeMillis());
		BIDCService service = new BIDCServiceImpl();
		Map<String, Object> model = new HashMap<String, Object>();
		
		User thisUser = new User();
		
		String providerId = provider.getProviderId();
		
		Profile profile = null;
		try {
			profile = provider.getUserProfile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String socialId = profile.getValidatedId();
		
		BIDCCustomer cust = null;
		try {
			
			cust = service.getCustomer(providerId, socialId);
			
			BIDCAccount account = service.getAccountDetails(cust);
			
			if (cust != null) {
				thisUser.setEmail(cust.getEmail());
			}


			List<BIDCTransaction> transactions = service
					.getTransactions(account);
			model.put("user", thisUser);
			model.put("account", account);
			model.put("transactions", transactions);
			
		} catch (BIDCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




		ModelAndView view = new ModelAndView("registrationSuccess", model);
		
		
		return view;

	}
		

}
