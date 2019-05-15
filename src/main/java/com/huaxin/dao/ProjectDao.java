package com.huaxin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.huaxin.entity.Project;

/**
* @author zhangyao
* @data May 15, 2019
*/
@Mapper
public interface ProjectDao {

	//获取项目list
	public List<Project> getProjectList();
}

