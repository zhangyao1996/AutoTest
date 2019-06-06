package com.huaxin.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.huaxin.entity.Model;

/**
* @author zhangyao
* @data May 15, 2019
*/
@Mapper
public interface ModelDao {
	
	
	public List searchModelList(Model model);
	//获取模块List
	public List getModelList();

	public int addNewModel(Model model);

	public HashMap<String, Object> getModelById(Long id);

	public int updateModel(Model model);

	public int deleteModelById(Long id);
	
	public Model getModelByModelName(String modelName);
	
	public void deleteModelByProId(Long id);
	
	public List getModelByProjectId(Long projectId);

}

