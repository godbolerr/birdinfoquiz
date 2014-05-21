package com.threepillar.labs.socialauthsample.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bidc.data.AppDao;
import com.bidc.data.BIDCMerchant;
import com.bidc.data.BIDCProduct;
import com.bidc.data.EmfDao;

@Controller
@RequestMapping("/admin/refdata")
public class RefDataController {

	String message;

	AppDao dao = EmfDao.INSTANCE;;

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public ModelAndView getProduct(@PathVariable String id, ModelMap model) {
		
		BIDCProduct product = dao.get(BIDCProduct.class, id);

		if ( product != null ) {
			model.addAttribute("product", product);
		}
		
		ModelAndView view = new ModelAndView("viewProduct", model);
		
		return view;

	}
	
	@RequestMapping(value = "/product/{type}/{name}/{description}", method = { RequestMethod.GET })
	public ModelAndView addProduct(@PathVariable String name, @PathVariable String description , @PathVariable String type, ModelMap model) {
		
		BIDCProduct product = new BIDCProduct();

		product.setName(name);
		product.setDescription(description);
		product.setType(type);
		
		dao.add(BIDCProduct.class, product);
		
		List<BIDCProduct> products = dao.getAll(BIDCProduct.class);
		
		model.addAttribute("products", products);
		
		ModelAndView view = new ModelAndView("listProducts", model);
		
		return view;

	}
	
	
	@RequestMapping(value = "/merchant/{name}/{phone}/{email}", method = { RequestMethod.GET })
	public ModelAndView addMerchant(@PathVariable String name, @PathVariable String phone , @PathVariable String email, ModelMap model) {
		
		BIDCMerchant merchant = new BIDCMerchant();

		merchant.setPhoneNumber(phone);
		merchant.setEmail(email);
		merchant.setName(name);
		
		dao.add(BIDCMerchant.class, merchant);
		
		List<BIDCMerchant> merchants = dao.getAll(BIDCMerchant.class);
		
		model.addAttribute("merchants", merchants);
		
		ModelAndView view = new ModelAndView("listMerchants", model);
		
		return view;

	}	
	
	

}