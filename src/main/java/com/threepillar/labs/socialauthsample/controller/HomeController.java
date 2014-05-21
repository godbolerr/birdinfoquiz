package com.threepillar.labs.socialauthsample.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.exception.SocialAuthException;
import org.brickred.socialauth.spring.bean.SocialAuthTemplate;
import org.brickred.socialauth.util.HttpUtil;
import org.brickred.socialauth.util.MethodType;
import org.brickred.socialauth.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bidc.data.BIDCAccount;
import com.bidc.data.BIDCCustomer;
import com.bidc.data.BIDCTransaction;
import com.bidc.service.BIDCService;
import com.bidc.service.impl.BIDCServiceImpl;
import com.threepillar.labs.socialauthsample.bean.User;
import com.threepillar.labs.socialauthsample.util.Constants;

@Controller
public class HomeController {

	private final Logger logger = Logger.getLogger(HomeController.class.getName());
	
	 protected static final String VIEW_NAME_HOMEPAGE = "index";
	 

	@Autowired
	private SocialAuthTemplate socialAuthTemplate;
	
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String showHomePage() {
    	logger.info("Rendering homepage.");
        return VIEW_NAME_HOMEPAGE;
    }
    

	@RequestMapping(value = "/registration")
	public ModelAndView showRegistration(final HttpServletRequest request) {
		logger.info("Showing registration page");
		HttpSession session = request.getSession();
		
		String who = request.getParameter("W");
		
		String guest = "Guest" ; 
		
		if ( who != null && "ABCCBA".equals(who)){
			guest  = "Nagaraj" ;
					
			
		}
		
		if ( who != null && "XYZZYX".equals(who)){
			guest  = "Sanjoy" ;
					
			
		}
		
		
		session.setAttribute(Constants.REQUEST_TYPE, Constants.REGISTRATION);
		session.setAttribute("CLIENT_IP", request.getRemoteAddr());
		session.setAttribute("GUEST", guest);
		
		String geoUrl = "http://freegeoip.net/json/" + request.getRemoteAddr() ;
		// Get the geolocation details from the request ip.
		
		try {
			Response response = HttpUtil.doHttpRequest(geoUrl,
					MethodType.GET.toString(), null, null);

				String result = response.getResponseBodyAsString(org.brickred.socialauth.util.Constants.ENCODING);
				
				if ( result != null ) {
					session.setAttribute("GEODETAILS",result);
				}
		
		} catch (SocialAuthException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		
		
		
		
		ModelAndView modelAndView = new ModelAndView("registration");
		return modelAndView;

	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout(final HttpServletRequest request) {

		HttpSession session = request.getSession();
		
		String who = request.getParameter("W");
		
		String guest = "Guest" ; 
		
		if ( who != null && "ABCCBA".equals(who)){
			guest  = "Nagaraj" ;
					
			
		}
		
		if ( who != null && "XYZZYX".equals(who)){
			guest  = "Sanjoy" ;
					
			
		}
		
		
		session.setAttribute(Constants.REQUEST_TYPE, Constants.REGISTRATION);
		session.setAttribute("CLIENT_IP", request.getRemoteAddr());
		session.setAttribute("GUEST", guest);
		
		String geoUrl = "http://freegeoip.net/json/" + request.getRemoteAddr() ;
		// Get the geolocation details from the request ip.
		
		try {
			Response response = HttpUtil.doHttpRequest(geoUrl,
					MethodType.GET.toString(), null, null);

				String result = response.getResponseBodyAsString(org.brickred.socialauth.util.Constants.ENCODING);
				
				if ( result != null ) {
					session.setAttribute("GEODETAILS",result);
				}
		
		} catch (SocialAuthException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		
		
		ModelAndView modelAndView = new ModelAndView("registration");
		return modelAndView;

	}
	


		
	
	@RequestMapping(value = "/qrform")
	public ModelAndView generateQrForm(final HttpServletRequest request) {
		logger.info("QR Code generation...");
		ModelAndView modelAndView = new ModelAndView("qrform");
		return modelAndView;
	}	

	@RequestMapping(value = "/importContacts")
	public ModelAndView importContacts(final HttpServletRequest request) {
		logger.info("Showing import contacts page");
		HttpSession session = request.getSession();
		session.setAttribute(Constants.REQUEST_TYPE, Constants.IMPORT_CONTACTS);
		ModelAndView modelAndView = new ModelAndView("importContacts");
		return modelAndView;
	}

	@RequestMapping(value = "/shareForm")
	public ModelAndView shareForm(final HttpServletRequest request) {
		logger.info("Showing share form");
		HttpSession session = request.getSession();
		session.setAttribute(Constants.REQUEST_TYPE, Constants.SHARE);
		SocialAuthManager manager = socialAuthTemplate.getSocialAuthManager();
		List<String> connectedProvidersIds = new ArrayList<String>();
		if (manager != null) {
			connectedProvidersIds = manager.getConnectedProvidersIds();
		}

		ModelAndView modelAndView = new ModelAndView("shareForm",
				"connectedProvidersIds", connectedProvidersIds);
		return modelAndView;

	}

	@RequestMapping(value = "/share", method = RequestMethod.POST)
	public ModelAndView share(
			@RequestParam(value = "message", required = true) final String message,
			final HttpServletRequest request) {
		logger.info("Showing share form");
		HttpSession session = request.getSession();
		session.setAttribute(Constants.REQUEST_TYPE, Constants.SHARE);
		SocialAuthManager manager = socialAuthTemplate.getSocialAuthManager();
		List<String> connectedProvidersIds = new ArrayList<String>();
		if (manager != null) {
			connectedProvidersIds = manager.getConnectedProvidersIds();
		}
		String providerIds = null;
		for (String id : connectedProvidersIds) {
			try {
				AuthProvider provider = manager.getProvider(id);
				provider.updateStatus(message);
				if (providerIds == null) {
					providerIds = provider.getProviderId();
				} else {
					providerIds += ", " + provider.getProviderId();
				}
			} catch (Exception e) {
				logger.log(Level.SEVERE,e.getMessage());
			}
		}
		ModelAndView modelAndView = new ModelAndView("shareForm");
		modelAndView.addObject("connectedProvidersIds", connectedProvidersIds);
		if (providerIds != null) {
			String str = "Status is updated on " + providerIds;
			if (providerIds.indexOf(',') != -1) {
				str += " providers.";
			} else {
				str += " provider.";
			}
			modelAndView.addObject("message", str);
		}
		return modelAndView;

	}
}
