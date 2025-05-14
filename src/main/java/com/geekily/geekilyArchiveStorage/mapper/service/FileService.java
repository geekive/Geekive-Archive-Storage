package com.geekily.geekilyArchiveStorage.mapper.service;

import java.util.List;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekily.geekilyArchiveStorage.geekilyCustom.GeekilyMap;
import com.geekily.geekilyArchiveStorage.mapper.FileMapper;

@Service
public class FileService implements FileMapper{
	
	@Autowired
	FileMapper fileMapper;

	@Override
	public int insertFile(GeekilyMap gMap) {
		return fileMapper.insertFile(gMap);
	}
	
	
}
