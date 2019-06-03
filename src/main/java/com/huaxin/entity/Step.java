package com.huaxin.entity;

import lombok.Data;

/**
* @author zhangyao
* @data May 18, 2019
*/
@Data
public class Step {

	private Long id;
	
	private String stepName;
	
	private Long caseId;
	
	private String reqMode;
	
	private String header;
	
	private String param;

	private String ast;
	
	private String stepDesc;
	
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public String getReqMode() {
		return reqMode;
	}

	public void setReqMode(String reqMode) {
		this.reqMode = reqMode;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getAst() {
		return ast;
	}

	public void setAst(String ast) {
		this.ast = ast;
	}

	public String getStepDesc() {
		return stepDesc;
	}

	public void setStepDesc(String stepDesc) {
		this.stepDesc = stepDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

