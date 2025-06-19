package com.geekive.geekiveArchiveStorage.mapper.service;

import java.util.List;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekive.geekiveArchiveStorage.geekiveCustom.GeekiveMap;
import com.geekive.geekiveArchiveStorage.mapper.FileMapper;

@Service
public class FileService implements FileMapper{
	
	@Autowired
	FileMapper fileMapper;

	@Override
	public int insertFile(GeekiveMap gMap) {
		return fileMapper.insertFile(gMap);
	}
	
	
}
