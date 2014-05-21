package com.bidc.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.OutputSettings;
import com.google.appengine.api.images.Transform;


public class QRCodeGen {

	public QRCodeGen() {
	}

	final static String urlGoogleChart = "http://chart.apis.google.com/chart";
	final static String urlQRApi = "?chs=200x200&cht=qr&chl=";
	final static String urlMySite = "http://sunil-android.blogspot.in/";

	public  void loadQRCode() {

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

			ImagesService imagesService = ImagesServiceFactory.getImagesService();
			

			Image image = ImagesServiceFactory.makeImage(buffer.toByteArray());
			
		


			inputStream.close();
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
	
	public static void main(String[] args) {
		new QRCodeGen().loadQRCode();
	}

}
