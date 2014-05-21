package com.threepillar.labs.socialauthsample.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bidc.data.AppDao;
import com.bidc.data.AppUser;
import com.bidc.data.BIDCAccount;
import com.bidc.data.BIDCTransaction;
import com.bidc.data.Coffee;
import com.bidc.data.EmfDao;
import com.bidc.service.BIDCException;
import com.bidc.service.BIDCService;
import com.bidc.service.impl.BIDCServiceImpl;

@Controller
@RequestMapping("/product")
public class MerchantController {

	String message;

	AppDao dao = null;

	@RequestMapping(value = "/{name}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getProduct(@PathVariable String name, ModelMap model) {

		model.addAttribute("product", name);
		model.addAttribute("message", "Hello World");
		return "list";

	}

	/**
	 * Transaction specific API
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/xml/transaction/{accountId}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BIDCTransaction getTransaction(@PathVariable String accountId) {

		dao = EmfDao.INSTANCE;
		return dao.getTransactionsForAccount(accountId).get(0);
	}

	@RequestMapping(value = "/xml/promotion/{promoId}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	ResponseEntity<String> processPromotion(@PathVariable String promoId) {

		String message = "<html><body>Vendor Promotion code  " + promoId
				+ " processed successfully.</body></html>";
		
		
		
		
		// Initiate payment transfer.
		
		// As of now source and target accounts are hardcoded.
		
		
		BIDCService service = new BIDCServiceImpl();
		
		BIDCAccount fromAccount =  new BIDCAccount();
		fromAccount.setId("A1392369822638");
		
		
		BIDCAccount toAccount = new BIDCAccount();
		toAccount.setId("A1392368232309");
		
		
		try {
			service.transferFunds(fromAccount, toAccount, 17.00d, "Merchant purchase ");
		} catch (BIDCException e) {
			message = "Issue with fund transfer... ";
			e.printStackTrace();
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.TEXT_HTML);
		return new ResponseEntity<String>(message, responseHeaders,
				HttpStatus.CREATED);

	}

	@RequestMapping(value = "/xml/{name}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	Coffee getCoffeeInXML(@PathVariable String name) {

		Coffee coffee = new Coffee(name, 100);

		dao = EmfDao.INSTANCE;

		// Create user and populate it with data.

		AppUser user = new AppUser();

		user.setSocialId("rogogog");
		user.setGeoData(">> " + coffee);

		dao.add(AppUser.class, user);

		return coffee;

	}

}