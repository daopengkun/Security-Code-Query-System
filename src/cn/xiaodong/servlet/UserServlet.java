package cn.xiaodong.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xiaodong.dao.UserDao;
import cn.xiaodong.domain.User;


public class UserServlet extends BaseServlet {

	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//测试信息
		System.out.println("UserServlet  login  转跳成功");
		
		//1.获取用户名和密码
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		//2.根据用户名和密码从书库中查询
		User u = UserDao.getByUsernameAndPassword(username, password);
		if ( u == null ){
			request.setAttribute("msg", "用户不存在，请检查后重新登录！");
			return "WEB-INF/msg.jsp";
		}
		
		return "WEB-INF/manage.html";
	}
}
