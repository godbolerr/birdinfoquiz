<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<style type="text/css">
.style1 {
	text-align: justify;
}
</style>
<div id="main">
	<div class="alert alert-info">
		<strong>Step 1&nbsp;:&nbsp;</strong>Lets begin with your favorite
		social hub..
	</div>
	<div>
		<table cellpadding="10" cellspacing="10" align="center">

			<tr>
				<td>
					<form action="qrservlet" method="get">
						<p>Enter Text to create QR Code</p>
						<input type="text" name="qrtext" /> <input type="submit"
							value="Generate QR Code" />
					</form>
				</td>
	
			</tr>
		</table>
		<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
		<p class="additional">
			
		</p>
	</div>
</div>
