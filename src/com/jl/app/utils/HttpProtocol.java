package com.jl.app.utils;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.*;

public class HttpProtocol {

	public static String request(String url) {
		HttpURLConnection conn = null;

		try {
			conn = (HttpURLConnection) (new URL(url).openConnection());
			BufferedInputStream buffer = new BufferedInputStream(conn.getInputStream());
			
			return readStream(buffer);

		} catch (Exception e) {
			return null;
		} finally {
			conn.disconnect();
		}
	}

	private static String readStream(InputStream is) {
		String response = null;
		int count;

		try {
			do {
				byte[] buffer = new byte[1024];
				count = is.read(buffer);

				response += new String(buffer);
			} while (count != -1);

			return response;
		} catch (IOException e) {
			return null;
		}
	}
}
