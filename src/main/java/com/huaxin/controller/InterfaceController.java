package com.huaxin.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import com.huaxin.entity.Project;
import com.huaxin.entity.Step;
import com.huaxin.service.CaseService;
import com.huaxin.service.ModelService;
import com.huaxin.service.ProjectService;
import com.huaxin.service.StepService;
import com.huaxin.service.UserService;

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
	@Autowired
	private UserService userService;
	@Autowired
	private StepService stepService;

	// 获取测试模块页面
	@RequestMapping("/testModel")
	public String toTestModel(Model model) {

		// 获取项目list
		List projectList = projectService.doGetProjectList();

		// 获取模块list
		List modelList = modelService.doGetModelList();

		// 获取项目list

		// 获取开发人员list
		List devList = userService.doGetUserList("1");
		// 获取测试人员list
		List testList = userService.doGetUserList("0");
		model.addAttribute("devList", devList);
		model.addAttribute("testList", testList);
		model.addAttribute("projectList", projectList);
		model.addAttribute("modelList", modelList);
		return "/sys/testModel";
	}

	// 获取测试模块table
	@RequestMapping("/testModelList")
	@ResponseBody
	public HashMap toGetTestModelList(@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "limit", required = false) int limit,
			@RequestParam(value = "modelName", required = false) String modelName,
			@RequestParam(value = "projectId", required = false) Long projectId,
			@RequestParam(value = "testId", required = false) Long testId,
			@RequestParam(value = "devId", required = false) Long devId) {

		com.huaxin.entity.Model model = new com.huaxin.entity.Model();
		model.setModelName(modelName);
		model.setProjectId(projectId);
		model.setTestId(testId);
		model.setDevId(devId);
		// 分页设置
		PageHelper.startPage(page, limit);

		List modelList = modelService.doSearchModelList(model);

		// 获取分页数据
		PageInfo<HashMap> pageInfo = new PageInfo<HashMap>(modelList);
		HashMap map = new HashMap<>();

		map.put("code", 0);
		map.put("msg", "success");
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getList());
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

	// 获取测试用例table
	@RequestMapping("/testCaseList")
	@ResponseBody
	public HashMap toGetTestCaseList(@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "limit", required = false) int limit,
			@RequestParam(value = "caseName", required = false) String caseName,
			@RequestParam(value = "projectId", required = false) Long projectId,
			@RequestParam(value = "modelId", required = false) Long modelId,
			@RequestParam(value = "api", required = false) String api,
			@RequestParam(value = "version", required = false) String version) {

		Case case1 = new Case();
		case1.setCaseName(caseName);
		case1.setProjectId(projectId);
		case1.setModelId(modelId);
		case1.setApi(api);
		case1.setVersion(version);
		case1.setModel(new com.huaxin.entity.Model());
		case1.setProject(new Project());

		// 分页设置
		PageHelper.startPage(page, limit);
		List<Case> caseList = caseSercvice.doSearchCaseList(case1);

		// 获取分页数据
		PageInfo<Case> pageInfo = new PageInfo<Case>(caseList);
		HashMap map = new HashMap<>();
		map.put("code", 0);
		map.put("msg", "success");
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getList());
		return map;
	}

	// 获取增加用例页面
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

	// 获取增加用例页面
	@RequestMapping("/toGetModelListByProId")
	@ResponseBody
	public List toGetModelListByProjectId(@RequestParam(value = "projectId", required = false) Long projectId) {
		HashMap map=new HashMap<>();
		List modelList=modelService.doGetModelByProjectId(projectId);
		map.put("modelList", modelList);
		return modelList;
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
		Case oldCase = caseSercvice.doGetCaseByCaseName(case1.getCaseName());

		HashMap map = new HashMap<>();
		Long modelId = case1.getModelId();
		Long proId = case1.getProjectId();
		HashMap model = modelService.doGetModelById(modelId);

		if (oldCase != null) {
			map.put("result", false);
			map.put("msg", "该用例名已存在,创建用例失败！");
			return map;
		}

		if (model.get("projectId").equals(proId) == false) {
			map.put("result", false);
			map.put("msg", "该项目下无此模块,创建用例失败！");
			return map;
		}

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
	@Transactional
	public HashMap deleteCase(@RequestParam(value = "ids", required = false) String ids) {

		String[] id = ids.split(",");
		HashMap map = new HashMap<>();
		try {
			for (int i = 0; i < id.length; i++) {
				System.out.println(id[i]);
				Long num = Long.valueOf(id[i]);
				caseSercvice.doDeleteCase(num);
				stepService.doDeleteStepByCaseId(num);
			}
			map.put("result", true);
			map.put("msg", "删除用例成功！");
		} catch (Exception e) {
			map.put("result", false);
			map.put("msg", "删除用例失败！");
		}
		return map;
	}

	// 获取编辑用例页面
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

		Long modelId = case1.getModelId();
		Long proId = case1.getProjectId();
		HashMap model = modelService.doGetModelById(modelId);

		Case oldCase = caseSercvice.doGetCaseByCaseName(case1.getCaseName());
		if (oldCase != null && (oldCase.getId() != case1.getId())) {
			map.put("result", false);
			map.put("msg", "该用例名已存在,编辑用例失败！");
			return map;
		}

		if (model.get("projectId").equals(proId) == false) {
			map.put("result", false);
			map.put("msg", "该项目下无此模块,创建用例失败！");
			return map;
		}

		if (caseSercvice.doUpdateCase(case1) == 1) {
			map.put("result", true);
			map.put("msg", "编辑用例成功！");
		} else {
			map.put("result", false);
			map.put("msg", "编辑用例失败！");
		}
		return map;
	}

//	@RequestMapping("/test")
//	@ResponseBody
//	public List test() {
//		System.out.println("ss");
//		// 获取项目list
//		// List list=projectService.doGetProjectList();
//		// 获取模块list
//		List list = modelService.doGetModelList();
//		return list;
//	}

	// 获取增加模块页面
	@RequestMapping("/toAddModel")
	public String toAddModelPage(Model model) {

		// 获取项目list
		List projectList = projectService.doGetProjectList();

		// 获取开发人员list
		List devList = userService.doGetUserList("1");
		// 获取测试人员list
		List testList = userService.doGetUserList("0");
		model.addAttribute("projectList", projectList);
		model.addAttribute("devList", devList);
		model.addAttribute("testList", testList);
		return "/sys/addModel";
	}

	// 增加模块
	@RequestMapping("/addModel")
	@ResponseBody
	public HashMap toAddModel(@RequestBody(required = false) com.huaxin.entity.Model model) {

		HashMap map = new HashMap<>();
		if (model.getStatus() != null) {
			model.setStatus("True");
		} else {
			model.setStatus("False");
		}
		if (modelService.doGetModelByModelName(model.getModelName()) != null) {
			map.put("result", false);
			map.put("msg", "模块名已存在！");
			return map;
		}

		if (modelService.doAddNewModel(model) == 1) {
			map.put("result", true);
			map.put("msg", "创建模块成功！");
		} else {
			map.put("result", false);
			map.put("msg", "创建模块失败！");
		}
		return map;
	}

	// 获取编辑模块页面
	@RequestMapping("/toUpdateModel")
	public String toUpdateModelPage(@RequestParam(value = "id", required = false) Long id, Model model) {

		// 获取model
		HashMap<String, Object> model2 = modelService.doGetModelById(id);
		com.huaxin.entity.Model model3 = new com.huaxin.entity.Model();
		model3.setDevId((Long) model2.get("devId"));
		model3.setProjectId((Long) model2.get("projectId"));
		model3.setTestId((Long) model2.get("testId"));
		model3.setId((Long) model2.get("id"));
		model3.setModelName((String) model2.get("modelName"));
		model3.setModelDesc((String) model2.get("modelDesc"));

		// 获取项目list
		List projectList = projectService.doGetProjectList();

		// 获取开发人员list
		List devList = userService.doGetUserList("1");
		// 获取测试人员list
		List testList = userService.doGetUserList("0");
		model.addAttribute("projectList", projectList);
		model.addAttribute("devList", devList);
		model.addAttribute("testList", testList);
		model.addAttribute("model", model3);
		return "/sys/updateModel";
	}

	// 编辑模块
	@PostMapping("/updateModel")
	@ResponseBody
	public HashMap toUpdateModel(@RequestBody(required = false) com.huaxin.entity.Model model) {
		if (model.getStatus() != null) {
			model.setStatus("True");
		} else {
			model.setStatus("False");
		}
		HashMap map = new HashMap<>();
		HashMap oldModel = modelService.doGetModelById(model.getId());
		com.huaxin.entity.Model newModel = modelService.doGetModelByModelName(model.getModelName());

		if (modelService.doGetModelByModelName(model.getModelName()) != null
				&& (oldModel.get("modelName").equals(newModel.getModelName()) == false)) {
			map.put("result", false);
			map.put("msg", "模块名已存在！");
			return map;
		}

		if (modelService.doUpdateModel(model) == 1) {
			map.put("result", true);
			map.put("msg", "编辑用例成功！");
		} else {
			map.put("result", false);
			map.put("msg", "编辑用例失败！");
		}
		return map;
	}

	// 删除模块
	@DeleteMapping("/deleteModel")
	@ResponseBody
	@Transactional
	public HashMap deleteModel(@RequestParam(value = "ids", required = false) String ids) {

		String[] id = ids.split(",");
		HashMap map = new HashMap<>();
		try {
			for (int i = 0; i < id.length; i++) {
				System.out.println(id[i]);
				Long num = Long.valueOf(id[i]);
				modelService.doDeleteModel(num);
				caseSercvice.doDeleteCaseByModelId(num);
			}
			map.put("result", true);
			map.put("msg", "删除模块成功！");
		} catch (Exception e) {
			map.put("result", false);
			map.put("msg", "删除模块失败！");
		}
		return map;
	}

	// 获取测试项目页面
	@RequestMapping("/testProject")
	public String toTestProject(Model model) {

		// 获取开发人员list
		List devList = userService.doGetUserList("1");
		// 获取测试人员list
		List testList = userService.doGetUserList("0");
		model.addAttribute("devList", devList);
		model.addAttribute("testList", testList);
		return "/sys/testProject";
	}

	// 获取测试项目table
	@RequestMapping("/testProjectList")
	@ResponseBody
	public HashMap toGetTestProjectList(@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "limit", required = false) int limit,
			@RequestParam(value = "projectName", required = false) String projectName,
			@RequestParam(value = "testId", required = false) Long testId,
			@RequestParam(value = "devId", required = false) Long devId) {

		// 分页设置
		PageHelper.startPage(page, limit);
		Project project = new Project();
		project.setDevId(devId);
		project.setProjectName(projectName);
		project.setTestId(testId);
		List projectList = projectService.doSearchProjectList(project);
		// 获取分页数据
		PageInfo<HashMap> pageInfo = new PageInfo<HashMap>(projectList);
		HashMap map = new HashMap<>();

		map.put("code", 0);
		map.put("msg", "success");
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getList());
		return map;

	}

	// 获取添加项目页面
	@RequestMapping("/toAddProject")
	public String toAddProjectPage(Model model) {
		// 获取开发人员list
		List devList = userService.doGetUserList("1");
		// 获取测试人员list
		List testList = userService.doGetUserList("0");
		model.addAttribute("devList", devList);
		model.addAttribute("testList", testList);

		return "/sys/addProject";
	}

	// 增加项目
	@RequestMapping("/addProject")
	@ResponseBody
	public HashMap toAddProject(@RequestBody(required = false) Project project) {

		HashMap map = new HashMap<>();
		if (project.getStatus() != null) {
			project.setStatus("True");
		} else {
			project.setStatus("False");
		}

		Project project2 = projectService.doGetProjectByName(project.getProjectName());
		if (project2 != null) {
			map.put("result", false);
			map.put("msg", "该项目名已存在，请修改项目名！");
			return map;
		}
		if (projectService.doAddNewProject(project) == 1) {
			map.put("result", true);
			map.put("msg", "创建项目成功！");
		} else {
			map.put("result", false);
			map.put("msg", "创建项目失败！");
		}
		return map;
	}

	// 获取添加项目页面
	@RequestMapping("/toUpdateProject")
	public String toAddProjectPage(@RequestParam(value = "id", required = false) Long id, Model model) {
		// 获取开发人员list
		List devList = userService.doGetUserList("1");
		// 获取测试人员list
		List testList = userService.doGetUserList("0");
		model.addAttribute("devList", devList);
		model.addAttribute("testList", testList);
		// 根据Id获取Project
		Project project = projectService.doGetProjectById(id);
		model.addAttribute("project", project);
		return "/sys/updateProject";
	}

	// 修改项目
	@RequestMapping("/updateProject")
	@ResponseBody
	public HashMap toUpdateProject(@RequestBody(required = false) Project project) {

		HashMap map = new HashMap<>();
		if (project.getStatus() != null) {
			project.setStatus("True");
		} else {
			project.setStatus("False");
		}

		Project oldProject = projectService.doGetProjectById(project.getId());
		Project project2 = projectService.doGetProjectByName(project.getProjectName());
		if (project2 != null && (oldProject.getProjectName().equals(project2.getProjectName()) == false)) {
			map.put("result", false);
			map.put("msg", "该项目名已存在，请修改项目名！");
			return map;
		}
		if (projectService.doUpdateProject(project) == 1) {
			map.put("result", true);
			map.put("msg", "修改项目成功！");
		} else {
			map.put("result", false);
			map.put("msg", "修改项目失败！");
		}
		return map;
	}

	// 删除项目
	@DeleteMapping("/deleteProject")
	@ResponseBody
	public HashMap deleteProject(@RequestParam(value = "ids", required = false) String ids) {

		String[] id = ids.split(",");
		HashMap map = new HashMap<>();
		try {
			for (int i = 0; i < id.length; i++) {
				System.out.println(id[i]);
				Long num = Long.valueOf(id[i]);

				caseSercvice.doDeleteCaseByProId(num);
				modelService.doDeleteModelByProId(num);
				projectService.doDeleteProById(num);

			}
			map.put("result", true);
			map.put("msg", "删除模块成功！");
		} catch (Exception e) {
			map.put("result", false);
			map.put("msg", "删除模块失败！");
		}
		return map;
	}

	// 获取测试步骤页面
	@RequestMapping("/testStep")
	public String toTestStep() {
		return "/sys/testStep";
	}

	// 获取测试步骤table
	@RequestMapping("/testStepList")
	@ResponseBody
	public HashMap toTestStepList(@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "limit", required = false) int limit,
			@RequestParam(value = "stepName", required = false) String stepName,
			@RequestParam(value = "reqMode", required = false) String reqMode) {
		// 分页设置
		PageHelper.startPage(page, limit);
		Step step = new Step();
		step.setStepName(stepName);
		step.setReqMode(reqMode);

		List stepList = stepService.doSearchStepList(step);
		// 获取分页数据
		PageInfo<HashMap> pageInfo = new PageInfo<HashMap>(stepList);
		HashMap map = new HashMap<>();

		map.put("code", 0);
		map.put("msg", "success");
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getList());
		return map;
	}

	// 删除步骤
	@DeleteMapping("/deleteStep")
	@ResponseBody
	@Transactional
	public HashMap deleteStep(@RequestParam(value = "ids", required = false) String ids) {

		String[] id = ids.split(",");
		HashMap map = new HashMap<>();
		try {
			for (int i = 0; i < id.length; i++) {
				System.out.println(id[i]);
				Long num = Long.valueOf(id[i]);
				stepService.doDeleteStepById(num);
			}
			map.put("result", true);
			map.put("msg", "删除步骤成功！");
		} catch (Exception e) {
			map.put("result", false);
			map.put("msg", "删除步骤失败！");
		}
		return map;
	}

	// 获取增加步骤页面
	@RequestMapping("/toAddStep")
	public String toAddStepPage(Model model) {

		// 获取caselist
		List caseList = caseSercvice.doGetCaseList();
		model.addAttribute("caseList", caseList);
		return "/sys/addStep";
	}

	// 获取增加步骤页面
	@RequestMapping("/toUpdateStep")
	public String toUpdateStepPage(Model model) {

		// 获取caselist
		List caseList = caseSercvice.doGetCaseList();
		model.addAttribute("caseList", caseList);
		return "/sys/updateStep";
	}
}
