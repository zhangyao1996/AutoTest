package com.huaxin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huaxin.dao.ProjectDao;
import com.huaxin.entity.Project;

/**
* @author zhangyao
* @data May 15, 2019
*/
@Service
public class ProjectService {

	@Autowired
	private ProjectDao projectDao;
	
	public List<Project> doGetProjectList(){
		return projectDao.getProjectList();
	}
}

