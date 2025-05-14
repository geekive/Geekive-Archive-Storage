package com.geekily.geekilyArchiveStorage.geekilyCustom;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.geekily.geekilyArchiveStorage.common.Util;



public class GeekilyMap extends HashMap<String, Object>{
	
	public GeekilyMap() {
		putInitialData();
	}
	
	private void putInitialData() {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
		GeekilyMap gMap 	= (GeekilyMap) session.getAttribute("userMap");
		String userId		= Util.isNotEmpty(gMap) ? gMap.getString("userUid") : "";
		this.put("userUid"		, userId);
		this.put("resultCode"	, 1);
	}

	public String getString(Object key) {
		return (String) super.get(key);
	}
	
	public int getInt(Object key) {
		return (int) super.get(key);
	}
	
	public String[] getStringArray(Object key) {
		String[] dataArray = null;
		try {
			String data = super.get(key).toString();
	        dataArray 	= data.replaceAll("[\\[\\]\\s]", "").split(",");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataArray;
	}
	
	public MultipartFile[] getMultipartArray(Object key) {
		return (MultipartFile[]) super.get(key);
	}
	
}
