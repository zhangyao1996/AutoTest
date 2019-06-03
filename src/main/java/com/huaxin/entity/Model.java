package com.huaxin.entity;

import java.io.Serializable;

import lombok.Data;

/**
* @author zhangyao
* @data May 14, 2019
*/

public class Model implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String modelName;
	
	private Long projectId;
	
	private String modelDesc;
	
	private String status;
	
	private Long testId;
	
	private Long devId;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getModelDesc() {
		return modelDesc;
	}

	public void setModelDesc(String modelDesc) {
		this.modelDesc = modelDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
}

