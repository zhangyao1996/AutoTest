package com.huaxin.controller;

import java.util.List;

import org.apache.http.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.huaxin.service.HttpClientService;

/**
* @author zhangyao
* @data Jun 5, 2019
*/
@Controller
public class HttpClientController {

	@Autowired
	private HttpClientService hClientService;
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/testInterface")
	@ResponseBody
	public Object testInterface() throws Exception {
		//RestTemplate template=new RestTemplate();
		Object[] params= {new Object()};
		Object[] values={new Object()};
		List<NameValuePair> nameValuePairList=hClientService.getParams(params, values);
		String url="http://localhost:8080/interface/toAddModel";
		return hClientService.sendGet(url, nameValuePairList);
	}
	
	
	@RequestMapping("/testInterface2")
	@ResponseBody
	public String testInterface2() throws Exception {
		//建议以注入方式使用
		
		String url="http://localhost:8080/interface/toAddModel";
		String result = restTemplate.getForObject(url, String.class);
		return result;
	}
	
	
	
}

