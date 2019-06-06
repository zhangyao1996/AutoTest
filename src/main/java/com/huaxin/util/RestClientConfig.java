package com.huaxin.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
* @author zhangyao
* @data Jun 5, 2019
*/
@Configuration
public class RestClientConfig {

	@Bean  
    public RestTemplate restTemplate() {  
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;  
    }  

}

