package com.huaxin.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huaxin.dao.CaseDao;
import com.huaxin.entity.Case;

/**
* @author zhangyao
* @data May 15, 2019
*/
@Service
public class CaseService {
	
	@Autowired
	private CaseDao caseDao;

	public List<Case> doGetCaseList() {
		return caseDao.getCaseList();
	}
	
	public int doCreateNewCase(Case case1) {
		return caseDao.createNewCase(case1);
	}
	
	public int doDeleteCase(Long id) {
		return caseDao.deleteCase(id);
	}
	
	public Case doGetCaseById(Long id) {
		return caseDao.getCaseById(id);
	}
	
	public int doUpdateCase(Case case1) {
		return caseDao.updateCase(case1);
	}

	public int doDeleteCaseByModelId(Long id) {
		return caseDao.deleteCaseByModelId(id);
	}

	public List<Case> doSearchCaseList(Case case1) {
		return caseDao.searchCaseList(case1);
	}

	public void doDeleteCaseByProId(Long id) {
		caseDao.deleteCaseByProId(id);
	}

	public Case doGetCaseByCaseName(String caseName) {
		return caseDao.getCaseByCaseName(caseName);
	}
}

