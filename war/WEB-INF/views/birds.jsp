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
						<img
							src="images/bird.png"></a>
					</div>

				</div>
				<!-- #logo-wrap -->

			
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
					<c:set var="pageHeading" value="Get on board with the leader ..." />
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
							<li class="<c:out value="${registration}"/>">Registration</li>
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

							<div class="alert alert-info">
								<strong>Add new bird information..</strong>	</div>
							<div align="center">

								<form action='<c:url value="/addBirds.do"/>'
									method="post">
									<table border="1">
									
									
										<tr>
											<td>English Name</td>
											<td><input type="text" size="25" name="englishName" id="englishName"
												 /></td>
										</tr>
										
										
							
										<tr>
											<td>Marathi Name</td>
											<td><input type="text" size="25" name="marathiName" id="marathiName"
										 /></td>
										</tr>
										
										
										<tr>
											<td>Picture</td>
											<td><input type="text" size="25" name="picUrl" id="picUrl"
												value="" /></td>
										</tr>
										<tr>
											<td>Options [English]</td>
											<td><input type="text" size="25" name="enOptions"
												id="enOptions"/> </td>
										</tr>
										
										<tr>
											<td>Options [Marathi]</td>
											<td><input type="text" size="25" name="mrOptions"
												id="mrOptions"/> </td>
										</tr>										
										
										<tr>
											<td colspan="2" align="center"><input type="hidden"
												name="uniqueId" id="uniqueId"
												value='<c:out value="${profile.validatedId}"/>' /> <input
												type="submit" value="Submit" /></td>
										</tr>
									</table>
								</form>
							</div>


						<div align="center">

								<c:set var="count" value="0" scope="page" />
								<c:if test="${!empty birds}">
									<h4>Birds</h4>
									<table border="1">
										<tr>
											<th>#</th>
											<th>Id</th>
											<th>English Name</th>
											
											<th>Lang Options</th>
											<th>Lang Names</th>	
											<th>URL</th>
									
										</tr>

										<c:forEach items="${birds}" var="bird">
											<c:set var="count" value="${count + 1}" scope="page" />
											<tr>
												<td><c:out value="${count}" /></td>
												<td><c:out value="${bird.id}" /></td>
												<td><c:out value="${bird.name}" /></td>
												<td><c:out value="${bird.langOptions}" /></td>
												<td><c:out value="${bird.langNames}" /></td>
												<td><c:out value="${bird.picUrl}" /></td>
												
											</tr>
										</c:forEach>
									</table>
								</c:if>




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
