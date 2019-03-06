package com.nexcloud.util;

import java.net.URL;

public class PropertyUtil {
	public static String getFilePath(String fileName)
	{
		String value = null;
		ClassLoader cl = null;
		
		cl = Thread.currentThread().getContextClassLoader();
		
		if(cl == null)
			cl = ClassLoader.getSystemClassLoader();
		
		URL url = cl.getResource(fileName);
		
		return url.getPath().toString();
	}
}
