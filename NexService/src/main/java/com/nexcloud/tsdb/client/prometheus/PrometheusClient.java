package com.nexcloud.tsdb.client.prometheus;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.nexcloud.util.response.Error;
import com.nexcloud.util.response.Success;


@Service
public class PrometheusClient {
	@Value("${prometheus.endpoint}")	private String ENDPOINT;
	private Gson gson = new Gson();
	
	public String get(String query) {
		return this.request(query, "GET");
	}
	
	private String request(String query, String method) {
		URL url = null;
		HttpURLConnection con = null;
		try {
			String result;
			url = new URL(ENDPOINT + "/api/v1/query?query=" + URLEncoder.encode(query, "UTF-8"));
			
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod(method);
			System.out.println("[Prometheus Request]"
				+ "\n URL [" + url.toString() + "]"
				+ "\n Method["+ method +"]"
				+ "\n Query["+ ((query != null)?query:"null") +"]"
			);
			result = getResponseBody(con.getInputStream());
			System.out.println("[Prometheus response]"
				+ "\n Response [" + result + "]");
			return gson.toJson(new Success(con.getResponseCode(), 
					con.getResponseMessage(), "Prometheus", result));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return gson.toJson(
					new Error(20000, "Prometheus MalformedURLException", e.getMessage()));
		} catch (IOException e) {
			e.printStackTrace();
			return gson.toJson(
					new Error(20001, "Prometheus IOException", e.getMessage()));
		} finally {
			if (con != null) {
				con.disconnect();
				con = null;
			}
		}
	}

	private String getResponseBody(InputStream is) {
		String result = "";
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024 * 8];
		int length = 0;
		try {
			while ( (length = is.read(buf)) != -1) {
				out.write(buf, 0, length);
			}
			result = new String(out.toByteArray(), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
