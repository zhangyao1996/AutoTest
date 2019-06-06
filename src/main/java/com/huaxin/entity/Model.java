package com.huaxin.entity;

import java.io.Serializable;

import lombok.Data;

/**
* @author zhangyao
* @data May 14, 2019
*/
@Data
public class Model implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String modelName;
	
	private Long projectId;
	
	private String modelDesc;
	
	private String status;
	
	private Long testId;
	
	private Long devId;

}

