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

	// 搜索modelLsit
	public List doSearchModelList(Model model) {
		return modelDao.searchModelList(model);
	}

	// 获取modelLsit
	public List doGetModelList() {
		return modelDao.getModelList();
	}

	// 增加新model
	public int doAddNewModel(Model model) {
		return modelDao.addNewModel(model);
	}

	// 获取model
	public HashMap doGetModelById(Long id) {
		return modelDao.getModelById(id);
	}

	// 编辑model
	public int doUpdateModel(Model model) {
		return modelDao.updateModel(model);
	}

	// 删除model
	public int doDeleteModel(Long id) {
		return modelDao.deleteModelById(id);
	}

	public Model doGetModelByModelName(String modelName) {
		return modelDao.getModelByModelName(modelName);
	}

	public void doDeleteModelByProId(Long id) {
		modelDao.deleteModelByProId(id);
	}

	public List doGetModelByProjectId(Long projectId) {
		return modelDao.getModelByProjectId(projectId);
	}

}
