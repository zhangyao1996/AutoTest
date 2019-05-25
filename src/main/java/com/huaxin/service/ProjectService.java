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
	
	public List doSearchProjectList(Project project) {
		return projectDao.searchProjectList(project);
	}

	public Project doGetProjectByName(String projectName) {
		return projectDao.getProjectByName(projectName);
	}

	public int doAddNewProject(Project project) {
		return projectDao.addNewProject(project);
	}

	public Project doGetProjectById(Long id) {
		return projectDao.getProjectById(id);
	}

	public int doUpdateProject(Project project) {
		return projectDao.updateProject(project);
	}

	public void doDeleteProById(Long id) {
		projectDao.deleteProById(id);
	}
}

