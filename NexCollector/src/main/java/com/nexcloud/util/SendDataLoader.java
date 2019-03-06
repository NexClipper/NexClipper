package com.nexcloud.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nexcloud.api.akka.actor.DataSender;

public class SendDataLoader{	
	static final Logger logger = LoggerFactory.getLogger(DataSender.class);
	
	private static List<String> list;
	private static SendDataLoader thisObj = null;

	public synchronized static SendDataLoader getInstance(){
		if ( thisObj == null ){
			//System.out.println("ConfigLoader getInstance 실행!!!!!!!!!!!!!!!");
			try {
				thisObj = new SendDataLoader();
				if(list == null)
					SendDataLoader.init();
			}catch(IndexOutOfBoundsException ie){
				System.out.println("SendDataLoader Class getInstance IndexOutOfBoundsException Error = " +ie);
			}catch(NullPointerException ne){
				System.out.println("SendDataLoader Class getInstance NullPointerException Error = " +ne);
			} catch(Exception e) {
				System.out.println("SendDataLoader Class getInstance Exception Error = " + e);
			}	
		}	
		
		return thisObj;
	}
	
	/**
	 *	config파일을 로딩함.
	 *
	 *	@param		
	 *	@see		
	 *	@exception	IndexOutOfBoundsException - 문제발생시
	 *	@exception	NullPointerException - 문제발생시
	 */
	public static void init()
	{
		try{
			//System.out.println("ConfigLoader init 실행!!!!!!!!!!!!!!!");
			list	= null;
			list = new ArrayList<String>();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Resource Data Set
	 * @param key
	 * @param object
	 */
	public synchronized void set(String data)
	{
		try{
			list.add(data);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Resource Data Get
	 * @param key
	 * @return
	 */
	public synchronized String get( )
	{
		try{
			if( list.size() > 0)
			{
				return list.remove(0);
			}
			else
				return "";
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
}
