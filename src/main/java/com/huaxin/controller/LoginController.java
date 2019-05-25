package com.huaxin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huaxin.entity.User;
import com.huaxin.service.UserService;

/**
 * @author zhangyao
 * @data May 14, 2019
 */

@Controller
public class LoginController {
	@Autowired
	private UserService userService;

	@RequestMapping({"/toLogin","/"})
	public String toLoginPage() {

		return "/login";
	}
	
//	@RequestMapping("/")
//	public String toLoginPage1(HttpServletRequest request) {
//		Object user=request.getSession().getAttribute("cnName");
//		if(user==null) {
//			 return "redirect:/toLogin";
//		}else {
//			return "redirect:/index";
//		}
//	}


	@RequestMapping("/login")
	@ResponseBody
	public HashMap toLogin(@RequestBody User user,HttpSession session) {
		String userName=user.getUserName();
		
		String password=user.getPassword();
		User user1=userService.doGetUserByUserName(userName);
		HashMap map = new HashMap<>();
		if(user1==null) {
			map.put("msg", "账号不存在!");	
		}else if(user1.getPassword().equals(password)==false){
			map.put("msg","密码错误！");
		}else {
			session.setAttribute("cnName", user1.getCnName());
			map.put("msg", "success");
		}
	
		return map;
	}

	@RequestMapping("/index")
	public String toIndex(HttpServletRequest request) {
		
		Object user=request.getSession().getAttribute("cnName");
		if(user==null) {
			 return "redirect:/toLogin";
		}else {
			return "/index";
		}
	}
	
	
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 移除session
        session.removeAttribute("cnName");
        return "redirect:/toLogin";
    }

}
