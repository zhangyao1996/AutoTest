package com.huaxin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.huaxin.entity.Interface;
import com.huaxin.service.HttpClientService;
import com.huaxin.service.InterfaceService;

/**
* @author zhangyao
* @data Jun 5, 2019
*/
@Controller
@RequestMapping("/httpclient")
public class HttpClientController {

	@Autowired
	private HttpClientService hClientService;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private InterfaceService interfaceService;
	
	@RequestMapping("/useInterface")
	@ResponseBody
	public void useInterfaceGetReq(@RequestParam(value = "id", required = false) Long id)  {
		Interface iface=interfaceService.getById(id);
		//设置接口
		String url=iface.getInterfaceName();
		//String header=iface.getHeader();
		//String ast=iface.getAst();
		
		
		//设置param
		Map<String, Object>  map = new HashMap<>();
		String params=iface.getParam();
		if(params!=null && params.length()>0) {
			String[] first=params.split(",");
			for (int i = 0; i < first.length; i++) {
				String[] second=first[i].split("=");
				map.put(second[0], second[1]);
			}
		}
		
		
		

		
		// 设置header
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON_UTF8);
		
	
		
		//body
        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("id", 1);
		if(map.isEmpty()) {
			 ResponseEntity<String> response=restTemplate.getForEntity(url, String.class);

			 System.out.println(response);
		}else {
			 ResponseEntity<String> response=restTemplate.getForEntity(url, String.class, map);

			 System.out.println(response);
		}
       
       // ResponseEntity<String> response=restTemplate.getForEntity(url, String.class);
		 
		 
		
	}
	
	
	public void useInterfacePostReq(@RequestParam(value = "id", required = false) Long id)  {
		Interface iface=interfaceService.getById(id);
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class);

		responseEntity.getStatusCode();
		
		System.out.println(iface);
		
	}
	
	
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

