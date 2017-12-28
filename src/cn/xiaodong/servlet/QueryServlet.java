package cn.xiaodong.servlet;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.xiaodong.domain.QueryRequest;
import cn.xiaodong.service.QueryCode;

public class QueryServlet extends HttpServlet {
	
	public String Query(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
		
		//1.获取表单信息
		QueryRequest qr = new QueryRequest();
		BeanUtils.populate(qr, request.getParameterMap());
		
		//2.执行查询操作
		
		
		//3.页面转跳到查询成功页面
		
		return null;
	}
}
