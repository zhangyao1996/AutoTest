package com.huaxin.entity;

import java.io.Serializable;

import lombok.Data;


/**
 * @author zhangyao
 * @data May 14, 2019
 */

@Data
public class Case implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String caseName;

	private Long projectId;

	private Long modelId;

	private String api;

	private String version;

	private String caseDesc;

	private String status;

	private Project project;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getModelId() {
		return modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCaseDesc() {
		return caseDesc;
	}

	public void setCaseDesc(String caseDesc) {
		this.caseDesc = caseDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	//增加该方法，方便layui table field获取值
	public String getProjectName() {
		return project.getProjectName();
	}
	
	private Model model;
	
	public String getModelName() {
		return model.getModelName();
	}
		

}
