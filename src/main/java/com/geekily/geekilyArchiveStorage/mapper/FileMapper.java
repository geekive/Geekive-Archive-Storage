package com.geekily.geekilyArchiveStorage.mapper;

import java.util.List;


import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.geekily.geekilyArchiveStorage.geekilyCustom.GeekilyMap;

@Mapper
public interface FileMapper {
	public int insertFile(GeekilyMap gMap);
}
