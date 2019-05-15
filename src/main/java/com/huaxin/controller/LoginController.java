package com.huaxin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* @author zhangyao
* @data May 14, 2019
*/

@Controller
public  class LoginController {
	
	@RequestMapping("/")
	public String toLoginPage() {
		
		return "/login";
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public HashMap toLogin() {
		HashMap map=new HashMap<>();
		map.put("msg", "success");
		
		return  map;
	}
	
	
	@RequestMapping("/index")
	public String toIndex() {
		return "/index";
	}

}

