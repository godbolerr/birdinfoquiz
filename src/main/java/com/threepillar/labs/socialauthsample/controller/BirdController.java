package com.threepillar.labs.socialauthsample.controller;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	
	
	public static final String BIRD_START = "<b>";
	public static final String BIRD_END = "</b>";
	
	public static final String ID_START = "<id>";
	public static final String ID_END = "</id>";
	
	public static final String NAME_START = "<name>";
	public static final String NAME_END = "</name>";
	
	public static final String URL_START = "<url>";
	public static final String URL_END = "</url>";	
	
	public static final String ENGNAME_START = "<eName>";
	public static final String ENGNAME_END = "</eName>";
	
	public static final String ALTERNATIVE_START = "<alt>";
	public static final String ALTERNATIVE_END = "</alt>";
	
	public static final String BASE_URL	 = "http://birdinfoquiz.appspot.com/images/";
	

	private final Logger logger = Logger.getLogger(BirdController.class
			.getName());

	protected static final String VIEW_NAME_HOMEPAGE = "index";

	@Autowired
	private SocialAuthTemplate socialAuthTemplate;

	@RequestMapping(value = "/birds")
	public ModelAndView showBirds(final HttpServletRequest request,
			ModelMap model) {
		HttpSession session = request.getSession();

		String who = request.getParameter("W");

		String guest = "Guest";

		if (who != null && "ABCCBA".equals(who)) {
			guest = "Nagaraj";

		}

		if (who != null && "XYZZYX".equals(who)) {
			guest = "Sanjoy";

		}

		session.setAttribute(Constants.REQUEST_TYPE, Constants.REGISTRATION);
		session.setAttribute("CLIENT_IP", request.getRemoteAddr());
		session.setAttribute("GUEST", guest);

		AppService service = new AppServiceImpl();
		
		List<BirdInfo> birds = service.getAllBirds();
		
		
		

		model.addAttribute("birds", birds);

		ModelAndView modelAndView = new ModelAndView("birds", model);

		return modelAndView;

	}

	@RequestMapping(value = "/xml/{langCode}/getbirds", method = { RequestMethod.GET }, produces = "text/xml; charset=utf-8")
	public @ResponseBody
	String getBirds(@PathVariable String langCode , final HttpServletResponse resp) {

		// Initiate payment transfer.

		// As of now source and target accounts are hardcoded.
		
		resp.setCharacterEncoding("UTF-8");
		

		AppService svc = new AppServiceImpl();

		List<BirdInfo> birds = svc.getAllBirds();

		StringBuilder sb = new StringBuilder();
		
		sb.append("<?xml version='1.0' encoding='UTF-8' standalone='yes' ?>");

		sb.append("<birds>");

		String picUrl = null;
		
		for (Iterator<BirdInfo> iterator = birds.iterator(); iterator.hasNext();) {
			BirdInfo birdInfo = (BirdInfo) iterator.next();

			sb.append(BIRD_START);

			sb.append(ID_START);
			sb.append(birdInfo.getId());
			sb.append(ID_END);

			String thisName = birdInfo.getNameForLang(langCode);

			if (thisName == null) {
				thisName = birdInfo.getName();
			}

			sb.append(NAME_START);
			sb.append(thisName);
			sb.append(NAME_END);

			sb.append(ENGNAME_START);
			sb.append(birdInfo.getName());
			sb.append(ENGNAME_END);

			String options = birdInfo.getOptionsForLang(langCode);

			if ( options == null || options.length() < 4 ) {
				options = "NA1,NA2";
			}


			sb.append(ALTERNATIVE_START);
			sb.append(options);
			sb.append(ALTERNATIVE_END);

			
			picUrl = birdInfo.getPicUrl();
			
			if ( picUrl == null ) {
				picUrl = BASE_URL + "bird.jpg";
			} else {
				
				// Prepend base url if we dont find http in the base url value.
				
				if ( picUrl.contains("http") == false ) {
					picUrl = BASE_URL + picUrl;					
				}
				
			}
			
			sb.append(URL_START);
			sb.append(picUrl);
			sb.append(URL_END);

			sb.append(BIRD_END);
		}

		sb.append("</birds>");
		HttpHeaders responseHeaders = new HttpHeaders();
		
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		
		responseHeaders.setContentType(MediaType.TEXT_XML);
		
		
		
		return sb.toString();

	}

	@RequestMapping(value = "/xml/deleteBird/{bid}")
	public ModelAndView deleteBird(final HttpServletRequest request,final HttpServletResponse resp,
			ModelMap model, @PathVariable String bid) {
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");

		AppService service = new AppServiceImpl();

		BirdInfo binfo = service.getBirdInfo(bid);

		service.deleteBird(binfo);

		List<BirdInfo> birds = service.getAllBirds();

		model.addAttribute("birds", birds);

		ModelAndView modelAndView = new ModelAndView("birds", model);

		return modelAndView;

	}

	@RequestMapping(value = "/viewUpdate")
	public ModelAndView viewUpdate(final HttpServletRequest request,final HttpServletResponse resp,
			ModelMap model) {

		HttpSession session = request.getSession();
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");

		String guest = "Guest";

		String bid = request.getParameter("bid");

		AppService service = new AppServiceImpl();

		BirdInfo bInfo = service.getBirdInfo(bid);

		session.setAttribute(Constants.REQUEST_TYPE, Constants.REGISTRATION);
		session.setAttribute("CLIENT_IP", request.getRemoteAddr());
		session.setAttribute("GUEST", guest);

		String eOpt = new String(bInfo.getLangOptions().get("en"));
		String mrOpt = new String(bInfo.getLangOptions().get("mr"));
		String mrLang = new String(bInfo.getLangNames().get("mr"));

		List<BirdInfo> birds = service.getAllBirds();

		model.addAttribute("bird", bInfo);
		model.addAttribute("birds", birds);
		model.addAttribute("enOptions", eOpt);
		model.addAttribute("mrOptions", mrOpt);
		model.addAttribute("marathiName", mrLang);

		ModelAndView modelAndView = new ModelAndView("birds", model);

		return modelAndView;

	}

	@RequestMapping(value = "/addBirds")
	public ModelAndView addBirds(final HttpServletRequest request,
			ModelMap model) {

		HttpSession session = request.getSession();

		String guest = "Guest";

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

		if (request.getParameter("id") != null
				&& request.getParameter("id").startsWith("B")) {
			bInfo.setId(request.getParameter("id"));
			service.updateBirdInfo(bInfo);
		} else {
			service.addBird(bInfo);
		}

		session.setAttribute(Constants.REQUEST_TYPE, Constants.REGISTRATION);
		session.setAttribute("CLIENT_IP", request.getRemoteAddr());
		session.setAttribute("GUEST", guest);

		List<BirdInfo> birds = service.getAllBirds();

		model.addAttribute("birds", birds);

		ModelAndView modelAndView = new ModelAndView("birds", model);

		return modelAndView;

	}

}
