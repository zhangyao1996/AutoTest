package com.huaxin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.huaxin.util.LoginHandlerInterceptor;

/**
 * @author zhangyao
 * @data May 16, 2019
 */


//负责注册并生效我们自己定义的拦截器配置
@Configuration
public class LoginConfiguration implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 注册拦截器
		LoginHandlerInterceptor loginInterceptor = new LoginHandlerInterceptor();
		InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor);
		// 拦截路径
		loginRegistry.addPathPatterns("/**");
		// 排除路径
		loginRegistry.excludePathPatterns("/");
		loginRegistry.excludePathPatterns("/login");
		loginRegistry.excludePathPatterns("/toLogin");
		loginRegistry.excludePathPatterns("/index");
		// 排除资源请求
		loginRegistry.excludePathPatterns("/**");
//		loginRegistry.excludePathPatterns("/css/*.css");
//		loginRegistry.excludePathPatterns("/js/*.js");
//		loginRegistry.excludePathPatterns("/image/login.png");
	}

}
