package com.huaxin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.huaxin.entity.Step;

/**
* @author zhangyao
* @data May 18, 2019
*/
@Mapper
public interface StepDao {

	List searchStepList(Step step);

	void deleteStepByCaseId(Long id);

	void deleteStepById(Long id);

}

