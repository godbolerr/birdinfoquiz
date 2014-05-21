
<?xml version="1.0" encoding="UTF-8" ?>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
						Welcome <strong><c:out value="${user.name}" /></strong>
						&nbsp;&nbsp;&nbsp;&nbsp; [
						<c:out value="${user.email}" />
						] <a href="logout.do"> <img src="images/logout.jpg"
							alt="Log Out" height="32" width="32"></a>

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

				<nav class="primary-menu cf">
					<div class="menu">

						<ul>
							<li class="<c:out value="${contact}"/>"><a
								href="<c:url value="/importContacts.do"/>">Import Contacts</a></li>
							<li class="<c:out value="${share}"/>"><a
								href="<c:url value="/shareForm.do"/>">Share with friends</a></li>

						</ul>


					</div>
				</nav>

			</header>

		</div>
		<!-- #header-container -->

		<div id="container" class="singular-post">

			<div id="contentwrap" class="one-col">

				<div class="content">



					<div class="post-content" align="right">
						<form action="/sendoffer.do">
						
						 <form:select path="merchant" items="${merchants}" />
						
						
							<input type="Submit" value="Send Offer">
						</form>


						<p></p>

						<c:if test="${!empty sessionScope.OFFER}">
							Offer Id : <c:out value="${sessionScope.OFFER}" />
						</c:if>


					</div>

					<div id="post-187"
						class="post-187 portfolio type-portfolio status-publish hentry post singular-page">

						
						<div class="post-content" style="padding-left: 0px;">

							<p></p>
							<p></p>

							<!--  MAIN CONTENT STARTS HERE  -->
							<p></p>

							<div class="alert alert-info">
								Account # : <strong><c:out value="${account.id}" /></strong>
								&nbsp;&nbsp;&nbsp;&nbsp; Balance : $ <strong><c:out
										value="${account.balance}" /></strong>
							</div>

						<div class="post-content" align="left">
							<form action="/authSuccess.do">
								<input type="Submit" value="Refresh">
							</form>

						</div>
							<div align="center">

								<c:set var="count" value="0" scope="page" />
								<c:if test="${!empty transactions}">
									<h4>Transaction Details</h4>
									<table border="1">
										<tr>
											<th>#</th>
											<th>Date</th>
											<th>Id</th>
											<th>Type</th>
											<th>Amount</th>
											<th>Note</th>

										</tr>

										<c:forEach items="${transactions}" var="transaction">
											<c:set var="count" value="${count + 1}" scope="page" />
											<tr>
												<td><c:out value="${count}" /></td>

												<td><c:out value="${transaction.createdOn}" /></td>
												<td><c:out value="${transaction.id}" /></td>
												<td><c:out value="${transaction.type}" /></td>

												<td><c:out value="${transaction.amount}" /></td>
												<td><c:out value="${transaction.note}" /></td>
											</tr>
										</c:forEach>
									</table>
								</c:if>







								<p>.</p>
								<p>.</p>
								<p>.</p>
								<p>.</p>
								<p>.</p>
								<p>.</p>
								<p>.</p>
								<p></p>
								<p></p>
								<p></p>
								<p></p>
								<p></p>
								<p></p>
								<p></p>















							</div>



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

