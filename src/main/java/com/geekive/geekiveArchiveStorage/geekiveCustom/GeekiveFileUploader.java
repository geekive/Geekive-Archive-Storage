package com.geekive.geekiveArchiveStorage.geekiveCustom;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.geekive.geekiveArchiveStorage.common.Util;

public class GeekiveFileUploader {
	
	private MultipartFile 	multipartFile;
	private String			folderName;
	private String			fixedPath;
	private String			dynamicPath;
	
	public GeekiveFileUploader(MultipartFile multipartFile) {
		super();
		this.multipartFile 	= multipartFile;
		this.fixedPath		= Util.getUploadPath();
	}
	
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	
	public GeekiveMap uploadFile() {
		this.dynamicPath = (this.folderName != null) ? (this.folderName + File.separator) : "";
		return upload();
	}

	public GeekiveMap uploadFileInDatePath() {
		this.dynamicPath = (this.folderName != null) ? (this.folderName + File.separator + Util.getDatePath()) : "";
		return upload();
	}
	
	private GeekiveMap upload() {
		GeekiveMap gMap		= new GeekiveMap();
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
