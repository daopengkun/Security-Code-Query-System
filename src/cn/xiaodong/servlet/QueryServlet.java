package cn.xiaodong.servlet;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.xiaodong.domain.QueryRequest;
import cn.xiaodong.service.QueryService;

public class QueryServlet extends BaseServlet {
	
	/**
	 * 根据提交的表单查询结果
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SQLException 
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public String Query(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, SQLException, AddressException, MessagingException{
		
		//1.获取表单信息
		QueryRequest qr = new QueryRequest();
		BeanUtils.populate(qr, request.getParameterMap());
		
		//2.执行查询操作
		QueryService qs = new QueryService();
		qs.Query(qr);
		
		//3.页面转跳到查询成功页面
		return "WEB-INF/reminder.html";
		
	}
	
	
}
