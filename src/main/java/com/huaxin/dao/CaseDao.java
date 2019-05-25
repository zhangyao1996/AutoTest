package com.huaxin.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.huaxin.entity.Case;

/**
 * @author zhangyao
 * @data May 15, 2019
 */
@Mapper
public interface CaseDao {

	// 获取caseList
	public List<Case> getCaseList();
	
	//创建case
	public int createNewCase(Case case1);
	
	//删除
	public int deleteCase(Long id);
	
	//获取一条case
	public Case getCaseById(Long id);
	
	//编辑
	public int updateCase(Case case1);

	public int deleteCaseByModelId(Long id);

	public List<Case> searchCaseList(Case case1);

	public void deleteCaseByProId(Long id);

	public Case getCaseByCaseName(String caseName);
	
}
