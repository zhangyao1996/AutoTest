package com.huaxin.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huaxin.dao.UserDao;
import com.huaxin.entity.User;

/**
* @author zhangyao
* @data May 16, 2019
*/
@Service
public class UserService {
	
	
	@Autowired
	private UserDao userDao;
	
	public List<HashMap> doGetUserList(String identity){
		return userDao.getUserList(identity);
	}

	public User doGetUserByUserName(String username) {
		return userDao.getUserByUserName(username);
	}

}

