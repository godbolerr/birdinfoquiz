package com.threepillar.labs.socialauthsample.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.brickred.socialauth.spring.bean.SocialAuthTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.ModelAndView;

import com.bidc.data.BirdInfo;
import com.bidc.service.AppService;
import com.bidc.service.impl.AppServiceImpl;
import com.threepillar.labs.socialauthsample.util.Constants;

@Controller
public class BirdController {

	private final Logger logger = Logger.getLogger(BirdController.class.getName());
	
	 protected static final String VIEW_NAME_HOMEPAGE = "index";
	 

	@Autowired
	private SocialAuthTemplate socialAuthTemplate;
	
      

	@RequestMapping(value = "/birds")
	public ModelAndView showBirds(final HttpServletRequest request,ModelMap model) {
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
		
		AppService service = new AppServiceImpl();
		List<BirdInfo> birds = service.getAllBirds();
		
		model.addAttribute("birds", birds);
		
		ModelAndView modelAndView = new ModelAndView("birds",model);
		
		
		return modelAndView;

	}
	
	@RequestMapping(value = "/xml/{langCode}/getbirds", method = {
			RequestMethod.GET })
	public @ResponseBody
	ResponseEntity<String> getBirds(@PathVariable String langCode) {

		
		// Initiate payment transfer.
		
		// As of now source and target accounts are hardcoded.
		
		
		AppService svc = new AppServiceImpl();
		
		List<BirdInfo> birds = svc.getAllBirds();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<birds>");
		
		for (Iterator iterator = birds.iterator(); iterator.hasNext();) {
			BirdInfo birdInfo = (BirdInfo) iterator.next();
			
			sb.append("<bird>");
			
			
			sb.append("<id>");
			sb.append(birdInfo.getId());
			sb.append("</id>");
			

			sb.append("<name>");
			sb.append(birdInfo.getNameForLang(langCode));
			sb.append("</name>");
			
			sb.append("<englishName>");
			sb.append(birdInfo.getName());
			sb.append("</englishName>");
			
				
			
			sb.append("<alternatives>");
			sb.append(birdInfo.getOptionsForLang(langCode));
			sb.append("</alternatives>");
			
			sb.append("<picUrl>");
			sb.append(birdInfo.getPicUrl());
			sb.append("</picUrl>");
			
			sb.append("</bird>");
		}
		
		sb.append("</birds>");
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.TEXT_XML);
		return new ResponseEntity<String>(sb.toString(), responseHeaders,
				HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "/addBirds")
	public ModelAndView addBirds(final HttpServletRequest request,ModelMap model) {

		HttpSession session = request.getSession();
		
		
		String guest = "Guest" ; 
		
		String englishName = request.getParameter("englishName");
		String marathiName = request.getParameter("marathiName");
		String picUrl = request.getParameter("picUrl");
		String enOptions = request.getParameter("enOptions");
		String mrOptions = request.getParameter("mrOptions");
		
		AppService service = new AppServiceImpl();
		

		BirdInfo bInfo = new BirdInfo();
		
		bInfo.setName(englishName);
		
		bInfo.setPicUrl(picUrl);
		bInfo.addLangName("mr", marathiName);
		bInfo.addLangOptions("en", enOptions);
		bInfo.addLangOptions("mr", mrOptions);
		
		
		service.addBird(bInfo);
		
	
		session.setAttribute(Constants.REQUEST_TYPE, Constants.REGISTRATION);
		session.setAttribute("CLIENT_IP", request.getRemoteAddr());
		session.setAttribute("GUEST", guest);
		

		List<BirdInfo> birds = service.getAllBirds();
		
		model.addAttribute("birds", birds);
		
		ModelAndView modelAndView = new ModelAndView("birds",model);

		return modelAndView;

	}
	
}
