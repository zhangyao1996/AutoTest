package com.huaxin.entity;

import java.io.Serializable;

import lombok.Data;

/**
* @author zhangyao
* @data May 14, 2019
*/

@Data
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String userName;
	
	private String password;
	
	private String salt;
	
	private String identity;
	
	private String cnName;
	
}

