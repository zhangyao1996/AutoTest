package com.huaxin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huaxin.dao.StepDao;
import com.huaxin.entity.Step;

/**
* @author zhangyao
* @data May 18, 2019
*/
@Service
public class StepService {

	@Autowired
	private StepDao stepDao;
	public List doSearchStepList(Step step) {
		return stepDao.searchStepList(step);
	}
	public void doDeleteStepByCaseId(Long id) {
		stepDao.deleteStepByCaseId(id);
	}
	public void doDeleteStepById(Long id) {
		stepDao.deleteStepById(id);
	}

	
	

}

