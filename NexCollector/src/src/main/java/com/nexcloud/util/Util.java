package com.nexcloud.util;
/*
* Copyright 2019 NexCloud Co.,Ltd.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

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
	
	public static String nullToEmpty( String value )
	{
		if(value == null)
			return "";
		else
			return value;
	}
	
	public static String getToday( String dateformat)
	{
		Date date = new Date();
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		
		SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
		String toDate = sdf.format(cal.getTime());
		
		return toDate;
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
	 * 특정token으로 split하는 Method
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
	
	public static String replace (String originStr, String token, String replaceStr) {
	    String ret = "";
	    StringTokenizer st = null;
	    int len = 0;
		
		//value = (value==null?"":value.trim());
	    originStr = (originStr==null?" ":originStr);
		st = new StringTokenizer(originStr, token);
		len = st.countTokens();

		for(int i=0;i<len;i++)
		{
			ret += st.nextToken().trim()+replaceStr;
		}
	    return ret;
	}
	
	public static long addDate( 
			int addYear, int addMonth, int addDate,
			int addHour, int addMinute, int addSecond)
	{
		Date date = new Date();
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.YEAR, +addYear);
		cal.add(Calendar.MONTH, +addMonth);
		cal.add(Calendar.DATE, +addDate);
		cal.add(Calendar.HOUR_OF_DAY, +addHour);
		cal.add(Calendar.MINUTE, +addMinute);
		cal.add(Calendar.SECOND, +addSecond);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String toDate = sdf.format(cal.getTime());
		
		long exp = 0l;
		try {
			exp = sdf.parse( toDate ).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return exp;
	}
	
	public static String longToDateString( long longdate, String dateformat)
	{
		String sTodayTime 		= null;
		Date date				= new Date(longdate);
		SimpleDateFormat df   	= new SimpleDateFormat(dateformat);
		sTodayTime = df.format(date);
		
		return sTodayTime;
	}
	
	
	public static String getTime()
	{
		String sTodayTime = null;
		SimpleDateFormat df   = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sTodayTime = df.format(new java.util.Date());
		
		return sTodayTime;
	}
	
	
	public static Date getDate( String date, String date_format )
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(date_format);
	    try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Date();
		}
	}
	
	public static String randomJobName( ) 
	{   
        long currentTime = System.currentTimeMillis();   
        SimpleDateFormat simDf = new SimpleDateFormat("yyyyMMddHHmmss");   
        int randomNumber = (int)(Math.random()*100000);   
     
        String uniqueFileName = "" + randomNumber + simDf.format(new Date(currentTime));

        return uniqueFileName;
   }   
	
	
    
	/**
	 * Unix C printf
	 * @param format
	 * @param strings
	 * @return
	 */
	public static String printf(String format, String ...strings)
	{
		return String.format(format, strings);
	}
	
	
	/**
	 * IPv4 Validation Check
	 * @param ip
	 * @return
	 */
	public static boolean isValidInet4Address( String ip )
	{
		String[]groups			= ip.split("\\.");
		
		String IPv4_REGEX		= 
				"^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
				"(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
				"(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
				"(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
		
		Pattern IPv4_PATTERN	= Pattern.compile(IPv4_REGEX);
		
		if( groups.length != 4 )
			return false;
		
		try{
			/*return Arrays.stream(groups)
						.filter(s -> s.length() >1 && s.startsWith("0"))
						.map(Integer::parseInt )
						.filter( i -> ( i>= 0 && i <= 255 ))
						.count() == 4;*/
			Matcher matcher		= IPv4_PATTERN.matcher(ip);
			return matcher.matches();
		}catch(Exception e){
			return false;
		}
	}
	
	public static byte[] compress(String src) throws IOException {
		byte[] dataByte = src.getBytes();
		Deflater deflater = new Deflater();
		deflater.setLevel(Deflater.BEST_COMPRESSION);
		deflater.setInput(dataByte);
		deflater.finish();
		ByteArrayOutputStream bao = new ByteArrayOutputStream(dataByte.length);
		byte[] buf = new byte[1024];
		while(!deflater.finished()) {
			int compByte = deflater.deflate(buf);
			bao.write(buf, 0, compByte);
		}
		bao.close();
		deflater.end();
		return bao.toByteArray();
	}
	
	public static byte[] decompress(byte[] data) {
 		Inflater inflater = new Inflater();
		inflater.setInput(data);

		ByteArrayOutputStream bao = new ByteArrayOutputStream();

		try{
			byte[] buf = new byte[1024];
	
			while(!inflater.finished()) {
	
				int compByte = inflater.inflate(buf);			
	
				bao.write(buf, 0, compByte);

			}
			
			bao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		inflater.end();
		return bao.toByteArray();
	}
	
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	
	public static String byteArrayToHexString(byte[] bytes){ 
		
		StringBuilder sb = new StringBuilder(); 
		
		for(byte b : bytes){ 
			
			sb.append(String.format("%02X", b&0xff)); 
		} 
		
		return sb.toString(); 
	} 
}
