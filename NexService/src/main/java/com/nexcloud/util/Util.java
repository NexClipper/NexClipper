package com.nexcloud.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

import com.google.gson.Gson;


public class Util {
	
	public static String makeStackTrace(Throwable t){
		if(t == null) return "";
		try{
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			t.printStackTrace(new PrintStream(bout));
			bout.flush();
			String error = new String(bout.toByteArray());
	
			return error.replace("\r\n", " ");
		}catch(Exception ex){
		return "";
		}
	}

	
	/**
	 * 
	 * @param value
	 * @param token
	 * @return String[]
	 */
	public static String [] split (String value, String token) {
	    String[] ret = null;
	    StringTokenizer st = null;
	    int len = 0;
		
		//value = (value==null?"":value.trim());
	    value = (value==null?" ":value);
		st = new StringTokenizer(value, token);
		len = st.countTokens();
		ret = new String[len];
		
		for(int i=0;i<len;i++)
			ret[i] = st.nextToken().trim();	
	    return ret;
	}
	/**
	 * Bean to Json String Convert
	 * 
	 * @param src
	 * @return
	 */
	public static String beanToJson(Object src)
	{
		return new Gson().toJson(src); 
	}
	
	
	public static synchronized String getUUID()
	{
		return UUID.randomUUID().toString();
	}
	
	/**
	 * Json String to Bean  Convert
	 * 
	 * @param src
	 * @return
	 */
	public static <T> T JsonTobean( String json, Class<T> classOfT )
	{
		if( classOfT == null )
			return null;
		
		return new Gson().fromJson(json, classOfT );
	}
	
	/**
	 *  Asc sort
	 */
	public static List<String> asc( List<String> data )
	{
		Collections.sort(data, new Comparator<String>(){
		      public int compare(String obj1, String obj2)
		      {
		            // TODO Auto-generated method stub

		            return obj1.compareToIgnoreCase(obj2);
		      }
		});
		
		return data;
	}
}
