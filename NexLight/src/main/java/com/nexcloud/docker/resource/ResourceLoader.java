package com.nexcloud.docker.resource;

import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceLoader{	
	static final Logger logger = LoggerFactory.getLogger(ResourceLoader.class);
	
	private static Hashtable<String, Object> resourceMap = null;
	private static ResourceLoader thisObj = null;

	/**
	 *	Reload when config information changes	
	 */
	public static void getReload(){
		if(resourceMap != null)
			resourceMap.clear();
		init();
	}
	

	public synchronized static ResourceLoader getInstance(){
		if ( thisObj == null ){
			try {
				thisObj = new ResourceLoader();
				if(resourceMap == null)
					ResourceLoader.init();
			}catch(IndexOutOfBoundsException ie){
				logger.error("ResourceLoader Class getInstance IndexOutOfBoundsException Error = " +ie);
			}catch(NullPointerException ne){
				logger.error("ResourceLoader Class getInstance NullPointerException Error = " +ne);
			} catch(Exception e) {
				logger.error("ResourceLoader Class getInstance Exception Error = " + e);
			}	
		}	
		
		return thisObj;
	}
	
	/**
	 *	init resource data
	 */
	public static void init()
	{
		try{
			resourceMap	= null;
			resourceMap = new Hashtable<String, Object>();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Set resource data
	 */
	public void setResource(String key, Object object)
	{
		resourceMap.put(key, object);
	}
	
	/**
	 * Get resource data
	 */
	public Object getResource(String key)
	{
		return resourceMap.get(key);
	}
	
	/**
	 * Remove resource data
	 */
	public void removeResource(String key)
	{
		resourceMap.remove(key);
	}
}
