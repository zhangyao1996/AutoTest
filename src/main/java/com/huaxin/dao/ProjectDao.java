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

	public List searchProjectList(Project project);

	public Project getProjectByName(String projectName);

	public int addNewProject(Project project);

	public Project getProjectById(Long id);

	public int updateProject(Project project);

	public void deleteProById(Long id);
}

