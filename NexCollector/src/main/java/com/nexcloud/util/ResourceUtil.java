/*
 * Copyright (c) Abacus. All rights reserved.
 */
package com.nexcloud.util;

import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 *
 * @author 
 * @since
 *
 */
@Resource
public class ResourceUtil {

	private final static Logger logger = LoggerFactory.getLogger(ResourceUtil.class);

	@Autowired
	static ReloadableResourceBundleMessageSource messageSource;

	public ResourceUtil(ReloadableResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public static String getMessage(String messageResourceId) {
		return ResourceUtil.getMessage(messageResourceId, null, null);
	}

	public static String getMessage(String messageResourceId,
			String[] messageParam) {
		return ResourceUtil.getMessage(messageResourceId, null, messageParam);
	}

	public static String getMessage(String messageResourceId, String language) {
		return ResourceUtil.getMessage(messageResourceId, language, null);
	}

	public static String getMessage(String messageResourceId, String language,
			String[] messageParam) {

		String message = "";
		try {
			Locale locale = LocaleContextHolder.getLocale();
			if (language != null) {
				locale = new Locale(language);
			}

			message = messageSource.getMessage(messageResourceId, null, locale);

		} catch (Exception e) {
			logger.error("MessageResourceId[" + messageResourceId
					+ "] is not defined!", e);
		}
		if (org.apache.commons.lang.StringUtils.isNotEmpty(message)) {
			if (messageParam != null && messageParam.length > 0) {
				for (int i = 0; i < messageParam.length; i++) {
					message = message.replace("%" + i, messageParam[i]);
				}
			}
		}
		return message;
	}

	public static void clearCache() {
		messageSource.clearCache();
	}
}
