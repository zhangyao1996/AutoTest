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

	private Long id;
	
	private String projectName;
	
	private String projectDesc;
	
	private String status;
	
	private Long testId;
	
	private Long devId;
}

