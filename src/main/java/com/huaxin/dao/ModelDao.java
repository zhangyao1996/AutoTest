package com.huaxin.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.huaxin.entity.Model;

/**
* @author zhangyao
* @data May 15, 2019
*/
@Mapper
public interface ModelDao {
	
	//获取模块List
	public List<HashMap> getModelList();

}

