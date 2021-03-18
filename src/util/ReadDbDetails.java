package com.cg.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ReadDbDetails {
	Properties props;
	String propFile = "db.properties";
	public ReadDbDetails() {
	}
	public ReadDbDetails(String propFile) {
		this.propFile = propFile;
	}
	public Map<String, String> getDbProp (){
		Map<String, String> map = new HashMap<>();
		props = new Properties();
		try {
			props.load(new FileInputStream(propFile));
			Enumeration en = props.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				map.put(key, props.getProperty(key));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return map;
	}
	
}
