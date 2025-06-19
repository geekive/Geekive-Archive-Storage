package com.geekive.geekiveArchiveStorage.controller;



import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.geekive.geekiveArchiveStorage.geekiveCustom.GeekiveFileUploader;
import com.geekive.geekiveArchiveStorage.geekiveCustom.GeekiveMap;
import com.geekive.geekiveArchiveStorage.mapper.service.FileService;

@Controller
@RequestMapping(value = "/upload")
public class FileController {
	
	@Resource
	FileService fileService;

    @PostMapping("/file")
    public ResponseEntity<Map<String, Object>> file(@RequestParam("file") MultipartFile multipartFile , @RequestParam("name") String name
    		, @RequestParam("registrationUser") String registrationUser) {
    	GeekiveMap gMap = null;
    	try {
    		GeekiveFileUploader gFileUploader = new GeekiveFileUploader(multipartFile);
            gFileUploader.setFolderName(name);
            gMap = gFileUploader.uploadFileInDatePath();

            gMap.put("registrationUser", registrationUser);
            fileService.insertFile(gMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(gMap);
    }
}
