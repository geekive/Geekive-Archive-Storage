package com.geekive.geekiveArchiveStorage.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.geekive.geekiveArchiveStorage.geekiveCustom.GeekiveMap;

@Mapper
public interface FileMapper {
	public int insertFile(GeekiveMap gMap);
}
