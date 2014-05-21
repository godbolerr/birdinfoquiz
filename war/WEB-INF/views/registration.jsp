<?xml version="1.0" encoding="UTF-8" ?>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<style type="text/css">
.style1 {
	text-align: justify;
}
</style>


<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js ie6" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js ie7" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js ie8" lang="en"> <![endif]-->
<!--[if gt IE 8]><!-->
<html
	class=" js flexbox canvas canvastext webgl no-touch geolocation postmessage no-websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients no-cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers applicationcache svg inlinesvg smil svgclippaths"
	lang="en-US">
<!--<![endif]-->
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta charset="utf-8">

	<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>BIDC BANK</title>

<meta name="viewport" content="width=device-width">
<link rel="stylesheet" href="css/style.css" type="text/css"
	media="screen">
<link rel="stylesheet" id="color-scheme-stylesheet-css"
	href="css/brown.css" type="text/css" media="all">
<link rel="stylesheet" id="Droid+Sans:400,700-css" href="css/css.css"
	type="text/css" media="all">
<link rel="shortcut icon"
	href="http://bidcinsights.appspot.com/images/bankicon.jpg">
<style type="text/css" id="custom-background-css">
body.custom-background {
	background-color: #ffffff;
}
</style>
<!--[if IE 8]>
<style type="text/css">
.comment-body, li.pingback, .quote, .avatar, .defaultbtn, .button, .btn, #searchsubmit, #submit, .submit, .post-edit-link, .more-link, input[type="submit"], input[type="text"], textarea, ol.flex-control-nav li a, ol.flex-control-nav li a.active, .flex-direction-nav li a, .post-date, nav.secondary-menu, nav ul ul {
behavior: url(http://bidcinsights.appspot.com/wp-content/themes/designfolio-pro/includes/js/PIE.htc);
}
</style>
<![endif]-->

<!-- Designfolio Pro Google web font CSS -->
<style type="text/css">
h1,h2,h3,h4 {
	font-family: 'Droid Sans', serif;
}
</style>

</head>
<body data-twttr-rendered="true"
	class="single single-portfolio postid-187 custom-background designfolio-pro">

	<div id="body-container">


		<div id="header-container">
			<header class="cf">


				<div id="logo-wrap">
					<div id="site-logo">
						<a href="http://bidcinsights.appspot.com/"><img
							src="images/mybank.jpg"></a>
					</div>

				</div>
				<!-- #logo-wrap -->

				<div id="header-widget-area" class="widget-area"
					role="complementary">
					<div id="pc_info_widget_designfolio-pro-2"
						class="widget pc_info_widget">
																		<p class="additional">
													Welcome :
													<c:out value="${sessionScope.GUEST}" />
												</p>

					</div>
				</div>
				<!-- #header-widget-area -->
				<c:set var="registration" value="" />
				<c:set var="contact" value="" />
				<c:set var="share" value="" />
				<c:set var="pageHeading" value="" />
				<%
					String getURL = request.getRequestURL().toString();
					request.setAttribute("pageURL", getURL);
				%>

				<c:if
					test="${fn:indexOf(fn:toLowerCase(pageURL),'registration') > 0 || profile != null}">
					<c:set var="registration" value="current_page_item" />
					<c:set var="pageHeading"
						value="Get on board with the leader ..." />
				</c:if>
				<c:if
					test="${fn:indexOf(pageURL,'Contact') > 0 || contacts != null}">
					<c:set var="contact" value="current_page_item" />
					<c:set var="pageHeading" value="Import Contacts Demo" />
				</c:if>
				<c:if test="${fn:indexOf(pageURL,'share') > 0}">
					<c:set var="share" value="current_page_item" />
					<c:set var="pageHeading"
						value="Share your feedback with friends..." />
				</c:if>
				<nav class="primary-menu cf">
					<div class="menu">
						<ul>
							<li class="<c:out value="${registration}"/>"><a
								href="<c:url value="/registration.do"/>" title="Home">Registration</a></li>
						</ul>
					</div>
				</nav>

			</header>

		</div>
		<!-- #header-container -->

		<div id="container" class="singular-post">

			<div id="contentwrap" class="one-col">

				<div class="content">

					<div id="post-187"
						class="post-187 portfolio type-portfolio status-publish hentry post singular-page">

						<div class="post-content" style="padding-left: 0px;">
							<h3>
								<c:out value="${pageHeading }" />
							</h3>
							<p></p>
							<p></p>

							<!--  MAIN CONTENT STARTS HERE  -->

							<div id="main">
								<div class="alert alert-info">
									<strong>Step 1&nbsp;:&nbsp;</strong>Lets begin with your
									favorite social hub..
								</div>
								<div>
									<table cellpadding="10" cellspacing="10" align="center">

										<tr>
											<td>

											</td>
										</tr>


										<tr>

											<td><a href="socialauth.do?id=facebook"><img
													src="images/facebook_icon.png" alt="Facebook"
													title="Facebook" border="0"></img></a></td>

											<!-- 
											
																						<td><a href="socialauth.do?id=twitter"><img
													src="images/twitter_icon.png" alt="Twitter" title="Twitter"
													border="0"></img></a></td>
													
											<td><a href="socialauth.do?id=google"><img
													src="images/gmail-icon.jpg" alt="Gmail" title="Gmail"
													border="0"></img></a></td>
													
													
					<td>
						<a href="socialauth.do?id=yahoo"><img src="images/yahoomail_icon.jpg" alt="YahooMail" title="YahooMail" border="0"></img></a>
					</td>
					<td>
						<a href="socialauth.do?id=hotmail"><img src="images/hotmail.jpeg" alt="HotMail" title="HotMail" border="0"></img></a>
					</td>
					<td>
						<a href="socialauth.do?id=linkedin"><img src="images/linkedin.gif" alt="Linked In" title="Linked In" border="0"></img></a>
					</td>
					<td>
						<a href="socialauth.do?id=foursquare"><img src="images/foursquare.jpeg" alt="FourSquare" title="FourSquare" border="0"></img></a>
					</td>
					<td>
						<a href="socialauth.do?id=myspace"><img src="images/myspace.jpeg" alt="MySpace" title="MySpace" border="0"></img></a>
					</td>
					<td>
						<a href="socialauth.do?id=mendeley"><img src="images/mendeley.jpg" alt="Mendeley" title="Mendeley" border="0"></img></a>
					</td>
					<td>
						<a href="socialauth.do?id=yammer"><img src="images/yammer.jpg" alt="Yammer" title="Yammer" border="0"></img></a>
					</td>
					<td>
						<a href="socialauth.do?id=googleplus"><img src="images/googleplus.png" alt="Google Plus" title="Google Plus" border="0"></img></a>
					</td>
					<td>
						<a href="socialauth.do?id=instagram"><img src="images/instagram.png" alt="Instagram" title="Instagram" border="0"></img></a>
					</td>
					<td>
						<a href="socialauth.do?id=flickr"><img src="images/flickr_icon.jpg" alt="Flickr" title="Flickr" border="0"></img></a>
					</td>
					<td>
						<a href="socialauth.do?id=github"><img src="images/github.png" alt="GITHub" title="GITHub" border="0"></img></a>
					</td>
					 -->
										</tr>








									</table>
									<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
									<br />
									<p class="additional">
										IP Address :
										<c:out value="${sessionScope.CLIENT_IP}" />
									<p>
										Your location details :
										<c:out value="${sessionScope.GEODETAILS}" />
									</p>
									</p>

								</div>
							</div>


							<!--  MAIN CONTENT ENDS HERE  -->












						</div>
						<!-- post-content -->
					</div>
					<!-- post-item -->

				</div>
				<!-- .content -->

			</div>
			<!-- #contentwrap -->

		</div>
		<!-- #container -->

		<footer>

			<div id="site-info">
				<p class="copyright">
					&copy; 2014 <a href="http://bidcinsights.appspot.com/"
						title="MY Bank" rel="home">Bank on Us !</a>
				</p>
			</div>
		</footer>
	</div>
	<!-- #body-container -->



</body>

</html>

