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
}

