package com.huaxin.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huaxin.dao.ModelDao;
import com.huaxin.entity.Model;

/**
* @author zhangyao
* @data May 15, 2019
*/
@Service
public class ModelService {
	
	@Autowired
	private ModelDao modelDao;
	
	public List<HashMap> doGetModelList(){
		return modelDao.getModelList();
	}
	

}

