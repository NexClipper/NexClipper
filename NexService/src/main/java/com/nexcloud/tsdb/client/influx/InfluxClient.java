package com.nexcloud.tsdb.client.influx;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.nexcloud.util.consts.HTTP;
import com.nexcloud.util.response.Error;
import com.nexcloud.util.response.Success;


@Service
public class InfluxClient {
	@Value("${influxdb.endpoint}")	private String ENDPOINT;
	@Value("${influxdb.datasource}") private String DATASOURCE;
	private Gson gson = new Gson();
	static final Logger logger = LoggerFactory.getLogger(InfluxClient.class);
	
	public String get(String query) {
		return this.request(query, "GET");
	}
	
	private String request(String query, String method) {
		URL url = null;
		HttpURLConnection con = null;
		try {
			String result;
			url = new URL(ENDPOINT + "/query?db=" + DATASOURCE 
					+ "&q=" + URLEncoder.encode(query, "UTF-8"));
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod(method);
			logger.debug("[Influx Request]"
				+ "\n URL [" + url.toString() + "]"
				+ "\n Method["+ method +"]"
				+ "\n Query["+ ((query != null)?query:"null") +"]"
			);
			result = getResponseBody(con.getInputStream());
			logger.debug("[Influx response]"
				+ "\n Response [" + result + "]");
			return gson.toJson(new Success(con.getResponseCode(), 
					con.getResponseMessage(), "Influx",
					gson.toJson(gson.fromJson(result, InfluxResult.class).getTables())));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return gson.toJson(
					new Error(HTTP.CODE.MALFORMED_URL_EXCEPTION, 
							HTTP.MESSAGE.MALFORMED_URL_EXCEPTION, e.getMessage()));
		} catch (IOException e) {
			e.printStackTrace();
			return gson.toJson(
					new Error(HTTP.CODE.IO_EXCEPTION, 
							HTTP.MESSAGE.IO_EXCEPTION, e.getMessage()));
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
