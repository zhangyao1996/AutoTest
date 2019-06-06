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

	private Model model;
}
