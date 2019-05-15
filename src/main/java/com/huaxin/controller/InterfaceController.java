package com.huaxin.controller;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huaxin.entity.Case;
import com.huaxin.service.CaseService;
import com.huaxin.service.ModelService;
import com.huaxin.service.ProjectService;

/**
 * @author zhangyao
 * @data May 14, 2019
 */

@Controller
@RequestMapping("/interface")
public class InterfaceController {

	@Autowired
	private CaseService caseSercvice;
	@Autowired
	private ModelService modelService;
	@Autowired
	private ProjectService projectService;

	@RequestMapping("/testModel")
	public String toTestModel(Model model) {
		
		// 获取项目list
		List projectList = projectService.doGetProjectList();

		// 获取模块list
		List modelList = modelService.doGetModelList();

		model.addAttribute("projectList", projectList);
		model.addAttribute("modelList", modelList);
		return "/sys/testModel";
	}

	@RequestMapping("/testModelList")
	public HashMap toGetTestModelList() {

		HashMap map = new HashMap<>();

		map.put("code", 0);
		map.put("msg", "success");
		map.put("count", 1);
		map.put("data", null);
		return map;

	}

	// 获取用例列表
	@RequestMapping("/testCase")
	public String toTestCase(Model model) {

		// 获取项目list
		List projectList = projectService.doGetProjectList();

		// 获取模块list
		List modelList = modelService.doGetModelList();

		model.addAttribute("projectList", projectList);
		model.addAttribute("modelList", modelList);
		return "/sys/testCase";
	}

	
	@RequestMapping("/testCaseList")
	@ResponseBody
	public HashMap toGetTestCaseList(@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "limit", required = false) int limit) {

		// 分页设置
		PageHelper.startPage(page, limit);
		List<Case> caseList = caseSercvice.doGetCaseList();

		// 获取分页数据
		PageInfo<Case> pageInfo = new PageInfo<Case>(caseList);
		// PageInfo pageCaseList=CommonUtils.pageInfo(page, limit, caseList);
		HashMap map = new HashMap<>();
		map.put("code", 0);
		map.put("msg", "success");
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getList());
		return map;
	}

	@RequestMapping("/toAddCase")
	public String toAddCasePage(Model model) {

		// 获取项目list
		List projectList = projectService.doGetProjectList();

		// 获取模块list
		List modelList = modelService.doGetModelList();
		model.addAttribute("projectList", projectList);
		model.addAttribute("modelList", modelList);
		return "/sys/addCase";
	}

	// 增加用例
	@PostMapping("/addCase")
	@ResponseBody
	public HashMap toAddCase(@RequestBody(required = false) Case case1) {
		System.out.println(case1);
		if (case1.getStatus() != null) {
			case1.setStatus("True");
		} else {
			case1.setStatus("False");
		}
		HashMap map = new HashMap<>();
		if (caseSercvice.doCreateNewCase(case1) == 1) {
			map.put("result", true);
			map.put("msg", "创建用例成功！");
		} else {
			map.put("result", false);
			map.put("msg", "创建用例失败！");
		}

		return map;
	}

	// 删除用例
	@DeleteMapping("/deleteCase")
	@ResponseBody
	public HashMap deleteCase(@RequestParam(value = "ids", required = false) String ids) {

		String[] id = ids.split(",");
		HashMap map = new HashMap<>();
		try {
			for (int i = 0; i < id.length; i++) {
				System.out.println(id[i]);
				Long num = Long.valueOf(id[i]);
				caseSercvice.doDeleteCase(num);
			}
			map.put("result", true);
			map.put("msg", "删除用例成功！");
		} catch (Exception e) {
			map.put("result", false);
			map.put("msg", "删除用例失败！");
		}
		return map;
	}

	@RequestMapping("/toUpdateCase")
	public String toUpdateCasePage(@RequestParam(value = "id", required = false) Long id, Model model) {

		// 获取项目list
		List projectList = projectService.doGetProjectList();

		// 获取模块list
		List modelList = modelService.doGetModelList();

		Case case1 = caseSercvice.doGetCaseById(id);
		model.addAttribute("case", case1);
		model.addAttribute("projectList", projectList);
		model.addAttribute("modelList", modelList);
		return "/sys/updateCase";
	}

	// 编辑用例
	@PostMapping("/updateCase")
	@ResponseBody
	public HashMap toUpdateCase(@RequestBody(required = false) Case case1) {
		System.out.println(case1);
		if (case1.getStatus() != null) {
			case1.setStatus("True");
		} else {
			case1.setStatus("False");
		}
		HashMap map = new HashMap<>();
		if (caseSercvice.doUpdateCase(case1) == 1) {
			map.put("result", true);
			map.put("msg", "编辑用例成功！");
		} else {
			map.put("result", false);
			map.put("msg", "编辑用例失败！");
		}
		return map;
	}

	@RequestMapping("/test")
	@ResponseBody
	public List test() {
		System.out.println("ss");
		// 获取项目list
		// List list=projectService.doGetProjectList();
		// 获取模块list
		List list = modelService.doGetModelList();
		return list;
	}
}
