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
