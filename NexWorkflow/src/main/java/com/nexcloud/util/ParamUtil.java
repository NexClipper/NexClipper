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

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
public class ParamUtil  {
	/* ======================================================
	 * 속성 선언부
	 ====================================================== */

	/* ======================================================
	 * 메소드 구현부
	 ====================================================== */
	
	/**
	 * request의 parameter를 맵으로 반환.
	 * 배열형태의 파라미터는 반환하지 않음.
	 * 
	 * @param req
	 * @param result	null이면 새로 생성된 맵에 담아 반환.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final Map<String, String> getParameters(HttpServletRequest req, Map<String, String> result) {
		if (result == null)		result	= new HashMap<String, String>();
		
		Enumeration<String>	names	= req.getParameterNames();
		String				_name;
		while (names.hasMoreElements()) {
			_name	= names.nextElement();
			result.put(_name, getTrimedValue(req, _name));
		}
		
		return result;
	}
	
	/**
	 * 파라미터를 Integer type으로 형변환 하여 반환
	 * 
	 * @param req	HttpServletRequest
	 * @param name	파라미터명
	 * @return
	 */
	public static final Integer getParameterByInt(HttpServletRequest req, String name) {
		String	_value	= getTrimedValue(req, name);
		
		if ("".equals(_value))	return null;
		else					return Integer.parseInt(_value);
	}

	/**
	 * 앞뒤 공백을 제거한 파라미터를 반환.
	 * 
	 * @param req	HttpServletRequest
	 * @param name	파라미터명
	 * @return
	 */
	public static final String getTrimedValue(HttpServletRequest req, String name) {
		String	_value	= req.getParameter(name);
		return (_value == null ? "" : _value.trim());
	}
		
	public static final void setJsonContentType(HttpServletResponse res) {
		res.setContentType("application/x-json; charset=utf-8");
	}
	
	
	/* ======================================================
	 * getters, setters 선언부
	 ====================================================== */
}