package com.bidc.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QRCodeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final static String urlGoogleChart = "http://chart.apis.google.com/chart";
	final static String urlQRApi = "?chs=200x200&cht=qr&chl=";
	final static String urlMySite = "http://sunil-android.blogspot.in/";

	public QRCodeServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String title = req.getParameter("title");

		InputStream inputStream = null;

		try {
			inputStream = OpenHttpConnection(urlGoogleChart + urlQRApi
					+ urlMySite);

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			int nRead;
			byte[] data = new byte[16384];

			while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}

			buffer.flush();

			resp.setContentType("image/png");
			resp.getOutputStream().write(buffer.toByteArray());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private InputStream OpenHttpConnection(String strURL) throws IOException {
		InputStream is = null;
		URL url = new URL(strURL);
		URLConnection urlConnection = url.openConnection();

		try {
			HttpURLConnection httpConn = (HttpURLConnection) urlConnection;
			httpConn.setRequestMethod("GET");
			httpConn.connect();

			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				is = httpConn.getInputStream();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return is;
	}

}
