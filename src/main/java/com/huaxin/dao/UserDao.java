package com.huaxin.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.huaxin.entity.User;

/**
* @author zhangyao
* @data May 16, 2019
*/
@Mapper
public interface UserDao {
	
	//获取开发与测试人员List  idengtity："0"测试，"1"开发
	public List<HashMap> getUserList(String identity);

	public User getUserByUserName(String username);
	
}

