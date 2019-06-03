package com.huaxin.entity;

import java.io.Serializable;

import lombok.Data;

/**
* @author zhangyao
* @data May 14, 2019
*/
@Data
public class Project implements Serializable{
	private static final long serialVersionUID = 1L;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public String getTestEnv() {
		return testEnv;
	}

	public void setTestEnv(String testEnv) {
		this.testEnv = testEnv;
	}

	public String getDevEnv() {
		return devEnv;
	}

	public void setDevEnv(String devEnv) {
		this.devEnv = devEnv;
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	private Long id;
	
	private String projectName;
	
	private String projectDesc;

	private String testEnv;

	private String devEnv;
	
//	private String status;

	private Long testId;

	private Long devId;
}

