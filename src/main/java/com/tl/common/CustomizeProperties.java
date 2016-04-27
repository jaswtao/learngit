package com.tl.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.tl.exceptions.BaseException;

public class CustomizeProperties {

	public static Properties initProperties(String path) throws BaseException {
		if (StringUtils.isBlank(path)) {
			throw new BaseException("path is empty!");
		}
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(path)));
		} catch (IOException e) {
			throw new BaseException(e.getMessage());
		}
		return properties;
	}
}
