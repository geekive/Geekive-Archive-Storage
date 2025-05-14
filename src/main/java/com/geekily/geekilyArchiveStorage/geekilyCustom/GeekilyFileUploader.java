package com.geekily.geekilyArchiveStorage.geekilyCustom;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.geekily.geekilyArchiveStorage.common.Util;

public class GeekilyFileUploader {
	
	private MultipartFile 	multipartFile;
	private String			folderName;
	private String			fixedPath;
	private String			dynamicPath;
	
	public GeekilyFileUploader(MultipartFile multipartFile) {
		super();
		this.multipartFile 	= multipartFile;
		this.fixedPath		= Util.getUploadPath();
	}
	
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	
	public GeekilyMap uploadFile() {
		this.dynamicPath = (this.folderName != null) ? (this.folderName + File.separator) : "";
		return upload();
	}

	public GeekilyMap uploadFileInDatePath() {
		this.dynamicPath = (this.folderName != null) ? (this.folderName + File.separator + Util.getDatePath()) : "";
		return upload();
	}
	
	private GeekilyMap upload() {
		GeekilyMap gMap		= new GeekilyMap();
    	String originalName = multipartFile.getOriginalFilename();
    	String storedName	= Util.getFileName(originalName);
    	String fullPath		= this.fixedPath + this.dynamicPath + storedName;
        try {
        	 File file = new File(fullPath);
        	 if(file.mkdirs()) {
        		 multipartFile.transferTo(file);
        		 gMap.put("originalName"	, originalName);
        		 gMap.put("storedName"		, storedName);
        		 gMap.put("size"			, this.multipartFile.getSize());
        		 gMap.put("absolutePath"	, this.dynamicPath + storedName);
        		 gMap.put("urlPath"			, Util.getFileAccessURL() + gMap.getString("absolutePath"));
        		 gMap.put("extension"		, Util.getExtensionWithoutDot(originalName));
        		 gMap.put("fileUid"			, Util.generateUID("FLE"));
        	 }
		} catch (Exception e) {
			e.printStackTrace();
		}
        return gMap;
	}
}
