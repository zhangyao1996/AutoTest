package com.huaxin.util;
/**
* @author zhangyao
* @data May 15, 2019
*/

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class CommonUtils {
	
	//得到分页结果对象
	public static PageInfo pageInfo(int page,int limit,List list){
		PageHelper.startPage(page, limit);
		PageInfo pageInfo=new PageInfo<>(list);
		return pageInfo;
	}

}

